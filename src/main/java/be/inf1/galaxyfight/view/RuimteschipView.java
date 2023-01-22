package be.inf1.galaxyfight.view;

import be.inf1.galaxyfight.model.Ruimteschip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * view voor een ruimteschip
 * @author ruben & jonas
 */
public class RuimteschipView extends Region{
    /** model van het ruimteschip*/
    Ruimteschip ruimteschip;
    /** AnchorPane waarop het ruimteschip getekend moet worden*/
    AnchorPane paneel;
    
    /**
     * constructor voor de view van een ruimteschip
     * @param model het model waarvan de view getekend wordt
     * @param ruimteschip de foto die gebruikt moet worden
     */
    public RuimteschipView(Ruimteschip model, ImageView foto){
        this.ruimteschip = model;
        createRuimteschip(foto);
        update();
    }
    
    /**
     * verwijder de ruimteschipview en teken het op de juiste plaats terug
     */
    public void update(){
        getChildren().clear();
        paneel.setTranslateX(ruimteschip.getRuimteschipX()-10);
        paneel.setTranslateY(ruimteschip.getRuimteschipY()-10);
        getChildren().addAll(paneel);
        paneel.setRotate(-ruimteschip.getRuimteschipHoek() * 180/Math.PI);
    } 
    
    /**
     * zet een foto in de AnchorPane
     * @param foto de foto die aan de AnchorPane toegevoegd moet worden
     */
    public void createRuimteschip(ImageView foto){
        paneel = new AnchorPane();
        paneel.getChildren().addAll(foto);
    } 
    
}
