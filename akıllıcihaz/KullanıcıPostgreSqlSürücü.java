/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Yusuf
 */
public class KullanıcıPostgreSqlSürücü implements IKullanıcıVeriTabanıServisi {

    private Connection baglan() {
        System.out.println("Veri tabanına bağlanılıyor...");
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Veri tabanına bağlanılıyor...");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User", "postgres", "0508");
            if (conn != null) {
                System.out.println("Veritabanına bağlandı");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Veritabanına bağlandı");
            } else {
                System.out.println("Bağlantı girişimi başarısız");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Bağlantı girişimi başarısız...");
            }

        } catch (Exception e) {
        }

        return conn;
    }

    public Kullanıcı kullanıcıAra(String kullanıcıadi, String sifre) {
        
        Kullanıcı kullanıcı = null;

        String sql = "SELECT * FROM \"users\" WHERE \"id\" ='" + kullanıcıadi + "' and \"password\"='" + sifre + "'";   //sorgumuz  and \"sifre\"=$sifre
        
        Connection conn = this.baglan();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);  // rs değişkeni aranan değeri içeriyo
            conn.close();

            String id, password;
            while (rs.next()) {
                id = rs.getString("id");
                password = rs.getString("password");
                kullanıcı = new Kullanıcı(id, password);
            }
            System.out.println("Kullanıcı aranıyor...");
             LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Kullanıcı aranıyor...");
            rs.close();
            stmt.close();

        } catch (Exception e) {
        }

        return kullanıcı;
    }
}
