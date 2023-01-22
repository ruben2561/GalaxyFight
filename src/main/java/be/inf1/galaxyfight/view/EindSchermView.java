package be.inf1.galaxyfight.view;

import be.inf1.galaxyfight.model.EindScherm;
import javafx.scene.layout.Region;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * view voor het eindscherm
 * @author jonas & ruben
 */
public class EindSchermView extends Region{
    /** tekst die weergegeven moet worden*/
    private final Text restart;
    /** tekst voor op het eindscherm*/
    private final Text tekst;
    /** model van het eindscherm*/
    private final EindScherm model;
    

    /**
     * constructor voor de view van het eindscherm
     * @param model het model waarop de view gebasseerd is
     */
    public EindSchermView(EindScherm model){
        this.model = model;
        restart = new Text("PRESS SPACE TO RESTART");
        tekst = new Text("GAME OVER");
        tekst.setFill(YELLOW);
        tekst.setTranslateX(175);
        tekst.setTranslateY(210);
        tekst.setFont(Font.font ("OCR A", 50)); //methode voor fontinstelling komt van https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        restart.setTranslateX(196);
        restart.setTranslateY(290);
        restart.setFont(Font.font ("OCR A", 20));
        getChildren().addAll(restart, tekst);
    }
    
    /**
     * verwissel de tekst tussen zichtbaar of onzichtbaar
     */
    public void update(){
        getChildren().remove(restart);
        if(model.getTekstZichtbaar()){
            restart.setFill(YELLOW);
            getChildren().add(restart);
        }
        else{
            restart.setFill(TRANSPARENT);
            getChildren().add(restart);
        }
    }
}
