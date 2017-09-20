<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Rubis login</title>
    
    
    <link href='<c:url value="/resources/css/style.css" />' rel='stylesheet'>
    
       
    
    <style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}


</style>
    
  </head>
 
 <body onload='document.loginForm.email.focus();'>
 
    <div class="wrapper">
	<div class="container">
		
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<h1>Welcome</h1>
		<form id="form_id" class="form" name='loginForm'
			action="<c:url value='/login' />" method='POST'>
			
			<input type="text" name='email' placeholder="Email">
			<input type="password" name='password' placeholder="Password">
			<button  id="login-button">Login</button> 
				
			
				
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
    <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>


    <script src="<c:url value="/resources/js/index.js" />"></script>

    
    
    
  </body>
</html>
