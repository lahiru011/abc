package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.user.model.UserModel;
import com.user.service.UserInterface;
import com.user.service.UserService;

/**
 * Servlet implementation class UserSignUp
 */
public class UserSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		UserModel user = new UserModel();
		
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setPhone(request.getParameter("phone"));
        user.setRole(request.getParameter("role"));
        
        UserInterface userInterface = new UserService();
        userInterface.signUp(user);
        
        request.setAttribute("User", user);
        jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
	}

}
