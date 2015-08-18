/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.List;
import mx.avanti.siract.business.AreaAdministrativaDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.entity.Areaadministrativa;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;

/**
 *
 * @author Paco
 */
public class AreaAdministrativaBeanHelper implements Serializable {
    
    private AreaAdministrativaDelegate areaAdministrativaDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;
    private ProfesorDelegate profesorDelegate;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;
    
    private Areaadministrativa areaAdministrativa;
    private Programaeducativo programaEducativo;
    private Areaadministrativa seleccionAreaad;
    private Usuario usuariolog;
    private Profesor profesorLog;

    private List<Areaadministrativa> listaFiltrada;    
    private List<Programaeducativo> listaPEdlg;
    private List<Areaadministrativa> listaSeleccion;
    private List<Unidadacademica> listaUnidadAcademica;
    //private List<Programaeducativo> listaPEconsulta;
    
    private String Mensaje;
    private String rol;
    private String ocultarPE;
    
    public AreaAdministrativaBeanHelper() {
        this.areaAdministrativaDelegate = new AreaAdministrativaDelegate();
        programaEducativoDelegate = new ProgramaEducativoDelegate();
        profesorDelegate = new ProfesorDelegate();
        unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        
        areaAdministrativa = new Areaadministrativa(new Programaeducativo(), "");
        programaEducativo = new Programaeducativo();
        programaEducativo.setPedid(0);
        seleccionAreaad = new Areaadministrativa(new Programaeducativo(), "");
        usuariolog = new Usuario();
    }   
    
    //<editor-fold defaultstate="collapsed" desc="getter setter">
    
    public AreaAdministrativaDelegate getAreaAdministrativaDelegate() {
        return areaAdministrativaDelegate;
    }

    public void setAreaAdministrativaDelegate(AreaAdministrativaDelegate areaAdministrativaDelegate) {
        this.areaAdministrativaDelegate = areaAdministrativaDelegate;
    }

    public List<Areaadministrativa> getListaFiltrada() {
        return listaFiltrada;
    }

