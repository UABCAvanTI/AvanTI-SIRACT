<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/sample.css">
<title>SAML2 Demo App</title>
</head>
<body>	
	<div id="header-area">    
    	<table>
    		<tr>
    			<td>    		
		        	<img src="resources/imagenes/logonew.png" alt="Logo"  vspace="10" width="167" height="75" />					
				</td>
    			<td>
    				<h1 style="color:#14566d;">SSO</h1>
    			</td>  	    		
			</tr>
		</table>		
    </div>
    <!-- Header-are end -->

    <div id="content-area">                    
        <div class="box"> <!--Displaying one product-->
           <h1>Public Area!</h1>
                    <%
response.sendRedirect("https://llave.uabc.edu.mx/auth/login?SAMLRequest=lZNbT%2BMwEIX%2FijXvuVXsqlhNUbcIbSQuoUn3gTfjThdLjp31OKX8e5y0YfuAKniLZo59znzjzK72jWY7dKSsySGLU2BopN0o8zeHdX0TTeFqPiPR6JYvOv9iVvivQ%2FIsnDPEh0YOnTPcClLEjWiQuJe8Wtzd8kmc8tZZb6XVwBZE6HwwWlpDXYOuQrdTEter2xxevG95kpByQvq42eu4E88yfPBpOk2Taqgn8ngS2I11EodEOWyFJgRWXOcQ8hdUCiK1w%2F8Nog4LQ14Yn8MkzX5EWRZll3Wa8jTjF5fx5OfFE7DyGPWXMgcA5%2BZ6PoiI%2F67rMiofqhrYnxFkEMCIbXB3XwcmRkww%2Fx6UWXJqONrfB4PiurRayTe20Nq%2BLh0KH%2Bh41%2BEAshH%2BfKS%2BojbRdpDytp%2BSPBoPrCr7%2Bx87odVWocuh6NNDMrofnwtuhlWFxXvce7a0TSucoh4V7sMMR1j8VLXUgcQKtyfkvgzurExy2V8dyv1DebVu0y8eZUhZO2Gotc4fWH6aZz5y%2FnS2j%2B7p3zJ%2FBw%3D%3D&RelayState=null");
%>m   
        </div>

    </div>
    <!-- content-area end -->


    <div id="footer-area"><p>©2012 CertuIT </p></div>

</body>
</html>