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
        if(actual.valor == elem){
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
        }else if(ultimo_Nodo.valor == elem){
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
       if (ult == null){
        
       }else if (ult.valor == elem){
        res = true;
        
       }
      return res;
    }

    public void eliminar(T elem){
        Nodo A_borrar = buscarNodo(elem, _raiz);
        cantidad--;
        Boolean es_raiz = false;
        if (_raiz == null || A_borrar.valor != elem){
            cantidad++;
            return;
        }
        if(A_borrar.valor == _raiz.valor){ es_raiz = true;  }
        
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
            }else{
                A_borrar.padre.der = A_borrar.izq;
            }
        
        }else if(A_borrar.izq == null){ //Solo Hijo Derecho
            
            if (es_raiz){_raiz = _raiz.der;_raiz.padre = null;}
            
            else if(A_borrar.valor.compareTo(A_borrar.padre.valor) < 0){
                A_borrar.padre.izq = A_borrar.der;
            }else{
                A_borrar.padre.der = A_borrar.der;
            }
        }else{ //dosHijos
          Nodo suc = sucesor(A_borrar.der);
          
            
           A_borrar.valor = suc.valor;
           if(suc.der != null){
            suc.der.padre = suc.padre;
            if(suc.padre != A_borrar){
            suc.padre.izq = suc.der;}else{A_borrar.der = suc.der;}
           }else{suc.padre.izq = null;}
         
            
          

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
    
    

    public String toString(){
        
        String res = inOrder(_raiz);
        res = res.substring(0, res.length()-1);
        res = "{" + res + "}";
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;
        
        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
