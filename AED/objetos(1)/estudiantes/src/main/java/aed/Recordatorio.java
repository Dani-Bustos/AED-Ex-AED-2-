package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        _mensaje = mensaje;
         Fecha fechabuffer = new Fecha(fecha);
        _fecha = fechabuffer;
        _horario = horario;
    }

    public Horario horario() {
        return _horario;
    }

    public Fecha fecha() {
        Fecha fechaBuffer = new Fecha(_fecha);
        return fechaBuffer;
    }

    public String mensaje() {
        String mensajebufer = new String(_mensaje);
        return mensajebufer;
    
    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(_mensaje);
        sbuffer.append(" @ ");
        sbuffer.append(_fecha);
        sbuffer.append(" ");
        sbuffer.append(_horario);
        
        return sbuffer.toString();
    }

}
