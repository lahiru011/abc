package com.user.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.catalina.User;
import org.xml.sax.SAXException;

import com.abc.common.CommonConstants;
import com.abc.common.DBConnection;
import com.abc.common.QueryUtil;
import com.reserve.model.ReserveModel;
import com.services.model.ServicesModel;
import com.user.model.UserModel;
import com.abc.common.CommonUtil;

public class UserService implements UserInterface {
	
	private static Connection con;
    private static Statement st;
    private static PreparedStatement pSt;

    static {

        try {
			createUserTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
     			createServiceTable();
     		} catch (ClassNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (ParserConfigurationException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (SAXException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        
        try {
 			createReserveTable();
 		} catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (ParserConfigurationException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SAXException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

    }

    public static void createUserTable() throws SQLException, IOException, ParserConfigurationException, ClassNotFoundException, SAXException {
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            st.execute(QueryUtil.QueryById(CommonConstants.User_Table));
            System.out.println("USer Table created successfully.");
        } catch (SQLException | IOException | ParserConfigurationException | ClassNotFoundException | SAXException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL error during cleanup: " + e.getMessage());
            }
        }
    }
    
    public static void createServiceTable() throws SQLException, IOException, ParserConfigurationException, ClassNotFoundException, SAXException {
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            st.execute(QueryUtil.QueryById(CommonConstants.Services_Table));
            System.out.println("USer Table created successfully.");
        } catch (SQLException | IOException | ParserConfigurationException | ClassNotFoundException | SAXException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL error during cleanup: " + e.getMessage());
            }
        }
    }
    
