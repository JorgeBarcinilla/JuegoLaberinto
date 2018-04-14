/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class Mover2 implements KeyListener {
    
    Mover arr;
    Mover abj;
    Mover izq;
    Mover der;
    JButton arriba;
    JButton abajo;
    JButton izquierda;
    JButton derecha;
    Icon arri;
    Icon abaj;
    Icon dere;
    Icon izqui;
    JLabel interuptor;

    public Mover2(Mover arr, Mover abj, Mover izq, Mover der, JButton arriba, JButton abajo, JButton izquierda, JButton derecha,JLabel interuptor) {
        this.arr = arr;
        this.abj = abj;
        this.izq = izq;
        this.der = der;
        this.arriba = arriba;
        this.abajo = abajo;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.arri=arriba.getIcon();
        this.abaj=abajo.getIcon();
        this.izqui=izquierda.getIcon();
        this.dere=derecha.getIcon();
        this.interuptor=interuptor;
    }
    

    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.interuptor.getText().equals("si"))
        {
            if (e.getKeyCode() == KeyEvent.VK_UP) 
            {
                ActionEvent i = null;
                this.arr.actionPerformed(i);

                this.arriba.setIcon(this.arriba.getPressedIcon());
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) 
            {
                ActionEvent i = null;
                this.abj.actionPerformed(i);

                this.abajo.setIcon(this.abajo.getPressedIcon());
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) 
            {
                ActionEvent i = null;
                this.izq.actionPerformed(i);

                this.izquierda.setIcon(this.izquierda.getPressedIcon());
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                ActionEvent i = null;
                this.der.actionPerformed(i);

                this.derecha.setIcon(this.derecha.getPressedIcon());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            this.arriba.setIcon(this.arri);
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            this.abajo.setIcon(this.abaj);
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            this.izquierda.setIcon(this.izqui);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            this.derecha.setIcon(this.dere);
        }
        
    }
    
}
