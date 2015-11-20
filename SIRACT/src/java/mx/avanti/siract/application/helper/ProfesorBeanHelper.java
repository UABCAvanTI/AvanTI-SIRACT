/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.siract.application.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import mx.avanti.siract.business.CicloEscolarDelegate;
import mx.avanti.siract.business.ProfesorDelegate;
import mx.avanti.siract.business.ProgramaEducativoDelegate;
import mx.avanti.siract.business.RolDelegate;
import mx.avanti.siract.business.UnidadAcademicaDelegate;
import mx.avanti.siract.business.UsuarioDelegate;
import mx.avanti.siract.business.entity.Profesor;
import mx.avanti.siract.business.entity.Programaeducativo;
import mx.avanti.siract.business.entity.Rol;
import mx.avanti.siract.business.entity.Unidadacademica;
import mx.avanti.siract.business.entity.Usuario;
import mx.avanti.siract.ui.LoginBean;


/**
 *
 * @author balta
 */
public class ProfesorBeanHelper implements Serializable {

    private LoginBean loginBeanUI;

    private RolDelegate rolDelegate;
    private ProfesorDelegate profesorDeleagate;
    private UsuarioDelegate usuarioDelegate;
//    private PuestoDelegate puestoDelegate;
//    private ProfesorTienePuestoDelegate profesorTienePuestoDelegate;
    private ProgramaEducativoDelegate programaEducativoDelegate;
    private UnidadAcademicaDelegate unidadAcademicaDelegate;

    private Rol rol;
    private Profesor profesor;
    private Profesor profesor2;
    private Profesor selecProfesor;
    private Usuario usuario;
    private Usuario usuario2;
//    private Puesto puesto;
//    private ProfesorTienePuesto profesorTienePuesto;
//    private ProfesorTienePuestoId profesorTienePuestoId;
    private Programaeducativo selectPE;
    private Programaeducativo programaEducativo;
    private Programaeducativo programaEducativo2;
  //  private Responsableprogramaeducativo responsableProgramaEducativo;
    private Unidadacademica unidadAcademica;

    private List<Profesor> listaFiltrada;
    private List<Profesor> listaFiltrada2;
    private List<Profesor> listaSeleccionProfesores;
//    private List<Puesto> listaPuesto;
    private List<Programaeducativo> listaProgramaEducativo;
    private List<Programaeducativo> listaProgEduc;
    private List<Programaeducativo> listaPEMod;
//    private List<Responsableprogramaeducativo> listaREP;
    private List<Unidadacademica> listaUnidadAcademica;
    private List<Rol> listaRol;

    private List<String> listaSeleccionPuesto;
    private List<String> listaSeleccionPE;
    private List<String> listaPE;

    private boolean banNumEmp;
    private boolean banNom;
    private boolean banAPaterno;
    private boolean banAMaterno;
    private boolean banRFC;
    private boolean banUs;


    private String Mensaje;
    private String rolSeleccionado;
    private String ocultarLista;
    
    Unidadacademica auxUA = null;    

