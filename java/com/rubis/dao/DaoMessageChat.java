package com.rubis.dao;

import java.sql.Connection; 

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rubis.bean.SessionFactoryRubis;
import com.rubis.model.MessageChat;
import com.rubis.model.UserRubis;
import java.io.IOException;  
@Component
public class DaoMessageChat {
	
	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	
	
	
 
	public String listeConversation() {
		String req ="  SELECT  c.`conversation_id`, u.`user_id`,u.`nom`,u.`prenom`, u.`email`, u.`photo` FROM  `users` u,  `conversation` c "
				+ "WHERE c.`conversation_id` in ( SELECT `conversation_id` FROM `conversation` WHERE `user_id` = '"+sessionFactoryRubis.getUserrubis().getIdUser()+"')"
				+ " AND c.`user_id` = u.`user_id` "
				+ "AND NOT c.`user_id` = '"+sessionFactoryRubis.getUserrubis().getIdUser()+"'";
		String json="{ \"liste\":[";
		HashMap <Integer, ArrayList<UserRubis> > hashMap = new HashMap<Integer, ArrayList<UserRubis>>();
		ResultSet rs=null; 
		     try {
				Connection con= sessionFactoryRubis.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(req);
				
				rs= stmt.executeQuery();
				while(rs.next()){ 
					
					 int cId=rs.getInt("conversation_id");
					 
					 String uNom = rs.getString("nom");
					 int uId = rs.getInt("user_id");
					 String uPrenom=rs.getString("prenom");
					 String uEmail=rs.getString("email");
					 String uPhoto=rs.getString("photo");
					 UserRubis userConv =new  UserRubis(uId, uEmail, uNom, uPrenom, uPhoto);
					   ArrayList<UserRubis> al  ; 
					if(hashMap.containsKey(rs.getInt("conversation_id")))
					{
						al=hashMap.get(cId);
						al.add(userConv);
						hashMap.put(cId , al);
					} 
					else
					{
					    al=new ArrayList<UserRubis>();
					    al.add(userConv);
					    hashMap.put(cId , al);
					}  
					 	 
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     int sizehashmap= 0;
		     for (Map.Entry<Integer, ArrayList<UserRubis>> entry : hashMap.entrySet()) {
		    	 ArrayList<UserRubis> listUserConv= entry.getValue();
		 		System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue().get(0).getPrenom());
		 		
		 		 json+="{\"idconversation\":\""+entry.getKey()+"\", \"users\":[";
		 		 
		 		
		 			for (int i = 0; i < listUserConv.size(); i++) {
		 				if ( i==(listUserConv.size()-1))
		 					json+=""+listUserConv.get(i).getUserTabJson()+"]";
		 				
		 				else
		 					json+=""+listUserConv.get(i).getUserTabJson()+","; 
		 			}
		 			if(sizehashmap==hashMap.size()-1)
		 				json+="}";
		 			else
		 				json+="},";
		 		sizehashmap++;
		 	}

		     
			 
		      
		     json+="]}";
		return json;
		
	} 
	
	
	
	
	public String lastMessage(String idConvesation,String idLastMessage)  {
		String req ="SELECT cm.`user_id_expediteur`, u.`nom`, u.`prenom`, u.`online`, cm.`id_conversation` ,cm.`message_id`,  cm.`date`,`message` "
				+ "FROM `users` u, `conversation_message` cm, `conversation` c "
				+ "WHERE cm.`id_conversation`= c.`conversation_id` "
				+ "AND cm.`user_id_expediteur`= u.`user_id` "
				+ "AND cm.`id_conversation`="+idConvesation
				+ " AND cm.message_id > "+idLastMessage
				+ " AND c.`user_id` = "+sessionFactoryRubis.getUserrubis().getIdUser()+" ORDER BY `cm`.`message_id` ASC";
		
	//	SELECT cm.`user_id_expediteur`, u.`nom`, u.`prenom`, u.`online`, cm.`id_conversation`,  cm.`date`,`message` FROM `users` u, `conversation_message` cm, `conversation` c WHERE cm.`id_conversation`= c.`conversation_id` AND cm.`id_conversation`=1 AND c.user_id = 1 and cm.`user_id_expediteur`= u.`user_id`
		
		List<MessageChat> messageList= new ArrayList<MessageChat>(); 
		ResultSet rs=null; 
		     try {
				Connection con= sessionFactoryRubis.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(req);
				
				rs= stmt.executeQuery();
				while(rs.next()){ 
					
					 int cId=rs.getInt("id_conversation");
					 int mId=rs.getInt("message_id");
					 
					 int uId = rs.getInt("user_id_expediteur");
					 String uNomP= rs.getString("nom")+" "+rs.getString("prenom"); 
					 String umessage=rs.getString("message");
					 String date=rs.getString("date");
					 int sender;
					 if(uId==sessionFactoryRubis.getUserrubis().getIdUser())
						 sender =1;
					 else 
						 sender = 0;
					 MessageChat messageChat =new  MessageChat(mId, umessage, date, uNomP, cId, sender);
					 messageList.add(messageChat); 
					 
					 	 
				}
			
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		     String json="{ \"liste\":[";
		     for(int i = 0; i < messageList.size(); i++)
		     {
		       if(i==(messageList.size()-1))
		    	   json+=messageList.get(i).getMessgeTabJson();
		       else
		    	json+=messageList.get(i).getMessgeTabJson()+", ";
		    	   
		     } 
		     
		     json+="]}";
		return json;
	}
	
	
	
	
	
	public String messageConversation(String idConvesation) {
		String req ="SELECT cm.`user_id_expediteur`, u.`nom`, u.`prenom`, u.`online`, cm.`id_conversation` ,cm.`message_id`,  cm.`date`,`message` "
				+ "FROM `users` u, `conversation_message` cm, `conversation` c "
				+ "WHERE cm.`id_conversation`= c.`conversation_id` "
				+ "AND cm.`user_id_expediteur`= u.`user_id` "
				+ "AND cm.`id_conversation`="+idConvesation+" "
				+ "AND c.`user_id` = "+sessionFactoryRubis.getUserrubis().getIdUser()+" ORDER BY `cm`.`message_id` ASC";
		
	//	SELECT cm.`user_id_expediteur`, u.`nom`, u.`prenom`, u.`online`, cm.`id_conversation`,  cm.`date`,`message` FROM `users` u, `conversation_message` cm, `conversation` c WHERE cm.`id_conversation`= c.`conversation_id` AND cm.`id_conversation`=1 AND c.user_id = 1 and cm.`user_id_expediteur`= u.`user_id`
		
		List<MessageChat> messageList= new ArrayList<MessageChat>(); 
		ResultSet rs=null; 
		     try {
				Connection con= sessionFactoryRubis.getDataSource().getConnection();
				PreparedStatement stmt = con.prepareStatement(req);
				
				rs= stmt.executeQuery();
				while(rs.next()){ 
					
					 int cId=rs.getInt("id_conversation");
					 int mId=rs.getInt("message_id");
					 
					 int uId = rs.getInt("user_id_expediteur");
					 String uNomP= rs.getString("nom")+" "+rs.getString("prenom"); 
					 String umessage=rs.getString("message");
					 String date=rs.getString("date");
					 int sender;
					 if(uId==sessionFactoryRubis.getUserrubis().getIdUser())
						 sender =1;
					 else 
						 sender = 0;
					 MessageChat messageChat =new  MessageChat(mId, umessage, date, uNomP, cId, sender);
					 messageList.add(messageChat); 
					 
					 	 
				}
			
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		     String json="{ \"liste\":[";
		     for(int i = 0; i < messageList.size(); i++)
		     {
		       if(i==(messageList.size()-1))
		    	   json+=messageList.get(i).getMessgeTabJson();
		       else
		    	json+=messageList.get(i).getMessgeTabJson()+", ";
		    	   
		     } 
		     
		     json+="]}";
		return json;
	}





	public String envoiMessage(String message,String idConversation) {
		// TODO Auto-generated method stub
		String req ="INSERT INTO `rubis`.`conversation_message`"
				+ " (`message`, `id_conversation`, `user_id_expediteur`, `date`, `message_id`) "
				+ "VALUES ('"+message+"', '"+idConversation+"', '"+sessionFactoryRubis.getUserrubis().getIdUser()+"', CURRENT_TIMESTAMP, NULL)";
		
		try{
		 Connection conn =  sessionFactoryRubis.getDataSource().getConnection();
	     
	      // create a sql date object so we can use it in our INSERT statement
	   
	 
	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn.prepareStatement(req);
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
	       
	      conn.close();
	    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return "envoyer";
	}
	

}
