package mx.avanti.siract.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import mx.avanti.siract.application.helper.EnvioAlertasHelper;
import mx.avanti.siract.business.CalendarioreporteDelegate;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.ConfiguracionDelegate;
import mx.avanti.siract.business.entity.Calendarioreporte;
import mx.avanti.siract.business.entity.CalendarioreporteTieneAlerta;
import mx.avanti.siract.business.entity.Cicloescolar;
import mx.avanti.siract.business.entity.Configuracion;
import mx.avanti.siract.business.entity.Mensaje;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Usuario;

public class EnvioAlertas extends Thread implements Serializable{
    private EnvioAlertasHelper envioAlertasHelper=new EnvioAlertasHelper();
    private List<Calendarioreporte> calendarioreportes;
    
    private int numReporte=0;
    private int tipoDeMensaje=99;
    private String MENSAJE_HTML="<div style=\"width: 700px; height: 525px; background: white; border: 3px solid green; border-radius: 10px;\">\n" +
"            <img src=\"http://ingenieria.mxl.uabc.mx/images/logonew.png\" width=\"480\" style=\"margin-left: 110px; margin-top: 10px;\" alt=\"\">\n" +
"            <table style=\"margin: 0 auto; width: 480px; margin-top: 20px; border-radius: 10px; border: 4px green solid;\">\n" +
"                <tr>\n" +
"                    <td style=\"text-align: center; color: white; font-family: arial; background-color: green; border-radius: 5px;\">\n" +
"                        TituloAlerta\n" +
"                    </td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                    <td>\n" +
"                        <p style=\"text-align: center; font-family: arial; font-size: 16px;\">Universidad Autónoma de Baja California<br>\n" +
"                                Facultad de Ingeniería\n" +
"                        </p>\n" +
"                        <p style=\"font-family: arial; font-size: 16px; margin-left: 5px;\">\n" +
"                        <br>\n" +
"                        <b>Profesor: nombreProfesor</b><br><br>\n" +
"                        Mensaje<br><br>\n" +
"                        diadeentrega<br><br>\n"+
"                        Sin más por el momento, un cordial saludo.<br><br>\n"+
"                        Para cualquier comentario o aclaración sobre el sistema favor de enviar un correo\n" +
"                        al Administrador del Sistema ejemplo@uabc.edu.mx\n" +
"                        </p>\n" +
"                    </td>\n" +
"                </tr>\n" +
"            </table>\n" +
"            <p style=\"text-align: center; font-family: arial; font-size: 12px;\">\n" +
"                ©SIRACT\n" +
"            </p>\n" +
"        </div>";
    private Mensaje mensaje;
    private String mensajeTxt;
    //Dates para determinar las fechas de la BD--------------
    private Date fechaActual=null;
    Date[] fechasCorte;
    Date[] fechasLimite;
    Date[] fechasAleCorte;
    Date[] fechasAleLimite;
    Date[] fechasAleAtraso;
    
