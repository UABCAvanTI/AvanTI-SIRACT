/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Cesar Favela
 */
package mx.avanti.siract.integration.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mx.avanti.common.integration.persistence.HibernateUtil;
import mx.avanti.siract.business.entity.Usuario;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.metadata.ClassMetadata;

public class BaseDAO<T>  {

    private Class<T> tipo;

    public BaseDAO() {

    }

    public BaseDAO(Class<T> tipo) {
        this.tipo = tipo;
        System.out.println(" Se crea el bean con la clae: " + tipo.getName());
    }

    public Class<T> getTipo() {
        return tipo;
    }

    public void setTipo(Class<T> tipo) {
        this.tipo = tipo;
    }

    public void save(T t) {
        System.out.println("Salvando en: " + tipo.getSimpleName());
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            HibernateUtil.getSession().save(t);
            HibernateUtil.commitTransaction();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void delete(T t) {
        System.out.println("Eliminado en: " + tipo.getSimpleName());
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        try {
            HibernateUtil.getSession().delete(t);
            HibernateUtil.getSession().flush();
            HibernateUtil.commitTransaction();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public T find(int id) {
        //System.out.println("Consultando en: " + tipo.getSimpleName()+"BaseDAO");

        T obj = null;
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String identificador = catMeta.getIdentifierPropertyName();
        String nombre = tipo.getSimpleName();
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            String query1 = "from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where " + identificador + " =:id";
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id", id);
            obj = (T) query.uniqueResult();
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return obj;
    }
        public T findProf(int id) {
        System.out.println("Consultando en: " + tipo.getSimpleName()+"BaseDAO");

        T obj = null;
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String identificador = catMeta.getIdentifierPropertyName();
        String nombre = tipo.getSimpleName();
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            String query1 = "from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where Usuario_USUid=:id";
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id", id);
            obj = (T) query.uniqueResult();
        } catch (Exception x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return obj;
    }

         public Usuario Login(String usuusuario, String usucontrasenia) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Property.forName("usuusuario").eq(usuusuario));
        criteria.add(Property.forName("usucontrasenia").eq(usucontrasenia));
        List<Usuario> listUsuario = null;
        Usuario usuario = null;
        try {
            listUsuario = criteria.list();
            for (Iterator it = listUsuario.iterator(); it.hasNext();) {
                usuario = (Usuario) it.next();
            }
        } catch (Exception x) {
            x.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.flush();
            session.close();
        }
        return usuario;
    }
        
    public List<T> findall(String clase) {
       // System.out.println("Consultando en: " + tipo.getSimpleName());
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("from " + tipo.getName()).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return (List<T>) result;
    }

    public void saveOrUpdate(T instance) {
        //System.out.println("Modificando en: " + tipo.getSimpleName());
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            HibernateUtil.getSession().saveOrUpdate(instance);
            HibernateUtil.getSession().flush();
            HibernateUtil.commitTransaction();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<T> executeQuery(String query) {
        List<T> result = new ArrayList<T>();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            result = HibernateUtil.getSession().createQuery(query).list();
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }


    public List<T> findAll() {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("from " + tipo.getName()).list();
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    public List<T> findFromWhere(String de, String campo, String criterio) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from " + getTipo().getCanonicalName() + " a join a." + de + " b where b." + campo + "=" + criterio).list();
            
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    public List<T> findFromWhereUnic(String de, String campo, String criterio,String clase) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from " + clase+ " a join a." + de + " b where b." + campo + "=" + criterio).list();
            
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
   public List<T> subperPermRol(String de, String campo, String criterio, String campo2, String criterio2, String clase) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
         //   System.out.println("select a from " + getTipo().getCanonicalName() + " a join a." + de + " b where b." + campo + "=" + criterio);
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from " + clase + " a join a." + de + " b where b." + campo + "=" + criterio+" and b."+campo2+"="+criterio2).list();
            
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    ////////////////// CONFIGURACION /////////////////////////
    
    public T bConfigPorCiclo(int id) {
        T obj=null;
        ClassMetadata catMeta=HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre=tipo.getSimpleName();
         try{
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            String query1="from "+getTipo().getCanonicalName()+" as "+ nombre.toLowerCase()+ " where CicloEscolar_CESid=:id";
            Query query=HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id",id);
            obj=(T) query.uniqueResult();
         }catch(Exception x){
            x.printStackTrace();
        }finally{
            HibernateUtil.closeSession();
        }
        return obj;
    }
    public T findAlerta(String tipo) {
        T obj=null;
        ClassMetadata catMeta=HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre=this.tipo.getSimpleName();
         try{
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            String query1="from "+getTipo().getCanonicalName()+" as "+ nombre.toLowerCase()+ " where ALEtipo=:tipo";
            Query query=HibernateUtil.getSession().createQuery(query1);
            query.setString("tipo", tipo);
            obj=(T) query.uniqueResult();
         }catch(Exception x){
            x.printStackTrace();
        }finally{
            HibernateUtil.closeSession();
        }
        return obj;
    }
    
    ////////////////// FIN CONFIGURACION /////////////////////////
    
    public T reporteFechaActual() {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
            String query1 = "select i from " + getTipo().getCanonicalName() + " i where current_date between i.crefechaCorte and i.crefechaLimite";
            Query query = HibernateUtil.getSession().createQuery(query1);
            dto = (T) query.uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }

    public List<T> findFromWhereB(String de, String campo, String criterio, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            if(de.isEmpty()){
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+"="+criterio).list();
//            }else{
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from " + getTipo().getCanonicalName() + " a where a." + de + "." + campo + "=" + criterio + " order by a." + order).list();
//            }
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    
     public List<T> findFromWhereBExtra(String de, String campo, String criterio, String order,String clase) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from " +clase+ " a where a." + de + "." + campo + "=" + criterio + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    
    
    

    public List<T> findFromWhereDoble(String de, String campo, String criterio, String de2, String campo2, String criterio2, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+
                    getTipo().getCanonicalName()+" a join a."+de+" b join b."+de2+
                    " c where b."+campo+"="+criterio+" and c."+campo2+"="+criterio2+" order by "+order).list();
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from "
                    + getTipo().getCanonicalName() + " a join a." + de + " b where a." + de2 + "." + campo2 + "=" + criterio2 + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }

    public List<T> findFromWhereTriple(String de, String de2, String campo2, String criterio2, String de3, String campo3, String criterio3, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from "
                    + getTipo().getCanonicalName() + " a join a." + de + " b where a." + de2 + "." + campo2 + "=" + criterio2 + " and a." + de3 + "." + campo3 + "=" + criterio3 + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    
    public List<T> findFromWhereTriple2(String de, String de2, String campo2, String criterio2, String de3, String campo3, String criterio3, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from "
                    + getTipo().getCanonicalName() + " a join a." + de + " b where b." + de2 + "." + campo2 + "=" + criterio2 + " and a." + de3 + "." + campo3 + "=" + criterio3 + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    
    
    
    public List<T> findFromWhereDobleJoin(String de, String campo, String criterio, String de2, String campo2, String criterio2, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from "
                    + getTipo().getCanonicalName() + " a join a." + de + " where " + de2 + "." + campo2 + "=" + criterio2 + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    //Obtener todos los elementos de una lista ordenados por el atributo orderBy
    public List<T> findAllOrderBy(String orderBy) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("from " + getTipo().getCanonicalName() + " a order by a.uninombre").list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    //METODO PARA BUSCAR REPORTE (DEBIDO A LOS MULTIPLES ID)
    public T findMultipleID(int id, String criterio, String campo_id,String order) {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
//            String query1 = "from " + getTipo().getCanonicalName() + " as" + nombre.toLowerCase() + " where " + identificador + " = :id";
            String query1;
            if (criterio.isEmpty()) {
                query1 = "from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where "+campo_id + " = :id "+order;

            } else {
                query1 = "from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where " + criterio + " AND " + campo_id + " = :id "+order;
            }
            
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id", id);
            //SET MAX RESULT - RESTRINGE EL NUMERO DE RESULTADOS
            dto = (T) query.setMaxResults(1).uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }
    
    public T findMultipleIDExtra(int id, String criterio, String campo_id,String order,String clase) {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
//            String query1 = "from " + getTipo().getCanonicalName() + " as" + nombre.toLowerCase() + " where " + identificador + " = :id";
            String query1;
            if (criterio.isEmpty()) {
                query1 = "from " +clase+ " as a where "+campo_id + " = :id "+order;

            } else {
                query1 = "from " +clase+ " as a where " + criterio + " AND " + campo_id + " = :id "+order;
            }
            
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id", id);
            //SET MAX RESULT - RESTRINGE EL NUMERO DE RESULTADOS
            dto = (T) query.setMaxResults(1).uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }
    
    
    
    
    public void deleteWhere(String campo, String criterio) {
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
                HibernateUtil.getSession().createQuery("delete from " + getTipo().getCanonicalName() + " where " + campo + "=" + criterio).executeUpdate();
                HibernateUtil.commitTransaction();
   
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }
    
    public List<T> findFromWhereB2(String de, String campo, String criterio, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from " + getTipo().getCanonicalName() + " a where a." + de + "." + campo + "=" + criterio + " order by a." + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
      public Usuario findByUsuario(Usuario usuario) {
        Usuario user = null;
        try {
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            user = (Usuario) HibernateUtil.getSession().createQuery("from Usuario as usuario where usuario.usuusuario = :nombre").setString("nombre", String.valueOf(usuario.getUsuusuario())).uniqueResult();
            System.out.println(user);
        } catch (Exception x) {
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return user;
    }
    public Usuario login(Usuario usuario) {
        Usuario user = this.findByUsuario(usuario);
        String contraEncrypt, contra;
        contra = usuario.getUsucontrasenia();
        contraEncrypt=DigestUtils.md5Hex(contra);
        usuario.setUsucontrasenia(contraEncrypt);
        if (user != null) {
            if (!usuario.getUsucontrasenia().equals(user.getUsucontrasenia())) {
                user = null;
            }
        }
        return user;
    }
    
     public void deleteWhere(String clase,String campo, String criterio) {
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
                HibernateUtil.getSession().createQuery("delete from " + clase + " a where a." + campo + "=" + criterio).executeUpdate();
                HibernateUtil.commitTransaction();
   
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }
     
     
     
     public T find2(String clase,int id) {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
            String query1 = "from " + clase + " as" + nombre.toLowerCase() + " where " + identificador + " = :id";
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("id", id);
            dto = (T) query.uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }

    
     public T siguienteReporte() {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
            String query1 = "select i from Calendarioreporte i where current_date < i.crefechaLimite order by crefechaCorte asc";
            Query query = HibernateUtil.getSession().createQuery(query1);
            dto = (T) query.setMaxResults(1).uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }
    
    
    
    
        public T cicloEscolarActual() {
        T dto = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
        String nombre = tipo.getSimpleName();
        String identificador = catMeta.getIdentifierPropertyName();
        try {
            String query1 = "select i from Cicloescolar i join i.configuracions c where c.confechaInicioSemestre < current_date order by  c.confechaInicioSemestre desc";
            Query query = HibernateUtil.getSession().createQuery(query1);
            dto = (T) query.setMaxResults(1).uniqueResult();
        } catch (RuntimeException x) {
            x.printStackTrace();
        } finally {
            HibernateUtil.closeSession();
        }
        return dto;
    }

        public List<T> findFromWhere(String clase,String de, String campo, String criterio) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from " + clase+ " a join a." + de + " b where b." + campo + "=" + criterio).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
   
    
    public List<T> findFromWheres(String clase,String de, String de2, String campo2, String criterio2, String de3, String campo3, String criterio3, String order) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
            result = (List<T>) HibernateUtil.getSession().createQuery("select distinct a from "
                    +clase+ " a join a." + de + " where b." + de2 + "." + campo2 + "=" + criterio2 + " and "+ de3 + "." + campo3 + "=" + criterio3 + " order by " + order).list();

            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
    
    
    
    
    public void updateWhere(String clase,String campoUpdate,String valorUpdate, String campo, String criterio) {
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();

        try {
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
                HibernateUtil.getSession().createQuery("update " + clase + " set "+campoUpdate+"="+valorUpdate+" where " + campo + "=" + criterio).executeUpdate();
                HibernateUtil.commitTransaction();
   
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
    }
    
     public List<T> findCreAle(int creid){
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        try {
            String query1="from " + tipo.getName()+ " where "+
                    "Calendarioreporte_CREid=:creid order by Alerta_ALEid";
            Query query = HibernateUtil.getSession().createQuery(query1);
            query.setInteger("creid",creid);
            result=(List<T>) query.list();
            if (result == null) {
                System.out.println("nulo");
            }
        } catch (Exception x) {
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
      public T findCreAle(int creid, int aleid){
        T obj=null;
        String nombre=tipo.getSimpleName();
         try{
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            String query1="from "+getTipo().getCanonicalName()+" as "+ nombre.toLowerCase()+ " where "+
                    "Calendarioreporte_CREid=:creid and Alerta_ALEid=:aleid";
            Query query=HibernateUtil.getSession().createQuery(query1);
            query.setInteger("creid",creid);//ID de calendario
            query.setInteger("aleid",aleid);//ID de alerta
            obj=(T) query.uniqueResult();
         }catch(Exception x){
            x.printStackTrace();
        }finally{
            HibernateUtil.closeSession();
        }
        return obj;
    }
      
      public List<T> findWhereExtra(String campo,String criterio, String clase,String orden) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+ clase +" a where a."+campo+"="+criterio+" ORDER BY length("+orden+"),"+orden+" ASC ").list();
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
      public List<T> findWhere(String clase,String campo, String criterio,String orden) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+ clase +" where a."+campo+"='"+criterio+"' ORDER BY "+orden+" ASC ").list();
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
      
        public T findUap(int id) {
         Object obj = null;
         ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
         String nombre = tipo.getSimpleName();
         String identificador = "uapclave";
         
        try{
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            obj = HibernateUtil.getSession().createQuery("from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where " + identificador + " = :clave").setString("clave", String.valueOf(id)).uniqueResult();
        // Persona p = new Persona();
          //  System.out.println("");
       //System.out.println(p.getNombre());
        }catch(Exception x){
            HibernateUtil.rollbackTransaction();
        }finally{
            HibernateUtil.closeSession();
        }
        //System.out.println(obj);
        return (T) obj;
    }
        public T findUap(int id,String acoId) {
         Object obj = null;
         ClassMetadata catMeta = HibernateUtil.getSessionFactory().getClassMetadata(getTipo());
         String nombre = tipo.getSimpleName();
         String identificador = "uapclave";
         
        try{
            HibernateUtil.getSession();
            HibernateUtil.beingTransaccion();
            obj = HibernateUtil.getSession().createQuery("from " + getTipo().getCanonicalName() + " as " + nombre.toLowerCase() + " where " + identificador + " = :clave").setString("clave", String.valueOf(id)).uniqueResult();
        // Persona p = new Persona();

        }catch(Exception x){
            HibernateUtil.rollbackTransaction();
        }finally{
            HibernateUtil.closeSession();
        }
        return (T) obj;
    }
        
        
         public List<T> findPlanesWherePuesto(String de,String igualdad,String campo,String criterio) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a join a.programaeducativo."+de+" b where b.programaeducativo."+igualdad+"=a.programaeducativo."+igualdad+" and b.puesto."+campo+"="+criterio).list();
            //result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+de+".pedid="+de+".pedid and a."+de+"."+campo+"="+criterio).list();
            System.out.println("PLAN: select a from "+getTipo().getCanonicalName()+" a join "+de+" b where b."+igualdad+"=a."+igualdad+" and b."+campo+"="+criterio);
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
         
             public List<T> findPuestoFromId(String de,String campo,String criterio) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            System.out.println("Tipoo: " + getTipo().getCanonicalName());
            
            //result = (List<T>) HibernateUtil.getSession().createQuery("select a from Puesto a join a."+de+" b where b."+campo+"="+criterio).list();
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from Puesto a join a."+de+" b where b."+campo+"="+criterio).list();
            System.out.println("PE: select a from Puesto a join a."+de+" b where b."+campo+"="+criterio);
//            System.out.println("resultadoo:" + result.get(0));
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
             
               public T findRPEwhereIdProfesor(int id){
        T result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
//            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+campo+
//                    "="+criterio).list();
            System.out.println("-----------------------REHACER CONSULTA findRPEwhereIdProfesor");
            //result = (T) HibernateUtil.getSession().createQuery("select a from "+ Responsableprogramaeducativo.class.getCanonicalName()+" a join a.profesorTienePuestos b where b.profesor.proid"+"="+id).uniqueResult();
            
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
             
      public List<T> findPlanesWherePE(int idProgramaeducativo) {
        List<T> result = null;
        HibernateUtil.getSession();
        HibernateUtil.beingTransaccion();
        
        try{
            result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a.programaeducativo.pedid="+idProgramaeducativo).list();
            //result = (List<T>) HibernateUtil.getSession().createQuery("select a from "+getTipo().getCanonicalName()+" a where a."+de+".pedid="+de+".pedid and a."+de+"."+campo+"="+criterio).list();
            
            if(result == null){
                System.out.println("nulo");
            }
        } catch(Exception x){
            x.printStackTrace();
            HibernateUtil.rollbackTransaction();
        } finally {
            HibernateUtil.closeSession();
        }
        return result;
    }
}
