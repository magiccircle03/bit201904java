package com.bitcamp.mm.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.mm.member.domain.ListViewData;
import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.SearchParam;
import com.bitcamp.mm.member.service.MemberListService;

@Controller
public class MemberListController {
	
	@Autowired
	private MemberListService listService;
	
	@RequestMapping("member/memberList")
	public String memberList(
			Model model,
			@RequestParam(value = "p", defaultValue = "1") int pageNumber,
			@RequestParam(value = "stype", required=false) String stype,
			@RequestParam(value = "keyword", required=false) String keyword
			) {
		
		SearchParam searchParam = null;
		
		if(stype!=null && keyword != null && !stype.isEmpty() && !keyword.isEmpty()) {
			searchParam = new SearchParam();
			searchParam.setStype(stype);
			searchParam.setKeyword(keyword);
		}
		
		ListViewData listdata = listService.getListData(pageNumber,searchParam);
		
		System.out.println("전체 회원의 수 : "+listdata.getTotalCount());
		
		/*
		 * //프린트ln이 생각보다 출력시 메모리를 사용해서 성능에 영향을.. 그러니 이제 안 쓰면 주석! 그리고 콘솔 로그가 톰캣에 남는데 남겨두는건 꼭 필요한 것들만!
		 * for(MemberInfo m : listdata.getMemberList()) { System.out.println(m); //이러면
		 * 멤버인포에 투스트링 해놓은게 찍힐 것 }
		 */
		
		model.addAttribute("viewData",listdata);
		
		// 나중에 와이어프레임이나 페이퍼들 산출물로 요구할거란 말씀을 하고계신다.
		return "member/memberList";
	}
	
	
}
