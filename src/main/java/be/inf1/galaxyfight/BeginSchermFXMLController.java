package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.BeginScherm;
import be.inf1.galaxyfight.view.BeginSchermView;
import java.io.IOException;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller van het beginscherm
 * @author jonas & ruben
 */
public class BeginSchermFXMLController {
    
    @FXML
    /** de anchorpane waarop alles getekend wordt*/
    private AnchorPane beginScherm;
    /** de view van het beginscherm*/
    private BeginSchermView view;
    /** het model van het beginscherm*/
    private BeginScherm model;

    @FXML
    /** initialize van de controller (wordt maar 1 keer gerund)*/            
    void initialize() {
        model = new BeginScherm();
        view = new BeginSchermView(model);
        beginScherm.setOnKeyPressed(this::starten);
        beginScherm.setFocusTraversable(true);
        beginScherm.getChildren().add(view);
        Timer t = new Timer(true);
        BeginSchermThread taak = new BeginSchermThread(model, this);
        t.scheduleAtFixedRate(taak, 0, 500);
    } 
   
    /**
     * verander van fxml en start het spel
     * @param t deze houdt bij welke toets is ingedrukt
     */
    private void starten(KeyEvent t){
        switch (t.getCode()) {
            case SPACE:                
            try {
                App.setRoot("SpelFXML");
            } 
            catch (IOException ex) {
            }                              
                break;
        }
    }

    /**
     * update de view
     */
    public void update(){
        view.update();
    }    
}
