package aed;

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
        @Override
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

