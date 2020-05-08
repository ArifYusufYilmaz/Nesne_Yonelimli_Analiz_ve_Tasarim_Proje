/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.util.Random;

/**
 *
 * @author Yusuf
 */
public class SicaklikAlgilayici {

    private int sayi;

    public SicaklikAlgilayici() {
        this.sayi = 0;
    }

    public int rastgele() {
        Random rastgele = new Random();
        sayi = rastgele.nextInt(100);
        return sayi;
    }

    public String sıcaklık(ISicaklik sicaklik, int sicaklikDegeri) {
        return "Sıcaklık Hesaplanıyor...\t" + sicaklik.sicaklikDegeri(sicaklikDegeri);
    }

    public int sicaklikOlustur(ISicaklik sicaklik) {
        return sicaklik.sicaklikOlustur(this);
    }

}
