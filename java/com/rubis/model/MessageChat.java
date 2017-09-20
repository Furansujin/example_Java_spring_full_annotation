package com.rubis.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.rubis.bean.SessionFactoryRubis;

public class MessageChat {
	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	
	
	int idMessage;
	String message;
	String dateMessage;
	String expediteurMessage;
	int idConversation;
	int sender;
	
	public MessageChat(int idMessage, String message, String dateMessage, String expediteurMessage,int idConversation, int sender) {
		super();
		this.idMessage = idMessage;
		this.message = message;
		this.dateMessage = dateMessage;
		this.expediteurMessage = expediteurMessage;
		this.sender = sender;
		
		this.idConversation = idConversation;
	}

	public int getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(String dateMessage) {
		this.dateMessage = dateMessage;
	}

	public String getExpediteurMessage() {
		return expediteurMessage;
	}

	public void setExpediteurMessage(String expediteurMessage) {
		this.expediteurMessage = expediteurMessage;
	}

	public int getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(int idConversation) {
		this.idConversation = idConversation;
	}
	
	
	 
	//retourne un tableau a mettre dans un json pour une bulle message
		public String getMessgeTabJson(){  
			
			return "{\"sender\" :\""+sender+"\",\"idMessage\" :\""+idMessage+"\",\"dateMessage\" :\""+dateMessage+"\",\"expediteurMessage\" :\""+expediteurMessage+"\",\"contentMessage\" :\""+message+"\"}";
		}

}
