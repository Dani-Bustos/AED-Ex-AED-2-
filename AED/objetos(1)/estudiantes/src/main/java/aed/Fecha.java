package aed;

public class Fecha {
    private int dia;
    private int mes;
    

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia();
        this.mes = fecha.mes();
    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }
    @Override
    public String toString() {
    StringBuffer sbuffer = new StringBuffer();
    sbuffer.append(dia);       
    sbuffer.append("/");
    sbuffer.append(mes); 
    return sbuffer.toString();    
}

    @Override
    public boolean equals(Object otra) {
        boolean oen = (otra == null);
        boolean cd = otra.getClass() != this.getClass();
        if (cd || oen){
            return false;
        }
        
        Fecha otraFecha = (Fecha) otra;
        
        if (mes == otraFecha.mes() && dia == otraFecha.dia()){
             return true;
        }else{
            return false;
        }
    }

    public void incrementarDia() {
        if (dia == diasEnMes(mes)){
            if(mes == 12){
                mes = 1;
                dia = 1;
            }else{
                mes++;
                dia = 1;
            }
        }else{
            dia++;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
