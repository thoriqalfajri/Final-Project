package Sistem;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Perusahaan extends Nasabah{
    private StringProperty nib;
    
    public Perusahaan(String _nib, Integer _id, String _nama, String _alamat, ArrayList<Rekening> _listRek) {
        super(_id, _nama, _alamat, _listRek);

        nib = new SimpleStringProperty(_nib);
    }
    
    public Perusahaan(String _nib, Integer _id, String _nama, String _alamat, Rekening _rekening) {
        super(_id, _nama, _alamat, _rekening);

        nib = new SimpleStringProperty(_nib);
    }
    
    public String getNib() {
        return nib.get();
    }
    
    //public void setNib(String _nib) { nib.set(_nib); }
    
    //public StringProperty nibProperty() { return nib; }
}
