/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.avanti.common.integration.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar Favela
 */
public class IntrospectorData {
    private Class clase;
    private Field[] fields;
    private Method[] metods;
    private Object object;
    private int countAtribute;
    
    public IntrospectorData(Object object){
        this.clase=object.getClass();
        this.object=object;
        fields=clase.getDeclaredFields();
        metods = clase.getDeclaredMethods();
    }
    
    public Field[] getFields(){
        return fields;
    }
    
    private Field getFieldLike(String like){
        Field atributos[]=clase.getDeclaredFields();
        for(Field field:atributos){
            if(field.getName().endsWith(like)){
                return field;
            }
        }
        return null;
    }
    public Object executeGetMethod(String atributo)throws NoSuchMethodException, InvocationTargetException{
        Field field=getFieldLike(atributo);
        atributo=field.getName();
        atributo=atributo.replaceFirst(atributo.charAt(0)+""+"",(atributo.charAt(0)+"").toUpperCase().charAt(0)+"");
        atributo = "get"+atributo;
        Method metodo = clase.getMethod(atributo);
        Object result = null;
        try {
                   return result = metodo.invoke(object);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IntrospectorData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(IntrospectorData.class.getName()).log(Level.SEVERE, null, ex);
        }
   return null;
    }
    
    public void executeSetObject(String atributo,Object parametro) throws NoSuchMethodException, InvocationTargetException{
        Field field=getFieldLike(atributo);
        atributo=field.getName();
              atributo=atributo.replaceFirst(atributo.charAt(0)+"",(atributo.charAt(0)+"").toUpperCase().charAt(0)+"");
        atributo = "set"+atributo;
        Method metodo = clase.getMethod(atributo,parametro.getClass());
        Object result = null;
        try {
                   result = metodo.invoke(object,parametro);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IntrospectorData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(IntrospectorData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
