/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.util.Scanner;

/**
 *
 * @author Yusuf
 */
public class TusMekanizması implements ITusMekanizması{

    @Override
    public int getIntData() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    @Override
    public String getStringData() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    
       
}
