# Final Project Kelompok 1

About Project : 

Project ini berisi sistem yang dapat digunakan untuk merekam transaksi data nasabah di bank, dimana terdapat 2 jenis nasabah yaitu individu dan perusahaan. Setiap nasabah dapat memiliki 1 atau lebih rekening.

Libraries And Tools :
1. Mysql-connector-java-8.0.25
2. Scene Builder
3. Javafx-sdk-17.0.1
4. XAMPP

-------------------------------
Contributors :
1. Rafi Dinata - 2017051043
2. Silvia Rukmana - 2017051047
3. M. Thoriq Al Fajri - 2057051002

-------------------------------
Work Divisions :

1. Rafi Dinata
      - Form
      - Controller.java
      - DBHelper.java

2. Silvia Rukmana
      - Nasabah.java
      - Individu.java
      - Perusahaan.java
      - Rekening.java

3. M. Thoriq Al Fajri
      - Class Diagram
      - ER Diagram
      - NasabahDataModel.java

-------------------------------
- Class Diagram

![mermaid-diagram-20211225204434](https://user-images.githubusercontent.com/83461390/147439351-beb846d4-f2a2-4ef9-b79a-d9e937056ced.png)

      classDiagram
          Nasabah <|-- Individu
          Nasabah <|-- Perusahaan
          Nasabah "1"--o"*" Rekening : has

          class Nasabah{
            <<abstract>>
            #int id
            #String nama
            #String alamat
          }

          class Individu{
            -String nik
            -String npwp
          }
          class Perusahaan{
            -String nib
          }
          class Rekening{
            -int rek
            -double saldo
            +double getSaldo()
            +double getRek()
          }
         
- ER Diagram

![mermaid-diagram-20211225202811](https://user-images.githubusercontent.com/83461390/147439361-e84c1b9d-0a94-4a5a-8535-589122f0dcbf.png)

      erDiagram
                Nasabah ||..|| Individu : is
                Nasabah ||--|| Perusahaan : is
                Nasabah ||--|{ Rekening: "has"
                Nasabah {
                  int id
                  string nama
                  string alamat
                }
                Individu{
                  string nik
                  string npwp
                }
                Perusahaan{
                  string nib
                }
                Rekening{
                  int rek
                  double saldo
                }
                
- Class Diagram for JavaFX and Database

![mermaid-diagram-20211225202345](https://user-images.githubusercontent.com/83461390/147439376-26b4d501-c9db-4fc8-afac-f5de25abf072.png)

      classDiagram
         Nasabah <|-- Individu
         Nasabah <|-- Perusahaan
         Nasabah "1"--o"*" Rekening : has
         Nasabah o-- NasabahDataModel : Data Modeling
         NasabahDataModel <-- Controller : Data Control
         NasabahDataModel --> DBHelper : DB Connection
         Controller <.. Form : Form Control      

         class Nasabah{
           <<abstract>>
           #IntegerProperty id
           #StringProperty nama
           #StringProperty alamat
         }

         class Individu{
           -StringProperty nik
           -StringProperty npwp
         }
         class Perusahaan{
           -IntegerProperty rek
           -DoubleProperty saldo
         }
         class Rekening{
           -DoubleProperty balance
           +Double getSaldo()
           +Integer getRek()
         }

         class NasabahDataModel{
             Connection conn
             addNasabah()
             addRekening()
             getIndividu()
             getPerusahaan()
             getRekening()
             nextID()
             nextRek()
             updateSaldo()
         }

         class Controller{
             initialize()
             btnTambah()
             btnHapus()
             btnRefresh()
             viewIndividu()
             viewPerusahaan()
             viewRekening()
          }
         class DBHelper{
             - String USERNAME
             - String PASSWORD
             - String DB
             getConnection()
             getConnection(String driver)
             createTable();
         }
         
-------------------------------
