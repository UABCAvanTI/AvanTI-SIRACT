<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SAML2 Demo App</title>
</head>
<body>
<%
session=request.getSession(true);  
session.invalidate();  
response.sendRedirect("https://llave.uabc.edu.mx/auth/logout");

%>

</body>
</html>