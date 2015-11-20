/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.siract.business;

import java.io.Serializable;
import java.util.ArrayList;
import mx.avanti.siract.business.services.ServiceFacadeLocator;

/**
 *
 * @author Ricardo
 */
public class EsperadosDelegate implements Serializable{
    private ArrayList<String> resultado;
    //-----------------------------------------------AREA DE CONOCIMIENTO--------------------------------------//
    public ArrayList<String> getAreaConocimientoEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE) {
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        return resultado;
    }
    
    public ArrayList<String> getAreaConocimientoEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String num) {
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE, num);
        return resultado;
    }
    
    public ArrayList<String> getAreaConocimientoEsperados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE) {
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEsperadosAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        return resultado;
    }
    
    public ArrayList<String> getFullAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE) {
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaFullAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        return resultado;
    }

    //------------------------------------------------PROGRAMA EDUCATIVO---------------------------------------//
    public ArrayList<String> getProgramaEduEntregados(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosProgramaEdu(UnidadAcademica, PE, PrE, CE);
        return resultado;
    }
    
    public ArrayList<String> getProgramaEduEntregados(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String ract){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosProgramaEdu(UnidadAcademica, PE, PrE, CE, ract);
        return resultado;
    }
    
    public ArrayList<String> getProgramaEduEsperados(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEsperadosProgramaEdu(UnidadAcademica, PE, PrE, CE);
        return resultado;
    }
    
    public ArrayList<String> getFullProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaFullProgramaEdu(UnidadAcademica, PE, PrE, CE);
        return resultado;
    }
    
    public ArrayList<String> getAtiempoProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaAtiempoProgramaEducativo(UnidadAcademica, PE, PrE, CE, r1,r2,r3);
        return resultado;
    }
    
    public ArrayList<String> getEnLimiteProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEnLimiteProgramaEducativo(UnidadAcademica, PE, PrE, CE, r1,r2,r3);
        return resultado;
    }
    
    public ArrayList<String> getDespuesLimiteProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaDespuesLimiteProgramaEducativo(UnidadAcademica, PE, PrE, CE, r1,r2,r3);
        return resultado;
    }

    //------------------------------------------------UNIDAD APRENDIZAJE---------------------------------------//
    public ArrayList<String> getUnidadAprendEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosUnidadAprend(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }

    public ArrayList<String> getUnidadAprendEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi, String ract){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosUnidadAprend(UnidadAcademica, AC, PE, PrE, CE, aprendi,ract);
        return resultado;
    }
    
    public ArrayList<String> getUnidadAprendEsperados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEsperadosUnidadAprend(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }
    
    public ArrayList<String> getFullUnidadAprend(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaFullUnidadAprend(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }

    //--------------------------------------------------PROFESOR-----------------------------------------------//
    public ArrayList<String> getProfesorEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }
    
    public ArrayList<String> getProfesorEntregados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi,String ract){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi, ract);
        return resultado;
    }
    
    public ArrayList<String> getProfesorEsperados(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaEntregadosProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }
    
    public ArrayList<String> getFullProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        resultado = null;
        resultado = ServiceFacadeLocator.getFacadeEsperados().consultaFullProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        return resultado;
    }
    
    public double getSemaforo(int UnidadAcademica){
        double porcentaje = ServiceFacadeLocator.getFacadeEsperados().consultaSemaforo(UnidadAcademica);
        return porcentaje;
    }
    
    public ArrayList<String> getSemadoroProgEd(int UnidadAcademica){
        ArrayList<String> progEds = ServiceFacadeLocator.getFacadeEsperados().semaforoProgEd(UnidadAcademica);
        return progEds;
    }
    
    public ArrayList<String> getSemadoroProgEdValor(int UnidadAcademica){
        ArrayList<String> progEds = ServiceFacadeLocator.getFacadeEsperados().semaforoProgEdValor(UnidadAcademica);
        return progEds;
    }
}