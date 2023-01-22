package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.BeginScherm;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * Thread voor het beginscherm
 * @author ruben & jonas
 */
public class BeginSchermThread extends TimerTask {
    /** het model van het beginscherm*/
    private final BeginScherm model;
    /** de controller van het beginscherm*/
    private final BeginSchermFXMLController controller;
    
    /**
     * constructor voor de thread van het beginscherm
     * @param model het model van dewelke de thread een functie zal runnen
     * @param controller de controller van dewelke de thread een functie zal runnen
     */
    public BeginSchermThread(BeginScherm model, BeginSchermFXMLController controller){
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
