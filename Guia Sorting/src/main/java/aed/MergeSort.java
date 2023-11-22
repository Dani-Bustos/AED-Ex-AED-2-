package aed;
import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.LinkedList;
class Sorting <T extends Comparable<T>> {
       
   
     
       Sorting(){
         
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
         TuplaEstable[] tuplasA =  new TuplaEstable[n];
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
      //Ej 8 , 2
      //Complejidad : O(|A| + n(log(n) +|B|) )
      public int[] NSecuenciasRepetidasBmismosElementos(int[] A ,int[] B, int n){
         TuplaEstable[] tuplasA =   new TuplaEstable[n];
         
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
         Ordenadores ord = new Ordenadores<>();
         //O(n*log(n))
         ord.mergeSort(tuplasA, 0, tuplasA.length-1);
         
         int k = 0;
         while(k<tuplasA.length-1){
            if(tuplasA[k].primerElem() == tuplasA[k+1].primerElem()){
               tuplasA[k].segundo += tuplasA[k+1].segundo;
               tuplasA[k+1].primero = 0;
               tuplasA[k+1].segundo = 0;
               k++;
            }
           k++;
         }  
         //O(n*|B|) Agregamos a las repeticiones de las tuplas las apareciones en B
         for(int z = 0; z < tuplasA.length;z++){
            for (int j = 0; j< B.length;j++){
               if(tuplasA[z].primerElem() == B[j]){
                  tuplasA[z].segundo++;
               }
            }
         }
      
      
         //Theta(|A| + |B|)Reconsturimos el arreglo final 
         int[] res = new int[A.length + B.length];
         int indRes = 0;
         for(int i = 0; i<tuplasA.length;i++){
            for(int j = 0; j <tuplasA[i].segundoElem();j++){
             res[indRes] = tuplasA[i].primerElem();
             indRes++;
            } 
         }
      
         return res;
      }  
      //Necesito definir una comparacion diferente para el ejercicio que sigue
      private static class TuplaEscalera implements Comparable<TuplaEscalera>{
            int primero;
            int segundo;
            
            TuplaEscalera(int a,int b){
               primero = a;
               segundo = b;
            }
            
            @Override
            public int compareTo( TuplaEscalera otro) {
                if(this.primero > otro.primero){
                  return -1;
                }else if ( this.primero < otro.primero){
                  return 1;
                }else{
                  if(this.segundo < otro.segundo){
                     return -1;
                  }else if(this.segundo > otro.segundo){
                     return 1;
                  }else{
                     return 0;
                  }
                  }
                }
            }
         
         
     
     
      public int[] OrdenaEscaleras(int[] A){
         
        
         //Se define escalera como la subsecuencia maximal de sucesores en un arreglo
         LinkedList<TuplaEscalera> tuplasEscaleras = new LinkedList<TuplaEscalera>();
         
        
         //O(|A|) Obtenemos le primer elemento  de cada escalera y su longitud
         int x = 0;
         while(x < A.length-1){
            int cuenta = 1;
            int j = x;
           while(j < A.length && A[j]+1 == A[j+1]){
               cuenta++;
               j++;
           }
           tuplasEscaleras.add(new TuplaEscalera(cuenta,A[x]));
           x = j+1;        
         }
         if(A[A.length-2] != A[A.length-1] + 1){
            tuplasEscaleras.add(new TuplaEscalera(1, A[A.length-1]));
         }
         
         TuplaEscalera[] arrRes =  tuplasEscaleras.toArray(new TuplaEscalera[tuplasEscaleras.size()]);
         Ordenadores ord = new Ordenadores<>();
         //Peor caso: nlog(n), en verdad es cantEscaleras*log(cantEscaleras)
         ord.mergeSort(arrRes, 0, arrRes.length -1);
         
         //Theta(n)
         int[] res = new int[A.length];
         int indRes = 0;
         for(int i = 0; i<arrRes.length;i++){
            
            for(int j = 0; j<arrRes[i].primero;j++){
              res[indRes] = arrRes[i].segundo + j;    
              indRes++;
            }
                     
         }
         //Theta(n) Reconstruimos las escaleras
         return res;

      }

      
      public Integer[] RaizDeNNumerosFueraDeRango(int[] A, int L, int R){
         LinkedList<Integer> FueraDeRango = new LinkedList<Integer>();  
         LinkedList<Integer> EnRango = new LinkedList<Integer>();

         for(int i = 0 ; i<A.length;i++){
            //Guardo los elementos fuera de rango, cant: √n
            if(R < A[i] ||  A[i]< L){
               FueraDeRango.add(A[i]);
            }else{
               EnRango.add(A[i]);
            }
           
         }
        //O (n-√n)
         Integer[] arrEnRango =  EnRango.toArray(new Integer[EnRango.size()]);
          //O (√n)
         Integer[] arrFueraDeRango =  FueraDeRango.toArray(new Integer[FueraDeRango.size()]);
        
         Ordenadores<Integer> ord = new Ordenadores<Integer>();
         //O(√nlog(√n))
         ord.mergeSort(arrFueraDeRango, 0, arrFueraDeRango.length-1);
         //O(n) se puede optimizar para reducirel rango del counting;
         ord.countingSort(A, R);
         
         return ord.UnionArreglosOrdenados(arrEnRango, arrFueraDeRango);


         

      
      
      }

}
