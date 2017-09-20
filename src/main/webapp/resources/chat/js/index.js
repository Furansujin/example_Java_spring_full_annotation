
  var chat = {
		  
    idLastMessage: 0,
    idConverstaion: 0,
    messageToSend: '',
//    messageResponses: [
//      'Why did the web developer leave the restaurant? Because of the table layout.',
//      'How do you comfort a JavaScript bug? You console it.',
//      'An SQL query enters a bar, approaches two tables and asks: "May I join you?"',
//      'What is the most used language in programming? Profanity.',
//      'What is the object-oriented way to become wealthy? Inheritance.',
//      'An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol'
//    ],
    init: function(attrDataChat) {
      this.cacheDOM();
//      this.bindEvents();
//      this.render();
      $('.chat-header').html("");
      this.miseAJour();
      this.$chatHistoryList.html("");
      $('.chat-header').append($('.person[data-chat = '+attrDataChat+']').html());
      
      recuperJson(attrDataChat);
//      var callback = {
//  			success : function(o)
//  			{
//  				var json = eval('('+o.responseText+')').liste;
//  				console.log(json); 
//  				for( var i=0; i<json.length; i++){
//  					if(json[i].sender=='1'){
//  						$('.chat-history').find('ul').append(creeMessageEnvoyer(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage)); 
//  					}
//  					else{
//  						
//  						$('.chat-history').find('ul').append(creeMessageEnvoyer(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage));
//  						
//  					}
//  					
//  					
//  				}
//  				
//  				
//  			},
//  	failure : function(o)
//  	{
//  		alert(o.status);
//  	}
//  	};
//  	YAHOO.util.Connect.asyncRequest('GET', './json', callback,  'personne='+attrDataChat);
//  	
//  	console.log(attrDataChat);
  	
      
  	
      
    },
    cacheDOM: function() {
      this.$chatHistory = $('.chat-history');
      this.$button = $('#buttonSend');
      this.$textarea = $('#message-to-send');
      this.$chatHistoryList =  this.$chatHistory.find('ul');
    },
//    bindEvents: function() {
//      this.$button.on('click', this.addMessage.bind(this));
//      this.$textarea.on('keyup', this.addMessageEnter.bind(this));
//    },
    
    addMessage: function() {
      this.messageToSend = this.$textarea.val()
       render();         
    },
    miseAJour: function() {
    	MAJmessage();
    	
     },
    addMessageEnter: function(event) {
        // enter was pressed
        if (event.keyCode === 13) {
          this.addMessage();
        }
    },
    scrollToBottom: function() {
       this.$chatHistory.scrollTop(this.$chatHistory[0].scrollHeight);
    },
    getCurrentTime: function() {
      return new Date().toLocaleTimeString().
              replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
    },
    getRandomItem: function(arr) {
      return arr[Math.floor(Math.random()*arr.length)];
    }
    
  };
  
  
  /*
  var searchFilter = {
    options: { valueNames: ['name'] },
    init: function() {
      var userList = new List('people-list', this.options);
      var noItems = $('<li id="no-items-found">No items found</li>');
      
      userList.on('updated', function(list) {
        if (list.matchingItems.length === 0) {
          $(list.list).append(noItems);
        } else {
          noItems.detach();
        }
      });
    }
  };
  
  searchFilter.init();*/
    

  function openContact(e){
    if ($(e).hasClass('.personeActive')) {
        return false;
    } else {
  		  $('.person').removeClass('personeActive');
    		var findChat = $(e).attr('data-chat'); 
        $('.person[data-chat = '+findChat+']').addClass('personeActive');
        chat.init(findChat);  
//        console.log(findChat);
    }
	} ;

recuperContact();

//this.$('#message-to-send').on('keyup',  chat.addMessageEnter.bind(this));
function render() {
	 
	
	var callback = {
			success : function(o)
			{
				$('#message-to-send').val('');
				 chat.init(chat.idConverstaion);
				
			},
	failure : function(o)
	{
		alert("erreur connexion");
	}
	};
	YAHOO.util.Connect.asyncRequest('GET', './envoimessage?message='+chat.messageToSend+'&idConverstaion='+chat.idConverstaion, callback, null);
 
	
	 
} 