    public int comprobarFecha(){//determina cual de los reportes corresponde en la fecha
        SimpleDateFormat sdfDDMMYYYY=new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar=Calendar.getInstance(); //Obtiene la fecha actual del sistema
        //fechaActual=new Date(114, 6, 28); //Para fecha de prueba //comentar o borrar
        fechaActual=calendar.getTime(); //Para la fecha actual //Descomentar
        System.out.println(fechaActual);
        List<Calendarioreporte> calendarios=comprobarCalendarioReportes();//obtiene los CR del metodo
        calendarioreportes=calendarios;
        for(int i=0; i<calendarios.size(); i++){
            System.out.println("id: "+calendarios.get(i).getCreid());
        }
        //List<CalendarioreporteTieneAlerta> diasCalendarios=envioAlertasHelper.getCalendarioReporteTieneAlertaDelegate().getCalendarioReporteTieneAlerta();
        List<CalendarioreporteTieneAlerta> diasCalendarios=new ArrayList<CalendarioreporteTieneAlerta>();
        for(int i=0; i<calendarios.size(); i++){
            int aleid=2;
            for(int x=0; x<=2; x++){
                diasCalendarios.add(envioAlertasHelper.getCalendarioReporteTieneAlertaDelegate().getCalendarioReporteTieneAlertas(calendarios.get(i).getCreid(), aleid));
                System.out.println("Id: "+calendarios.get(i).getCreid()+" Dias: "+diasCalendarios.get(x).getCaldias());
                aleid++;
            }
        }
        
        //---------------------Variables de las fechas------------------------------------------
        System.out.println("FA: "+sdfDDMMYYYY.format(fechaActual));
        fechasCorte=new Date[calendarios.size()];
        fechasLimite=new Date[calendarios.size()];
        for(int i=0; i<calendarios.size(); i++){ //Obtiene las fechas de corte y limite
            fechasCorte[i]=calendarios.get(i).getCrefechaCorte();
            fechasLimite[i]=calendarios.get(i).getCrefechaLimite();
            System.out.println("FC: "+sdfDDMMYYYY.format(fechasCorte[i]));
            System.out.println("FL: "+sdfDDMMYYYY.format(fechasLimite[i]));
        }
        
        fechasAleCorte=new Date[calendarios.size()];
        fechasAleLimite=new Date[calendarios.size()];
        fechasAleAtraso=new Date[calendarios.size()];
        for(int i=0; i<calendarios.size(); i++){ //Obtiene las fechas en que se mandaran las alertas, respecto a la bd de los dias antes
            for(int x=(i*(diasCalendarios.size()/calendarios.size())); x<((i+1)*(diasCalendarios.size()/calendarios.size())); x++){
                int saltos=i*(diasCalendarios.size()/calendarios.size());
                fechasAleCorte[i]=new Date((calendarios.get(i).getCrefechaCorte().getTime())-(diasCalendarios.get(saltos).getCaldias()*24*3600*1000));
                fechasAleLimite[i]=new Date((calendarios.get(i).getCrefechaLimite().getTime())-(diasCalendarios.get(saltos+2).getCaldias()*24*3600*1000)); //Se le restan o suman dias
                fechasAleAtraso[i]=new Date((calendarios.get(i).getCrefechaLimite().getTime())+(diasCalendarios.get(saltos+1).getCaldias()*24*3600*1000));
            }
        }
        
        for(int i=0; i<calendarios.size(); i++){
                System.out.println("----------------------------------------");
                System.out.println("PreCorte: "+sdfDDMMYYYY.format(fechasAleCorte[i]));
                System.out.println("PreLimite: "+sdfDDMMYYYY.format(fechasAleLimite[i]));
                System.out.println("Atraso: "+sdfDDMMYYYY.format(fechasAleAtraso[i]));
        }
        
        for(int i=0; i<calendarios.size(); i++){ //Obtiene el numero de reporte correspondiente
            if((fechaActual.before(fechasAleAtraso[i])||fechaActual.equals(fechasAleAtraso[i]))&&(fechaActual.after(fechasAleCorte[i])||fechaActual.equals(fechasAleCorte[i]))){
                numReporte=i+1;
            }
            
        }
        System.out.println("Numero Reporte: "+numReporte);

        //-------------------Saber que alerta mandara---------------------------------------------
        for(int i=0; i<fechasAleCorte.length; i++){
            if(fechaActual.equals(fechasAleCorte[i])){
                String numero=(i+1)+"1";
                tipoDeMensaje=Integer.parseInt(numero);
            }
        }
//        for(int i=0; i<fechasAleLimite.length; i++){
//            if(fechaActual.equals(fechasAleLimite[i])){
//                String numero=(i+1)+"2";
//                tipoDeMensaje=Integer.parseInt(numero);
//            }
//        }
        for(int i=0; i<fechasAleLimite.length; i++){
            if(sdfDDMMYYYY.format(fechaActual).equals(sdfDDMMYYYY.format(fechasAleLimite[i]))){
                String numero=(i+1)+"2";
                tipoDeMensaje=Integer.parseInt(numero);
            }
        }
        for(int i=0; i<fechasAleAtraso.length; i++){
            if(fechaActual.equals(fechasAleAtraso[i])){
                String numero=(i+1)+"3";
                tipoDeMensaje=Integer.parseInt(numero);
            }
        }
        System.out.println("Tipo de Mensaje: "+tipoDeMensaje);
        
        return tipoDeMensaje; //regresa el numero de reporte a mandar
    }
    
