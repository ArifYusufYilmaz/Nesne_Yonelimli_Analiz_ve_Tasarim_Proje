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
public class Eyleyici {
    public int soğutmaGucuDuşuk(int sıcaklık){
        return sıcaklık -= 7;
    }
    public int soğutmaGucuYuksek(int sıcaklık){
        return sıcaklık -=26;
    }
}
