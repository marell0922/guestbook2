package com.douzon.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.guestbook.dao.GuestbookDao;
import com.douzon.guestbook.vo.GuestbookVo;
import com.douzon.web.WebUtils;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//요청 분리
		String action=request.getParameter("a");
		if("deleteform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/deleteform.jsp");
		}else if("delete".equals(action)) {
			int no=Integer.parseInt(request.getParameter("no"));
			new GuestbookDao().delete(no);
			
			WebUtils.redirect(request, response, request.getContextPath()+"/gb");
		}
			else if("add".equals(action)) {
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String content=request.getParameter("message");
			GuestbookVo vo=new GuestbookVo();
			vo.setName(name);
			vo.setPasswd(password);
			vo.setMessage(content);
			new GuestbookDao().insert(vo);
			
			WebUtils.redirect(request, response, request.getContextPath()+"/gb");
		}else {
		/*default action : 디볼트 요청*/
		GuestbookDao dao=new GuestbookDao();
		List<GuestbookVo>list=dao.getList();
		
		//데이터를 request 범위에 저장.
		request.setAttribute("list", list);
		//forwarding
		WebUtils.forward(request, response, "/WEB-INF/views/index.jsp");
		
		//getServletContext().
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