    public ProfesorBeanHelper() {
        try {
            this.profesorDeleagate = new ProfesorDelegate();
            this.usuarioDelegate = new UsuarioDelegate();
//            this.puestoDelegate = new PuestoDelegate();
            this.programaEducativoDelegate = new ProgramaEducativoDelegate();
            this.unidadAcademicaDelegate = new UnidadAcademicaDelegate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        profesor = new Profesor();
        profesor.setProid(0);
        selecProfesor = new Profesor();
        usuario = new Usuario();
        usuario2 = new Usuario();
//        puesto = new Puesto();
        selectPE = new Programaeducativo();
        programaEducativo = new Programaeducativo();
        programaEducativo.setPedid(0);
        unidadAcademica = new Unidadacademica();
//        responsableProgramaEducativo = new Responsableprogramaeducativo();
        listaPE = new ArrayList<String>();

    }

    public ProfesorBeanHelper(Profesor profesor) {

    }

    public void setListaProgEduc(List<Programaeducativo> listaProgEduc) {
        this.listaProgEduc = listaProgEduc;
    }

//    public void setListaREP(List<Responsableprogramaeducativo> listaREP) {
//        this.listaREP = listaREP;
//    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public void setRolSeleccionado(String rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

    public LoginBean getLoginBeanUI() {
        return loginBeanUI;
    }

    public void setLoginBeanUI(LoginBean loginBeanUI) {
        this.loginBeanUI = loginBeanUI;
    }

    public List<Programaeducativo> getListaProgramaEducativo() {
        return listaProgramaEducativo;
    }

    public void setListaProgramaEducativo(List<Programaeducativo> listaProgramaEducativo) {
        this.listaProgramaEducativo = listaProgramaEducativo;
    }

//    public List<Puesto> getListaPuesto() {
//        listaPuesto = puestoDelegate.getListaPuesto();
//        return listaPuesto;
//    }
//
//    public void setListaPuesto(List<Puesto> listaPuesto) {
//        this.listaPuesto = listaPuesto;
//    }

    public Unidadacademica getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(Unidadacademica unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public UnidadAcademicaDelegate getUnidadAcademicaDelegate() {
        return unidadAcademicaDelegate;
    }

    public void setUnidadAcademicaDelegate(UnidadAcademicaDelegate unidadAcademicaDelegate) {
        this.unidadAcademicaDelegate = unidadAcademicaDelegate;
    }

    public List<String> getListaSeleccionPuesto() {
        return listaSeleccionPuesto;
    }

    public void setListaSeleccionPuesto(List<String> listaSeleccionPuesto) {
        this.listaSeleccionPuesto = listaSeleccionPuesto;
    }

    public List<String> getListaSeleccionPE() {
        return listaSeleccionPE;
    }

    public void setListaSeleccionPE(List<String> listaSeleccionPE) {
        this.listaSeleccionPE = listaSeleccionPE;
    }

    public List<Profesor> getListaSeleccionProfesores() {
        return listaSeleccionProfesores;
    }

    public void setListaSeleccionProfesores(List<Profesor> listaSeleccionProfesores) {
        this.listaSeleccionProfesores = listaSeleccionProfesores;
    }

//    public PuestoDelegate getPuestoDelegate() {
//        return puestoDelegate;
//    }
//
//    public void setPuestoDelegate(PuestoDelegate puestoDelegate) {
//        this.puestoDelegate = puestoDelegate;
//    }

//    public Puesto getPuesto() {
//        return puesto;
//    }
//
//    public void setPuesto(Puesto puesto) {
//        this.puesto = puesto;
//    }

    public ProfesorDelegate getProfesorDeleagate() {
        profesor.setUsuario(usuario2);
        return profesorDeleagate;
    }

//    public void setProfesorDeleagate(ProfesorDelegate profesorDeleagate) {
//        this.profesorDeleagate = profesorDeleagate;
//    }
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor2() {
        return profesor2;
    }

    public void setProfesor2(Profesor profesor2) {
        this.profesor2 = profesor2;
    }

    public Programaeducativo getSelectPE() {
        return selectPE;
    }

    public void setSelectPE(Programaeducativo selectPE) {
        this.selectPE = selectPE;
    }

    public Profesor getSelecProfesor() {
        return selecProfesor;
    }

    public void setSelecProfesor(Profesor selecProfesor) {
        this.selecProfesor = selecProfesor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public UsuarioDelegate getUsuarioDelegate() {
        return usuarioDelegate;
    }

    public void setUsuarioDelegate(UsuarioDelegate usuarioDelegate) {
        this.usuarioDelegate = usuarioDelegate;
    }

    public void setListaFiltrada(List<Profesor> listaFiltrada) {
        this.listaFiltrada = listaFiltrada;
    }

    public void setListaFiltrada2(List<Profesor> listaFiltrada2) {
        this.listaFiltrada2 = listaFiltrada2;
    }

    public List<Profesor> getListaFiltrada2() {
        return listaFiltrada2;
    }

    public ProgramaEducativoDelegate getProgramaEducativoDelegate() {
        return programaEducativoDelegate;
    }

    public void setProgramaEducativoDelegate(ProgramaEducativoDelegate programaEducativoDelegate) {
        this.programaEducativoDelegate = programaEducativoDelegate;
    }

    public Programaeducativo getProgramaEducativo() {
        return programaEducativo;
    }

    public void setProgramaEducativo(Programaeducativo programaEducativo) {
        this.programaEducativo = programaEducativo;
    }

    public Programaeducativo getProgramaEducativo2() {
        return programaEducativo2;
    }

    public void setProgramaEducativo2(Programaeducativo programaEducativo2) {
        this.programaEducativo2 = programaEducativo2;
    }

    public List<Programaeducativo> getListaPEMod() {
        return listaPEMod;
    }

    public void setListaPEMod(List<Programaeducativo> listaPEMod) {
        this.listaPEMod = listaPEMod;
    }

//    public Responsableprogramaeducativo getResponsableProgramaEducativo() {
//        return responsableProgramaEducativo;
//    }
//
//    public void setResponsableProgramaEducativo(Responsableprogramaeducativo responsableProgramaEducativo) {
//        this.responsableProgramaEducativo = responsableProgramaEducativo;
//    }

    public List<String> getListaPE() {
        return listaPE;
    }

    public void setListaPE(List<String> listaPE) {
        this.listaPE = listaPE;
    }

    public List<Unidadacademica> getListaUnidadAcademica() {
        return listaUnidadAcademica;
    }

    public void setListaUnidadAcademica(List<Unidadacademica> listaUnidadAcademica) {
        this.listaUnidadAcademica = listaUnidadAcademica;
    }

    public String getOcultarLista() {
        return ocultarLista;
    }

    public void setOcultarLista(String ocultarLista) {
        this.ocultarLista = ocultarLista;
    }

    /*Metodo de filtrado inicia*/
    public List<Profesor> filtrado(String campo, String busqueda) {
        String CambioBus = busqueda.toLowerCase();
        String CambioObj = "";
        filtrarPE();
        getUsuarioTienePE();

//        listaFiltrada = profesorDeleagate.getListaProfesor();
//        for (Profesor prof : listaFiltrada) {
//            prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
//        }
        if (busqueda.trim().length() > 0) {
            listaFiltrada2.clear();

            for (Profesor prof : listaFiltrada) {
                //prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
                CambioObj = prof.getPronombre().toLowerCase();
                if (CambioObj.startsWith(CambioBus)) {
                    listaFiltrada2.add(prof);
                } else {
                    CambioObj = prof.getProapellidoPaterno().toLowerCase();
                    if (CambioObj.startsWith(CambioBus)) {
                        listaFiltrada2.add(prof);
                    } else {

                        String CambioObjNum = Integer.toString(prof.getPronumeroEmpleado());
                        if (CambioObjNum.startsWith(busqueda)) {
                            listaFiltrada2.add(prof);
                        } else {
                            //cambios realizados por Marco
                            CambioObj = prof.getUsuario().getUsuusuario().toLowerCase();
                            if(CambioObj.startsWith(CambioBus)){
                                listaFiltrada2.add(prof);
                            }
                            
                        }
                    }
                }
            }
        } else {
            listaFiltrada2 = listaFiltrada;
        }
        return listaFiltrada2;
    }

    public void getUsuarioTienePE() {
        System.out.println(rolSeleccionado);
        System.out.println("profesorFromUser: " + profesorDeleagate.findProfesorFromUser(usuario.getUsuid()).getProid());
        listaProgramaEducativo = programaEducativoDelegate.getListaProgramaEducativo();
        try {
            listaProgramaEducativo.clear();
        } catch (NullPointerException e) {

        }
        //System.out.println("Role menu: " + loginBeanUI.getRoles().size());
//        System.out.println("Map roles: " + loginBeanUI.getRolMenu());   
//        System.out.println("Rol: " + loginBeanUI.getRol());
//        List<Rol> list = null;
//        System.out.println("Roles: " + loginBeanUI.getRoles().get(0));

        //System.out.println("variable R: " + loginBeanUI.() + "\n variable Rol: " + loginBeanUI.getRol());
//        //if(getLoginBeanUI().getRoles() ==  getUsuario().getUsuusuario()){
        //listaUsuario = usuarioDelegate.getListaUsuario();}
        //rolMenu esta vacio
        if (rolSeleccionado.equalsIgnoreCase("Director") || rolSeleccionado.equalsIgnoreCase("Subdirector") || rolSeleccionado.equalsIgnoreCase("Administrador")) {
            ocultarLista = "true";
            System.out.println("sout 1");
            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            System.out.println("sout 2");
            //a partir de aqui aparece NullPointer
            listaUnidadAcademica = unidadAcademicaDelegate.getProfUAC(profesor2.getProid()); // guardar en objeto lista de unidadAcademica
            System.out.println("sout 3");
            listaProgEduc = programaEducativoDelegate.getListaProgramaEducativo();
            System.out.println("sout 4");
            for (Unidadacademica uac : listaUnidadAcademica) {
                System.out.println("sout 5");
                for (Programaeducativo pe : listaProgEduc) {
                    System.out.println("sout 6");
                    if (uac.getUacid() == pe.getUnidadacademica().getUacid()) {
                        System.out.println("sout 7");
                        //aqui da NullPointer
                        listaProgramaEducativo.add(pe);
                        System.out.println("sout exitoso");
                    }
                }
//                System.out.println("sout 4");
//                    System.out.println("sout 5");
//                        listaProgramaEducativo = programaEducativoDelegate.getPEUAC(uac.getUacid());
//                        System.out.println("sout 6");
            }

        } else if(rolSeleccionado.equalsIgnoreCase("Responsable de Programa Educativo")
                ||rolSeleccionado.equalsIgnoreCase("Coordinador de Formación Básica")){
//            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            listaProgramaEducativo.add(programaEducativoDelegate.findProgramaEducativoById(programaEducativoDelegate.getResponsablePE(profesor2.getProid()).get(0).getPedid()));
            
            listaFiltrada = profesorDeleagate.getProfPE(listaProgramaEducativo.get(0).getPedid());
            
            for (Profesor prof : listaFiltrada) {
                prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
            }            
//            for(Profesor prof : profesor2)
//            listaProgramaEducativo = programaEducativoDelegate.getPEdeResponsable(profesor2.getProid());
//            if(profesor2.getProid() == responsableProgramaEducativo.getProfesor().getProid()){
//                listaProgramaEducativo.add(programaEducativoDelegate.findProgramaEducativoById(responsableProgramaEducativo.getProgramaeducativo().getPedid()));
//            }
        } else{            
                if(rolSeleccionado.equalsIgnoreCase("Coordinador de Área de Conocimiento")){
                    //profesor = profesorDelegate.findProfesorFromUser(usuario.getUsuid());
                    listaProgramaEducativo = programaEducativoDelegate.getPEdeCoordinadorAreaAdmin(profesor2.getProid());
                    listaFiltrada = profesorDeleagate.getProfPE(listaProgramaEducativo.get(0).getPedid());
                    for (Profesor prof : listaFiltrada) {
                prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
            }  
                }
            ocultarLista = "false";
        }

    }

//    public List<Profesor> filtrarPE(){
//        listaFiltrada.clear();
//        
//        if(getProgramaEducativo().getPedid() == 0){
//            listaFiltrada = profesorDeleagate.getListaProfesor();
//            for(Profesor prof : listaFiltrada){
////                prof.setProgramaeducativos();
//            }
//        }
//        else{
//            if(getProgramaEducativo().getPedid() >= 1){
//                for(Profesor prof : profesorDeleagate.getListaProfesor()){
//                    //if(prof.getProgramaeducativos().)
//                }
//            }
//        }
//        return listaFiltrada;
//    }
    public void seleccionarRegistro() {
        for (Profesor prof : profesorDeleagate.getListaProfesor()) {
            if (prof.getProid().equals(selecProfesor.getProid())) {
                profesor = prof;
                usuario2 = usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid());
                getPEModProf(profesor);
            }
        }
    }

    public void eliminarDeLista(int id) {
        for (Profesor prof : listaSeleccionProfesores) {
            if (prof.getProid().equals(id)) {
                int index = listaSeleccionProfesores.indexOf(prof);
                listaSeleccionProfesores.remove(index);
                break;
            }
        }
        for (Profesor prof : listaSeleccionProfesores) {
            System.out.println(prof.getPronombre());
        }

    }

    public String Validar() {
        banNumEmp = true;
        banNom = true;
        banAPaterno = true;
        banAMaterno = true;
        banRFC = true;
        banUs = true;
        Mensaje = "";
        for (Profesor prof : profesorDeleagate.getListaProfesor()) {
            //Compara el campo de numero de empleado y al mismo tiempo una bandera para que 
            //en caso de que encuentre un registro identico no lo vuelva a comparar(esto aplica en los 
            //casos de que se repita un apellido o un nombre)
            if (prof.getPronumeroEmpleado() == profesor.getPronumeroEmpleado() && banNumEmp == true
                    && !prof.getProid().equals(profesor.getProid())) {
                Mensaje = Mensaje + " Numero de Empleado, ";
                banNumEmp = false;
            }
            if (prof.getPronombre().equals(profesor.getPronombre()) && banNom == true
                    && !prof.getProid().equals(profesor.getProid())) {
                banNom = false;
                if (prof.getProapellidoPaterno().equals(profesor.getProapellidoPaterno()) && banAPaterno == true) {
                    banAPaterno = false;
                    if (prof.getProapellidoMaterno().equals(profesor.getProapellidoMaterno()) && banAMaterno == true) {
                        Mensaje = Mensaje + "Nombre, ";
                        Mensaje = Mensaje + "Apellido Paterno, ";
                        Mensaje = Mensaje + "Apellido Materno, ";
                        banAMaterno = false;
                    }
                }
            }
            if (prof.getProrfc().equals(profesor.getProrfc()) && banNumEmp == true
                    && !prof.getProid().equals(profesor.getProid())) {
                Mensaje = Mensaje + "RFC, ";
                banNumEmp = false;
            }
            if (prof.getUsuario().getUsuid().equals(usuario2.getUsuid()) && banUs == true
                    && !prof.getProid().equals(profesor.getProid())) {
                Mensaje = Mensaje + "Usuario";
                banUs = false;
            }
        }
        return Mensaje;
    }

//    public List<Puesto> consultapuestos() {//Ciclo para consultar los roles
//        List<Puesto> puestos;//creacion de lista rols
//        List<Puesto> puestos2 = new ArrayList<Puesto>();//creacion de lista rols2
//        puestos = puestoDelegate.getListaPuesto();//Consulta de roles en la tabla rol
//        for (int i = 0; i < listaSeleccionPuesto.size(); i++) {  //recorriendo el tamaÃ±o para obtener el dato de rols en cada posicion      
//            for (int x = 0; x < puestos.size(); x++) {// recorriendo el tamaÃ±o de rols
//                if (puestos.get(x).getPuepuesto().equals(listaSeleccionPuesto.get(i))) {//comparacion para saber cuales roles son los que se elijen para agregar 
//                    puestos2.add(puestos.get(x));//se prepara el objeto rols2 con la informacion de los datos que se mandaron
//                }
//            }
//        }
//        return puestos2;//regresado rols2
//    }

    public List<Programaeducativo> consultaPE() {
        List<Programaeducativo> pe;
        List<Programaeducativo> pe2 = new ArrayList<Programaeducativo>();
        pe = programaEducativoDelegate.getListaProgramaEducativo();
        for (int i = 0; i < listaSeleccionPE.size(); i++) {
            for (int x = 0; x < pe.size(); x++) {
                if (pe.get(x).getPednombre().equals(listaSeleccionPE.get(i))) {
                    pe2.add(pe.get(x));
                }
            }
        }
        return pe2;
    }

//    public void asignarPuestos(HashSet puestos) {
//        for (String puestosProfesor : listaSeleccionPuesto) {
//            for (Puesto puesto : listaPuesto) {
//                if (puesto.getPuepuesto().equalsIgnoreCase(puestosProfesor)) {
//                    System.out.println("ID's de los puestos que se van a asignar:" + puesto.getPueid());
//                    profesorTienePuestoId = new ProfesorTienePuestoId();
//                    profesorTienePuestoId.setPuestoPueid(puesto.getPueid());
//                    profesorTienePuestoId.setProfesorProid(profesor.getProid());
//
//                    profesorTienePuesto.setPuesto(puesto);
//                    profesorTienePuesto.setProfesor(profesor);
//                    profesorTienePuesto.setId(profesorTienePuestoId);
//
//                    profesorTienePuestoDelegate.saveProfesorTienePuesto(profesorTienePuesto);
//                }
//            }
//        }
//    }

    public void seleccionarPE() {
        for (Profesor prof : profesorDeleagate.getListaProfesor()) {
            if (prof.getProid().equals(selecProfesor.getProid())) {
                profesor = prof;
                usuario = usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid());
            }
        }
    }

    public List<String> getListaProgramaEducativo(Profesor p) {
        List<Programaeducativo> recibe = null;

        listaPE = new ArrayList();
        try{
        if (p.getProid() > 0) {
            recibe = programaEducativoDelegate.getPEProf(p.getProid());
            for (Programaeducativo pe : recibe) {
                listaPE.add(pe.getPednombre());
            }
            
        } else {
            recibe = consultaPE();

        }
        }catch(NullPointerException e){
            
        }
        return listaPE;
    }

    public void filtrarPE() {
        try {
            listaFiltrada.clear();
        } catch (NullPointerException e) {
//            System.out.println("");
        }

//        if(getProgramaEducativo().getPedid() == null){
//            listaFiltrada = profesorDeleagate.getListaProfesor();            
//        }
        if (getProgramaEducativo().getPedid() == 0) {
            //listaFiltrada = profesorDeleagate.getListaProfesor();
            profesor2 = profesorDeleagate.findProfesorFromUser(usuario.getUsuid());
            System.out.println(profesor2.getPronombre() + profesor2.getProid());
            //Metodopara traer profesores de facultad actual
            System.out.println("ID DE PROFESOR" + profesor2.getProid());

                System.out.println("UA encontrado: " + unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0).getUacnombre());

                auxUA = unidadAcademicaDelegate.getProfUAC(profesor2.getProid()).get(0);
            if (auxUA == null || auxUA.getUacid() == null) {
                System.out.println("NO SE ENCONTRO UNIDAD ACADEMICA PARA ");
            }
            listaFiltrada = profesorDeleagate.getProfMismoPE(auxUA.getUacid());

        } else {
            listaFiltrada = profesorDeleagate.getProfPE(programaEducativo.getPedid());
        }
        for (Profesor prof : listaFiltrada) {
            prof.setUsuario(usuarioDelegate.findUsuarioById(prof.getUsuario().getUsuid()));
        }
    }
    
