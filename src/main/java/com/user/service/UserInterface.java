package com.user.service;

import java.util.ArrayList;

import com.reserve.model.ReserveModel;
import com.services.model.ServicesModel;
import com.user.model.UserModel;

public interface UserInterface {
	
	// User Model Related
	
	public void signUp(UserModel user);
	
	public ArrayList<UserModel> getUsers();
	
	public String login(String email, String password);
	
	public ArrayList<UserModel> gerUserbyId (String id, UserModel user);
	
	public void deleteUser(String id);
	
	//Services Model Related
	
	public void addService(ServicesModel service);
	
	public ArrayList<ServicesModel> getServices();
	
	public ArrayList<ServicesModel> getServiceById(String id, ServicesModel services);
	
	public void updateService(String id, ServicesModel services);
	
	public void deleteService(String id);
	
	//Reserve Model Related
	
	public void addReserve(ReserveModel reserve);
	
	public ArrayList<ReserveModel> getReserves();
	
	public void deleteReserve(String id);

}
