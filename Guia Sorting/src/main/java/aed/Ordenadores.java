package aed;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Ordenadores< T extends Comparable<T>> {
    //O(nlog(d)), d los elementos dsitintos en el arreglo
     void  AVLSort(T[] A ){
        //Los null van primero
        TreeMap<T,Integer> DicNumApariciones =  new TreeMap<T, Integer>(Comparator.nullsFirst(Comparator.naturalOrder()));
        for(int i = 0; i<A.length ;i++){
            if(DicNumApariciones.containsKey(A[i])){
              //Agregamos al avl las apariciones anteriroes +1
                Integer nuevasReps = DicNumApariciones.get(A[i]) +1;
                DicNumApariciones.put(A[i] ,nuevasReps);
            }else{
                DicNumApariciones.put(A[i],1);
            }

        }
       //Theta(n*log(d))Iteramos por el avl, rearmando en orden el arreglo
       int indArreglo = 0;
       for(Map.Entry<T, Integer> entry : DicNumApariciones.entrySet()){
         for(int i = 0; i<entry.getValue();i++){
             A[indArreglo] = entry.getKey();
             indArreglo++;
         }
       }
    }
    //Complejidad: O(nlog(n))
    void mergeSort(T[] A,int p, int r){
          
             
        if(p<r){
        int q = (p+r) /2;
        mergeSort(A,p,q);
        mergeSort(A,q+1,r);
        Merge(A,p,q,r); 
      }
   }
  //O(|A|) opera como cinta transportadora. Parte a la mimtad el arreglo y compara cual es mas chico
  //de cada uno. Ese lo mete al nuevo, y avanza esa cinta. Al terminar añadimos todo lo que falta del otro
   void Merge(T[] A,int p, int q, int r){
     
     int longIzq = q-p + 1;
     int longDerecha = r - q;
     T[] L = (T[]) new Comparable[longIzq];
     T[] R = (T[]) new Comparable[longDerecha];
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
     
    
              
    }
}
