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
            if(this.segundo.compareTo(otro.segundo) > 0){
               return 1;
            }else if (this.segundo.compareTo(otro.segundo) < 0){
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
           PriorityQueueTupla<TuplaEstable> heap = new PriorityQueueTupla<TuplaEstable>(arbolDeRep.size());
        //Hagamos de cuenta qeu esto es un iterador y que podemos recorrer todo el arbol
        // en nlogn
       for(Map.Entry<Integer, Integer> entry : arbolDeRep.entrySet()){
         TuplaEstable buffer = new TuplaEstable(entry.getValue(), entry.getKey()); 
         heap.encolar(buffer);
       }
       //Theta(n)
       Integer[] res = new Integer[A.length];
       int indiceRes = 0;
       //Theta(n)
       while(heap.cardinal() != 0){
          TuplaEstable raiz =  heap.desencolar();
         for(int i = 0; i< raiz.primerElem();i++){
           res[indiceRes] = raiz.segundo;
           indiceRes++;
         }
       }
       return res; 
        
      }
                 
}
