package com.daae.culturefork.review.dao;

import java.util.List;

import com.daae.culturefork.review.domain.RequestReviewEdit;
import com.daae.culturefork.review.domain.ReviewInfo;

public interface ReviewDaoInterface {
	// 회원가입
	public int insertReview(ReviewInfo reviewInfo);
	// 회원의 전체 리스트 출력
	public List<ReviewInfo> selectAllList();
	// 회원 정보 삭제
	public int reviewDelete(int r_num);
	// 회원 정보 수정을 위한 정보 획득
	public ReviewInfo selectReviewByR_num(int r_num);
	// 회원 정보 수정
	public int reviewUpdate(RequestReviewEdit requestReviewEdit);
	// 조회수 업데이트
	public int updateHits(int r_num);
}