    public String obtenerMensaje(){//Obtiene el mensaje en base al numero de este
        SimpleDateFormat sdfDDMMMMYYYYEs=new SimpleDateFormat("dd/MMMM/yyyy", new Locale("es"));
        String mensajeHtml=MENSAJE_HTML;
        comprobarFecha();
        String numeroLetra=null;
        System.out.println("numero de reporte: "+numReporte);
            switch(numReporte){
                case 1:
                    numeroLetra="primer";
                    break;
                case 2:
                    numeroLetra="segundo";
                    break;
                case 3:
                    numeroLetra="tercer";
                    break;
                case 4:
                    numeroLetra="cuarto";
                    break;
                case 5:
                    numeroLetra="quinto";
                    break;
            }
        if(tipoDeMensaje==11||tipoDeMensaje==21||tipoDeMensaje==31){ //Corte
            mensaje=envioAlertasHelper.getMensajeDelegate().getMensajes().get(1);
            mensajeTxt=mensaje.getMenmensaje();
            mensajeTxt=mensajeTxt.replace("numeroreporte", numeroLetra);
            mensajeHtml=mensajeHtml.replace("TituloAlerta", "ALERTA DE CORTE");
            mensajeHtml=mensajeHtml.replace("Mensaje", mensajeTxt);
            for(int i=0; i<calendarioreportes.size(); i++){
                if(numReporte==i){
                    mensajeHtml=mensajeHtml.replace("diadeentrega", "Periodo de Entrega: "+sdfDDMMMMYYYYEs.format(calendarioreportes.get(i-1).getCrefechaCorte())+" al "+sdfDDMMMMYYYYEs.format(calendarioreportes.get(i-1).getCrefechaLimite()));
                }
            }
        }
        if(tipoDeMensaje==12||tipoDeMensaje==22||tipoDeMensaje==32){ //Limite
            mensaje=envioAlertasHelper.getMensajeDelegate().getMensajes().get(3);
            mensajeTxt=mensaje.getMenmensaje();
            mensajeHtml=mensajeHtml.replace("TituloAlerta", "ALERTA DE LIMITE");
            mensajeHtml=mensajeHtml.replace("Mensaje", mensajeTxt);
            for(int i=0; i<calendarioreportes.size(); i++){
                if(numReporte==i){
                    mensajeHtml=mensajeHtml.replace("diadeentrega", "Periodo de Entrega: "+sdfDDMMMMYYYYEs.format(calendarioreportes.get(i-1).getCrefechaCorte())+" al "+sdfDDMMMMYYYYEs.format(calendarioreportes.get(i-1).getCrefechaLimite()));
                }
            }
        }
        if(tipoDeMensaje==13||tipoDeMensaje==23||tipoDeMensaje==33){ //Atraso
            mensaje=envioAlertasHelper.getMensajeDelegate().getMensajes().get(2);
            mensajeTxt=mensaje.getMenmensaje();
            mensajeHtml=mensajeHtml.replace("TituloAlerta", "ALERTA DE ATRASO");
            mensajeHtml=mensajeHtml.replace("Mensaje", mensajeTxt);
            //String numeroLetra=null;
            switch(numReporte){
                case 1:
                    numeroLetra="primer";
                    break;
                case 2:
                    numeroLetra="segundo";
                    break;
                case 3:
                    numeroLetra="tercer";
                    break;
                case 4:
                    numeroLetra="cuarto";
                    break;
                case 5:
                    numeroLetra="quinto";
                    break;
            }
            mensajeHtml=mensajeHtml.replace("diadeentrega", " ");//cosas para las fechas
        }
        //System.out.println(mensajeHtml);
        return mensajeHtml; //retorna el mensaje
    }
    
