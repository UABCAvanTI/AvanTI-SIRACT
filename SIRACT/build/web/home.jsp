<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.certuit.saml2.entity.User"%><html>
<head>
<link rel="stylesheet" type="text/css" href="css/sample.css">
<title>SAML2 Demo App</title>
</head>
<body>
	<%	
		String user_name = "";
		
		if(session.getAttribute("user") == null)
			response.sendRedirect("denied.jsp");		
		else
			user_name = session.getAttribute("user").toString(); 	    
	%>
	<div id="header-area">    
    	<table>
    		<tr>
    			<td>    		
		        	<img src="images/certuit_logo.jpg" alt="Logo"  vspace="10" width="167" height="75" />					
				</td>
    			<td>
    				<h1 style="color:#14566d;">SAML2 SAMPLE APP</h1>
    			</td>  	    		
			</tr>
		</table>		
    </div>
    <!-- Header-are end -->

    <div id="content-area">                    
        <div class="box"> <!--Displaying one product-->
           <h1>Welcome!</h1>
           <h2> You are logged in as <%=user_name%></h2>
           <br/>
           
            <a href="logout.jsp">Logout</a>           
        </div>

    </div>
    <!-- content-area end -->


    <div id="footer-area"><p>©2012 CertuIT </p></div>

</body>
</html>







