package aed;

public class Horario {
    
    private int hora;
    private int minutos;
    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public String toString() {
       StringBuffer sbuffer = new StringBuffer();
       sbuffer.append(hora);
       sbuffer.append(":");
       sbuffer.append(minutos);
       return sbuffer.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean cd = (otro == null);
        boolean difClass = (otro.getClass() != this.getClass());
        if(difClass || cd ){
            return false;
        }
        Horario otroHorario = (Horario) otro;
        if(hora == otroHorario.hora() && minutos == otroHorario.minutos()){

            return true;
        }else{
            return false;
        }
    }

}
