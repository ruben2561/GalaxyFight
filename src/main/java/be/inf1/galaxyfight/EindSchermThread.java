package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.EindScherm;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * Thread voor het eindscherm
 * @author ruben & jonas
 */
public class EindSchermThread extends TimerTask {
    /** het model van het eindscherm*/
    private final EindScherm model;
    /** de controller van het eindscherm*/
    private final EindSchermFXMLController controller;
    
    /**
     * constructor voor de thread van het eindscherm
     * @param model het model van dewelke de thread een functie moet runnen
     * @param controller de controller van dewelke de thread een functie moet runnen
     */
    public EindSchermThread(EindScherm model, EindSchermFXMLController controller){
        this.model = model;
        this.controller = controller;
    }
    
    /**
     * de methode die de thread uitvoert
     */
    @Override
    public void run(){
        model.tick();
        Platform.runLater(() -> controller.update());
    }
    
}
