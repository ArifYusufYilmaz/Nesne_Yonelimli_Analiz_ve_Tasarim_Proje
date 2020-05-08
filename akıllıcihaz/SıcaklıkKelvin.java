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
public class SıcaklıkKelvin implements ISicaklik{

    @Override
    public String sicaklikDegeri(int sicaklikDegeri) {
        int sıcaklıkDeğeriKelvin= sicaklikDegeri;
        return "<Kelvin> "+ sıcaklıkDeğeriKelvin+"K";
    }

    @Override
    public int sicaklikOlustur(SicaklikAlgilayici sıcaklıkAlgılayıcı) {
        return sıcaklıkAlgılayıcı.rastgele() + 273;
        
    }
    public int birim(){
        return 2;   // kelvin ise 2 döndürür.
    }
    
}
