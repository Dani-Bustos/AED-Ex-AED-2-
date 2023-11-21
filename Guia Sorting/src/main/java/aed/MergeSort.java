package aed;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import aed.PriorityQueueTupla;
import javax.swing.RowFilter.Entry;
class Sorting {
       
      public class TuplaEstable implements Comparable<TuplaEstable>{
         Integer primero;
        Integer segundo;
         TuplaEstable(Integer a, Integer b){
            primero = a;
            segundo = b;
         }
         public  Integer primerElem(){
            return primero;
         }

         public Integer segundoElem(){
            return segundo;
         }
         
         public int compareTo(TuplaEstable otro){
          if(this.primero.compareTo(otro.primero) > 0){
            return 1;
          }else if (this.primero.compareTo(otro.primero) < 0){
            return -1;
          }else{
            if(this.segundo.compareTo(otro.segundo) < 0){
               return 1;
            }else if (this.segundo.compareTo(otro.segundo) > 0){
               return -1;
            }else{
               return 0;
            }
          }
        }
      
      } 
     
       Sorting(){
         
       }


       Integer[] mergeSort(Integer[] A,int p, int r){
          if(p >= r){
            return new Integer[0]; 
          }else{
            int q = (p+r) /2;
            mergeSort(A,p,q);
            mergeSort(A,q+1,r);
            return  Merge(A,p,q,r); 
          }
       }
      
       Integer[] Merge(Integer[] A,int p, int q, int r){
         int longIzq = q-p + 1;
         int longDerecha = r - q;
         Integer[] L =  new Integer[longIzq];
         Integer[] R =  new Integer[longDerecha];
         for(int i = 0; i < longIzq;i++){
            L[i] = A[p+i];
         }
          for(int i = 0; i < longDerecha;i++){
            R[i] = A[q+i+1];
         }
         int i = 0; int j = 0; int k = p;

         while(i < longIzq && j < longDerecha){
            if(L[i].compareTo(R[j]) <= 0){
                A[k] = L[i];
                i++;
            }else{A[k] = R[j]; j++;}
         k++; 
        }
         while(i < longIzq){
            A[k] = L[i];
            i++;
            k++;
         }
         
         while(j < longDerecha){
            A[k] = R[j];
            k++;
            j++;
         }
         
        return A;
                  
        }
      
      Integer[] OrdenarPorFrecuencia(Integer[] A){
        TreeMap<Integer,Integer> arbolDeRep = new TreeMap<>();
       //nlog(n)
        for(int i = 0; i< A.length; i++){
         if(arbolDeRep.containsKey(A[i])){
            arbolDeRep.put(A[i], arbolDeRep.get(A[i]) + 1);
            
         }else{arbolDeRep.put(A[i], 1);}
        }
           PriorityQueue<TuplaEstable> heap = new PriorityQueue<TuplaEstable>(arbolDeRep.size(),Collections.reverseOrder());
        //Hagamos de cuenta qeu esto es un iterador y que podemos recorrer todo el arbol
        // en nlogn
       for(Map.Entry<Integer, Integer> entry : arbolDeRep.entrySet()){
         TuplaEstable buffer = new TuplaEstable(entry.getValue(), entry.getKey()); 
         heap.add(buffer);
       }
       //Theta(n)
       Integer[] res = new Integer[A.length];
       int indiceRes = 0;
       //Theta(n)
       while(heap.size() != 0){
          TuplaEstable raiz =  heap.poll();
         for(int i = 0; i< raiz.primerElem();i++){
           res[indiceRes] = raiz.segundo;
           indiceRes++;
         }
       }
       return res; 
        
      }
      //Ej 8 guia de sorting
      public int[] NsecuenciasRepetidasUnoRandom(int[] A, int[] B , int n){
         TuplaEstable[] tuplasA = new TuplaEstable[n];
         tuplasA[0] = new TuplaEstable(A[0],1);
         //Obtengo un arreglo de tuplas de las n secuencias continuas de a
         int indTA = 0;
         //O(|A|)
         for(int i =1 ; i<A.length;i++){
            
            if(A[i] == tuplasA[indTA].primerElem()){
                  tuplasA[indTA].segundo++;
            }else{
               indTA++;
               tuplasA[indTA] = new TuplaEstable(A[i], 1);
            }
           
         }
         //Inserto en el avl los elementos de la tupla y los de b, compleidad |A|*log(n) + |B|*log(n+|B|)
         TreeMap<Integer,Integer> NumRep = new TreeMap<Integer,Integer>();
         for(int j = 0; j < n;j++){
            if(NumRep.containsKey(tuplasA[j].primerElem())){
                NumRep.put( tuplasA[j].primerElem(),  NumRep.get(tuplasA[j].primerElem()) + tuplasA[j].segundoElem());
            }else {
                NumRep.put( tuplasA[j].primerElem(), tuplasA[j].segundoElem());
            }
         }
        
          
         for(int j = 0; j < B.length;j++){
            if(NumRep.containsKey(B[j])){
                NumRep.put( B[j],  NumRep.get(B[j]) + 1);
            }else {
                NumRep.put( B[j], 1);
            }
         }
         //Itero los elemtso y reconstruyo a partir de eso
         int[] res = new int[A.length + B.length];
         int indRes = 0;
         for(Map.Entry<Integer, Integer> entry : NumRep.entrySet()){
            //Lo escirbo la cantidad de repeticiones
            for(int j = 0; j<entry.getValue();j++){
               res[indRes] = entry.getKey();
               indRes++;
            } 
         
         }
         return res;
        
      }
      
}
