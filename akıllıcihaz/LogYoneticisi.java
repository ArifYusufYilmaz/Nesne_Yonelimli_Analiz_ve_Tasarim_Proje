/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author Yusuf
 */
public class LogYoneticisi {

    private static LogYoneticisi instance;
    private PrintWriter out;

    private LogYoneticisi(String logDosyasi) {
        try {
            out = new PrintWriter(new FileWriter(logDosyasi, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized LogYoneticisi getInstance(String logDosyasi) {
        if (instance == null) {
            instance = new LogYoneticisi(logDosyasi);
        }
        return instance;
    }

    public void dosyayaYaz(String mesaj) {
        out.println(LocalDateTime.now() + ":" + mesaj);
    }
}
