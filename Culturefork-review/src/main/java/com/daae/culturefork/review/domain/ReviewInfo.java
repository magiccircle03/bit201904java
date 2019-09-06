package com.daae.culturefork.review.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewInfo {

	private int r_num;
	private String r_title;
	private String r_content;
	private Date r_date;
	private String u_id;
	private int r_star;
	private int r_hits;
	private String r_seq;
	private String r_ptitle;
	private String r_thumb;
	private String r_gpsX;
	private String r_gpsY;

	// default 생성자
	public ReviewInfo() {
		this.r_date = new Date();
	}

	public ReviewInfo(String r_title, String r_content, String u_id, int r_star, String r_seq, String r_ptitle, String r_thumb, String r_gpsX, String r_gpsY) {
		super();
		this.r_title = r_title;
		this.r_content = r_content;
		this.r_date = new Date();
		this.u_id = u_id;
		this.r_star = r_star;
		this.r_seq = r_seq;
		this.r_ptitle = r_ptitle;
		this.r_thumb = r_thumb;
		this.r_gpsX = r_gpsX;
		this.r_gpsY = r_gpsY;
	}

	public ReviewInfo(int r_num, String r_title, String r_content, Date r_date, String u_id, int r_star, int r_hits,
			String r_seq, String r_ptitle, String r_thumb, String r_gpsX, String r_gpsY) {
		super();
		this.r_num = r_num;
		this.r_title = r_title;
		this.r_content = r_content;
		this.r_date = r_date;
		this.u_id = u_id;
		this.r_star = r_star;
		this.r_hits = r_hits;
		this.r_seq = r_seq;
		this.r_ptitle = r_ptitle;
		this.r_thumb = r_thumb;
		this.r_gpsX = r_gpsX;
		this.r_gpsY = r_gpsY;
	}
	
	

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
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

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
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

	public int getR_hits() {
		return r_hits;
	}

	public void setR_hits(int r_hits) {
		this.r_hits = r_hits;
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
		return "ReviewInfo [r_num=" + r_num + ", r_title=" + r_title + ", r_content=" + r_content + ", r_date=" + r_date
				+ ", u_id=" + u_id + ", r_star=" + r_star + ", r_hits=" + r_hits + ", r_seq=" + r_seq + ", r_ptitle="
				+ r_ptitle + ", r_thumb=" + r_thumb + ", r_gpsX=" + r_gpsX + ", r_gpsY=" + r_gpsY + "]";
	}


}
