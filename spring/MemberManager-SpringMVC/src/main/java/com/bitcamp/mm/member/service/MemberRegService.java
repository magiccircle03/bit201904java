package com.bitcamp.mm.member.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.member.dao.MemberJdbcTemplateDao;
import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.RequestMemberRegist;

@Service("registService") // 이름안쓰면 memberRegService
public class MemberRegService implements MemberService {
	
	@Autowired 
	private MemberJdbcTemplateDao dao;
	
	public int memberInsert(
			HttpServletRequest request, // 절대경로 겟~컨텍스트 위해서 받아옴
			RequestMemberRegist regist	// 사용자가 입력한 거 
			) {
		
		// 서버 경로
		String path = "/uploadfile/userphoto"; // 리소스 매핑 필요
		// 절대 경로
		String dir = request.getSession().getServletContext().getRealPath(path);
		
		// 물리적으로 먼저 저장하고 그걸 디비에 저장하는거져? 안저장하고 그걸 디비에 넣다 오류나면 말이 안되는거 
		MemberInfo memberInfo = regist.toMemberInfo();
		
		//새로운 파일 이름 생성
		//String newFileName = memberInfo.getuId()+System.nanoTime(); //이래도 되고
		String newFileName = memberInfo.getuId()+"_"+regist.getuPhoto().getOriginalFilename();//이렇게 만들어봄
		
		int resertCnt=0;
		
		try {
		
			// 파일을 서버의 지정경로에 저장 (있으면 덮어쓰기 해준다)
			regist.getuPhoto().transferTo(new File(dir,newFileName)); // 서버에는 썼으나 디비에 넣기 실패한 경우 지워주는 처리가 필요함. 안그럼 쓰레기 데이터가 계속 서버에.. 보통 매치해서 스케쥴러? 정기적으로 스케줄러 하는 경우도 있다. 어노테이션으로 간편하게? 스케쥴러 이용하면 특정시간 쿠폰뿌리기 등도 가능
			// (파일 실패하면 디비 인설트도 하면 안 되니까 디비처리도 이쪽에 해줘야)
			// 데이터베이스 저장을 하기 위한 파일이름 set
			memberInfo.setuPhoto(newFileName);
			resertCnt = dao.insertMember(memberInfo);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("오류 확인!");
			//new File(dir,newFileName).delete();
		}
		return resertCnt;
	}
	
	
	public char idCheck(String id) {
		char chk = dao.selectMemberById(id)==null?'Y':'N';
		return chk;
	}
	
	public String idCheck2(String id) {
		String chk = dao.selectMemberById(id)==null?"Y":"N";
		return chk;
	}
	
	
//	@Autowired //주입받기 위해서. 디에이오엔 리포지토리 되어있으니까. 그 같은 타입의 클래스 객체를 찾는 오토와이어드
//	private MemberDao dao;
//	
//	public int memberInsert(
//			HttpServletRequest request, // 절대경로 겟~컨텍스트 위해서 받아옴
//			RequestMemberRegist regist	// 사용자가 입력한 거 
//			) {
//		
//		// 서버 경로
//		String path = "/uploadfile/userphoto"; // 리소스 매핑 필요
//		// 절대 경로
//		String dir = request.getSession().getServletContext().getRealPath(path);
//		
//		// 물리적으로 먼저 저장하고 그걸 디비에 저장하는거져? 안저장하고 그걸 디비에 넣다 오류나면 말이 안되는거 
//		MemberInfo memberInfo = regist.toMemberInfo();
//		
//		//새로운 파일 이름 생성
//		//String newFileName = memberInfo.getuId()+System.nanoTime(); //이래도 되고
//		String newFileName = memberInfo.getuId()+"_"+regist.getuPhoto().getOriginalFilename();//이렇게 만들어봄
//		
//		int resertCnt=0;
//		Connection conn=null;
//		
//		try {
//			conn = ConnectionProvider.getConnection();
//			// 파일을 서버의 지정경로에 저장 (있으면 덮어쓰기 해준다)
//			regist.getuPhoto().transferTo(new File(dir,newFileName)); // 서버에는 썼으나 디비에 넣기 실패한 경우 지워주는 처리가 필요함. 안그럼 쓰레기 데이터가 계속 서버에.. 보통 매치해서 스케쥴러? 정기적으로 스케줄러 하는 경우도 있다. 어노테이션으로 간편하게? 스케쥴러 이용하면 특정시간 쿠폰뿌리기 등도 가능
//			// (파일 실패하면 디비 인설트도 하면 안 되니까 디비처리도 이쪽에 해줘야)
//			// 데이터베이스 저장을 하기 위한 파일이름 set
//			memberInfo.setuPhoto(newFileName);
//			resertCnt = dao.insertMember(conn, memberInfo);
//			
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("오류 확인!");
//			new File(dir,newFileName).delete();
//		}
//		return resertCnt;
//	}
	
	
	
}
