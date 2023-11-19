package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int cantidad;
    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;
        public Nodo(T v){
           valor = v;
           izq = null;
           der = null;
           padre = null;
        }
       
        public Nodo(T v, Nodo izquierda , Nodo derecha, Nodo padr){
            valor = v;
            izq = izquierda;
            der = derecha;
            padre = padr;
        }
    
    
    }

    public ABB() {
        _raiz = null;
        cantidad = 0;
    }

    public int cardinal() {
        return cantidad;
    }

    public T minimo(){
        Nodo actual = _raiz;
        while(actual.izq != null){
            actual = actual.izq;
        }
       return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while(actual.der != null){
            actual = actual.der;
        }
       return actual.valor;
    }
    
    private Nodo buscarNodo( T elem, Nodo actual){
        if(actual==null){
            return null;
        }
        if(actual.valor.compareTo(elem) == 0){
            return actual;
        }else{
            if(actual.valor.compareTo(elem) < 0){
                if(actual.der == null){
                    return actual;
                }else{
                    actual = actual.der;
                    return buscarNodo(elem,actual);
                }
            }else {
                if(actual.izq == null){
                    return actual;
                }else{
                    actual = actual.izq;
                    return buscarNodo(elem,actual);
            }
        
        }   
    } 
        
    }
    

    public void insertar(T elem){
        
        Nodo actual = _raiz;
       
        Nodo ultimo_Nodo = buscarNodo(elem,actual);
        if (ultimo_Nodo == null){
            _raiz = new Nodo(elem,null,null,null);
             cantidad += 1;
        }else if(ultimo_Nodo.valor.equals(elem)){
            return;
        }else if (ultimo_Nodo.valor.compareTo(elem) < 0){
            cantidad += 1;
            ultimo_Nodo.der = new Nodo(elem,null,null,ultimo_Nodo);
        }else{
            cantidad += 1;
            ultimo_Nodo.izq = new Nodo(elem,null,null,ultimo_Nodo);}
        
    }

    public boolean pertenece(T elem){
       Nodo actual = _raiz;
       Boolean res = false;
       Nodo ult = buscarNodo(elem, actual);
       T v = null;
       if (ult != null){
        v = ult.valor;}
       if (ult == null){
         
       }else if (v.equals(elem) ){
        res = true;
        
       }
      return res;
    }
    
    public void eliminar(T elem){
        Nodo A_borrar = buscarNodo(elem, _raiz);
        cantidad--;
        Boolean es_raiz = false;
        if (_raiz == null || !A_borrar.valor.equals(elem)){
            cantidad++;
            return;
        }
        if(A_borrar.valor.compareTo(_raiz.valor) == 0){ es_raiz = true;  }
        
        if(A_borrar.izq == null && A_borrar.der == null){ //Sin Hijos
            if(es_raiz){
                _raiz = null;
            }else if(A_borrar.valor.compareTo(A_borrar.padre.valor) < 0){
                A_borrar.padre.izq = null;
            }else{
                A_borrar.padre.der = null;
            }

        }else if (A_borrar.der == null){//Solo Hijo Izquierdo
            
            if (es_raiz){_raiz = _raiz.izq; _raiz.padre = null;}
              
            else if(A_borrar.valor.compareTo(A_borrar.padre.valor) < 0){
                A_borrar.padre.izq = A_borrar.izq;
                A_borrar.izq.padre = A_borrar.padre;
            }else{
                A_borrar.padre.der = A_borrar.izq;
                A_borrar.izq.padre = A_borrar.padre;
            }
        
        }else if(A_borrar.izq == null){ //Solo Hijo Derecho
            
            if (es_raiz){_raiz = _raiz.der;_raiz.padre = null;}
            
            else if(A_borrar.valor.compareTo(A_borrar.padre.valor) < 0){
                A_borrar.padre.izq = A_borrar.der;
                A_borrar.der.padre = A_borrar.padre;
            }else{
                A_borrar.padre.der = A_borrar.der;
                A_borrar.der.padre = A_borrar.padre;
            }
            
        }else{ //dosHijos
          
            Nodo suc = sucesor(A_borrar.der);
          

           
           if(suc.padre.valor.compareTo(A_borrar.valor) != 0 ){
        
            suc.padre.izq = suc.der;
            if (suc.der != null){
            suc.der.padre = suc.padre;
            }
           }else{
            suc.padre.der  = suc.der;
            if(suc.der != null){
            suc.der.padre = suc.padre;
            }
           }
         
           A_borrar.valor = suc.valor;
           if (es_raiz){
            _raiz = A_borrar;
           }
        }
            
    
    }

    private Nodo sucesor(Nodo actual){
        if(actual.izq == null){
            return actual;
        }else{
            return sucesor(actual.izq);
        }
    }
    
    
    private String inOrder(Nodo r){
        if (r == null){
            return "";
        }else{
           
           return  inOrder(r.izq) + r.valor.toString() + "," + inOrder(r.der);
        }        
    
    }
    public boolean hayCamino(int n){
       List<T> historial = new ArrayList<>(cantidad);
       
        Nodo actual = _raiz;
        if(_raiz == null ){
            return false;
        }else{
            int profundidad = 1;
            //casos base
            while(true){
            
            if(actual == null){
                return false;
            }else if(profundidad == n){
                return true;
            }else{
                //lo añadimos para no bajar de vuelta a este nodo si necesitamos subir
                historial.add(actual.valor);
                //casos iterativos
                
                if(actual.der != null &&  !historial.contains(actual.der.valor) ){
                    actual = actual.der;
                    profundidad++;
                }else if (actual.izq != null && !historial.contains(actual.izq.valor)){
                    actual = actual.izq;
                    profundidad++;
                }else{
                    actual = actual.padre;
                    profundidad--;
                }
            }
           }
            
        }
    }
    
    

    public String toString(){
        
        String res = inOrder(_raiz);
        res = res.substring(0, res.length()-1);
        res = "{" + res + "}";
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        private boolean primeraVez;
        ABB_Iterador(){
            _actual = _raiz;
            primeraVez = true;
        }
        public boolean haySiguiente() {            
            if(_actual.der == null && (_actual.padre == null || _actual.padre.izq != _actual) ){
            return false;
            }else{
                return true;
            }
        }
    
        public T siguiente() {
            if (primeraVez){
                primeraVez = false;
                
                _actual =  sucesor(_actual);
                return _actual.valor;
            }
            if (_actual.der != null){
                _actual = sucesor(_actual.der);
               return _actual.valor;
            }else{  
              Nodo y = _actual.padre;
              while(y != null && _actual == y.der){
                _actual = y;
                y = y.padre;
              }
              _actual = y;
              return y.valor;
            }
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
