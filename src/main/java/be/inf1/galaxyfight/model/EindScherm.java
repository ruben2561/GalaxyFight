package be.inf1.galaxyfight.model;

/**
 * Model voor het eindscherm
 * @author jonas & ruben
 */
public class EindScherm {
    /** is de tekst zichtbaar? true = zichtbaar ; false = niet zichtbaar*/
    private boolean tekstZichtbaar;
    
    /**
     * constructor voor het eindscherm
     */
    public EindScherm(){  
        tekstZichtbaar = true;
    }
    
    /**
     * wisselt de boolean tekstZichtbaar van status
     */
    public void tick(){
        tekstZichtbaar = tekstZichtbaar == false; // we doen dit op deze manier omdat netbeans IDE ons deze tip gaf
    }
    
   /**
     * is de tekst zichtbaar of niet?
     * @return true als de tekst zichtbaar is
     */
    public boolean getTekstZichtbaar(){
        return tekstZichtbaar;
    } 
}