function MAJmessage() {
var callback = {
		success : function(o)
		{
			var json = eval('('+o.responseText+')').liste;
				console.log(json); 
				for( var i=0; i<json.length; i++){
					if(json[i].sender=='1'){
						$('.chat-history').find('ul').append(creeMessageEnvoyer(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage)); 
						chat.scrollToBottom();
					}
					else{
						
						$('.chat-history').find('ul').append(creeMessageReception(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage));
						chat.scrollToBottom();
					}
					if(i==(json.length-1)){
						chat.idLastMessage=json[i].idMessage;
						chat.scrollToBottom();
					}
					
				} 
				chat.idConverstaion=attrDataChat;
				chat.scrollToBottom();
		},
failure : function(o)
{
	alert(o.status);
}
};
YAHOO.util.Connect.asyncRequest('GET', './messageConversationMaj?idConv='+chat.idConverstaion+'&lastMessage='+chat.idLastMessage, callback, null);
 
setTimeout(MAJmessage ,2000);

}
function recuperJson(attrDataChat){
	
	var callback = {
			success : function(o)
			{
				var json = eval('('+o.responseText+')').liste;
  				console.log(json); 
  				for( var i=0; i<json.length; i++){
  					if(json[i].sender=='1'){
  						$('.chat-history').find('ul').append(creeMessageEnvoyer(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage)); 
  					}
  					else{
  						
  						$('.chat-history').find('ul').append(creeMessageReception(json[i].idMessage, json[i].dateMessage, json[i].expediteurMessage, json[i].contentMessage));
  						
  					}
  					if(i==(json.length-1)){
  						chat.idLastMessage=json[i].idMessage;
  						
  					}
  					
  				} 
  				chat.idConverstaion=attrDataChat;
  				chat.scrollToBottom();
			},
	failure : function(o)
	{
		alert(o.status);
	}
	};
	YAHOO.util.Connect.asyncRequest('GET', './messageConversation?personne='+attrDataChat, callback, null);
	
	
}

function recuperContact(){
	
	var callback = {
			success : function(o)
			{
				var json = eval('('+o.responseText+')').liste;
				console.log(json); 
				for( var i=0; i<json.length; i++){
				$('#people-list').find('ul').append(creePersonne(json[i].idconversation, json[i].users[0].nom +" "+json[i].users[0].prenom, json[i].users[0].photo));
				}
				
			},
	failure : function(o)
	{
		alert(o.status);
	}
	};
	YAHOO.util.Connect.asyncRequest('GET', './contact', callback, null);
	
	
}

function rafraichirMessage(){
	if(chat.idConverstaion != null){
	chat.init(chat.idConverstaion);
	setTimeout(rafraichirMessage,2000);
	}
}

function creeMessageEnvoyer(idMessage,dateMessage,expediteurMessage,contentMessage){
	var template = Handlebars.compile(templateMessagEnvoyer);
	var context={
			idMessage:idMessage,
			dateMessage:dateMessage,
			expediteurMessage:expediteurMessage,
			contentMessage:contentMessage
	};
	return template(context);
	
	
}


function creeMessageReception(idMessage,dateMessage,expediteurMessage,contentMessage){
	var template = Handlebars.compile(templateMessageReception);
	var context={
			idMessage:idMessage,
			dateMessage:dateMessage,
			expediteurMessage:expediteurMessage,
			contentMessage:contentMessage
	};
	return template(context);
	
	
}





function creePersonne(idPersonne,nomPersonne,photoPersonne){
	var template = Handlebars.compile(templatePersonne);
	var context={
			idPersonne:idPersonne,
			photoPersonne:photoPersonne,
			nomPersonne:nomPersonne 
	};
	return template(context);
	
	
}



/*
 * Template Chat
 */

var templateMessagEnvoyer = "<li class='clearfix' id='{{idMessage}}'>" +
							" <div class='message-data align-right'>" +
								" <span class='message-data-time' >{{dateMessage}}</span> &nbsp; &nbsp;" +
								"<span class='message-data-name' >{{expediteurMessage}}</span>" +
								" <i class='fa fa-circle me'></i>	" +
							" </div> <div class='message other-message float-right' id='message'>" +
							"		{{contentMessage}} " +
							"</div> " +	
						"</li>";



//var templateMessageReception ="<li class='clearfix' id='{{idMessage}}'>" +
//							" <div class='message-data align-right'>" +
//							" <span class='message-data-time' >{{dateMessage}}</span> &nbsp; &nbsp;" +
//							"<span class='message-data-name' >{{expediteurMessage}}</span>" +
//							" <i class='fa fa-circle me'></i>	" +
//							" </div> <div class='message other-message float-right' id='message'>" +
//							"		{{contentMessage}} " +
//							"</div> " +	
//							"</li>";


var templateMessageReception = "<li  class='clearfix' id='{{idMessage}}'> <div class='message-data' >" +
									" <span class='message-data-name'>" +
									"<i class='fa fa-circle online'></i> {{expediteurMessage}}</span> " +
									"<span class='message-data-time'>{{dateMessage}}</span> " +
									"</div> <div class='message my-message' id='message'> " +
									"{{contentMessage}} " +
									"</div> " +
									"</li>";

var templatePersonne = "<li class='clearfix person' data-chat='{{idPersonne}}' onclick='openContact(this)'>"+
						"<img src='{{photoPersonne}}' alt='avatar' />"+
						"<div class='about'>"+
						"<div class='name'>{{nomPersonne}}</div>"+
						"<div class='status'>"+
						"<i class='fa fa-circle online'></i> online"+
						"</div>"+
						"</div>"+
						"</li>";



