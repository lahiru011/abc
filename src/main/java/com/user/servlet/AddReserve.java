package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.reserve.model.ReserveModel;
import com.services.model.ServicesModel;
import com.user.service.UserInterface;
import com.user.service.UserService;

/**
 * Servlet implementation class AddReserve
 */
public class AddReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReserve() {
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
		
		ReserveModel reserve = new ReserveModel();
		
        reserve.setName(request.getParameter("name"));
        reserve.setPhone(request.getParameter("phone"));
        reserve.setEmail(request.getParameter("email"));
        reserve.setDate(request.getParameter("date").toString());
        
        UserInterface userInterface = new UserService();
        userInterface.addReserve(reserve);
        
        request.setAttribute("Reserve", reserve);
        jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/reserve.jsp");
        requestDispatcher.forward(request, response);
	}

}
