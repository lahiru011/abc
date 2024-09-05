<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ page import = "com.services.model.ServicesModel" %>
 <%@ page import = "com.user.service.UserInterface" %>
 <%@ page import = "com.user.service.UserService" %>
 <%@ page import = "java.util.ArrayList" %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.tailwindcss.com"></script>
<title>View Services</title>
</head>
<body>

<header class="lg:px-16 px-4 bg-white flex flex-wrap items-center py-4 shadow-md">
    <div class="flex-1 flex justify-between items-center">
        <a href="#" class="text-xl">Admin Pannel</a>
    </div>

    <label for="menu-toggle" class="pointer-cursor md:hidden block">
      <svg class="fill-current text-gray-900"
        xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
        <title>menu</title>
        <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z"></path>
      </svg>
    </label>
    <input class="hidden" type="checkbox" id="menu-toggle" />

    <div class="hidden md:flex md:items-center md:w-auto w-full" id="menu">
        <nav>
            <ul class="md:flex items-center justify-between text-base text-gray-700 pt-4 md:pt-0">
                <li><a class="md:p-4 py-3 px-0 block" href="<%= request.getContextPath() %>/addServices.jsp">Add Services</a></li>
                <li><a class="md:p-4 py-3 px-0 block" href="<%= request.getContextPath() %>/viewServices.jsp">View Services</a></li>
                <li><a class="md:p-4 py-3 px-0 block" href="<%= request.getContextPath() %>/viewUsers.jsp">View Users</a></li>
                <li><a class="md:p-4 py-3 px-0 block md:mb-0 mb-2" href="<%= request.getContextPath() %>/viewReserves.jsp">View Reserves</a></li>
            </ul>
        </nav>
    </div>
</header>

<h1 class="text-3xl font-bold p-5">View Services</h1>

<div class="flex flex-col">
    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
        <div class="py-2 inline-block min-w-full sm:px-6 lg:px-8">
            <div class="overflow-hidden">
                <table class="min-w-full">
                    <thead class="border-b">
                        <tr>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">id</th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">Name</th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">Price</th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">Description</th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">Image</th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">Action</th>
                        </tr>
                    </thead>
                    <tbody>
			            <%
			        	
			        		UserInterface Interface = new UserService(); 
			        		ArrayList<ServicesModel> ServicesList = Interface.getServices();
				
			        	%>
			        	
			        	
			        	<%
			        		for(ServicesModel services: ServicesList){
			        	%>
                        <tr class="border-b">
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= services.getId() %></td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= services.getName() %></td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= services.getPrice() %></td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= services.getDescription() %></td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= services.getImage() %></td>
                            <td>
                            	<div>
			          				<form action="<%= request.getContextPath()%>/DeleteService" method="post">
			          					<input type="hidden" name="id" value="<%= services.getId() %>"/>
			          					
			          					<button class="bg-red-500 p-1 rounded-lg text-white" type="submit">
			          						Delete
			          					</button>
			          				</form>
			          			</div>
                            </td>
                        </tr>
                        
                        <%
			        		}
			        	%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>