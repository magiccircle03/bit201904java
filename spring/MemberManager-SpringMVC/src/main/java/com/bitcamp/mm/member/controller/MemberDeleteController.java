package com.bitcamp.mm.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.mm.member.service.MemberDeleteService;

@Controller
public class MemberDeleteController {
	
	@Autowired
	private MemberDeleteService deleteService;
	
	@RequestMapping("/member/memberDelete")
	public String delete(
			//디폴트나 널을 받으면 안 됨. idx가 꼭 필요하니까?
			@RequestParam("memberId") int id
			) {
		
		deleteService.memberDelete(id);
		
		return "redirect:/member/memberList";
	}
}
