package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.Custmor;
//mapping url
@WebServlet("/login")
public class Login extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// Receive values from Front-end
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	//verify if name exist
	Mydao dao=new Mydao();
	Custmor C=dao.fetchByEmail(email);
	if(email.equals("admin@jsp.com")&&password.equals("admin")) {
		resp.getWriter().print("<h1 style='color:orange'>login success</h1>");
		//this is logic to send to next page
		req.getRequestDispatcher("adminhome.html").include(req, resp);
		
	}else {
		Custmor c = dao.fetchByEmail(email);
	
	if(C==null) {
		resp.getWriter().print("<h1 style='color:pink'>INVALID name</h1>");
		req.getRequestDispatcher("login.html").include(req, resp);
	}
	else
	{
		if(password.equals(C.getPassword()))
		{
			resp.getWriter().print("<h1 style='color:orange'>LOGIN SUCCESFULLY</h1>");
			req.getRequestDispatcher("Home1.html").include(req, resp);
		}
		else {
			resp.getWriter().print("<h1 style='color:yellow'>INVALID PASSWORD</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
}

}