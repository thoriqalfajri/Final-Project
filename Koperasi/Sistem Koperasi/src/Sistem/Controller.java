package Sistem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {
    NasabahDataModel nasabahDataModel;

    //region Individu

    @FXML
    private Label lbStatusIndividu;

    @FXML
    private TextField
            tfIdIndividu,
            tfNamaIndividu,
            tfAlamatIndividu,
            tfNikIndividu,
            tfNpwpIndividu,
            tfRekIndividu,
            tfSaldoIndividu,
            tfIdIndividu_Baru,
            tfRekIndividu_Baru,
            tfSaldoIndividu_Baru;

    @FXML
    private Button btnTambahIndividu,
            btnRefreshIndividu,
            btnHapusIndividu,
            btnTambahIndividu_Baru;

    @FXML
    private TableView<Individu> tvNasabahIndividu;

    @FXML
    private TableColumn<Individu, Integer> colIdNasabahIndividu;

    @FXML
    private TableColumn<Individu, String> colNamaIndividu;

    @FXML
    private TableColumn<Individu, String> colAlamatIndividu;

    @FXML
    private TableColumn<Individu, String> colNikIndividu;

    @FXML
    private TableColumn<Individu, String> colNpwpIndividu;

    @FXML
    private TableView<Rekening> tvRekIndividu;

    @FXML
    private TableColumn<Rekening, Integer> colRekIndividu;

    @FXML
    private TableColumn<Rekening, Double> colSaldoIndividu;

    @FXML
    private AnchorPane apRekIndividu_Baru;

    //--------------------------------------------------------------------------------//

    @FXML
    void btnHapusIndividu(ActionEvent event) {
        try {
            tfIdIndividu.setText("" + nasabahDataModel.nextID());
            tfRekIndividu.setText(tfIdIndividu.getText() + "01");
            tfNamaIndividu.setText("");
            tfAlamatIndividu.setText("");
            tfNikIndividu.setText("");
            tfNpwpIndividu.setText("");
            tfSaldoIndividu.setText("");
            tfIdIndividu_Baru.setText("");
            tfSaldoIndividu_Baru.setText("");

            tvRekIndividu.setItems(null);

            apRekIndividu_Baru.setVisible(false);

            btnTambahIndividu.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnRefreshIndividu(ActionEvent event) {
        ObservableList<Individu> data = nasabahDataModel.getIndividu();

        colIdNasabahIndividu.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNamaIndividu.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatIndividu.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNikIndividu.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colNpwpIndividu.setCellValueFactory(new PropertyValueFactory<>("npwp"));

        tvNasabahIndividu.setItems(null);
        tvNasabahIndividu.setItems(data);

        apRekIndividu_Baru.setVisible(false);

        btnTambahIndividu.setDisable(false);
    }

    @FXML
    void btnTambahIndividu(ActionEvent event) {
        Individu individu = new Individu(tfNikIndividu.getText(), tfNpwpIndividu.getText(),
                Integer.parseInt(tfIdIndividu.getText()), tfNamaIndividu.getText(),
                tfAlamatIndividu.getText(), new Rekening(Integer.parseInt(tfRekIndividu.getText()),
                Double.parseDouble(tfSaldoIndividu.getText())));

        try {
            nasabahDataModel.addNasabah(individu);

            btnRefreshIndividu.fire();
            btnHapusIndividu.fire();
            btnRefreshPerusahaan.fire();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnTambahRekIndividu(ActionEvent event) {
        try {
            Rekening rek = new Rekening(Integer.parseInt(tfRekIndividu_Baru.getText()),
                    Double.parseDouble(tfSaldoIndividu_Baru.getText()));

            nasabahDataModel.addRekening(Integer.parseInt(tfIdIndividu_Baru.getText()),rek);

            viewRekIndividu(Integer.parseInt(tfIdIndividu_Baru.getText()));

            btnRefreshIndividu.fire();

            tfSaldoIndividu_Baru.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewRekIndividu(int _id){
        ObservableList<Rekening> data = nasabahDataModel.getRekening(_id);

        colRekIndividu.setCellValueFactory(new PropertyValueFactory<>("rek"));
        colSaldoIndividu.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        tvRekIndividu.setItems(null);
        tvRekIndividu.setItems(data);
    }

    //endregion

    //region Perusahaan

    @FXML
    private Label lbStatusPerusahaan;

    @FXML
    private TextField
            tfIdPerusahaan,
            tfNamaPerusahaan,
            tfAlamatPerusahaan,
            tfNibPerusahaan,
            tfRekPerusahaan,
            tfSaldoPerusahaan,
            tfIdPerusahaan_Baru,
            tfRekPerusahaan_Baru,
            tfSaldoPerusahaan_Baru;

    @FXML
    private Button
            btnTambahPerusahaan,
            btnRefreshPerusahaan,
            btnHapusPerusahaan,
            btnTambahPerusahaan_Baru;

    @FXML
    private TableView<Perusahaan> tvNasabahPerusahaan;

    @FXML
    private TableColumn<Perusahaan, Integer> colIdNasabahPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNamaPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colAlamatPerusahaan;

    @FXML
    private TableColumn<Perusahaan, String> colNibPerusahaan;

    @FXML
    private TableView<Rekening> tvRekPerusahaan;

    @FXML
    private TableColumn<Rekening, Integer> colRekPerusahaan;

    @FXML
    private TableColumn<Rekening, Double> colSaldoPerusahaan;

    @FXML
    private AnchorPane apRekPerusahaan_Baru;

    //--------------------------------------------------------------------------------//

    @FXML
    void btnHapusPerusahaan(ActionEvent event) {
        try {
            tfIdPerusahaan.setText("" + nasabahDataModel.nextID());
            tfRekPerusahaan.setText(tfIdPerusahaan.getText() + "01");
            tfNamaPerusahaan.setText("");
            tfAlamatPerusahaan.setText("");
            tfNibPerusahaan.setText("");
            tfSaldoPerusahaan.setText("");
            tfSaldoPerusahaan_Baru.setText("");

            tvRekPerusahaan.setItems(null);

            apRekPerusahaan_Baru.setVisible(false);

            btnTambahPerusahaan.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnRefreshPerusahaan(ActionEvent event) {
        ObservableList<Perusahaan> data = nasabahDataModel.getPerusahaan();

        colIdNasabahPerusahaan.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colNamaPerusahaan.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatPerusahaan.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNibPerusahaan.setCellValueFactory(new PropertyValueFactory<>("nib"));

        tvNasabahPerusahaan.setItems(null);
        tvNasabahPerusahaan.setItems(data);

        apRekPerusahaan_Baru.setVisible(false);

        btnTambahPerusahaan.setDisable(false);
    }

    @FXML
    void btnTambahPerusahaan(ActionEvent event) {
        Perusahaan perusahaan = new Perusahaan(tfNibPerusahaan.getText(),
                Integer.parseInt(tfIdPerusahaan.getText()), tfNamaPerusahaan.getText(),
                tfAlamatPerusahaan.getText(), new Rekening(Integer.parseInt(tfRekPerusahaan.getText()),
                Double.parseDouble(tfSaldoPerusahaan.getText())));

        try {
            nasabahDataModel.addNasabah(perusahaan);

            btnRefreshPerusahaan.fire();
            btnHapusPerusahaan.fire();
            btnRefreshIndividu.fire();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnTambahRekPerusahaan(ActionEvent event) {
        try {
            Rekening rek = new Rekening(Integer.parseInt(tfRekPerusahaan_Baru.getText()),
                    Double.parseDouble(tfSaldoPerusahaan_Baru.getText()));

            nasabahDataModel.addRekening(Integer.parseInt(tfIdPerusahaan_Baru.getText()), rek);

            viewRekPerusahaan(Integer.parseInt(tfIdPerusahaan_Baru.getText()));

            btnRefreshPerusahaan.fire();

            tfSaldoPerusahaan_Baru.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewRekPerusahaan(int _id){
        ObservableList<Rekening> data = nasabahDataModel.getRekening(_id);

        colRekPerusahaan.setCellValueFactory(new PropertyValueFactory<>("rek"));
        colSaldoPerusahaan.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        tvRekPerusahaan.setItems(null);
        tvRekPerusahaan.setItems(data);
    }

    //endregion

    //region Transaksi (Tambah)

    @FXML
    private Label lbStatusTransaksi_Tambah;

    @FXML
    private TextField
            tfCariIdTransaksi_Tambah,
            tfIdTransaksi_Tambah,
            tfRekTransaksi_Tambah,
            tfSaldoTransaksi_Tambah_Baru;

    @FXML
    private Button
            btnTambahTransaksi,
            btnHapusTransaksi_Tambah,
            btnCariIdTransaksi_Tambah;

    @FXML
    private TableView<Rekening> tvRekTransaksi_Tambah;

    @FXML
    private TableColumn<Rekening, Integer> colRekTransaksi_Tambah;

    @FXML
    private TableColumn<Rekening, Double> colSaldoTransaksi_Tambah;


    //--------------------------------------------------------------------------------//

    @FXML
    void btnHapusTransaksi_Tambah(ActionEvent event) {
        tfCariIdTransaksi_Tambah.setText("");
        tfIdTransaksi_Tambah.setText("");
        tfRekTransaksi_Tambah.setText("");
        tfSaldoTransaksi_Tambah_Baru.setText("");

        btnTambahTransaksi.setDisable(true);

        tvRekTransaksi_Tambah.setItems(null);
    }

    @FXML
    void btnTambahSaldoTransaksi(ActionEvent event) {
        Rekening rek = tvRekTransaksi_Tambah.getSelectionModel().getSelectedItem();

        try {
            nasabahDataModel.updateSaldo(rek.getRek(), Double.parseDouble(tfSaldoTransaksi_Tambah_Baru.getText())<=0
                    ?rek.getSaldo():rek.getSaldo()+Double.parseDouble(tfSaldoTransaksi_Tambah_Baru.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnHapusTransaksi_Tambah.fire();
    }

    @FXML
    void btnCariIdTransaksi_Tambah(ActionEvent event) {
        tfIdTransaksi_Tambah.setText("");
        tfRekTransaksi_Tambah.setText("");
        tfSaldoTransaksi_Tambah_Baru.setText("");

        btnTambahTransaksi.setDisable(true);

        tvRekTransaksi_Tambah.setItems(null);

        viewRekTambahSaldo(Integer.parseInt(tfCariIdTransaksi_Tambah.getText()));
    }

    public void viewRekTambahSaldo(int _id){
        ObservableList<Rekening> data = nasabahDataModel.getRekening(_id);

        colRekTransaksi_Tambah.setCellValueFactory(new PropertyValueFactory<>("rek"));
        colSaldoTransaksi_Tambah.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        tvRekTransaksi_Tambah.setItems(null);
        tvRekTransaksi_Tambah.setItems(data);
    }

    //endregion

    //region Transaksi (Tarik)

    @FXML
    private Label lbStatusTransaksi_Tarik;

    @FXML
    private TextField
            tfCariIdTransaksi_Tarik,
            tfIdTransaksi_Tarik,
            tfRekTransaksi_Tarik,
            tfSaldoTransaksi_Tarik_Baru;

    @FXML
    private Button
            btnTarikTransaksi,
            btnHapusTransaksi_Tarik,
            btnCariIdTransaksi_Tarik;

    @FXML
    private TableView<Rekening> tvRekTransaksi_Tarik;

    @FXML
    private TableColumn<Rekening, Integer> colRekTransaksi_Tarik;

    @FXML
    private TableColumn<Rekening, Double> colSaldoTransaksi_Tarik;

    //--------------------------------------------------------------------------------//

    @FXML
    void btnHapusTransaksi_Tarik(ActionEvent event) {
        tfCariIdTransaksi_Tarik.setText("");
        tfIdTransaksi_Tarik.setText("");
        tfRekTransaksi_Tarik.setText("");
        tfSaldoTransaksi_Tarik_Baru.setText("");

        btnTarikTransaksi.setDisable(true);

        tvRekTransaksi_Tarik.setItems(null);
    }

    @FXML
    void btnTarikSaldo(ActionEvent event) {
        Rekening rek = tvRekTransaksi_Tarik.getSelectionModel().getSelectedItem();

        try {
            nasabahDataModel.updateSaldo(rek.getRek(), rek.getSaldo()-Double.parseDouble(tfSaldoTransaksi_Tarik_Baru.getText())<=0
                    ?rek.getSaldo():rek.getSaldo()-Double.parseDouble(tfSaldoTransaksi_Tarik_Baru.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnHapusTransaksi_Tarik.fire();
    }

    @FXML
    void btnCariIdTransaksi_Tarik(ActionEvent event) {
        tfIdTransaksi_Tarik.setText("");
        tfRekTransaksi_Tarik.setText("");
        tfSaldoTransaksi_Tarik_Baru.setText("");

        btnTarikTransaksi.setDisable(true);

        tvRekTransaksi_Tarik.setItems(null);

        viewRekTarikSaldo(Integer.parseInt(tfCariIdTransaksi_Tarik.getText()));
    }

    public void viewRekTarikSaldo(int _id){
        ObservableList<Rekening> data = nasabahDataModel.getRekening(_id);

        colRekTransaksi_Tarik.setCellValueFactory(new PropertyValueFactory<>("rek"));
        colSaldoTransaksi_Tarik.setCellValueFactory(new PropertyValueFactory<>("saldo"));

        tvRekTransaksi_Tarik.setItems(null);
        tvRekTransaksi_Tarik.setItems(data);
    }

    //endregion

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nasabahDataModel = new NasabahDataModel("MYSQL");
            setStatus(nasabahDataModel.connection !=null?"Terkoneksi":"");

            tfIdIndividu.setText(Integer.toString(nasabahDataModel.nextID()));
            tfRekIndividu.setText(Integer.toString(nasabahDataModel.nextID()) + "01");

            tfIdPerusahaan.setText(Integer.toString(nasabahDataModel.nextID()));
            tfRekPerusahaan.setText(Integer.toString(nasabahDataModel.nextID()) + "01");

            btnHapusIndividu.fire();
            btnRefreshIndividu.fire();

            btnHapusPerusahaan.fire();
            btnRefreshPerusahaan.fire();

            apRekIndividu_Baru.setVisible(false);
            apRekPerusahaan_Baru.setVisible(false);

            btnTambahTransaksi.setDisable(true);
            btnTarikTransaksi.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tvNasabahIndividu.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tvNasabahIndividu.getSelectionModel().getSelectedItem() != null){
                Individu individu = tvNasabahIndividu.getSelectionModel().getSelectedItem();

                viewRekIndividu(individu.getID());

                apRekIndividu_Baru.setVisible(true);

                btnTambahIndividu.setDisable(true);

                tfIdIndividu_Baru.setText("" + individu.getID());

                try {
                    tfRekIndividu_Baru.setText("" + nasabahDataModel.nextRek(individu.getID()));
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tvNasabahPerusahaan.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tvNasabahPerusahaan.getSelectionModel().getSelectedItem() != null){
                Perusahaan perusahaan = tvNasabahPerusahaan.getSelectionModel().getSelectedItem();

                viewRekPerusahaan(perusahaan.getID());

                apRekPerusahaan_Baru.setVisible(true);

                btnTambahPerusahaan.setDisable(true);

                tfIdPerusahaan_Baru.setText("" + perusahaan.getID());

                try {
                    tfRekPerusahaan_Baru.setText("" + nasabahDataModel.nextRek(perusahaan.getID()));
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tvRekTransaksi_Tambah.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tvRekTransaksi_Tambah.getSelectionModel().getSelectedItem() != null){

                Rekening rekening = tvRekTransaksi_Tambah.getSelectionModel().getSelectedItem();

                tfRekTransaksi_Tambah.setText("" + rekening.getRek());

                tfIdTransaksi_Tambah.setText(tfCariIdTransaksi_Tambah.getText());

                btnTambahTransaksi.setDisable(false);
            }
        });
        
        tvRekTransaksi_Tarik.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if (tvRekTransaksi_Tarik.getSelectionModel().getSelectedItem() != null){
                Rekening rek = tvRekTransaksi_Tarik.getSelectionModel().getSelectedItem();

                tfRekTransaksi_Tarik.setText("" + rek.getRek());

                tfIdTransaksi_Tarik.setText(tfCariIdTransaksi_Tarik.getText());

                btnTarikTransaksi.setDisable(false);
            }
        });
    }
    
    public void setStatus(String _status){
        lbStatusIndividu.setText(_status);
        lbStatusPerusahaan.setText(_status);
        lbStatusTransaksi_Tambah.setText(_status);
        lbStatusTransaksi_Tarik.setText(_status);
    }
}
