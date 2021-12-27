package Sistem;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rekening {
    private IntegerProperty rek;
    private DoubleProperty saldo;
    
    public Rekening(int _rek, double _saldo) {
        rek = new SimpleIntegerProperty(_rek);
        saldo = new SimpleDoubleProperty(_saldo);
    }
    
    public Double getSaldo() {
        return saldo.get();
    }
    
    
    public Integer getRek() { return rek.get(); }
    
}
