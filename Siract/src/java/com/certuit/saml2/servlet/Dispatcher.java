package com.certuit.saml2.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.certuit.common.security.exception.UserNotFoundException;
import com.certuit.saml2.entity.User;
import com.certuit.saml2.logic.UserManager;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.avanti.siract.application.helper.LoginBeanHelper;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.ui.LoginBean;

/**
 * Servlet implementation class Dispatcher
 */
public class Dispatcher extends HttpServlet {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    
    private static final long serialVersionUID = 1L;

    String user_name;
    String password;
    String user_type;
    LoginBeanHelper loginBeanHelp = new LoginBeanHelper();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispatcher() {

        super();

        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user_name = request.getParameter("subject");
        String password = request.getParameter("password");
        String user_type = request.getParameter("userType");

        System.out.println(user_name);
        System.out.println(password);
        System.out.println(user_type);

        User user = null;

        if (user_type.toLowerCase().compareTo("student") == 0 || user_type.toLowerCase().compareTo("employee") == 0) ////if(user_type.toLowerCase().compareTo("employee") == 0)
        {
            System.out.println("si llegue a qui");
            ////us.setUsuusuario("cristyan.hernandez");

            try {
                user = (User) new UserManager().getUser(user_name, password);
                System.out.println(user.getUserName() + "dispatcher");
                

                request.getSession().setAttribute("user", user_name);

            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (user != null) {

            Usuario usuario = new Usuario();

            HttpSession session = request.getSession(true);
            String username = (String) session.getAttribute("user");
            usuario.setUsuusuario(username);
            usuario = loginBeanHelp.getUsuDel().login(usuario);/////////Cambio de ruta
            ///////loginBean.setLogueado(usuario);
            
                        
            if (usuario == null) {

                request.getSession().setAttribute("user", null);
                request.getSession(false).invalidate();
                response.sendRedirect("denied.jsp");
            } else {
                System.out.println(username + "Dispatcher 2");
            ////String userName=(String)session.getAttribute("username");

//                    System.out.println(lb.getU().getUsuusuario()+"login bena");
//                   ///// us.setUsucontrasenia(password);
                response.sendRedirect("index.xhtml");
            }
        } else {
            response.sendRedirect("denied.jsp");
        }
    }
    
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
