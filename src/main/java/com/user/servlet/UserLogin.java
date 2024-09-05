package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.user.service.UserService;
import com.user.service.UserInterface;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
		String email = request.getParameter("email");
		
		System.out.println("This is from servclet email: " + email);
		
	    String password = request.getParameter("password");

	    UserInterface userInterface = new UserService();
	    String role = userInterface.login(email, password);
	    
	    System.out.println("Role: " + role);

	    if (role != null) {
	        if (role.equals("admin")) {
	            jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/adminHome.jsp");
	            requestDispatcher.forward(request, response);
	        } else {
	        	System.out.println("Role is user");
	            jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/userHome.jsp");
	            requestDispatcher.forward(request, response);
	        }
	    } else {
	        request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
	        request.getRequestDispatcher("index.jsp").forward(request, response);
	    }
	}

}
