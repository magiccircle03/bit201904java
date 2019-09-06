package com.daae.culturefork.review.service;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daae.culturefork.review.dao.ReviewDaoInterface;
import com.daae.culturefork.review.domain.RequestReviewEdit;
import com.daae.culturefork.review.domain.ReviewInfo;

@Service
public class ReviewEditService {

	private ReviewDaoInterface dao;

	@Autowired
	private SqlSessionTemplate template;

	public ReviewInfo getEditFormData(int r_num) {
		// SqlSessionTemplate getMapper 를 이용해 dao 생성
		dao = template.getMapper(ReviewDaoInterface.class);
		ReviewInfo reviewInfo = dao.selectReviewByR_num(r_num);
		return reviewInfo;
	}

	public int edit(RequestReviewEdit editRequest) {
	  dao = template.getMapper(ReviewDaoInterface.class); 
	  int rCnt = 0; 

	  rCnt = dao.reviewUpdate(editRequest);
	  
	  return rCnt; 
	}
	 
	 
}
