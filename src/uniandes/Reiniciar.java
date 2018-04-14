/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jorge
 */
public class Reiniciar implements ActionListener {

    Cargar load;
    
    public Reiniciar(Cargar load) {
        this.load=load;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.load.Cargar();
    }
    
}
