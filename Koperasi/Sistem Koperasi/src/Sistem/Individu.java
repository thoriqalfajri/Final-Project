package Sistem;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Individu extends Nasabah{
    private StringProperty nik;
    private StringProperty npwp;

    public Individu(String _nik, String _npwp, Integer _id, String _nama, String _alamat, ArrayList<Rekening> listRek) {
        super(_id, _nama, _alamat, listRek);

        nik = new SimpleStringProperty(_nik);
        npwp = new SimpleStringProperty(_npwp);
    }
    
    public Individu(String _nik, String _npwp, Integer _id, String _nama, String _alamat, Rekening _rekening) {
        super(_id, _nama, _alamat, _rekening);

        nik = new SimpleStringProperty(_nik);
        npwp = new SimpleStringProperty(_npwp);
    }
    
    public String getNik() {
        return nik.get();
    }
    
    
    public String getNpwp() {
        return npwp.get();
    }

}
