/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.avanti.siract.business.AreaConocimientoDelegate;
import mx.avanti.siract.business.ConsultaDelegate;
import mx.avanti.siract.business.PlanEstudioDelegate;
import mx.avanti.siract.business.PracticasLaboratorioDelegate;
import mx.avanti.siract.business.PracticasTallerDelegate;
import mx.avanti.siract.business.SubtemaunidadDelegate;
import mx.avanti.siract.business.TemaunidadDelegate;
import mx.avanti.siract.business.UnidadAprendizajeDelegate;
import mx.avanti.siract.business.UnidadDelegate;
import mx.avanti.siract.business.entity.Areaconocimiento;
import mx.avanti.siract.business.entity.Planestudio;
import mx.avanti.siract.business.entity.Practicalaboratorio;
import mx.avanti.siract.business.entity.Practicataller;
import mx.avanti.siract.business.entity.Subtemaunidad;
import mx.avanti.siract.business.entity.Temaunidad;
import mx.avanti.siract.business.entity.Unidad;
import mx.avanti.siract.business.entity.Unidadaprendizaje;
import mx.avanti.siract.ui.CatalogoCTBeanUI;

/**
 *
 * @author Y
 */
@ManagedBean
@ViewScoped
public class UtilesBean implements Serializable {

    public List<String> mensajes = new ArrayList();

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public void arreglarPorcentajes() {
        UnidadAprendizajeDelegate unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
        UnidadDelegate unidadDelegate = new UnidadDelegate();
        TemaunidadDelegate temaunidadDelegate = new TemaunidadDelegate();
        SubtemaunidadDelegate subtemaunidadDelegate = new SubtemaunidadDelegate();
        ConsultaDelegate consultaDelegate = new ConsultaDelegate();
        PracticasLaboratorioDelegate practicasLaboratorioDelegate = new PracticasLaboratorioDelegate();
        PracticasTallerDelegate practicasTallerDelegate = new PracticasTallerDelegate();
        CatalogoCTBeanUI catalogoCTBeanUI = new CatalogoCTBeanUI();

        for (Unidadaprendizaje ua : unidadAprendizajeDelegate.getListaUnidadAprendizaje()) {
            //RECORRIENDO TODAS LAS UNIDADES DE APRENDIZAJE CON UA
            int horasClase = ua.getUaphorasClase() * 16;
            int horasTaller = ua.getUaphorasTaller() * 16;
            int horasLaboratorio = ua.getUaphorasLaboratorio() * 16;

            for (Unidad u : unidadDelegate.consultaUnidades("unidadaprendizaje.uapclave", String.valueOf(ua.getUapclave()))) {
                float horasUnidad = u.getUnihoras();
                if (horasClase == 0) {
                    mensajes.add("La unidad de aprendizaje " + ua.getUapnombre() + " tiene 0 hras CLASE pero intenta agregar " + u.getUninombre());
                    System.out.println("LA UNIDAD DE APRENDIZAJE " + ua.getUapnombre() + " TIENE 0 HRAS CLASE PERO INTENTA AGREGAR" + u.getUninombre());
                } else {
                    for (Temaunidad tu : temaunidadDelegate.getListaTemaunidadsFromWhere("unidad", "uniid", u.getUniid().toString(), "tunnumero")) {
                        float horasTema = tu.getTunhoras();
                        tu.setTunhoras(catalogoCTBeanUI.formatoAHoras(catalogoCTBeanUI.horasAFormato(tu.getTunhoras())));

                        for (Subtemaunidad s : subtemaunidadDelegate.listaTemasUnidadDe("temaunidad", "tunid", tu.getTunid().toString(), "sutnumero")) {
                            s.setSuthoras(catalogoCTBeanUI.formatoAHoras(catalogoCTBeanUI.horasAFormato(s.getSuthoras())));
                            s.setSutvalorPorcentaje(s.getSuthoras() * 100 / horasClase);
                            subtemaunidadDelegate.agregarSubtemaunidad(s);
                        }
                        tu.setTunvalorPorcentaje(horasTema * 100 / horasClase);
                        temaunidadDelegate.agregarTemaunidad(tu);
                    }

                    u.setUnivalorPorcentaje(horasUnidad * 100 / horasClase);
                    unidadDelegate.agregarUnidad(u);
                }
            }

            for (Practicalaboratorio pl : practicasLaboratorioDelegate.consultarPracticas("unidadaprendizaje.uapclave", String.valueOf(ua.getUapclave()))) {
                if (horasLaboratorio == 0) {
                    mensajes.add("La unidad de aprendizaje " + ua.getUapnombre() + " tiene 0 hras LABORATORIO pero intenta agregar " + pl.getPrlnombre());
                    System.out.println("LA UNIDAD DE APRENDIZAJE " + ua.getUapnombre() + " TIENE 0 HRAS LABORATORIO PERO INTENTA AGREGAR" + pl.getPrlnombre());
                } else {
                    pl.setPrlvalorPorcentaje(pl.getPrlhoras() * 100 / horasLaboratorio);
                    practicasLaboratorioDelegate.agregarPracticaLab(pl);
                }
            }

            for (Practicataller pt : practicasTallerDelegate.consultarPracticas("unidadaprendizaje.uapclave", String.valueOf(ua.getUapclave()))) {
                if (horasTaller == 0) {
                    mensajes.add("La unidad de aprendizaje " + ua.getUapnombre() + " tiene 0 hras TALLER pero intenta agregar " + pt.getPrtnombre());
                    System.out.println("LA UNIDAD DE APRENDIZAJE " + ua.getUapnombre() + " TIENE 0 HRAS TALLER PERO INTENTA AGREGAR" + pt.getPrtnombre());
                } else {
                    pt.setPrtvalorPorcentaje(pt.getPrthoras() * 100 / horasTaller);
                    practicasTallerDelegate.agregarPracticaTall(pt);
                }
            }

        }

        System.out.println("******************[FIN DE LA CORRECION DE PORCENTAJES]******************");
    }

