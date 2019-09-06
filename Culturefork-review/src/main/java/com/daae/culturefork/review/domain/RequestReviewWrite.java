package com.daae.culturefork.review.domain;

import java.util.Date;

public class RequestReviewWrite {
	
	private String r_title;
	private String r_content;
	private String u_id;
	private int r_star;
	private String r_seq;
	private String r_ptitle;
	private String r_thumb;
	private String r_gpsX;
	private String r_gpsY;

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
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getR_star() {
		return r_star;
	}
	public void setR_star(int r_star) {
		this.r_star = r_star;
	}
	
	public String getR_seq() {
		return r_seq;
	}
	public void setR_seq(String r_seq) {
		this.r_seq = r_seq;
	}
	public String getR_ptitle() {
		return r_ptitle;
	}
	public void setR_ptitle(String r_ptitle) {
		this.r_ptitle = r_ptitle;
	}
	public String getR_thumb() {
		return r_thumb;
	}
	public void setR_thumb(String r_thumb) {
		this.r_thumb = r_thumb;
	}
	

	public String getR_gpsX() {
		return r_gpsX;
	}
	public void setR_gpsX(String r_gpsX) {
		this.r_gpsX = r_gpsX;
	}
	public String getR_gpsY() {
		return r_gpsY;
	}
	public void setR_gpsY(String r_gpsY) {
		this.r_gpsY = r_gpsY;
	}

	@Override
	public String toString() {
		return "RequestReviewWrite [r_title=" + r_title + ", r_content=" + r_content + ", u_id=" + u_id + ", r_star="
				+ r_star + ", r_seq=" + r_seq + ", r_ptitle=" + r_ptitle + ", r_thumb=" + r_thumb + ", r_gpsX=" + r_gpsX
				+ ", r_gpsY=" + r_gpsY + "]";
	}
	
	public ReviewInfo toReviewInfo() {
		
		ReviewInfo info = new ReviewInfo(r_title, r_content, u_id, r_star, r_seq, r_ptitle, r_thumb, r_gpsX, r_gpsY);
		
		return info;
	}
	
}
