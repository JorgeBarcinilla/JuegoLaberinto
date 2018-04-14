/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uniandes;

/**
 *
 * @author Jorge
 */
public class Jugador {
    
    private int llavesRojas;
    private int llavesAzules;
    private int llavesAmarillas;
    private int llavesEspeciales;
    private int cantLlavesRojas;
    private int cantLlavesAzules;
    private int cantLlavesAmarillas;
    private int cantLlavesEspeciales;
    private int visitasCasillas;
    
    public Jugador()
    {
        this.llavesAmarillas=0;
        this.llavesAzules=0;
        this.llavesEspeciales=0;
        this.llavesRojas=0;
        this.cantLlavesAmarillas=0;
        this.cantLlavesAzules=0;
        this.cantLlavesEspeciales=0;
        this.cantLlavesRojas=0;
        this.visitasCasillas=0;
    }

    public int getLlavesRojas() {
        return llavesRojas;
    }

    public void setLlavesRojas(int llavesRojas) {
        this.llavesRojas = llavesRojas;
    }

    public int getLlavesAzules() {
        return llavesAzules;
    }

    public void setLlavesAzules(int llavesAzules) {
        this.llavesAzules = llavesAzules;
    }

    public int getLlavesAmarillas() {
        return llavesAmarillas;
    }

    public void setLlavesAmarillas(int llavesAmarillas) {
        this.llavesAmarillas = llavesAmarillas;
    }

    public int getLlavesEspeciales() {
        return llavesEspeciales;
    }

    public void setLlavesEspeciales(int llavesEspeciales) {
        this.llavesEspeciales = llavesEspeciales;
    }

    public int getCantLlavesRojas() {
        return cantLlavesRojas;
    }

    public void setCantLlavesRojas(int cantLlavesRojas) {
        this.cantLlavesRojas = cantLlavesRojas;
    }

    public int getCantLlavesAzules() {
        return cantLlavesAzules;
    }

    public void setCantLlavesAzules(int cantLlavesAzules) {
        this.cantLlavesAzules = cantLlavesAzules;
    }

    public int getCantLlavesAmarillas() {
        return cantLlavesAmarillas;
    }

    public void setCantLlavesAmarillas(int cantLlavesAmarillas) {
        this.cantLlavesAmarillas = cantLlavesAmarillas;
    }

    public int getCantLlavesEspeciales() {
        return cantLlavesEspeciales;
    }

    public void setCantLlavesEspeciales(int cantLlavesEspeciales) {
        this.cantLlavesEspeciales = cantLlavesEspeciales;
    }

    public int getVisitasCasillas() {
        return visitasCasillas;
    }

    public void setVisitasCasillas(int visitasCasillas) {
        this.visitasCasillas = visitasCasillas;
    }
    
    public void Reiniciar()
    {
        this.llavesAmarillas=0;
        this.llavesAzules=0;
        this.llavesEspeciales=0;
        this.llavesRojas=0;
        this.cantLlavesAmarillas=0;
        this.cantLlavesAzules=0;
        this.cantLlavesEspeciales=0;
        this.cantLlavesRojas=0;
        this.visitasCasillas=0;
    }
}
