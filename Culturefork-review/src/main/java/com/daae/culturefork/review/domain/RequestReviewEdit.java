package com.daae.culturefork.review.domain;

public class RequestReviewEdit {
	
	private String r_num;
	private String r_title;
	private String r_content;
	private int r_star;
	
	public RequestReviewEdit() {
	}

	public RequestReviewEdit(String r_num, String r_title, String r_content, int r_star) {
		this.r_num = r_num;
		this.r_title = r_title;
		this.r_content = r_content;
		this.r_star = r_star;
	}

	public String getR_num() {
		return r_num;
	}

	public void setR_num(String r_num) {
		this.r_num = r_num;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_star() {
		return r_star;
	}

	public void setR_star(int r_star) {
		this.r_star = r_star;
	}

	@Override
	public String toString() {
		return "RequestReviewEdit [r_num=" + r_num + ", r_title=" + r_title + ", r_content=" + r_content + ", r_star="
				+ r_star + "]";
	}
	
}
