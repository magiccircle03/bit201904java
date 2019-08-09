package com.bitcamp.mm.member.domain;

import org.springframework.web.multipart.MultipartFile;

public class RequestMemberRegist {
	// 폼과 이름 맞춤
	private String uId;
	private String uPW;
	private String uName;
	private MultipartFile uPhoto; // 멀티파트 파일 객체를 얻어오면 파일 이름 사이즈 저장할수있는 메서드들 이용해서 처리할 수 있었음
	// 예전 프로젝트의 멤버인포를 보면 idx랑 regdate가 있음. 파일도 string으로 이름을 받아왔다. 복붙해서 가져옴 로그인 인포도
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPW() {
		return uPW;
	}
	public void setuPW(String uPW) {
		this.uPW = uPW;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public MultipartFile getuPhoto() {
		return uPhoto;
	}
	public void setuPhoto(MultipartFile uPhoto) {
		this.uPhoto = uPhoto;
	}
	@Override
	public String toString() {
		return "RequestMemberRegist [uId=" + uId + ", uPW=" + uPW + ", uName=" + uName + ", uPhoto=" + uPhoto.getOriginalFilename() + "]";
	}
	
	public MemberInfo toMemberInfo() {
		MemberInfo info = new MemberInfo();
		info.setuId(uId);
		info.setuName(uName);
		info.setuPW(uPW);
		return info;
	}
	
}
