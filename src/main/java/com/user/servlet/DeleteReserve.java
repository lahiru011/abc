package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.user.service.UserInterface;
import com.user.service.UserService;

/**
 * Servlet implementation class DeleteReserve
 */
public class DeleteReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReserve() {
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
		
		String  id = request.getParameter("id");
		
		
		UserInterface Interface = new UserService();
		Interface.deleteReserve(id);
		
		request.setAttribute("id", id);
		jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/viewReserves.jsp");
        requestDispatcher.forward(request, response);
	}

}
