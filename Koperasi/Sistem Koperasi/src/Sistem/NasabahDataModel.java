package Sistem;

import Database.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NasabahDataModel {
    public final Connection connection;
    
    public NasabahDataModel(String _driver) throws SQLException {
        connection = DBHelper.getConnection(_driver);
    }
    
    public void addNasabah(Individu _individu) throws SQLException{
        String sqlNasabah = "INSERT INTO nasabah (nasabah_id, nama, alamat)" + " VALUES (?,?,?)";
        String sqlIndividu = "INSERT INTO individu (nasabah_id, nik, npwp)" + " VALUES (?,?,?)";
        String sqlRekening = "INSERT INTO rekening (no_rekening, saldo, nasabah_id)" + " VALUES (?,?,?)";

        PreparedStatement stmtNb = connection.prepareStatement(sqlNasabah);
        PreparedStatement stmtIndividu = connection.prepareStatement(sqlIndividu);
        PreparedStatement stmtRekening = connection.prepareStatement(sqlRekening);

        stmtNb.setInt(1, _individu.getID());
        stmtNb.setString(2, _individu.getNama());
        stmtNb.setString(3, _individu.getAlamat());
        stmtNb.execute();

        stmtIndividu.setInt(1, _individu.getID());
        stmtIndividu.setString(2, _individu.getNik());
        stmtIndividu.setString(3, _individu.getNpwp());
        stmtIndividu.execute();

        stmtRekening.setInt(1, _individu.getRekenings().get(0).getRek());
        stmtRekening.setDouble(2, _individu.getRekenings().get(0).getSaldo());
        stmtRekening.setInt(3, _individu.getID());
        stmtRekening.execute();
    }
    
    public void addNasabah(Perusahaan _perusahaan) throws SQLException{
        String sqlNasabah = "INSERT INTO nasabah (nasabah_id, nama, alamat)" + " VALUES (?,?,?)";
        String sqlPerusahaan = "INSERT INTO perusahaan (nasabah_id, nib)" + " VALUES (?,?)";
        String sqlRekening = "INSERT INTO rekening (no_rekening, saldo, nasabah_id)" + " VALUES (?,?,?)";

        PreparedStatement stmtNb = connection.prepareStatement(sqlNasabah);
        PreparedStatement stmtIndividu = connection.prepareStatement(sqlPerusahaan);
        PreparedStatement stmtRekening = connection.prepareStatement(sqlRekening);

        stmtNb.setInt(1, _perusahaan.getID());
        stmtNb.setString(2, _perusahaan.getNama());
        stmtNb.setString(3, _perusahaan.getAlamat());
        stmtNb.execute();

        stmtIndividu.setInt(1, _perusahaan.getID());
        stmtIndividu.setString(2, _perusahaan.getNib());
        stmtIndividu.execute();

        stmtRekening.setInt(1, _perusahaan.getRekenings().get(0).getRek());
        stmtRekening.setDouble(2, _perusahaan.getRekenings().get(0).getSaldo());
        stmtRekening.setInt(3, _perusahaan.getID());
        stmtRekening.execute();
    }
    
    public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();

        String sql="SELECT nasabah_id, nama, alamat, nik, npwp " + "FROM nasabah NATURAL JOIN individu ORDER BY nama";

        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT no_rekening, saldo " + "FROM rekening WHERE nasabah_id=" + rs.getInt(1);

                ResultSet rsRekening = connection.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();

                while (rsRekening.next()){
                    dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }

                data.add(new Individu(rs.getString(4), rs.getString(5), rs.getInt(1),
                        rs.getString(2),rs.getString(3), dataRekening));
            }

        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    public ObservableList<Perusahaan> getPerusahaan(){
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();

        String sql="SELECT nasabah_id, nama, alamat, nib " + "FROM nasabah NATURAL JOIN perusahaan ORDER BY nama";

        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()){
                String sqlRekening = "SELECT no_rekening, saldo " + "FROM rekening WHERE nasabah_id=" + rs.getInt(1);

                ResultSet rsRekening = connection.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();

                while (rsRekening.next()){
                    dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                data.add(new Perusahaan(rs.getString(4),rs.getInt(1),rs.getString(2),rs.getString(3), dataRekening));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    public ObservableList<Rekening> getRekening (int _id){
        ObservableList<Rekening> data = FXCollections.observableArrayList();

        String sql="SELECT no_rekening, saldo FROM rekening WHERE nasabah_id=" + _id;

        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);

            while (rs.next()){
                data.add(new Rekening(rs.getInt(1),rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }
    
    public int nextID() throws SQLException{
        String sql="SELECT MAX(nasabah_id) from nasabah";

        ResultSet rs = connection.createStatement().executeQuery(sql);

        while (rs.next()){
                return rs.getInt(1) == 0 ? 1001 : rs.getInt(1) + 1;
            }
        return 1001;
    }
    
    public int nextRek(int _id) throws SQLException{
        String sql="SELECT MAX(no_rekening) FROM rekening WHERE nasabah_id=" + _id;

        ResultSet rs = connection.createStatement().executeQuery(sql);

        while (rs.next()){
                return rs.getInt(1)+1;
            }

        return 0;
    }
    
    public void addRekening(int _id, Rekening _rekening) throws SQLException{
        String insertNb = "INSERT INTO rekening (nasabah_id, no_rekening, saldo)"
                + " VALUES (?,?,?)";
  
        PreparedStatement stmtNb = connection.prepareStatement(insertNb);

        stmtNb.setInt(1, _id);
        stmtNb.setInt(2, _rekening.getRek());
        stmtNb.setDouble(3, _rekening.getSaldo());
        stmtNb.execute();
    }
    
    public void updateSaldo(int _rek, double _saldo) throws SQLException{
        String updateNb = "UPDATE rekening SET saldo = ? WHERE no_rekening = ?";
        
        PreparedStatement stmtNb = connection.prepareStatement(updateNb);

        stmtNb.setDouble(1, _saldo);
        stmtNb.setInt(2, _rek);
        stmtNb.execute();
    }
}
