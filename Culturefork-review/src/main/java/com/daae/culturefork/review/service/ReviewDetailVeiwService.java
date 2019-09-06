package com.daae.culturefork.review.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daae.culturefork.review.dao.ReviewDaoInterface;
import com.daae.culturefork.review.domain.ReviewInfo;

@Service
public class ReviewDetailVeiwService {
	private ReviewDaoInterface dao;
	@Autowired
	private SqlSessionTemplate template;

	// 디테일 가져오기
	public ReviewInfo getDetailReviewData(int r_num) {
		// SqlSessionTemplate getMapper 를 이용해 dao 생성
		dao = template.getMapper(ReviewDaoInterface.class);
		ReviewInfo reviewInfo = dao.selectReviewByR_num(r_num);
		return reviewInfo;
	}
	
	//조회수
	public int addHits(int r_num) {
		dao = template.getMapper(ReviewDaoInterface.class);
		return dao.updateHits(r_num);
	}
	

}
