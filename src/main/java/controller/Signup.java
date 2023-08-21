package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Mydao;
import dto.Custmor;

@WebServlet("/signup")
@MultipartConfig
public class Signup extends HttpServlet {
	@Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//logic to receive value from fronted
		String fullname=req.getParameter("fullname");
		
		//password
		String password=req.getParameter("password");
		
		//mobile number
		long mobile=Long.parseLong(req.getParameter("mobile"));
		
		//Gmail id
		String gmail=req.getParameter("email");
		
		//gender
		String gender=req.getParameter("gender");
		
		//country
		String country=req.getParameter("country");
		
		//DOB
		LocalDate date=LocalDate.parse(req.getParameter("Dob"));
		
//calculating the age
		int age=Period.between(date, LocalDate.now()).getYears();
		
		
		
//logic for the storing the photo
		Part pic=req.getPart("picture");
		byte picture[]=null;
		picture=new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		Mydao dao =new Mydao();
		
//logic to verify email and mobile number is not repeated
		
	if(dao.fetchByEmail(gmail)==null && dao.fetchByMobile(mobile)==null)	
	{
// loading values inside object
		
		Custmor C=new Custmor ();
		C.setAge(age);
		C.setCountry(country);
		C.setDob(date);
		C.setEmail(gmail);
		C.setFullname(fullname);
		C.setGender(gender);
		C.setMobile(mobile);
		C.setPassword(password);
		C.setPicture(picture);
		
		//persisting(storing the values)
		
		dao.save(C);
		
			resp.getWriter().print("<h1 style='color:red'>Account Created Successfully</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		
	
	
	else {
		resp.getWriter().print("<h1 style='color:green'>email and mobile should not be unique</h1>");
		req.getRequestDispatcher("signup.html").include(req, resp);
	}
}}