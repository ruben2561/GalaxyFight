package be.inf1.galaxyfight.view;

import be.inf1.galaxyfight.model.Spel;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * view van het spel
 * @author ruben & jonas
 */
public class SpelView extends Region{
    /** de view van het oranje ruimteschip*/
    private RuimteschipView oranjeView;
    /** de view van het groene ruimteschip*/
    private RuimteschipView groenView;
    /** het model van het spel*/
    private final Spel spel;
    /** arraylist waarin alle kogelViews in worden opgeslagen*/
    private ArrayList<KogelView> kogelViews;
    
    /**
     * constructor voor de view van het spel
     * @param spel het model waarop de view gebasseerd moet zijn
     */
    public SpelView(Spel spel){
        this.spel = spel;
        kogelViews = new ArrayList<>();
        update();
    }
    
    /**
     * verwijdert alle views en tekent ze de juiste plaats terug
     * laat ook methodes runnen die geluiden afspelen op de juiste momenten
     */
    public void update(){
        getChildren().clear();
        maakRuimteschipView();
        groenView.update();
        oranjeView.update();
        maakKogelView();
        updateKogels();
        getChildren().addAll(oranjeView, groenView);
        muteGeluid();
        raakGeluid();
    }
    
    /**
     * maak kogelViews op basis van de arraylist van modellen
     * doorloopt de lijst met modellen, maakt van elk element in de lijst een view en voegt al deze views toe aan de lijst met kogelViews
     */
    public void maakKogelView(){
        kogelViews.clear();
        for (int i=0; i < spel.getKogels().size(); i++){
            kogelViews.add(new KogelView(spel.getKogels().get(i)));
        }
    }
    
    /**
     * update alle kogelViews in de lijst kogelViews
     */
    public void updateKogels(){
        for (int i=0; i < kogelViews.size(); i++){
            kogelViews.get(i).update();
            getChildren().add(kogelViews.get(i));
        }
    }
    
    /**
     * maakt de view voor beide ruimteschepen
     * leest foto's in en voegt deze toe aan de view
     */
    public void maakRuimteschipView(){
        String url1 = getClass().getResource("/be/inf1/galaxyfight/Ruimteschip_groen.png").toString();
        String url2 = getClass().getResource("/be/inf1/galaxyfight/Ruimteschip_oranje.png").toString();
        ImageView fotoGroen = new ImageView(url1);
        ImageView fotoOranje = new ImageView(url2);
        oranjeView = new RuimteschipView(spel.getOranjeRuimteschip(), fotoOranje);
        groenView = new RuimteschipView(spel.getGroenRuimteschip(), fotoGroen);
    }
    
    /**
     * zet een mute-icoon op de juiste locatie wanneer dit moet
     */
    public void muteGeluid(){
        ImageView mute = new ImageView(getClass().getResource("/be/inf1/galaxyfight/mute.png").toString());
        mute.setTranslateX(222);
        mute.setTranslateY(-45);
        mute.setScaleX(0.1);
        mute.setScaleY(0.1);
        if (spel.getMuteGeluid()){
            getChildren().add(mute);            
        }
        else{
            getChildren().remove(mute);
        }
    }
    
    /**
     * speel het geluid af voor schieten van het groene schip
     */
    public void schietGeluidGroen() {
        String url1 = getClass().getResource("/be/inf1/galaxyfight/geluidGroen").toString();
        Media media = new Media(url1);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
    
    /**
     * speel het geluid af voor het schieten van het oranje schip
     */
    public void schietGeluidOranje() {
        String url1 = getClass().getResource("/be/inf1/galaxyfight/geluidOranje").toString();
        Media media = new Media(url1);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
    
    /**
     * speel het geluid af voor het raken van een kogel tegen een schip
     */
    public void raakGeluid() {
        if(spel.getRaak() && !spel.getMuteGeluid()){
        String url1 = getClass().getResource("/be/inf1/galaxyfight/boem").toString();
        Media media = new Media(url1);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        spel.setRaak(false);
        }
    }
    
}

