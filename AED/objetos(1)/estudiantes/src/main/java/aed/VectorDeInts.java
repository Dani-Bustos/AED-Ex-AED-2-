package aed;
class VectorDeInts implements SecuenciaDeInts {
 
   private int[] _secuencia;
   private int l;  
    public VectorDeInts() {
        
        l = 0;
         _secuencia = new int[0];
    }

    public VectorDeInts(VectorDeInts vector) {
        _secuencia = new int[0];
        for(int i = 0; i < vector.longitud();i++){
          agregarAtras(vector.obtener(i));
        }

        
    }

    public int longitud() {
    
       return l; 
    
    }

    public void agregarAtras(int i) {
        int[] nueva = new int[l + 1];
        
        for(int j = 0; j < l; j++){
            nueva[j] = _secuencia[j];

        }
        nueva[l] = i;
        _secuencia = nueva;
        l++;

    }

    public int obtener(int i) {
        return _secuencia[i];
    }

    public void quitarAtras() {
        int[] nueva = new int[l-1];
        for(int i = 0; i < l-1 ; i++){

            nueva[i] =  _secuencia[i];
        }
        _secuencia = nueva;
        l--;
    }

    public void modificarPosicion(int indice, int valor) {
        _secuencia[indice] = valor;
    }

    public VectorDeInts copiar() {
        VectorDeInts nuevo = new VectorDeInts();

        for(int i = 0; i < l; i++){
            nuevo.agregarAtras(_secuencia[i]);
        }
        return nuevo;
        

    }

}