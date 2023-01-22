package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.Spel;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 * Thread voor het spel
 * @author ruben & jonas
 */
public class SpelThread extends TimerTask {
    /** het model van het spel*/
    private final Spel model;
    /** de controller van het spel*/
    private final SpelFXMLController controller;
    
    /**
     * constructor voor de thread van het spel
     * @param model het model van dewelke de thread een functie zal runnen
     * @param controller de controller van dewelke de thread een functie zal runnen
     */
    public SpelThread(Spel model, SpelFXMLController controller){
        this.model = model;
        this.controller = controller;
    }
    
    /**
     * de methode die door de thread uitgevoerd wordt
     */
    @Override
    public void run(){
        model.tick();
        Platform.runLater(() -> controller.update());
    }
    
}
