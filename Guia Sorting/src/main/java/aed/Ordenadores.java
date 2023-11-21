package aed;

public class Ordenadores< T extends Comparable<T>> {
    
    
    void mergeSort(T[] A,int p, int r){
          
             
        if(p<r){
        int q = (p+r) /2;
        mergeSort(A,p,q);
        mergeSort(A,q+1,r);
        Merge(A,p,q,r); 
      }
   }
  
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
