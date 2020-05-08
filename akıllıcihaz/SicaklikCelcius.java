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
public class SicaklikCelcius implements ISicaklik{

    @Override
    public String sicaklikDegeri(int sicaklikDegeri) {
        return "<Celcius> "+ sicaklikDegeri + "°C";
    }
    @Override
    public int sicaklikOlustur(SicaklikAlgilayici sıcaklıkAlgılayıcı) {
        return sıcaklıkAlgılayıcı.rastgele();
    }
    public int birim(){
        return 1;         // celcius ise 1 döndürür.
    }
    
    
}
