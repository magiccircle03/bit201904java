package com.bitcamp.guest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitcamp.guest.domain.MessageListView;
import com.bitcamp.guest.service.GetMessageListService;

@Controller
public class GuestListControlloer {
	
	@Autowired
	private GetMessageListService listService;
	
	@RequestMapping("/guest/list")
	public String getList(
			@RequestParam(value="page",defaultValue = "1") int pageNumber,
			Model model) {
		
		MessageListView listView = listService.getMessageListView(pageNumber);
		model.addAttribute("viewData",listView);
		
		return "guest/list";
	}
	
	//(어떤 클라이언트든) 이걸로 경로 쳐서 들어가면 그 json형식의 데이터를 볼 수 있다
	//이게 신기했는데 생각해보니 String으로 Y를 받거나 N을 받기도 했었다.
	@RequestMapping("/guest/listJson")
	@ResponseBody
	public MessageListView getListJson(
			@RequestParam(
					value = "page", 
					defaultValue = "1") int pageNumber
			) {
		
		MessageListView listView = 
				listService.getMessageListView(pageNumber);

		return listView;
	}
}
