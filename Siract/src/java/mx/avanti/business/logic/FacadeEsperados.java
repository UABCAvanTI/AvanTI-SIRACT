/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.avanti.business.logic;

import java.util.ArrayList;
import mx.avanti.siract.common.integration.ServiceLocator;

/**
 *
 * @author Ricardo
 */
public class FacadeEsperados {
    private ArrayList<String> resultado;
    //-----------------------------------------------AREA DE CONOCIMIENTO--------------------------------------//
    public ArrayList<String> consultaEntregadosAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosAreaConocimiento();
        return resultado; 
    }
    
    public ArrayList<String> consultaEntregadosAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE, String num){
        ServiceLocator.getInstanceEsperados().initAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosAreaConocimiento(num);
        return resultado; 
    }
    
    public ArrayList<String> consultaEsperadosAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEsperadosAreaConocimiento();
        return resultado; 
    }
    
    public ArrayList<String> consultaFullAreaConocimiento(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initAreaConocimiento(UnidadAcademica, AC, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getRactsAreaConocimiento();
        return resultado; 
    }
    
    //------------------------------------------------PROGRAMA EDUCATIVO---------------------------------------//
    
    public ArrayList<String> consultaEntregadosProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosProgramaEducativo();
        return resultado; 
    }

    public ArrayList<String> consultaEntregadosProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String ract){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosProgramaEducativo(ract);
        return resultado; 
    }
    
    public ArrayList<String> consultaEsperadosProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEsperadosProgramaEducativo();
        return resultado;
    }
    
    public ArrayList<String> consultaFullProgramaEdu(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getRactsProgramaEducativo();
        return resultado;
    }
    
    public ArrayList<String> consultaAtiempoProgramaEducativo(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getAtiempoProgramaEducativo(r1,r2,r3);
        return resultado;
    }
    
    public ArrayList<String> consultaEnLimiteProgramaEducativo(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEnLimiteProgramaEducativo(r1,r2,r3);
        return resultado;
    }
    
    public ArrayList<String> consultaDespuesLimiteProgramaEducativo(int UnidadAcademica,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,String r1,String r2,String r3){
        ServiceLocator.getInstanceEsperados().initProgramaEducativo(UnidadAcademica, PE, PrE, CE);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getDespuesLimiteProgramaEducativo(r1,r2,r3);
        return resultado;
    }
    //------------------------------------------------UNIDAD APRENDIZAJE---------------------------------------//
    
    public ArrayList<String> consultaEntregadosUnidadAprend(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initUnidadAprendizaje(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosUnidadAprendizaje();
        return resultado;
    }

    public ArrayList<String> consultaEntregadosUnidadAprend(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi,String ract){
        ServiceLocator.getInstanceEsperados().initUnidadAprendizaje(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosUnidadAprendizaje(ract);
        return resultado;
    }

    public ArrayList<String> consultaEsperadosUnidadAprend(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initUnidadAprendizaje(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEsperadosUnidadAprendizaje();
        return resultado;
    }

    public ArrayList<String> consultaFullUnidadAprend(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initUnidadAprendizaje(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getRactsUnidadAprendizaje();
        return resultado;
    }

    //--------------------------------------------------PROFESOR-----------------------------------------------//

    public ArrayList<String> consultaEntregadosProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosProfesor();
        return resultado;
    }
    
    public ArrayList<String> consultaEntregadosProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi,String ract){
        ServiceLocator.getInstanceEsperados().initProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEntregadosProfesor(ract);
        return resultado;
    }

    public ArrayList<String> consultaEsperadosProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getEsperadosProfesor();
        return resultado;
    }

    public ArrayList<String> consultaFullProfesor(int UnidadAcademica,ArrayList<String> AC,ArrayList<String> PE,ArrayList<Integer> PrE,ArrayList<String> CE,ArrayList<String> aprendi){
        ServiceLocator.getInstanceEsperados().initProfesor(UnidadAcademica, AC, PE, PrE, CE, aprendi);
        resultado = null;
        resultado = ServiceLocator.getInstanceEsperados().getRactsProfesor();
        return resultado;
    }
    
    public double consultaSemaforo(int UnidadAcademica){
        double porcentaje = ServiceLocator.getInstanceEsperados().contarSemaforo(UnidadAcademica);
        return porcentaje;
    }
    
    public ArrayList<String> semaforoProgEd(int UnidadAcademica){
        ArrayList<String> progEds = ServiceLocator.getInstanceEsperados().contarSemaforoProgEd(UnidadAcademica);
        return progEds;
    }
    
    public ArrayList<String> semaforoProgEdValor(int UnidadAcademica){
        ArrayList<String> progEds = ServiceLocator.getInstanceEsperados().contarSemaforoProgEdValor(UnidadAcademica);
        return progEds;
    }
}