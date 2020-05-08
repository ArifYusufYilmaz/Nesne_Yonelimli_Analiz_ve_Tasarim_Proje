/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Yusuf
 */
public class MerkeziIslemBirimi {

    private final SicaklikAlgilayici sicaklikAlgilayici;
    private final ISicaklik sicaklik;
    private final AgArayuzu arayuz;                       // bunları düzgün bir sekilde ayarla
    private final Eyleyici eyleyici;
    private final IKullanıcıVeriTabanıServisi veriTabanı;
    private Kullanıcı kullanıcı;
    private String kullaniciAdi;
    private String sifre;
    private int sicaklikDurumu;
    private final ISubject publisher;
    private final IObserver subscriber;
    private boolean sicaklikGoruntulendi;
private String sicaklikGoruntusu;
    public MerkeziIslemBirimi() {
        sicaklikAlgilayici = new SicaklikAlgilayici();
        sicaklik = new SicaklikCelcius();
        arayuz = new AgArayuzu();
        eyleyici = new Eyleyici();
        veriTabanı = new KullanıcıPostgreSqlSürücü();
        publisher = new Publisher();
        subscriber = new Subscriber();
        kullanıcı = null;
        kullaniciAdi = null;
        sifre = null;
        sicaklikDurumu = -100;  // sıcaklık görüntülenmedi
        publisher.attach(subscriber);
        sicaklikGoruntulendi = false;
        
    }

    public void basla() {

        kullaniciDogrula();
        kullanıcı = veriTabanı.kullanıcıAra(kullaniciAdi, sifre);

        if (kullanıcı != null) {
            arayuz.ekran.mesajGoruntule("Kullanıcı Bulundu, Giriş izni verildi...");
            LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Kullanıcı Bulundu, Giriş izni verildi...");

            islemYap();
        } else {
            arayuz.ekran.mesajGoruntule("Kullanıcı bulunamadı, giriş izni verilemez...");
            LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Kullanıcı bulunamadı, giriş izni verilemez...");
        }

    }

    private boolean sicaklikGoruntulendiMi() {
        return sicaklikGoruntulendi = true;
    }

    private void sicaklikGoruntule(int sicaklikDurumu) {

        this.sicaklikDurumu = sicaklikDurumu;

         sicaklikGoruntusu = sicaklikAlgilayici.sıcaklık(sicaklik, this.sicaklikDurumu);
        arayuz.ekran.mesajGoruntule(sicaklikGoruntusu);
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz(sicaklikGoruntusu);
        if (kritikBilgilendirme()) {
            publisher.notify("Sıcaklık Seviyesi Çok Yüksek, LÜTFEN SOĞUTUCUYU ÇALIŞTIRIN!!!");
            LogYoneticisi.getInstance("Log.txt").dosyayaYaz("<BİLGİLENDİRME> Sıcaklık Seviyesi Çok Yüksek, LÜTFEN SOĞUTUCUYU ÇALIŞTIRIN!!!");
        } else {
            publisher.notify("Sıcaklık Seviyesi Normal, Soğutucu İsteğe Göre Çalıştırılabilir...");
            LogYoneticisi.getInstance("Log.txt").dosyayaYaz("<BİLGİLENDİRME> Sıcaklık Seviyesi Normal, Soğutucu İsteğe Göre Çalıştırılabilir...");
        }
        sicaklikGoruntulendiMi();   // sıcaklıgın görüntülendiğini döndürür.

    }

    private boolean kritikBilgilendirme() {

        switch (sicaklik.birim()) {     // 1 ise Celcius değerine, 2 ise Kelvin değerine göre kontrol yapıır, genişletilebilir.
            case 1:
                if (this.sicaklikDurumu > 50) // eşik seviyesi aşımı
                {
                    return true;

                } else {
                    return false;
                }

            case 2:
                if (this.sicaklikDurumu > 323) //eşik seviyesi aşımı 
                {
                    return true;
                } else {
                    return false;
                }

            default:
                arayuz.ekran.mesajGoruntule("Sıcaklık birimi algılanamadı, Normal seviye bilgisi varsayılan olarak verildi");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık birimi algılanamadı, Normal seviye bilgisi varsayılan olarak verildi");
                return false;
        }

    }

    private void celciusYazdir(int sicaklikDurumu) {
        arayuz.ekran.mesajGoruntule("Sıcaklık " + sicaklikDurumu + "°C den " + this.sicaklikDurumu + "°C ye düşürüldü..."); // dereceye
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık " + sicaklikDurumu + "°C den " + this.sicaklikDurumu + "°C ye düşürüldü...");

    }

