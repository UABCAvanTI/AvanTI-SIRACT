///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package mx.avanti.siract.ui;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Eduardo
// */
//public class EnvioAlertaThread {
//    
//    EnvioAlertas alertas=new EnvioAlertas();
//    
////    public void revisarAlertas(boolean estado){    
////        int ciclos=1;
////        alertas.start();//se inicia el hilo
////        while(true){//hilo se hace infinito
////            try {
////               alertas.sleep(172800000);//el proceso se duerme un dia completo
////               alertas.run();
////               ciclos++;
////               if(!alertas.isAlive()){//condicion por si el thread muere, se arranca de nuevo
////                   System.out.println("Ciclos completados: "+ciclos);
////               }
////            } catch (InterruptedException ex) {
////                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        }
////    }
//    //asdasdasdasdasdasasasdasdasdasdsa
//    static boolean asd=true;
//    public void estatusAlertas(boolean estatus){
//        asd=estatus;
//        System.out.println("----------"+asd);
//        activarAlertas();
//    }
//    public void activarAlertas(){
//            try {
//                alertas=new EnvioAlertas();
//                alertas.start();
//                while(asd){
//                    alertas.sleep(172800000);
//                    alertas.run();
//                }
//            } catch (InterruptedException ex) {
//                Logger.getLogger(EnvioAlertaThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//    }
//    public void apagarAlertas(){
//        if(!alertas.isAlive()){
//            System.out.println(" esta muerto ");
//        }else{
//            alertas=null;
//        }
//    }
//}
