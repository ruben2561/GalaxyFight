package be.inf1.galaxyfight.view;

import be.inf1.galaxyfight.model.BeginScherm;
import javafx.scene.layout.Region;
import static javafx.scene.paint.Color.TRANSPARENT;
import static javafx.scene.paint.Color.YELLOW;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * view voor het beginscherm
 * @author ruben & jonas
 */
public class BeginSchermView extends Region{
    /** tekst die weergegeven moet worden*/
    private final Text tekst;
    /** model van het beginscherm*/
    private final BeginScherm model;

    /**
     * constructor voor de view van het beginscherm
     * @param model model waarvan de view getekent moet worden
     */
    public BeginSchermView(BeginScherm model){
        this.model = model;
        tekst = new Text("PRESS SPACE TO START");
        tekst.setTranslateX(210);
        tekst.setTranslateY(290);
        tekst.setFont(Font.font ("OCR A", 20)); //methode voor fontinstelling komt van https://docs.oracle.com/javafx/2/text/jfxpub-text.htm
        getChildren().add(tekst);
    }
    
    /**
     * wissel de tekst tussen zichtbaar of onzichtbaar
     */
    public void update(){
        getChildren().clear();
        if(model.getTekstZichtbaar()){
            tekst.setFill(YELLOW);
            getChildren().add(tekst);
        }
        else{
            tekst.setFill(TRANSPARENT);
            getChildren().add(tekst);
        }
    }
    

}
