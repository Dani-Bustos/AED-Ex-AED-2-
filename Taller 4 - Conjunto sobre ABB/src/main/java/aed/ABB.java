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
        Nodo ultima_pos = buscarNodo(elem, _raiz);
        cantidad--;
        if(ultima_pos == null || ultima_pos.valor.compareTo(elem ) != 0){
            cantidad++;
            return;
            
        }else if (ultima_pos.der == null && ultima_pos.izq == null){
            if(ultima_pos.padre.der == ultima_pos){
                ultima_pos.padre.der = null;
            }else if (ultima_pos.padre.izq == ultima_pos){
                ultima_pos.padre.izq = null;
            }
        
        }else if (ultima_pos.der == null || ultima_pos.izq != null){ // si no hay derecha, el predecesor reemplaza al borrado
               Nodo pre = predecesor(ultima_pos.izq);
               if (pre.izq != null){
                  pre.izq.padre = pre.padre;
                  pre.padre.der = pre.izq;
                }
                 pre.padre = ultima_pos.padre;
                 pre.izq = ultima_pos.izq;
                 pre.der = ultima_pos.der;
                
                 //Cambiamos referencias del padre
                 if(ultima_pos.padre != null){
                    if(ultima_pos.padre.valor.compareTo(ultima_pos.valor) < 0){
                        ultima_pos.padre.der = pre;
                    }else{
                        ultima_pos.padre.izq = pre;
                    }
                 }
        }else if(ultima_pos.der != null){
            Nodo suc = sucesor(ultima_pos.der);
             if (suc.der != null){
                  suc.der.padre = suc.padre;
                  suc.padre.izq = suc.der;
                }
                 suc.padre = ultima_pos.padre;
                 suc.izq = ultima_pos.izq;
                 suc.der = ultima_pos.der;
                 
                 //Cambiamos referencias del padre
                 if(ultima_pos.padre != null){
                    if(ultima_pos.padre.valor.compareTo(ultima_pos.valor) < 0){
                        ultima_pos.padre.der = suc;
                    }else{
                        ultima_pos.padre.izq = suc;
                    }
        
                }
            }
        
    }

    private Nodo sucesor(Nodo actual){
        if(actual.izq == null){
            return actual;
        }else{
            return predecesor(actual.izq);
        }
    }

    private Nodo predecesor(Nodo actual){
       if (actual.der == null){
        return actual;
       }else{
        return predecesor(actual.der);
       }
    }
    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
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
