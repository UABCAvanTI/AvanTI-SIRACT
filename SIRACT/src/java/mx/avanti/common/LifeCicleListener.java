//package mx.avanti.common;
//
//import javax.faces.application.NavigationHandler;
//import javax.faces.context.FacesContext;
//import javax.faces.event.PhaseEvent;
//import javax.faces.event.PhaseId;
//import javax.faces.event.PhaseListener;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author Cesar Favela
// */
//public class LifeCicleListener implements PhaseListener {
//    @Override
//    public void afterPhase(PhaseEvent event) {
//        FacesContext facesContext = event.getFacesContext();
//        String correntPage = facesContext.getViewRoot().getViewId();
//        boolean IsLoginPage =(correntPage.lastIndexOf("login.xhtml") > -1);
//        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(true);
//        Object Usuario = sesion.getAttribute("usuario");
//        
//        if(!IsLoginPage && Usuario == null) {
//            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
//            nh.handleNavigation(facesContext, null, "login.xhtml");
//        }
//    }
//
//    @Override
//    public void beforePhase(PhaseEvent pe) {
//        System.out.println("STAR PHASE" + pe.getPhaseId());
//    }
//
//    @Override
//    public PhaseId getPhaseId() {
//        return PhaseId.ANY_PHASE;
//    }
//}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.common;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author Cesar Favela
 */
public class LifeCicleListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent pe) {
        System.out.println("END PHASE" + pe.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        System.out.println("STAR PHASE" + pe.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