    public static void createReserveTable() throws SQLException, IOException, ParserConfigurationException, ClassNotFoundException, SAXException {
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            st.execute(QueryUtil.QueryById(CommonConstants.Create_Reserve));
            System.out.println("USer Table created successfully.");
        } catch (SQLException | IOException | ParserConfigurationException | ClassNotFoundException | SAXException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                System.out.println("SQL error during cleanup: " + e.getMessage());
            }
        }
    }


	@Override
	public void signUp(UserModel user) {
		// TODO Auto-generated method stub
		
		 try {

	            con = DBConnection.getConnection();
	            pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Create_User));

	            con.setAutoCommit(false);

	            pSt.setString(1, user.getEmail());
	            pSt.setString(2, user.getPassword());
	            pSt.setString(3, user.getPhone());
	            pSt.setString(4, user.getRole());
	         

	            pSt.executeLargeUpdate();
	            con.commit();

	        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

	            System.out.println("con error" + e.getMessage());
	        } finally {
	            try {
	                if (con != null) {

	                    con.close();
	                }
	                if (pSt != null) {
	                	pSt.close();
	                }
	            } catch (SQLException e){
	                System.out.println("SQL error" + e.getMessage());
	            }
	        }
		
		
	}

	@Override
	public ArrayList<UserModel> getUsers() {
		
		ArrayList<UserModel> UserList = new ArrayList<UserModel>();
		
		try {
			
			  con = DBConnection.getConnection();
	          pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.All_Users));

	          ResultSet rs = pSt.executeQuery();
	          
	          while(rs.next() ) {
	        	  
	        	  UserModel userModel = new UserModel();
	        	  
	        	  userModel.setId(rs.getString(1));
	        	  userModel.setEmail(rs.getString(2));
	        	  userModel.setPassword(rs.getString(3));
	        	  userModel.setPhone(rs.getString(4));
	        	  userModel.setRole(rs.getString(5));
	        	  
	        	  UserList.add(userModel);
	        	 
	          }
			
		}catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

            System.out.println("Connection error" + e.getMessage());
        } finally {
            try {
                if (con != null) {

                    con.close();
                }
                if (pSt != null) {
                	pSt.close();
                }
            } catch (SQLException e){
                System.out.println("SQL error" + e.getMessage());
            }
        }
		
		return UserList;
	}

	@Override
	public String login(String email, String password) {
		String role = null;
	    Connection con = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        con = DBConnection.getConnection();
	        preparedStatement = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Login_User));

	        preparedStatement.setString(1, email);

	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            String storedPassword = resultSet.getString("password");
	            
	            System.out.println(storedPassword);
	            System.out.println(password);

	            
	            if (storedPassword.equals(password)) {
	            	System.out.println("Passwords Match");
	                role = resultSet.getString("role"); 
	            }
	        }

	    } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
	        System.out.println("con error: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL error: " + e.getMessage());
	        }
	    }

	    return role;
		
		
	}

	@Override
	public ArrayList<UserModel> gerUserbyId(String id, UserModel user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
		
		if (id != null && !id.isEmpty()) {
			
			try {
				
				con = DBConnection.getConnection();
				pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Delete_User));
				pSt.setString(1, id);
				
				pSt.execute();
				
	        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				
			} finally {
	            try {
	                if (con != null) {

	                    con.close();
	                }
	                if (pSt != null) {
	                	pSt.close();
	                }
	            } catch (SQLException e){
	                System.out.println("SQL error" + e.getMessage());
	            }
	        }
		}
		
	}


	@Override
	public void addService(ServicesModel service) {
		// TODO Auto-generated method stub
		
		 try {

	            con = DBConnection.getConnection();
	            pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Create_Service));

	            con.setAutoCommit(false);

	            pSt.setString(1, service.getName());
	            pSt.setString(2, service.getPrice());
	            pSt.setString(3, service.getDescription());
	            pSt.setString(4, service.getImage());
	         

	            pSt.executeLargeUpdate();
	            con.commit();

	        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

	            System.out.println("con error" + e.getMessage());
	        } finally {
	            try {
	                if (con != null) {

	                    con.close();
	                }
	                if (pSt != null) {
	                	pSt.close();
	                }
	            } catch (SQLException e){
	                System.out.println("SQL error" + e.getMessage());
	            }
	        }
		
		
	}


	@Override
	public ArrayList<ServicesModel> getServices() {
		// TODO Auto-generated method stub
		
		ArrayList<ServicesModel> ServicesList = new ArrayList<ServicesModel>();
		
		try {
			
			  con = DBConnection.getConnection();
	          pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.All_Services));

	          ResultSet rs = pSt.executeQuery();
	          
	          while(rs.next() ) {
	        	  
	        	  ServicesModel userModel = new ServicesModel();
	        	  
	        	  userModel.setId(rs.getString(1));
	        	  userModel.setName(rs.getString(2));
	        	  userModel.setPrice(rs.getString(3));
	        	  userModel.setDescription(rs.getString(4));
	        	  userModel.setImage(rs.getString(5));
	        	  
	        	  ServicesList.add(userModel);
	        	 
	          }
			
		}catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

            System.out.println("Connection error" + e.getMessage());
        } finally {
            try {
                if (con != null) {

                    con.close();
                }
                if (pSt != null) {
                	pSt.close();
                }
            } catch (SQLException e){
                System.out.println("SQL error" + e.getMessage());
            }
        }
		
		return ServicesList;
	}


	@Override
	public ArrayList<ServicesModel> getServiceById(String id, ServicesModel services) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateService(String id, ServicesModel services) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteService(String id) {
		// TODO Auto-generated method stub
if (id != null && !id.isEmpty()) {
			
			try {
				
				con = DBConnection.getConnection();
				pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Delete_Service));
				pSt.setString(1, id);
				
				pSt.execute();
				
	        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				
			} finally {
	            try {
	                if (con != null) {

	                    con.close();
	                }
	                if (pSt != null) {
	                	pSt.close();
	                }
	            } catch (SQLException e){
	                System.out.println("SQL error" + e.getMessage());
	            }
	        }
		}
		
		
	}

	@Override
	public void addReserve(ReserveModel reserve) {
		
		try {

            con = DBConnection.getConnection();
            pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Add_Reserve));

            con.setAutoCommit(false);

            pSt.setString(1, reserve.getName());
            pSt.setString(2, reserve.getPhone());
            pSt.setString(3, reserve.getEmail());
            pSt.setString(4, reserve.getDate());
         

            pSt.executeLargeUpdate();
            con.commit();

        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

            System.out.println("con error" + e.getMessage());
        } finally {
            try {
                if (con != null) {

                    con.close();
                }
                if (pSt != null) {
                	pSt.close();
                }
            } catch (SQLException e){
                System.out.println("SQL error" + e.getMessage());
            }
        }
		
	}

	@Override
	public ArrayList<ReserveModel> getReserves() {
		
		ArrayList<ReserveModel> ReserveList = new ArrayList<ReserveModel>();
		
		try {
			
			  con = DBConnection.getConnection();
	          pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.All_Reserve));

	          ResultSet rs = pSt.executeQuery();
	          
	          while(rs.next() ) {
	        	  
	        	  ReserveModel userModel = new ReserveModel();
	        	  
	        	  userModel.setId(rs.getString(1));
	        	  userModel.setName(rs.getString(2));
	        	  userModel.setPhone(rs.getString(3));
	        	  userModel.setEmail(rs.getString(4));
	        	  userModel.setDate(rs.getString(5));
	        	  
	        	  ReserveList.add(userModel);
	        	 
	          }
			
		}catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

            System.out.println("Connection error" + e.getMessage());
        } finally {
            try {
                if (con != null) {

                    con.close();
                }
                if (pSt != null) {
                	pSt.close();
                }
            } catch (SQLException e){
                System.out.println("SQL error" + e.getMessage());
            }
        }
		
		return ReserveList;
		
	}

	@Override
	public void deleteReserve(String id) {
		// TODO Auto-generated method stub
		
		try {
			
			con = DBConnection.getConnection();
			pSt = con.prepareStatement(QueryUtil.QueryById(CommonConstants.Delete_Reserve));
			pSt.setString(1, id);
			
			pSt.execute();
			
        } catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			
		} finally {
            try {
                if (con != null) {

                    con.close();
                }
                if (pSt != null) {
                	pSt.close();
                }
            } catch (SQLException e){
                System.out.println("SQL error" + e.getMessage());
            }
        }

		
	}

}
