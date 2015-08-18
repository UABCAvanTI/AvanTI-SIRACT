package mx.avanti.siract.ui;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.avanti.business.logic.FacadeConfiguracion;
import mx.avanti.siract.application.helper.ConfiguracionBeanHelper;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.integration.persistence.BaseDAO;

/**
 * @author Eduardo
 */
public class Main {
    public static void main(String[] args) {
//        ConfiguracionBeanUI beanUI=new ConfiguracionBeanUI();
        //EnvioAlertas envioAlertas=new EnvioAlertas();
//        
        //envioAlertas.enviarCorreo();
        ConfiguracionBeanHelper beanHelper=new ConfiguracionBeanHelper();
        beanHelper.findAllCiclosEscolares();
//        beanUI.ejecutarAlertas(true);
//        FacadeConfiguracion facadeConfiguracion=new FacadeConfiguracion();
//        List<Calendarioreporte> listaCalendario = facadeConfiguracion.findCREbyCON(4);
//        for(int x=0; x<listaCalendario.size(); x++)
//            System.out.println(listaCalendario.get(x).getCrefechaCorte());
  //      envioAlertas.comprobarFecha();
    }
}