    /////////////////PARTE PARA ASIGNACION DE UNIDADES DE APRENDIZAJE PLANES DE ESTUDIO
    PlanEstudioDelegate planesDelegate = new PlanEstudioDelegate();

    String areaConocimiento = "";
    String unidadesAprendizaje = "";
    AreaConocimientoDelegate areaconocimientoDelegate = new AreaConocimientoDelegate();

    List<Planestudio> listaPlanes = planesDelegate.getListaPlanEstudio();
    List<Areaconocimiento> listaAreas = areaconocimientoDelegate.getListaAreaConocimiento();

    public List<Areaconocimiento> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Areaconocimiento> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<Planestudio> getListaPlanes() {
        return listaPlanes;
    }

    public void setListaPlanes(List<Planestudio> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }

    public String getUnidadesAprendizaje() {
        return unidadesAprendizaje;
    }

    public void setUnidadesAprendizaje(String unidadesAprendizaje) {
        this.unidadesAprendizaje = unidadesAprendizaje;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    public void setAreaConocimiento(String areasConocimiento) {
        this.areaConocimiento = areasConocimiento;
    }

    public void asignarUnidadesAAreas() {
        mensajes = new ArrayList();
        UnidadAprendizajeDelegate unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
        ConsultaDelegate consultaDelegate = new ConsultaDelegate();
        Unidadaprendizaje auxUA = new Unidadaprendizaje();
        if (areaConocimiento.equals("0")) {

        } else {
            Areaconocimiento ac = areaconocimientoDelegate.findAreaConocimientoById(Integer.parseInt(areaConocimiento));
            System.out.println(ac.getAconombre());
            for (String unidadAprendizajeClave : unidadesAprendizaje.split(" ")) {
                auxUA = unidadAprendizajeDelegate.findUAbyClave(unidadAprendizajeClave);
                if (auxUA != null) {
                    System.out.println("SE ENCONTRO UA:" + auxUA.getUapnombre() + " // " + unidadAprendizajeClave + "");
                    //auxUA.setAreaconocimiento(ac);
                    unidadAprendizajeDelegate.agregarUnidadAprendizaje(auxUA);
                } else {
                    System.out.println("**********NO SE ENCONTRO UA CON CLAVE " + unidadAprendizajeClave);
                    mensajes.add("* No se encontro unidad de aprendizaje con clave: " + unidadAprendizajeClave);

                }
            }
        }
    }

    public void verUnidadesAprendizajeAsignadas() {

        mensajes = new ArrayList();
        UnidadAprendizajeDelegate unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
        ConsultaDelegate consultaDelegate = new ConsultaDelegate();
        if (areaConocimiento.equals("0")) {

        } else {
            Areaconocimiento ac = areaconocimientoDelegate.findAreaConocimientoById(Integer.parseInt(areaConocimiento));

            for (Unidadaprendizaje auxUA : consultaDelegate.getUnidadByArea(ac.getAcoid())) {
                mensajes.add(auxUA.getUapclave() + " -- " + auxUA.getUapnombre());
            }
        }
    }

    public void buscarUAConClave() {
        mensajes = new ArrayList();
        UnidadAprendizajeDelegate unidadAprendizajeDelegate = new UnidadAprendizajeDelegate();
        ConsultaDelegate consultaDelegate = new ConsultaDelegate();
        Unidadaprendizaje auxUA = new Unidadaprendizaje();
        Areaconocimiento ac = areaconocimientoDelegate.findAreaConocimientoById(Integer.parseInt(areaConocimiento));
        List<Areaconocimiento> areasConoimiento = null;
        for (String unidadAprendizajeClave : unidadesAprendizaje.split(" ")) {
            auxUA = unidadAprendizajeDelegate.findUAbyClave(unidadAprendizajeClave);
            if (auxUA != null) {
                System.out.println("SE ENCONTRO UA:" + auxUA.getUapnombre() + " // " + unidadAprendizajeClave + "");
                //auxUA.setAreaconocimiento(ac);

                areasConoimiento = areaconocimientoDelegate.getAreaByUnidad(String.valueOf(auxUA.getUapclave()));
                if (areasConoimiento == null) {

                    mensajes.add("!! La unidad de aprendizaje " + auxUA.getUapnombre() + " no esta asignado a ningun Area de conocimiento");
                } else {
                    for (Areaconocimiento auxAC : areasConoimiento) {
                        mensajes.add("La unidad de aprendizaje " + auxUA.getUapclave() + "(" + auxUA.getUapnombre() + ") pertenece a: " + auxAC.getAconombre());
                    }
                }
            } else {
                System.out.println("**********NO SE ENCONTRO UA CON CLAVE " + unidadAprendizajeClave);
                mensajes.add("*NO SE ENCONTRO UA CON CLAVE " + unidadAprendizajeClave);

            }
        }

    }
    
    public void auentarIdUnidadAprendizaje(){
        ConsultaDelegate consultaDelegate=new ConsultaDelegate();
        UnidadAprendizajeDelegate unidadAprendizajeDelegate=new UnidadAprendizajeDelegate();
        consultaDelegate.getUnidadesaprendizaje();
        int aumentoAreasConocmiento=13;
        int aumentoUnidadesAprendizaje=581;
        
        for(Areaconocimiento areaConocimiento:consultaDelegate.getAreasconocimiento()){
            int buscar=areaConocimiento.getAcoid();
            areaConocimiento.setAcoid(areaConocimiento.getAcoid()+aumentoAreasConocmiento);
            areaconocimientoDelegate.agregarAreaConocimiento(areaConocimiento);
          for(Unidadaprendizaje unidadAprendizaje:consultaDelegate.getUnidadByArea(buscar)){
        //NO SERA NECESARIO AUMENTAR IDs SI NO SE DUPLICAN UNIDADES DE APRENDIZAJE     
        //unidadAprendizaje.setUapid(unidadAprendizaje.getUapid()+aumentoUnidadesAprendizaje);
              
            //unidadAprendizaje.setAreaconocimiento(areaConocimiento);
            unidadAprendizajeDelegate.agregarUnidadAprendizaje(unidadAprendizaje);
        }
        }
        System.out.println("++++++++++++++++++++++++HECHO++++++++++++++++++++++");

    }

    
    
}
