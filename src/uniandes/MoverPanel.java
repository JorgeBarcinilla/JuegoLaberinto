/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jorge
 */
public class MoverPanel implements ActionListener{
    
    private Component panel;
    private JPanel escenario;
    private Timer tiempo;
    private int contador=0;

    public MoverPanel(Component panel,JPanel escenario) {
        this.panel = panel;
        this.escenario=escenario;
        this.tiempo = new Timer(1,acciones);
    }
    
    private final ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            
            if(contador>=100)
            {
                if (panel.getLocation().getY() <= -(panel.getSize().getHeight()+10) )
                {
                    tiempo.stop();
                    panel.setLocation((int)((escenario.getSize().getWidth()/2)-(panel.getSize().getWidth()/2)),(int)((escenario.getSize().getHeight()/2)-(panel.getSize().getHeight()/2)));
                    panel.setVisible(false);
                } 
                else
                {
                    
                    int y = (int) panel.getLocation().getY() - 5;
                    panel.setLocation((int)((escenario.getSize().getWidth()/2)-(panel.getSize().getWidth()/2)), y); 
                    
                }
            
            }
            
            contador++;
        }
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        this.tiempo.start();
    }
    
}
