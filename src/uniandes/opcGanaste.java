/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jorge
 */
public class opcGanaste implements ActionListener {

    JButton op;
    Cargar load;
    JPanel escenario;
    JPanel estado;
    
    public opcGanaste(JButton op, Cargar load,JPanel estado,JPanel escenario) {
        this.escenario=escenario;
        this.load=load;
        this.op=op;
        this.estado=estado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
        if(this.op.getName().equals("cargar"))
        {
            this.load.actionPerformed(e);
        }
        if(this.op.getName().equals("reiniciar"))
        {
            this.load.Cargar();
        }
        
    }
    
}
