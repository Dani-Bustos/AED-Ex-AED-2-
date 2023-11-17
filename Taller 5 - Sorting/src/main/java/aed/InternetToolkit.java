package aed;



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
        //Usamos radixsort, chekeando las direcciones de atras para adelante
        IPv4Address[] res = new IPv4Address[ipv4.length];
        ListaEnlazada<IPv4Address>[] buckets = new ListaEnlazada[256];
            
        //La 1era vez necesitamos generar  a mano o es nullpointer exception
        for(int i = 0; i<ipv4.length;i++){
                IPv4Address buffer = new IPv4Address(ipv4[i]);
                int tripla = buffer.getOctet(3);
                if(buckets[tripla] == null){
                buckets[tripla] = new ListaEnlazada<IPv4Address>();
                }
                buckets[tripla].agregarAtras(buffer);
        }
           
           

            //Comparamos con las otras tres triplas de numeros
        for(int i = 2;i>= 0;i--){  
            int indRes = 0;
            for(int j = 0; j < 256;j++){
                    
                    if(buckets[j] != null){
                       Iterador<IPv4Address> it = buckets[j].iterador();
                       
                       while(it.haySiguiente()){
                        res[indRes] = it.siguiente();
                        indRes++;
                        }
                        //vacio el balde
                        buckets[j] = new ListaEnlazada<IPv4Address>();    
                    }
                    
            }
               
            
            for(int j = 0;j<res.length;j++){
                    int tripla = res[j].getOctet(i);
                    if(buckets[tripla] == null){
                    buckets[tripla] = new ListaEnlazada<IPv4Address>();
                  
                        

                    }
                    buckets[tripla].agregarAtras(res[j]);
            }
           
            }
            
                 int indRes = 0;
                for(int j = 0; j < 256;j++){
                    
                    if(buckets[j]  != null){
                     Iterador<IPv4Address> it = buckets[j].iterador();
                    
                    while(it.haySiguiente()){
                        res[indRes] = it.siguiente();
                        indRes++;
                    }
                   }
                }
                
               
        
      return res;
    }
  



}

