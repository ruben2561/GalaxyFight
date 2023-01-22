package be.inf1.galaxyfight.model;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.paint.Color;

/**
 * Model voor een kogel
 * @author ruben & jonas
 */
public class Kogel {
    /** de x-coördinaat van de kogel*/
    private double x;
    /** de y-coördinaat van de kogel*/
    private double y;
    /** de snelheid van de kogel*/
    private final double snelheid;
    /** de hoek waaronder de kogel zich beweegt*/
    private final double hoek;
    /** de kleur van de kogel (=afhankelijk van het ruimteschip van welke de kogel komt)*/
    private final Color kleur;
    /** hoelang de kogel al bestaat*/
    private double tijd;
    
    /**
     * Constructor voor een kogel op een bepaald x- en y-coördinaat en met een bepaalde hoek
     * @param x de x-coördinaat waarop de kogel gezet moet worden
     * @param y de y-coördinaat waarop de kogel gezet moet worden
     * @param hoek de hoek waarmee de kogel zich moet voortbewegen
     * @param kleur de kleur die de kogel moet hebben
     */
    public Kogel(double x, double y, double hoek, Color kleur){
        this.x = x;
        this.y = y;
        this.hoek = hoek;
        snelheid = 2;
        this.kleur = kleur;
        tijd = 0;
    }
   
    /**
     * laat de kogel vooruit bewegen afhankelijk van de hoek
     */
    public void kogelVooruit(){
        this.x = x - snelheid * sin(hoek);
        this.y = y - snelheid * cos(hoek);
    }
    
    /**
     * verhoog de bestaanstijd van de kogel
     */
    public void tijdPlus(){
       this.tijd = tijd + 0.005;
    } 
    
    /**
     * verplaats de kogel helemaal naar links op het speelveld
     */
    public void kogelNaarLinks(){
        x = 0;
    }
    
     /**
     * verplaats de kogel helemaal naar rechts op het speelveld
     */
    public void kogelNaarRechts(){
        x = 640;
    }
    
     /**
     * verplaats de kogel helemaal naar boven op het speelveld
     */
    public void kogelNaarBoven(){
        y = 0;
    }
    
     /**
     * verplaats de kogel helemaal naar onder op het speelveld
     */
    public void kogelNaarOnder(){
        y = 480;
    }
    
    /**
     * wat is het x coordinaat van de kogel?
     * @return het x coordinaat van de kogel
     */
    public double getKogelX(){
        return x;
    }
    
    /**
     * wat is het y coordinaat van de kogel?
     * @return het y coordinaat van de kogel
     */
    public double getKogelY(){
        return y;
    }
    
    /**
     * wat is de hoek van de kogel?
     * @return de hoek van de kogel
     */
    public double getKogelHoek(){
        return hoek;
    }   
    
    /**
     * wat is de kleur van de kogel?
     * @return de kleur van de kogel
     */
    public Color getKleur(){
        return kleur;
    }
    
    /**
     * wat is de bestaanstijd van de kogel?
     * @return de bestaanstijd van de kogel
     */
    public double getTijd(){
        return tijd;
    }
    
}
