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
public class AgArayuzu {
     ITusMekanizması tusMekanizmasi;
     IAgEkrani ekran;
    public AgArayuzu() {
        tusMekanizmasi = new TusMekanizması();
        ekran = new AgEkrani();
    }
    
     
     
}