    public void setListaFiltrada(List<Areaadministrativa> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public Areaadministrativa getAreaAdministrativa() {
        return areaAdministrativa;
    }

    public void setAreaAdministrativa(Areaadministrativa areaAdministrativa) {
        this.areaAdministrativa = areaAdministrativa;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public List<Programaeducativo> getListaPEdlg() {
        return listaPEdlg;
    }

    public void setListaPEdlg(List<Programaeducativo> listaPEdlg) {
        this.listaPEdlg = listaPEdlg;
    }

    public List<Areaadministrativa> getListaSeleccion() {
        return listaSeleccion;
    }

    public void setListaSeleccion(List<Areaadministrativa> listaSeleccion) {
        this.listaSeleccion = listaSeleccion;
    }

    public Areaadministrativa getSeleccionAreaad() {
        return seleccionAreaad;
    }

    public void setSeleccionAreaad(Areaadministrativa seleccionAreaad) {
        this.seleccionAreaad = seleccionAreaad;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuariolog() {
        return usuariolog;
    }

    public void setUsuariolog(Usuario usuariolog) {
        this.usuariolog = usuariolog;
    }

    public String getOcultarPE() {
        return ocultarPE;
    }

    public void setOcultarPE(String ocultarPE) {
        this.ocultarPE = ocultarPE;
    }
    
//</editor-fold>
    
      public void seleccionarRegistro() {
          areaAdministrativa = areaAdministrativaDelegate.findAreaAdById(seleccionAreaad.getAadid());
          programaEducativo = programaEducativoDelegate.findProgramaEducativoById(areaAdministrativa.getProgramaeducativo().getPedid());
        
    }
      
    public List<Areaadministrativa> filtrado(String busqueda) {
        getUsuarioTienePE();
        String CambioBus = busqueda.toLowerCase();
        String CambioObj = "";
        //listaFiltrada = areaAdministrativaDelegate.consultarAreaAdministrativa();
        if(programaEducativo.getPedid().equals(0)){
            listaFiltrada = areaAdministrativaDelegate.consultarAreaAdministrativa();
        }else{
            listaFiltrada = areaAdministrativaDelegate.getAreaAdbyPE(programaEducativo.getPedid());
        }
        List<Areaadministrativa> listaFiltrada2 = areaAdministrativaDelegate.getAreaAdbyPE(programaEducativo.getPedid());
        if (busqueda.trim().length() > 0) {
            listaFiltrada2.clear();
            for (Areaadministrativa aad : listaFiltrada) {
                //prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
                CambioObj = aad.getAadnombre().toLowerCase();
                if (CambioObj.startsWith(CambioBus)) {
                    listaFiltrada2.add(aad);
                } 
            }
        }else{
            listaFiltrada2=listaFiltrada;
        }
        for (Areaadministrativa aad : listaFiltrada) {
            aad.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(aad.getProgramaeducativo().getPedid()));
        }
        return listaFiltrada2;
    }
    
    public void cargarListaPEdlg(){
        if(rol.equalsIgnoreCase("Responsable de Programa Educativo")||rol.equalsIgnoreCase("Coordinador de Formación Básica")){
            areaAdministrativa.setProgramaeducativo(programaEducativo);
        }else{            
            listaPEdlg = programaEducativoDelegate.getListaProgramaeducativo();
        }
    }
    
    public String Validar() {
        Mensaje = "";
        for (Areaadministrativa aad : areaAdministrativaDelegate.consultarAreaAdministrativa()) {
            if (aad.getAadnombre().endsWith(areaAdministrativa.getAadnombre() )
                    && aad.getProgramaeducativo().getPedid().equals(programaEducativo.getPedid())
                    && !aad.getAadid().equals(areaAdministrativa.getAadid())) {
                Mensaje = Mensaje + "Esta Area Administrativa ya existe en este Programa Educativo";
                break;
            }
            
        }
        return Mensaje;
    }

    public void eliminarDeLista(int id) {
        for (Areaadministrativa aad : listaSeleccion) {
            if (aad.getAadid().equals(id)) {
                int index = listaSeleccion.indexOf(aad);
                listaSeleccion.remove(index);
                break;
            }
        }

    }
    
    public void getUsuarioTienePE() {
        listaPEdlg = programaEducativoDelegate.getListaProgramaEducativo();
        try {
            listaPEdlg.clear();
        } catch (NullPointerException e) {

        }
        if (rol.equalsIgnoreCase("Director") || rol.equalsIgnoreCase("Subdirector") || rol.equalsIgnoreCase("Administrador")) {
            ocultarPE = "true";
            profesorLog = profesorDelegate.findProfesorFromUser(usuariolog.getUsuid());
            //a partir de aqui aparece NullPointer
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesorLog.getProid()); // guardar en objeto lista de unidadAcademica
            //listaProgEduc = programaEducativoDelegate.getListaProgramaEducativo();
            for (Unidadacademica uac : listaUnidadAcademica) {
                for (Programaeducativo pe : programaEducativoDelegate.getListaProgramaEducativo()) {
                    if (uac.getUacid().equals( pe.getUnidadacademica().getUacid())) {
                        //aqui da NullPointer
                        listaPEdlg.add(pe);
                    }
                }
            }

        } else{
            if(rol.equalsIgnoreCase("Responsable de Programa Educativo")){
//            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
                
            profesorLog = profesorDelegate.findProfesorFromUser(usuariolog.getUsuid());
            programaEducativo = programaEducativoDelegate.getResponsablePE(profesorLog.getProid());
            listaPEdlg.add(programaEducativoDelegate.findProgramaEducativoById(programaEducativo.getPedid()));
            listaFiltrada = areaAdministrativaDelegate.getAreaAdbyPE(listaPEdlg.get(0).getPedid());
            
            for (Areaadministrativa aad : listaFiltrada) {
                aad.setProgramaeducativo(programaEducativoDelegate.findProgramaEducativoById(aad.getProgramaeducativo().getPedid()));
            }          
        } 
            ocultarPE = "false";
        
    }

    }
    

}
