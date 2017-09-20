package com.rubis.model;

import java.util.HashMap;

public class ConversationChat {
	int idConversation;
	HashMap<Integer, String> listCOntact= new HashMap<Integer, String>();
	
	
	public ConversationChat(int idConversation, HashMap<Integer, String> listCOntact) {
		super();
		this.idConversation = idConversation;
		this.listCOntact = listCOntact;
	}


	public int getIdConversation() {
		return idConversation;
	}


	public void setIdConversation(int idConversation) {
		this.idConversation = idConversation;
	}


	public HashMap<Integer, String> getListCOntact() {
		return listCOntact;
	}


	public void setListCOntact(HashMap<Integer, String> listCOntact) {
		this.listCOntact = listCOntact;
	}
	
	
	
	
	

}
