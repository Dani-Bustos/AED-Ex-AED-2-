package aed;

import java.util.Vector;

public class Agenda {
    private Recordatorio[] _recordatorios;
    private Fecha _fecha;


    public Agenda(Fecha fechaActual) {
        _fecha = fechaActual;
        _recordatorios  = new Recordatorio[0];
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        Recordatorio [] nuevoRecordatorios = new Recordatorio[_recordatorios.length+1];    
        for( int i = 0; i < _recordatorios.length; i++){
           nuevoRecordatorios[i] = _recordatorios[i];
     }
        nuevoRecordatorios[_recordatorios.length] = recordatorio;
        _recordatorios = nuevoRecordatorios;
   
    }


    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(_fecha);
        sbuffer.append("\n=====\n");
        for(int i = 0; i < _recordatorios.length;i++){
           if (_recordatorios[i].fecha().equals(_fecha)){
           sbuffer.append(_recordatorios[i].toString());
           sbuffer.append("\n");
           }
        }
        return sbuffer.toString();
    
    }

    public void incrementarDia() {
        _fecha.incrementarDia();

    }

    public Fecha fechaActual() {
        Fecha fechabuffer = new Fecha(_fecha);
        return fechabuffer;
    }

}
