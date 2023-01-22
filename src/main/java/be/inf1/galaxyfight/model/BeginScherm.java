package be.inf1.galaxyfight.model;

/**
 * Model voor het beginscherm
 * @author jonas & ruben
 */
public class BeginScherm {
    /** is de tekst zichtbaar? true = zichtbaar ; false = niet zichtbaar*/
    private boolean tekstZichtbaar;
    
    /**
     * constructor voor het beginscherm
     */
    public BeginScherm(){
        tekstZichtbaar = true;
    }
    
    /**
     * wisselt de boolean tekstZichtbaar van status
     */
    public void tick(){
        tekstZichtbaar = tekstZichtbaar == false; // we doen dit op deze manier omdat netbeans IDE ons deze tip gaf
    }
    
    /**
     * is de tekst zichtbaar?
     * @return true als de tekst zichtbaar is
     */
    public boolean getTekstZichtbaar(){
        return tekstZichtbaar;
    }
}
