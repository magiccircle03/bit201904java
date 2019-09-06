package com.daae.culturefork.review.service;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daae.culturefork.review.dao.ReviewDaoInterface;
import com.daae.culturefork.review.domain.ReviewInfo;

@Service
public class ReviewListService {
	
	private ReviewDaoInterface dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<ReviewInfo> getAllList() {
		
		dao = template.getMapper(ReviewDaoInterface.class);
		
		//복습차원에서 dao새로 만들자? MemberDaoInterface에 뭔가 추가해줌
		List<ReviewInfo> list = dao.selectAllList();
		
		return list;
	}
	
}