    public void enviarCorreo(){
        if(tipoDeMensaje!=0){
            final String username = "ecampos13@uabc.edu.mx"; //emisor correo
            final String password = "3du4rd0c4mp0s"; //contrasena del correo emisor
            //---Envios de prueba
            String datosPrueba[]=new String[7];
            datosPrueba[0]="ecampos13";
            datosPrueba[1]="abraham.sanchez"; 
            datosPrueba[2]="ajuarez18";
            datosPrueba[3]="a1109336";
            datosPrueba[4]="angelicaastorga";
            datosPrueba[5]="gloria_chavez";
            datosPrueba[6]="araceli.justo";
            //-----------------
            //List<Profesor>prof=envioAlertasHelper.getProfesorDelegate().getProfesores(); //Obtiene los profesores de la BD
            Properties props = new Properties(); //Se utilizan para preparar el medio para mandar los correos
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");            
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getInstance(props, //Crea una session para autentificar el correo y pass
              new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                    }
              });
            String mensaj=obtenerMensaje();
            String mensaje2=mensaj;
            try {
                //for(Iterator<Profesor>it=prof.iterator(); it.hasNext();){ //Ciclo que recorre los maestros
                    //String mensaj=obtenerMensaje();
                    //Profesor profesor=it.next();
                for(int i=0; i<datosPrueba.length; i++){
                    System.out.println("mando mensaje "+i);
                    //System.out.println(profesor.getPronombre()+" "+profesor.getUsuario().getUsuusuario());
                    System.out.println(datosPrueba[i]);
                    MimeBodyPart messageBodyPart = new MimeBodyPart(); //mime y multi permiten agregar contenido html a los correos
                    Multipart multipart = new MimeMultipart();
                    Message message = new MimeMessage(session); //Se crea el mensaje
                    message.setFrom(new InternetAddress("from-email@gmail.com")); //Se establece el emisor
                    message.setRecipients(Message.RecipientType.TO,
                    //InternetAddress.parse(profesor.getUsuario().getUsuusuario()+"@uabc.edu.mx")); //aqui va el receptor
                    //InternetAddress.parse("ecampos13@uabc.edu.mx")); //aqui va el receptor, prueba con mi correo
                    InternetAddress.parse(datosPrueba[i]+"@uabc.edu.mx"));
                    if(tipoDeMensaje==11||tipoDeMensaje==21||tipoDeMensaje==31)
                        message.setSubject("(Prueba) SIRACT - Alerta De Corte #"+numReporte);
                    if(tipoDeMensaje==12||tipoDeMensaje==22||tipoDeMensaje==32)
                        message.setSubject("(Prueba) SIRACT - Alerta De Limite #"+numReporte);
                    if(tipoDeMensaje==13||tipoDeMensaje==23||tipoDeMensaje==33)
                        message.setSubject("(Prueba) SIRACT - Alerta De Atraso #"+numReporte);
                    //String nombreProfesorCompleto=""+profesor.getPronombre()+" "+profesor.getProapellidoPaterno()+" "+profesor.getProapellidoMaterno();
                    String nombreProfesorCompleto="Pruebas - "+datosPrueba[i];
                    mensaj=mensaj.replace("nombreProfesor", nombreProfesorCompleto);
                    messageBodyPart.setText(mensaj, "UTF-8", "html"); //se agrega el texto al correo
                    multipart.addBodyPart(messageBodyPart);
                    message.setContent(multipart); //se le agrega el texto al mensaje
                    mensaj=mensaje2;
                   // Transport.send(message); //se manda el mensaje
                    System.out.println("Done"); //mensaje de prueba
                }
                //}
            } catch(MessagingException e) {
                    throw new RuntimeException(e);
            }
        }
        
    }
    
    //Obtiene los calendarios reporte dependiendo del ciclo escolar
    public List comprobarCalendarioReportes(){ //"completo" //faltarian los intersemestrales
        List<Calendarioreporte> calendarioReportes=new ArrayList<>();
        SimpleDateFormat sdfMM=new SimpleDateFormat("MM");
        SimpleDateFormat sdfYYYY=new SimpleDateFormat("YYYY");
        String ciclo="2014-2";
        //Se arma el ciclo escolar respecto a la fecha actual//Creo que no es necesario consultar la BD
        int year=Integer.parseInt(sdfYYYY.format(fechaActual));
        int mes=Integer.parseInt(sdfMM.format(fechaActual));
        if(mes>=2&&mes<=5)
            ciclo=""+year+"-"+1;
        if(mes>=8&&mes<=11)
            ciclo=""+year+"-"+2;//faltan intersemestrales
        //----
        int numeroDeCiclo=Integer.parseInt(ciclo.substring(5));
        List<Calendarioreporte> cpc=new ArrayList<>();
        cpc=envioAlertasHelper.getCalendarioReporteDelegate().getCalendariosReporte();
        for(int i=0; i<cpc.size(); i++){//ciclo para dar sacar los CR respecto al ciclo
            Date d=cpc.get(i).getCrefechaCorte();
            if(Integer.parseInt(sdfYYYY.format(d))==Integer.parseInt(ciclo.substring(0, 4))){
                if((Integer.parseInt(sdfMM.format(d))>=2&&Integer.parseInt(sdfMM.format(d))<=5)&&numeroDeCiclo==1){
                    calendarioReportes.add(cpc.get(i));
                }
                if((Integer.parseInt(sdfMM.format(d))>=8&&Integer.parseInt(sdfMM.format(d))<=11)&&numeroDeCiclo==2){
                    calendarioReportes.add(cpc.get(i));
                }
            }
        }
        return calendarioReportes;
    }
    
    //segunda version :v
    
    CicloEscolarDelegate cicloEscolarDelegate=new CicloEscolarDelegate();
    ConfiguracionDelegate configuracionDelegate=new ConfiguracionDelegate();
    CalendarioreporteDelegate calendarioReporteDelegate=new CalendarioreporteDelegate();
    
    public void obtenerCalendarios(){
        Calendar calendar=Calendar.getInstance(); //Obtiene la fecha actual del sistema
        fechaActual=new Date(109, 7, 28); //Para fecha de prueba //comentar o borrar
        //fechaActual=calendar.getTime(); //Para la fecha actual //Descomentar
        
        List <Cicloescolar> cicloescolars=new ArrayList<Cicloescolar>();
        cicloescolars=cicloEscolarDelegate.getListaCicloEscolar();
        
        SimpleDateFormat sdfMM=new SimpleDateFormat("MM");
        SimpleDateFormat sdfYYYY=new SimpleDateFormat("YYYY");
        //Se arma el ciclo escolar respecto a la fecha actual//Creo que no es necesario consultar la BD
        int year=Integer.parseInt(sdfYYYY.format(fechaActual));
        int mes=Integer.parseInt(sdfMM.format(fechaActual));
        System.out.println(fechaActual);
        int numeroDeCiclo=0;
        if(mes>=2&&mes<=5)
            numeroDeCiclo=1;
        if(mes>=8&&mes<=11)
            numeroDeCiclo=2;
        //----
        int idCiclo=0;
        System.out.println(year+" "+mes+" "+numeroDeCiclo);
        List<Calendarioreporte> cpc=new ArrayList<>();
        for(int i=0; i<cicloescolars.size(); i++){//ciclo para dar sacar los CR respecto al ciclo
            if(year==Integer.parseInt(cicloescolars.get(i).getCescicloEscolar().substring(0,4))&&numeroDeCiclo==Integer.parseInt(cicloescolars.get(i).getCescicloEscolar().substring(5))){
                idCiclo=cicloescolars.get(i).getCesid();
                System.out.println(" asdasdasdazsdasdasdasdsasdsasdsadsa "+idCiclo);
            }
        }
        Configuracion configuracion=new Configuracion();
        configuracion=configuracionDelegate.bConfPorCiclo(idCiclo);
        
        List <Calendarioreporte> calendarioreportes=calendarioReporteDelegate.getCon_cre(configuracion.getConid());
        for(int i=0; i<calendarioreportes.size(); i++){
            System.out.println(calendarioreportes.get(i).getCrefechaCorte()+" - "+calendarioreportes.get(i).getCrefechaLimite());
        }
    }
    
    public void run(){
        enviarCorreo();
    }
    
}