package com.bitcamp.mm.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.mm.member.service.MemberLoginService;

@Controller
@RequestMapping("/member/login")
public class LoginController {
	
	@Autowired
	private MemberLoginService loginService;

	@RequestMapping(method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request) {
		// 물론 나중엔 인터셉터 수준에서 리퀘스트를 가로채서 세션정보 확인하고... 해볼 것
		String view = "member/loginForm";
		HttpSession session = request.getSession(false);// 현재 세션을 가지고있으면 갖고있고 없으면 null 반환하라고 펄스함
		if (session != null && session.getAttribute("loginInfo") != null) {
			view = "redirect:/main"; // 컨텍스트 경로 기준으로.. 센드 리다이렉트?
		}

		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(
			@RequestParam("uId") String id,
			@RequestParam("uPW") String pw,
			HttpServletRequest request
			
			) {
		
		//String view = "redirect:/main";
		String view = "member/loginfail";
		
		boolean loginChk = loginService.login(id, pw, request);
		
		
		if (loginChk) {
			view = "redirect:/main"; // 이 리다이렉트는 코어태그 쓰듯이 앞에 컨텍스트경로? 이런거 생략하고
		}
		
		return view;
	
	}

}
