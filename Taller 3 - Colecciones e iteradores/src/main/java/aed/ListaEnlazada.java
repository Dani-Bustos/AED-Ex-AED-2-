package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
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
       if (primero == null || ultimo == null){
          primero = nuevo;
          ultimo = nuevo;
       }else{
        nuevo.sig = primero;
        if(primero!= null){
            primero.prev = nuevo;
    
        }
       }
       tamaño++;
       primero = nuevo;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
       if (primero == null || ultimo == null){
        primero = nuevo;
        ultimo = nuevo;
       } else{
         ultimo.sig = nuevo;
         nuevo.prev = ultimo;
         ultimo = nuevo;
         
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
       tamaño = copia.tamaño;
       primero = copia.primero;
       ultimo = copia.ultimo;
       
    }
        
       
    
    
    @Override
    public String toString() {
        StringBuffer l_buffer = new StringBuffer();
        l_buffer.append("[");
        Nodo actual = primero;
        for(int i = 0; i < tamaño-1;i++){
            l_buffer.append(actual.valor);
            l_buffer.append(", ");
            actual = actual.sig;
        }
         l_buffer.append(actual.valor);
         l_buffer.append("]");
         return l_buffer.toString();
    
    }

    private class ListaIterador implements Iterador<T> {
    	private Nodo dedito;
        private int pos;
        ListaIterador(){
            dedito = primero;
            pos = 0;
        }
        public boolean haySiguiente() {
	        boolean res;
            if(pos > tamaño-1){
                res = false;
            }else{
                res = true;
            }
    
           return res;
        }
        
        public boolean hayAnterior() {
	        boolean res;
            if(pos < 1){
                res = false;
            }else{
                res = true;
            }
           
            return res;
        }

        public T siguiente() {
	        
            T res = dedito.valor;
            dedito = dedito.sig;
            pos++;
            return res;

        }
        

        public T anterior() {
            T res;
            if ( pos == tamaño){
                dedito = ultimo;
                res = dedito.valor;
                  
            }else{
             dedito = dedito.prev;
             res = dedito.valor;
            }
            pos--;
            return res;
        }
    }

    public Iterador<T> iterador() {
	    Iterador <T> it = new ListaIterador();
        return it;
    }

}
