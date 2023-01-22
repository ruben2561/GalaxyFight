package be.inf1.galaxyfight.view;

import be.inf1.galaxyfight.model.Kogel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * view voor een kogel
 * @author jonas & ruben
 */
public class KogelView extends Region{
    /** model van de kogel*/
    Kogel kogel;
    /** Anchorpane waarop de kogel getekend moet worden*/
    AnchorPane paneel;
    
    /**
     * constructor voor de view van een kogel
     * @param model het model van de kogel waarvan een view gemaakt moet worden
     */
    public KogelView (Kogel model){
        this.kogel = model;
        createKogel(model.getKleur());
        update();       
    }
    
    /**
     * verwijdert de kogelview en zet deze op de juiste plaats terug
     */
    public void update(){
        getChildren().clear();
        paneel.setTranslateX(kogel.getKogelX());
        paneel.setTranslateY(kogel.getKogelY());
        getChildren().addAll(paneel);
        paneel.toBack();
        paneel.setRotate(-kogel.getKogelHoek() * 180/Math.PI);       
    } 
  
    /**
     * maakt een kogelView aan
     * @param kleur de kleur die de kogel moet hebben
     */
    public void createKogel(Color kleur){
        paneel = new AnchorPane();
        Circle kogeltje = new Circle(0,0,2,null);
        kogeltje.setFill(kleur);
        paneel.getChildren().addAll(kogeltje);
    } 
}
