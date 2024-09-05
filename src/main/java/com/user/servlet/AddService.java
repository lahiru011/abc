package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.services.model.ServicesModel;
import com.user.model.UserModel;
import com.user.service.UserInterface;
import com.user.service.UserService;

/**
 * Servlet implementation class AddService
 */
public class AddService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddService() {
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
		
		ServicesModel service = new ServicesModel();
		
        service.setName(request.getParameter("name"));
        service.setPrice(request.getParameter("price"));
        service.setDescription(request.getParameter("description"));
        service.setImage(request.getParameter("image"));
        
        UserInterface userInterface = new UserService();
        userInterface.addService(service);
        
        request.setAttribute("Service", service);
        jakarta.servlet.RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/viewServices.jsp");
        requestDispatcher.forward(request, response);
	}

}
