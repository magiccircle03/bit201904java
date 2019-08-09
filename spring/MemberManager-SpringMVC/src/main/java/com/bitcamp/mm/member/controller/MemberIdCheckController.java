package com.bitcamp.mm.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.mm.member.service.MemberRegService;

@Controller
public class MemberIdCheckController {
	
	@Autowired
	private MemberRegService regService;
	
	// 1. 리스폰스바디를 사용하지 않는 방식은.. Y나 N 하나 반환하는데도 뷰 페이지를 만들었다!
	@RequestMapping("/member/idCheck1")
	public String idCheck1(@RequestParam("id") String id, Model model) {
		
		model.addAttribute("result",regService.idCheck(id));
		
		return "member/idCheck";
	}
	

	// 2. 간단한 문자나 문자열 처리해야한다면 이렇게 굳이 뷰페이지 만들지 않고 리스폰스바디를 사용해서 편하게 할 수 있다!
	@RequestMapping("/member/idCheck2")
	@ResponseBody //뷰페이지가 아니라 스트링값 그대로 응답처리하겠다
	public String idCheck2(@RequestParam("id") String id) {
		
		//model.addAttribute("result",regService.idCheck(id));
		
		return regService.idCheck2(id);
	}
}
