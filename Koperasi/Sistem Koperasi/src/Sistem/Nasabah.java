package Sistem;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Nasabah {
    private IntegerProperty id;
    private StringProperty nama;
    private StringProperty alamat;
    private ArrayList <Rekening> listRek;
    
    public Nasabah(Integer _id, String _nama, String _alamat, ArrayList<Rekening> _listRek) {
        id = new SimpleIntegerProperty(_id);
        nama = new SimpleStringProperty(_nama);
        alamat = new SimpleStringProperty(_alamat);
        listRek = _listRek;
    }
    
    public Nasabah(Integer _id, String _nama, String _alamat, Rekening _rekening) {
        listRek = new ArrayList<>();

        id = new SimpleIntegerProperty(_id);
        nama = new SimpleStringProperty(_nama);
        alamat = new SimpleStringProperty(_alamat);
        listRek.add(_rekening);
    }
    
    public Integer getID() {
        return id.get();
    }
    
    //public void setNasabahID(Integer _id) { id.set(_id); }
    
    public String getNama() {
        return nama.get();
    }
    
    //public void setNama(String _nama) { nama.set(_nama); }
    
    public String getAlamat() {
        return alamat.get();
    }
    
    //public void setAlamat(String _alamat) { alamat.set(_alamat); }
    
    public ArrayList<Rekening> getRekenings() {
        return listRek;
    }
    
    //public void setRekenings(ArrayList<Rekening> _listRek) { listRek = _listRek; }
    
    //public IntegerProperty nasabahIDProperty(){ return id; }
    
    //public StringProperty namaProperty(){ return nama; }
    
    //public StringProperty alamatProperty(){ return alamat; }
}
