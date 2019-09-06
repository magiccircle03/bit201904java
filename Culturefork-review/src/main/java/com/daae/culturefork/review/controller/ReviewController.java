package com.daae.culturefork.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daae.culturefork.review.domain.RequestReviewEdit;
import com.daae.culturefork.review.domain.RequestReviewWrite;
import com.daae.culturefork.review.domain.ReviewInfo;
import com.daae.culturefork.review.service.ReviewDeleteService;
import com.daae.culturefork.review.service.ReviewDetailVeiwService;
import com.daae.culturefork.review.service.ReviewEditService;
import com.daae.culturefork.review.service.ReviewListService;
import com.daae.culturefork.review.service.ReviewWriteService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ReviewListService listService;
	@Autowired
	private ReviewWriteService writeService;
	@Autowired
	private ReviewDeleteService deleteService;
	@Autowired
	private ReviewEditService editService;
	@Autowired
	private ReviewDetailVeiwService detailService;
	
	// 게시글 목록
	@CrossOrigin
	@GetMapping
	public List<ReviewInfo> getAllList(){ 
		return listService.getAllList();
	} 
	
	// 게시글 상세
	@CrossOrigin
	@GetMapping("/{r_num}")
	public ReviewInfo getDetailReview(@PathVariable("r_num") int r_num){
		ReviewInfo reviewInfo = detailService.getDetailReviewData(r_num);
		detailService.addHits(r_num);
		return reviewInfo;
	}
	
	// 게시글 작성
	@CrossOrigin
	@PostMapping("/post")
	public RequestReviewWrite writeReview(@RequestBody RequestReviewWrite writeRequest) {
		System.out.println(writeRequest);
		int result = writeService.memberInsert(writeRequest);
		return writeRequest;
	}
	// 게시글 삭제
	@CrossOrigin
	@DeleteMapping("/post/{r_num}")
	public String deleteReview(@PathVariable("r_num") int r_num) {
		
		System.out.println(r_num);
		int result = deleteService.reviewDelete(r_num);
	
		return result>0?"success":"fail";
	} 
	
	// 게시글 수정 폼 띄우기
	@CrossOrigin
	@GetMapping("/post/{r_num}")
	public ReviewInfo getOneReview(@PathVariable("r_num") int r_num){
		ReviewInfo reviewInfo = editService.getEditFormData(r_num);
		return reviewInfo;
	}
	
	// 게시글 수정
	@CrossOrigin
	@PutMapping("/post/{r_num}")
	public String editReview(
			@PathVariable("r_num") int r_num, 
			@RequestBody RequestReviewEdit editRequest){
		int cnt = editService.edit(editRequest);
		return cnt>0 ? "success" : "fail";
	}
	
}