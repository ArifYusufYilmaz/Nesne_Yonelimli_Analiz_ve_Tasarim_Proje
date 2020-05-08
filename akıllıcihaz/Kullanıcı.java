/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

/**
 *
 * @author Yusuf
 */
public class Kullanıcı {

   

    
    private String kullanıcıAdı;
    private String sifre;
    
    
    public Kullanıcı(String kullanıcıAdı, String sifre) {
        this.kullanıcıAdı = kullanıcıAdı;
        this.sifre = sifre;
        
    }
   
    public String getKullanıcıAdı() {
        return kullanıcıAdı;
    }
    

    public void setKullanıcıAdı(String kullanıcıAdı) {
        this.kullanıcıAdı = kullanıcıAdı;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
     
   
}
