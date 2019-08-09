package com.bitcamp.guest.domain;
//나중엔 메시지 객체 가져다놓고 쓸것임?
public class RequestGuestWrite {

	// 이름이 폼과 같아야하니까 오타없게 하려고 그 폼에서 복붙함
	private String guestName;
	private String password;
	private String message;
	
	//디폴트생성자 있어야하니 냅두고
	
	//게터세터
	
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	// 확인쉽게 투스트링도 
	
	@Override
	public String toString() {
		return "RequestGuestWrite [guestName=" + guestName + ", password=" + password + ", message=" + message + "]";
	}
	
	public Message toMessage() {
		// 아이디는 따로 입력하지 않으니 0으로 햇음?
		Message message = new Message(0,guestName, password, this.message);
		return message;
	}
	
	
	
	
}
