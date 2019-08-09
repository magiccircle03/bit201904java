 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var chk = ${rCnt}; //1 이렇게 남기고 사라지겠음. 얘 입장에선 숫자로 인식할테고 
	if(chk>0){
		alert('가입되었습니다. 감사합니다.');
		location.href='../main';
	} else {
		alert('회원가입이 되지 않았습니다. 관리자에 연락바랍니다.');
		location.href='../main'; 
	}
</script>