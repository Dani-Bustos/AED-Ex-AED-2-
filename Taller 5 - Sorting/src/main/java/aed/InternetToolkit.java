package aed;


import java.util.Iterator ;
import aed.PriorityQueueTupla;
import aed.Router;
import aed.ListaEnlazada;

public class InternetToolkit {
    public InternetToolkit() {
    }

    public Fragment[] tcpReorder(Fragment[] fragments) {
        //Insertion Sort
        for(int i = 1 ; i < fragments.length; i++){
            Fragment actual = fragments[i];
            int j = i-1;
            while(j >= 0){
                if(fragments[j+1].compareTo(fragments[j]) < 0){
                        fragments[j+1] = fragments[j];
                        fragments[j] = actual;
                }else{
                    j = -1;
                }
                j--;
            }
        }
        return fragments;
    }

    public Router[] kTopRouters(Router[] routers, int k, int umbral) {
        
        PriorityQueueTupla h = new PriorityQueueTupla(routers);
        //Algoritmo de Floyd
        Router[] res = new Router[k];
        int indiceRes = 0;
        while(k > 0 ){
            Router actual = h.desencolar();
            if(actual.getTrafico() >= umbral){
              res[indiceRes] = actual;
              indiceRes++;
            }else{
                k = -1;
            }
            k--;
        }
        
        

        
        return res;
    }
    
    public IPv4Address[] sortIPv4(String[] ipv4) {
        //Usamos radix
             IPv4Address[] res = new IPv4Address[ipv4.length];
            ListaEnlazada<IPv4Address>[] buckets = new ListaEnlazada[256];
            for(int i = 0; i<ipv4.length;i++){
                IPv4Address buffer = new IPv4Address(ipv4[i]);
                buckets[buffer.getOctet(3)] = new ListaEnlazada<IPv4Address>();
                buckets[buffer.getOctet(3)].agregarAdelante(buffer);
            }
           for(int i = 2;i>= 0;i--){  
             int Indres = 0;
             ListaEnlazada primera = buckets[0];
             for(int j = 1; j < 256;j++){
                  primera.concatenar(buckets[j]);
                 
            }
            res = primera.Lista2Array();  
           }

        
      return null;
    }
  



}

