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
public interface ISubject {

    public void attach(IObserver subscriber);

    public void detach(IObserver o);

    public void notify(String mesaj);
}
