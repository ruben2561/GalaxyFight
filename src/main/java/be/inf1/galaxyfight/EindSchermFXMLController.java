package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.EindScherm;
import be.inf1.galaxyfight.view.EindSchermView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
/**
 * controller voor het eindscherm
 * @author ruben & jonas
 */
public class EindSchermFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    /** de anchorpane waarop alle views van het eindscherm getekend zullen worden*/
    private AnchorPane eindScherm;
    /** model van het eindscherm*/
    private EindScherm model;
    /** view van het eindscherm*/
    private EindSchermView view;
    
    @FXML
    /** initialize van de controller (wordt maar 1 keer gerund)*/
    void initialize() {
        model = new EindScherm();
        view = new EindSchermView(model);
        eindScherm.setOnKeyPressed(this::resetten);
        eindScherm.setFocusTraversable(true);
        eindScherm.getChildren().add(view);
        // dit is de thread die de tekst laat flikkeren op het eindscherm
        Timer t = new Timer(true);
        EindSchermThread taak = new EindSchermThread(model, this);
        t.scheduleAtFixedRate(taak, 0, 500);
    } 
    
    /**
     * verander van fxml
     * @param t deze houdt bij welke toets is ingedrukt
     */
    private void resetten(KeyEvent t){
        switch (t.getCode()) {
            case SPACE:
                try {
                    App.setRoot("SpelFXML");
                } catch (IOException ex) {
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

