package be.inf1.galaxyfight;

import be.inf1.galaxyfight.model.Spel;
import be.inf1.galaxyfight.view.SpelView;
import java.io.IOException;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * controller voor het spel
 * @author ruben & jonas
 */
public class SpelFXMLController {
    /** model van het spel*/
    private Spel model;
    /** view van het spel*/
    private SpelView view;
    
    @FXML
    /** anchorpane waaraan alle views toegevoegd wordt*/
    private AnchorPane paneel;
   
    @FXML
    /** tekstvak dat weergeeft hoeveel levens het oranje schip heeft*/
    private Text levensOranje;

    @FXML
    /** tekstvak dat weergeeft hoeveel levens het groene schip heeft*/
    private Text levensGroen;
    
    @FXML
    /** initialize van de controller (wordt maar 1 keer gerund)*/
    public void initialize(){
        model = new Spel();
        view = new SpelView(model);
        paneel.getChildren().add(view);
        paneel.setOnKeyPressed(this::keyPressed);
        paneel.setOnKeyReleased(this::keyReleased);
        paneel.setFocusTraversable(true);
        // dit is de thread voor alle herhalende methodes die in spel worden gebruikt  
        Timer t = new Timer(true);
        SpelThread spelThread = new SpelThread(model, this);
        t.scheduleAtFixedRate(spelThread, 0, 5);
        update();
        
    }
    
    /**
     * deze functie voert methodes uit aan de hand van de ingedrukte toets
     * @param t deze houdt bij welke toets is ingedrukt 
     */
    private void keyPressed(KeyEvent t) {
        switch (t.getCode()) {
            case Q:
                model.setOranjeLinks(true);
                break;
            case D:
                model.setOranjeRechts(true);
                break;
            case LEFT:
                model.setGroenLinks(true);
                break;
            case RIGHT:
                model.setGroenRechts(true);
                break;
            case UP:
                if(!model.getGroenSchiet()){
                    model.maakKogelModel(Color.LIGHTGREEN);
                    view.maakKogelView(); 
                    model.setGroenSchiet(true);
                }
                if(!model.getMuteGeluid()){
                    view.schietGeluidGroen();
                }
                break;
            case Z:
                if (!model.getOranjeSchiet()){
                    model.maakKogelModel(Color.ORANGE);
                    view.maakKogelView();
                    model.setOranjeSchiet(true);
                }
                if(!model.getMuteGeluid()){
                    view.schietGeluidOranje();
                }
                break;
            case M:
                model.muteGeluid();
                break;       
        }
        update();
    }
    
    /**
     * deze functie voert methodes uit aan de hand van de losgelaten toets
     * @param t deze houdt bij welke toets is ingedrukt
     */
    public void keyReleased(KeyEvent t){
        switch (t.getCode()) {
            case Q:
                model.setOranjeLinks(false);
                break;
            case D:
                model.setOranjeRechts(false);
                break;
            case LEFT:
                model.setGroenLinks(false);
                break;
            case RIGHT:
                model.setGroenRechts(false);
                break;    
            case UP:  
                model.setGroenSchiet(false);
                break;
            case Z:
                model.setOranjeSchiet(false);
                break;
        }
        
    }
    
    /**
     * update de overkoepelende view
     * zet de levens-tekstvakken naar de juiste waarde
     * laat alle bestaande objecten bewegen
     * run eindeSpel()
     */
    public void update() {
        view.update();
        levensOranje.setText("" + model.getLevensOranje());
        levensGroen.setText("" + model.getLevensGroen());
        model.beweeg();
        eindeSpel();
    }
    
    int i = 0;
    /**
     * controlleer of één van de twee schepen dood is, zo ja, wissel naar de eindschermfxml
     */
    public void eindeSpel(){    
        if(i == 0){            
            if(model.getGroenDood()|| model.getOranjeDood()){
            try {
                   App.setRoot("EindSchermFXML");
                } catch (IOException ex) {
                }
            i++;
            }
        }
    }
    
}

   
    

/**
 * Bronvermelding:
 * if-lus die ervoor zorgt dat er maar 1 keer geschoten wordt;
 *       https://jvm-gaming.org/t/how-to-execute-a-keyevents-once/31893/2
 */