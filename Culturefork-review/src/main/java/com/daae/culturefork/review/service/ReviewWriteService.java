package com.daae.culturefork.review.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daae.culturefork.review.dao.ReviewDaoInterface;
import com.daae.culturefork.review.domain.RequestReviewWrite;
import com.daae.culturefork.review.domain.ReviewInfo;

@Service
public class ReviewWriteService {

	private ReviewDaoInterface dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int memberInsert( RequestReviewWrite writeRequest	// 사용자가 입력한 거 
			) {
		
		// SqlSessionTemplate getMapper 를 이용해 dao 생성
		dao = template.getMapper(ReviewDaoInterface.class);
			
		// 물리적으로 먼저 저장하고 그걸 디비에 저장하는거져? 안저장하고 그걸 디비에 넣다 오류나면 말이 안되는거 
		ReviewInfo reviewInfo = writeRequest.toReviewInfo();
		
		int resultCnt=0;
		
		resultCnt = dao.insertReview(reviewInfo);
	
		System.out.println(resultCnt);
		return resultCnt;
	}
}
