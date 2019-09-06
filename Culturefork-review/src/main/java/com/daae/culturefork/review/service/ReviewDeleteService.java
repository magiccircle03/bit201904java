package com.daae.culturefork.review.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daae.culturefork.review.dao.ReviewDaoInterface;

@Service
public class ReviewDeleteService {

	private ReviewDaoInterface dao;

	@Autowired
	private SqlSessionTemplate template;

	public int reviewDelete(int r_num) {

		// SqlSessionTemplate getMapper 를 이용해 dao 생성
		dao = template.getMapper(ReviewDaoInterface.class);

		return dao.reviewDelete(r_num);
	}
}
