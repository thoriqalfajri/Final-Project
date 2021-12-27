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
    
    //public void setSaldo(double _saldo) { saldo.set(_saldo); }
    
    public Integer getRek() { return rek.get(); }
    
    //public void setRek(int _rek) { rek.set(_rek); }
    
    //public void tambahSaldo(double _jumlah){ saldo.set(this.saldo.get() + _jumlah); }
    
    //public void tarikSaldo(double _jumlah){ saldo.set(this.saldo.get() - _jumlah); }
    
    //public IntegerProperty rekProperty(){ return rek; }
    
    //public DoubleProperty saldoProperty(){ return saldo; }
}