    private void kelvinYazdir(int sicaklikDurumu) {
        arayuz.ekran.mesajGoruntule("Sıcaklık " + sicaklikDurumu + " Kelvinden " + this.sicaklikDurumu + " Kelvine düşürüldü..."); // kelvin
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık " + sicaklikDurumu + " Kelvinden " + this.sicaklikDurumu + " Kelvine düşürüldü...");
    }

    private void sogutucuAc(int sicaklikDurumu) {
        if (this.sicaklikGoruntulendi == true) {
            this.sicaklikDurumu = sicaklikDurumu;
            if (kritikBilgilendirme()) {
                arayuz.ekran.mesajGoruntule("Sıcaklık eşik seviyesinin üzerinde...\n" + "Yüksek kapasiteli soğutucu çalıştırılıyor...");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık eşik seviyesinin üzerinde...");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Yüksek kapasiteli soğutucu çalıştırılıyor...");

                this.sicaklikDurumu = eyleyici.soğutmaGucuYuksek(this.sicaklikDurumu);
            } else {
                arayuz.ekran.mesajGoruntule("Soğutucu çalıştırılıyor...");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Soğutucu çalıştırılıyor...");
                this.sicaklikDurumu = eyleyici.soğutmaGucuDuşuk(this.sicaklikDurumu);
            }
            if (sicaklik.birim() == 1) {
                celciusYazdir(sicaklikDurumu);
            } else {
                kelvinYazdir(sicaklikDurumu);
            }

            if (kritikBilgilendirme()) {
                publisher.notify("Sıcaklık Seviyesi Hala Çok Yüksek, LÜTFEN SOĞUTUCUYU ÇALIŞTIRIN!!!");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık Seviyesi Hala Çok Yüksek, LÜTFEN SOĞUTUCUYU ÇALIŞTIRIN!!!");
            } else {
                publisher.notify("Sıcaklık Seviyesi Normal Seviyede ");
                LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Sıcaklık Seviyesi Normal Seviyede ");
            }
        } else {
            arayuz.ekran.mesajGoruntule("Önce sıcaklık değeri okunmalıdır!!");
            LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Önce sıcaklık değeri okunmalıdır!!");
             publisher.notify("Sıcaklık Değeri Okunmadan Soğutucu Açılmaya Çalışıldı!!!");
        }

    }

    private void cıkısYap() {
        arayuz.ekran.mesajGoruntule("Çıkış yapılıyor...");
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Çıkış yapılıyor...");
    }

    private void sogutucuKapat(int sicaklikDurumu) {
        this.sicaklikDurumu = sicaklikDurumu;
        arayuz.ekran.mesajGoruntule("Soğutucu kapatılıyor...");
        LogYoneticisi.getInstance("Log.txt").dosyayaYaz("Soğutucu kapatılıyor...");
        this.sicaklikDurumu = sicaklikAlgilayici.sicaklikOlustur(sicaklik);
    }

    private void islemYap() {
        int secim;

        sicaklikDurumu = sicaklikAlgilayici.sicaklikOlustur(sicaklik);

        do {
            secim = anaMenuGosterimi();

            switch (secim) {
                case 1:
                    sicaklikGoruntule(this.sicaklikDurumu);
                    break;
                case 2:
                    sogutucuAc(this.sicaklikDurumu);
                    break;

                case 3:
                    sogutucuKapat(this.sicaklikDurumu);
                    break;
                case 4:
                    cıkısYap();
                    break;

                default:
                    arayuz.ekran.mesajGoruntule("1-4 arasında bir rakam girmelisiniz...");
            }

        } while (secim != 4);

    }

    private int anaMenuGosterimi() {
        arayuz.ekran.mesajGoruntule("************");
        arayuz.ekran.mesajGoruntule("Ana Menü");
        arayuz.ekran.mesajGoruntule("[1] Sıcaklık Görüntüle");
        arayuz.ekran.mesajGoruntule("[2] Soğutucu Aç");
        arayuz.ekran.mesajGoruntule("[3] Soğutucu Kapat");
        arayuz.ekran.mesajGoruntule("[4] Çıkış");
        arayuz.ekran.mesajGoruntule("************");
        arayuz.ekran.mesajGoruntule("Seçiminiz : ");
        return arayuz.tusMekanizmasi.getIntData();
    }

    private void kullaniciDogrula() {       // Loglama yapacak mısın ?? 
        arayuz.ekran.mesajGoruntule("Giriş yapmak için lütfen kullanıcı adı ve şifrenizi giriniz...");
        arayuz.ekran.mesajGoruntule("*********");
        arayuz.ekran.mesajGoruntule("Kullanıcı Adı : ");
        kullaniciAdi = arayuz.tusMekanizmasi.getStringData();
        arayuz.ekran.mesajGoruntule("Sifre : ");
        sifre = arayuz.tusMekanizmasi.getStringData();
    }

}
