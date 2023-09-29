package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    
    private int tamaño;
    // Completar atributos privados

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo prev;
        Nodo(T v){
         valor = v;
         sig = null;
         prev = null;
        }
    }

    public ListaEnlazada() {
        primero = null;
       
        tamaño = 0;
    }

    public int longitud() {
        return tamaño;
    }

    public void agregarAdelante(T elem) {
       Nodo nuevo = new Nodo(elem);
       nuevo.sig = primero;
       if (primero != null){
       primero.prev = nuevo;
       }
       primero = nuevo;
    
       tamaño++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null){
            primero =  nuevo;
        }else{
            Nodo actual = primero;
            while(actual.sig != null){
                actual = actual.sig;
            }
             nuevo.prev = actual; 
             actual.sig = nuevo;
              
            }
        tamaño++;
        }

    public T obtener(int i) {
        T res; 
        if ( i == 0){
            
            res = primero.valor;
         }else{
            Nodo actual = primero;
            for(int j = 0; j < i;j++){
               actual = actual.sig;

            }
           res = actual.valor; 
        }
        return res;
    }


    public void eliminar(int i) {
        if ( i == 0){
            primero = primero.sig;
            if(primero != null){
            primero.prev = null;
            }
        }else{
            Nodo actual = primero;
            for(int j = 0; j < i; j++){
        
                actual = actual.sig;
                
            }
           
            if (actual.sig!= null){
              actual.sig.prev = actual.prev;
            }
        
            actual.prev.sig = actual.sig;
        }


        
        tamaño--;
    }

    public void modificarPosicion(int indice, T elem) {
    
        Nodo actual = primero;
        for (int j = 0; j < indice;j++){
              actual = actual.sig;
        }
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        
        ListaEnlazada<T> res = new ListaEnlazada();
        
           Nodo actual = primero;
            for(int i = 0; i < tamaño ;i++){   
            res.agregarAtras(actual.valor);
            actual = actual.sig;    
        }
        
    
    
       return res;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
       ListaEnlazada<T> copia  = lista.copiar();
       tamaño = copia.longitud();
       if (tamaño != 0){
        T valor = copia.obtener(0);
        primero = new Nodo(valor);
        if (tamaño > 1){
            primero.sig = copia.
        }
       }
    }
        
       
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
