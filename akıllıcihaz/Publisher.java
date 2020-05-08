/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akıllıcihaz;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yusuf
 */
public class Publisher implements ISubject {

    private IObserver subscriber;

    @Override
    public void attach(IObserver subscriber) {

        this.subscriber = subscriber;
    }

    @Override
    public void detach(IObserver o) {

    }

    @Override
    public void notify(String mesaj) {

        this.subscriber.update(mesaj);

    }

}