    public void asignarUA(){
        HashSet setUA = new HashSet();
        setUA.add(auxUA);
        profesor.setUnidadacademicas(setUA);
    }
    
//    public List<String> getListaPuesto(Profesor prof) {
//        List<Puesto> recibe = null;//Creacion de recibe 
//        // LoginBean_2 logBean = new LoginBean_2();
//        listaSeleccionPuesto = new ArrayList();
//        try{
//        if (prof.getProid() > 0) {
////            System.out.println("El usuario es: " + u.getUsuusuario());
//            recibe = puestoDelegate.getPuestoProf(prof.getProid());
//            for (Puesto usu : recibe) {
////                System.out.println("Estos son los roles del usuario? " + usu.getRoltipo());
//                listaSeleccionPuesto.add(usu.getPuepuesto());
//            }
//        } else {
//            recibe = consultapuestos();
////            for (Rol usu : recibe) {
//////                System.out.println("Estos son los roles del usuario? " + usu.getRoltipo());
////            }
//        }
//        }catch(NullPointerException e){
//            
//        }
//        return listaSeleccionPuesto;
//    }

    public List<String> getListaPE(Profesor prof) {
        List<Programaeducativo> recibe = null;
        listaSeleccionPE = new ArrayList();
        if (prof.getProid() > 0) {
            recibe = programaEducativoDelegate.getPEProf(prof.getProid());
            for (Programaeducativo pe : recibe) {
                listaSeleccionPE.add(pe.getPednombre());
            }
        } else {
            //recibe = consultaPE();
        }
        return listaSeleccionPE;
    }    
    
    public void getPEModProf(Profesor p){
        System.out.println("id del profesor en getPEModProg " + p.getProid());
        listaPEMod = programaEducativoDelegate.getPEProf(p.getProid());
        for(Programaeducativo pe : listaPEMod){
            System.out.println("nombre del PE " + pe.getPednombre());
            setProgramaEducativo2(programaEducativoDelegate.findProgramaEducativoById(pe.getPedid()));
            
        }    
    }
}