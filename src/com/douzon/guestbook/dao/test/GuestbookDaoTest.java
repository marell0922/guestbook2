package com.douzon.guestbook.dao.test;

import java.util.List;

import com.douzon.guestbook.dao.GuestbookDao;
import com.douzon.guestbook.vo.GuestbookVo;

public class GuestbookDaoTest {
	public static void main(String[] args) {
		
		//이름, 비밀번호, message
		//insertTest("judy", "pw", "dsfdfs");
		getListTest();
		selectTest(1);
		deleteTest(1);
		
	}

	private static void deleteTest(int no) {
		// TODO Auto-generated method stub
		boolean result=new GuestbookDao().delete(no);
		if(!result) System.out.println("deleteTest fail");
	}

	private static void selectTest(int no) {
		// TODO Auto-generated method stub
		GuestbookVo vo=new GuestbookDao().select(no);
		System.out.println(vo);
	}

	private static void insertTest(String name, String passwd, String message) {
		// TODO Auto-generated method stub
		GuestbookVo vo=new GuestbookVo();
		vo.setName(name);
		vo.setPasswd(passwd);
		vo.setMessage(message);
		
		boolean result=new GuestbookDao().insert(vo);
		if(!result) System.out.println("insertTest fail");
	}

	private static void getListTest() {
		// TODO Auto-generated method stub
		List<GuestbookVo> list=new GuestbookDao().getList();
		
		for(GuestbookVo guestbookVo : list) {
			System.out.println(guestbookVo);
		}
	}
	
	
}
