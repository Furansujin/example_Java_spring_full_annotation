<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>JQuery popup</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">    
    
    <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
    
    <link href='<c:url value="/resources/themeOs/css/style.css" />' rel='stylesheet'>
     <link href='<c:url value="/resources/themeOs/bootstrap/css/bootstrap.min.css" />' rel='stylesheet'>
     
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> 
        <link href='<c:url value="/resources/themeOs/dist/css/AdminLTE.min.css" />' rel='stylesheet'>
     
      <link href='<c:url value="/resources/themeOs/dist/css/skins/_all-skins.min.css" />' rel='stylesheet'>
       <link href='<c:url value="/resources/themeOs/css/font-awesome-4.5.0/css/font-awesome.min.css" />' rel='stylesheet'>  
    
    
    	

  </head>

  <body>
<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
		
		function callJson() { 

			$.ajax({
					type : "GET",
					url :"bureau/json",
				success: function(data){
				var a=data;
				alert(a);
				}});
		}
	</script>
    <!-- Google font -->

<link href='http://fonts.googleapis.com/css?family=Audiowide' rel='stylesheet' type='text/css'>



 


<div id="desktop" class="desktop">
	<div id="contentDesktop" class="contentDesktop">

 <div class="window" data-window="musique" data-etat="close" >
  <div class="window__titlebar">
     <div class="window__controls window__controls--right">
					<a class="window__minimize reduire" href="#">
				<i class="fa fa-minus"></i>
			</a>
			<a class="window__minus minus" href="#">
				<i class="fa fa-minus-square-o"></i>
			</a> 
			<a class="window__maximize agrandir" href="#">
				<i class="fa fa-plus-square-o"></i>
			</a> 
			<a class="window__close close" href="#">
				<i class="fa fa-close"></i>
			</a>
     </div> 
     <span class="window__title">  musique  </span> 
   </div> 
  <div id="content">   
   <textarea style="height:100%; width:100%;"></textarea>
  </div>
</div > 


	<div class='menu-start' data-window="menu-start" data-etat="close" >
		<div class='menu-start-left'>
				<div class='menu-start-left-head'>
					<div class='menu-start-user-info menu-toggle' data-menu="user">
						<img class="user-info__img media__img" src="http://i.imgur.com/KkCqvK9.png" alt="User image">
						<span>${nom} </span>
					</div>
					
					<div class='menu-start-power menu-toggle' data-menu="power">
						 <i class="fa fa-power-off"></i>
					</div>
				 
				 </div>
	<div class="menu-start-left-recent">
			<div class="menu__user menu" data-menu="user">
				<a href="#">Change account picture</a>
				<a href="#">Lock</a>
				<a href="javascript:formSubmit()">Sign out</a>
			 </div>
			 
			 <div class="menu__power menu" data-menu="power">
			  <a href="#">Sleep</a>
			  <a href="#">Power off</a>
			  <a href="#" onclick="callJson();">Restart</a>
			</div>
				<ul class="start-menu__recent">
				
					
				</ul>

	</div>
		</div>
			<div class='menu-start-right'>
			</div>
	</div>
	<div class="chat-right" id="chat-right">
	<iframe src="./bureau/chat" class="iframeChat"></iframe>
	
	
	</div>
	<div class='start-menu-fade' >
	</div>

	


		   

  
	    
 </div>


<!-- Menu -->
  <div class="taskbar">
      <a class="taskbar__menu_start taskbar__item--start " href="#" data-window="menu-start">
        <i class="fa fa-windows"></i>
      </a>
      
      
			  
      
      
      <div id="taskbar__tray" class="taskbar__tray">
      </div>
  </div>


 </div>	
  <!--fenêtre -->

    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
<script src='https://cdn.rawgit.com/desandro/masonry/master/dist/masonry.pkgd.min.js'></script>


     <script src="<c:url value="/resources/themeOs/js/windows.js" />"></script>
      <script src="<c:url value="/resources/themeOs/js/index.js" />"></script>
    
    
    
    
  </body>
</html>
