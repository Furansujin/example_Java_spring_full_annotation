package com.rubis.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rubis.bean.SessionFactoryRubis;
import com.rubis.dao.DaoMessageChat;
import com.rubis.dao.DaoUserRubis;
import com.rubis.model.Employee;
import com.rubis.model.UserRubis; 
 

@Controller
public class RestController {

	@Autowired
    private SessionFactoryRubis sessionFactoryRubis;
	@Autowired
	DaoUserRubis daoUser=new DaoUserRubis();
	
	@Autowired
	DaoMessageChat daoChat = new DaoMessageChat();
	
	
	@RequestMapping(value = "/bureau/messageConversation" , method = RequestMethod.GET )
	@ResponseBody
	  public String getEmployeeInJson(@RequestParam(value = "personne", required = true)  String personne) {  
		 
		return  daoChat.messageConversation(personne);
	}
	  
	@RequestMapping(value = "/bureau/messageConversationMaj" , method = RequestMethod.GET )
	@ResponseBody
	  public String getLastMessage(@RequestParam(value = "idConv", required = true)  String idConversation,
			  						@RequestParam(value = "lastMessage", required = true)  String idLastMessage) {  
		 
		return  daoChat.lastMessage(idConversation, idLastMessage);
	}
	
	
	@RequestMapping(value = "/bureau/envoimessage" , method = RequestMethod.GET )
	@ResponseBody
	  public String geteEnvoiMessage(@RequestParam(value = "message", required = true)  String message,@RequestParam(value = "idConverstaion", required = true)  String idConverstaion) {  
		 
		return  daoChat.envoiMessage(message,idConverstaion);
	}
	
	@RequestMapping(value = "/bureau/contact"  )
	@ResponseBody
	  public String getPersonneInJson() {  
		 
			       
//		return "{ \"tab\":[{\"nom\": \""+sessionFactoryRubis.getUserrubis().getName()+"\", \"email\": \""+sessionFactoryRubis.getUserrubis().getEmail()+"\",\"id\": \""+sessionFactoryRubis.getUserrubis().getIdUser()+"\" },{\"nom\": \"person2\", \"a\": \"https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_02.jpg \",\"b\": \"Aiden Chavez\" },{\"nom\": \"person3\", \"a\": \"https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_03.jpg\",\"b\": \"Mike Thomas\" },{\"nom\": \"person4\", \"a\": \"https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_04.jpg\",\"b\": \"Erica Hughes\" }] }";
		return daoChat.listeConversation();
	}
	
	
	@RequestMapping(value=  "/bureau/info"  , method = RequestMethod.GET)
	@ResponseBody
	public String info( 
			@RequestParam(value = "user", required = true) String userinfo, HttpServletRequest request) {
		UserRubis userRubis =daoUser.userRubisByEmail(userinfo);

		return  " nom:"+userRubis.getName()+"       id:"+userRubis.getIdUser();

	}
	

	
}
