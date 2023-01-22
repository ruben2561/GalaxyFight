package be.inf1.galaxyfight.model;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *Model voor een ruimteschip
 * @author ruben & jonas
 */
public class Ruimteschip {
    /** x-coördinaat van het vliegtuig*/
    private double x;
    /** y-coördinaat van het vliegtuig*/
    private double y;
    /** snelheid waarmee het vliegtuig vooruit gaat*/
    private final double snelheid;
    /** hoek waarmee het vliegtuig gedraad is*/
    private double hoek;
    
    /**
     * Een constructor om een ruimteschip met gekozen x-coordinaat, y-coordinaat en hoek te maken
     * @param x het x-coordinaat van het ruimteschip
     * @param y het y-coordinaat van het ruimteschip
     * @param hoek de hoek van het ruimteschip
     */
    public Ruimteschip(double x, double y, double hoek){
        this.x =  x;
        this.y =  y;
        snelheid = 0.7;
        this.hoek = hoek;
    }
   
    /**
     * laat het ruimteschip vooruit bewegen afhankelijk van de hoek
     */
    public void ruimteschipVooruit(){
        this.x = x - snelheid * sin(hoek);
        this.y = y - snelheid * cos(hoek);
        ruimteschipBuiten();
    }
    
    /**
     * controlleer of het ruimteschip zich buiten de spelgrenzen bevindt, en zet het aan de andere kant indien ja
     */
    public void ruimteschipBuiten(){
        if(x<0){
            ruimteschipNaarRechts();
        }
        if(x>640){
            ruimteschipNaarLinks();
        }
        if(y<0){
            ruimteschipNaarOnder();
        }
        if(y>480){
            ruimteschipNaarBoven();
        }
    } 
    
    /**
     * laat de hoek optellen en draait dus naar links
     */
    public void draaiLinks(){
       hoek = hoek + Math.PI/128;
    }
    
    /**
     * laat de hoek aftellen en draait dus naar rechts
     */
    public void draaiRechts(){
       hoek = hoek - Math.PI/128;
    }
    
    /**
     * verplaats het ruimteschip helemaal naar links op het speelveld
     */
    public void ruimteschipNaarLinks(){
        x = 0;
    }
    
    /**
     * verplaats het ruimteschip helemaal naar rechts op het speelveld
     */
    public void ruimteschipNaarRechts(){
        x = 640;
    }
    
    /**
     * verplaats het ruimteschip helemaal naar boven op het speelveld
     */
    public void ruimteschipNaarBoven(){
        y = 0;
    }
    
    /**
     * verplaats het ruimteschip helemaal naar onder op het speelveld
     */
    public void ruimteschipNaarOnder(){
        y = 480;
    }
    
    /**
     * wat is het x coordinaat van het ruimteschip?
     * @return het x coordinaat van het ruimteschip
     */
    public double getRuimteschipX(){
        return x;
    }
    
    /**
     * wat is het y coordinaat van het ruimteschip?
     * @return het y coordinaat van de kogel
     */
    public double getRuimteschipY(){
        return y;
    }
    
    /**
     * wat is de hoek van het ruimteschip?
     * @return de hoek van het ruimteschip
     */
    public double getRuimteschipHoek(){
        return hoek;
    }
}
