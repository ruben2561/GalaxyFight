package be.inf1.galaxyfight.model;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.LIGHTGREEN;
import static javafx.scene.paint.Color.ORANGE;

/**
 * Overkoepelend model voor het spel
 * @author ruben & jonas
 */
public class Spel {
    
    /** aantal levens van het oranje ruimteschip */
    public int levensOranje;
    /** aantal levens van het groene ruimteschip */
    private int levensGroen;
    /** model van het oranje ruimteschip */
    private final Ruimteschip oranjeRuimteschip;
    /** model van het groene ruimteschip */
    private final Ruimteschip groenRuimteschip;
    /** model van de kogel*/
    private Kogel kogel;
    /** Arraylist waar alle kogels in bijgehouden worden*/
    private final ArrayList<Kogel> kogels;
    /** is het oranje schip dood?  true = dood ; false = in leven */
    private boolean oranjeDood;
    /** is het groene schip dood?  true = dood ; false = in leven */
    private boolean groenDood;
    /** is het oranje schip naar links aan het draaien?  true = ja ; false = nee */
    private boolean oranjeLinks;
    /** is het oranje schip naar rechts aan het draaien?  true = ja ; false = nee */
    private boolean oranjeRechts;
    /** is het oranje schip aan het schieten?  true = ja ; false = nee */
    private boolean oranjeSchiet;
    /** is het groene schip naar links aan het draaien?  true = ja ; false = nee */
    private boolean groenLinks;
    /** is het groene schip naar rechts aan het draaien?  true = ja ; false = nee */
    private boolean groenRechts;
    /** is het groene schip aan het schieten?  true = ja ; false = nee */
    private boolean groenSchiet;
    /** is het spelgeluid gemuted?  true = ja ; false = nee */
    private boolean muteGeluid;
    /** is een kogel raakgeschoten?  true = ja ; false = nee */
    private boolean raak;
    
    /**
     * Algemene constructor voor het spel
     */
    public Spel(){       
        oranjeLinks = false;
        oranjeRechts = false;
        oranjeSchiet = false;
        groenLinks = false;
        groenRechts = false;
        groenSchiet = false;
        oranjeDood = false;
        groenDood = false;
        muteGeluid = false;
        levensOranje = 5;
        levensGroen = 5;
        oranjeRuimteschip = new Ruimteschip(10, 400, -Math.PI/2);
        groenRuimteschip = new Ruimteschip(10, 80, Math.PI/2);
        kogels = new ArrayList<>();
    }
    
     /**
      * Maak een kogelmodel met de coördinaten en hoek van het vliegtuig dat de kogel schiet
      * @param kleur de kleur van het vliegtuig dat de kogel schiet
      */
    public void maakKogelModel(Color kleur){
        if (kleur == LIGHTGREEN){
         kogel = new Kogel(groenRuimteschip.getRuimteschipX(), groenRuimteschip.getRuimteschipY(), groenRuimteschip.getRuimteschipHoek(), kleur);   
        }
        if (kleur == ORANGE){
         kogel = new Kogel(oranjeRuimteschip.getRuimteschipX(), oranjeRuimteschip.getRuimteschipY(), oranjeRuimteschip.getRuimteschipHoek(), kleur);   
        }
        kogels.add(kogel);
    }
    
    /**
     * tick-functie die alle methodes bevat die periodiek moeten worden uitgevoerd
     */
    public void tick(){
        beweegRuimteschip();
        beweegKogel();
        raakControle();
        tijdOptellen();
        doodControle();
        kogelBuiten();
    }
    
    /**
     * algemene functie om beide ruimteschepen vooruit te laten bewegen
     */
    public void beweegRuimteschip(){
        oranjeRuimteschip.ruimteschipVooruit();
        groenRuimteschip.ruimteschipVooruit();
    }
    
    /**
     * laat alle kogels in de arraylist vooruit bewegen
     */
    public void beweegKogel(){
        for(int i = 0; i < kogels.size() ; i++){
            kogels.get(i).kogelVooruit();
        }
    }
    
    /**
     * controleert of een kogel een vliegtuig met de andere kleur raakt, zo ja: tel 1 leven af van dat vliegtuig, verwijder de kogel, zet boolean raak op true
     */
    public void raakControle(){
        for (int i = 0; i < kogels.size(); i++){    
            if(kogels.get(i).getKogelX() > groenRuimteschip.getRuimteschipX()-10 && kogels.get(i).getKogelX() < groenRuimteschip.getRuimteschipX()+10 && kogels.get(i).getKogelY() > groenRuimteschip.getRuimteschipY()-10 && kogels.get(i).getKogelY() < groenRuimteschip.getRuimteschipY()+10 && kogels.get(i).getKleur() == ORANGE){
                levensGroen--;
                kogels.remove(i);
                raak = true;                
                return;
            }
            if(kogels.get(i).getKogelX() > oranjeRuimteschip.getRuimteschipX()-10 && kogels.get(i).getKogelX() < oranjeRuimteschip.getRuimteschipX()+10 && kogels.get(i).getKogelY() > oranjeRuimteschip.getRuimteschipY()-10 && kogels.get(i).getKogelY() < oranjeRuimteschip.getRuimteschipY()+10 && kogels.get(i).getKleur() == LIGHTGREEN){
                levensOranje--;
                kogels.remove(i);
                raak = true;
                return;
            } 
        }
    }
    
    /**
     * laat de bestaanstijd van alle kogels in de arraylist optellen, verwijdert kogel als deze een bepaalde tijd bestaat
     */
    public void tijdOptellen(){
        for(int i =0; i < kogels.size(); i++){
            if(kogels.get(i).getTijd() < 1.5){
                kogels.get(i).tijdPlus();
            }
            else{
                kogels.remove(i);
            }           
        }
    }
    
    /**
     * controleert of een van de vliegtuigen zijn levens op zijn
     */
    public void doodControle(){
        if(levensOranje == 0){
            oranjeDood = true;
        }
        if(levensGroen == 0){
            groenDood = true;
        }
    }
    
    /**
     * controleert of de kogel buiten het spel gaat en zo ja, wordt deze verwijderd 
     */
    public void kogelBuiten(){
        for (int i = 0; i < kogels.size(); i++){
            if (kogels.get(i).getKogelX() < 0 || kogels.get(i).getKogelX() > 640 || kogels.get(i).getKogelY() < 0 || kogels.get(i).getKogelY() > 480){
                kogels.remove(i);
            }
        }
    }
    
    /**
     * laat de vliegtuigen een richting uitdraaien als de bijhorende boolean true is
     */
    public void beweeg(){
        if (oranjeLinks){
                draaiLinksOranje();
                }
        if (oranjeRechts){
                draaiRechtsOranje();
                }
        if (groenLinks){
                draaiLinksGroen();
                }
        if (groenRechts){
                draaiRechtsGroen();
                }       
    }
    
    /**
     * laat de boolean muteGeluid van status wisselen
     */
    public void muteGeluid(){
        muteGeluid = muteGeluid == false; //we gebruiken dit op deze manier omdat netbeans IDE deze tip gaf
    }
    
    public void mute(){
        muteGeluid = muteGeluid == false;
    }
    
    /**
     * draai naar links met het oranje schip
     */
    public void draaiLinksOranje(){
        oranjeRuimteschip.draaiLinks();
    }
    
     /**
     * draai naar rechts met het oranje schip
     */
    public void draaiRechtsOranje(){
        oranjeRuimteschip.draaiRechts();
    }
    
     /**
     * draai naar links met het groene schip
     */
    public void draaiLinksGroen(){
        groenRuimteschip.draaiLinks();
    }
    
     /**
     * draai naar rechts met het groene schip
     */
    public void draaiRechtsGroen(){
        groenRuimteschip.draaiRechts();
    }
    
    /**
     * tel levens af van het groene ruimteschip
     */
    public void raakGroen(){
        levensGroen--;
    }

    /**
     * tel levens af van het oranje ruimteschip
     */
    public void raakOranje(){
        levensOranje--;
    }
    
    /**
     * hoeveel levens heeft het groene ruimteschip?
     * @return het aantal levens van het groene ruimteschip
     */
    public int getLevensGroen(){
        return levensGroen;
    }
    
    /**
     * hoeveel levens heeft het oranje ruimteschip?
     * @return het aantal levens van het oranje ruimteschip
     */
    public int getLevensOranje(){
        return levensOranje;
    }

    /**
     * wat is het model van het oranje ruimteschip?
     * @return het model van het oranje ruimteschip
     */
    public Ruimteschip getOranjeRuimteschip(){
        return oranjeRuimteschip;
    }
    
    /**
     * wat is het model van het groene ruimteschip?
     * @return het model van het groene ruimteschip
     */
    public Ruimteschip getGroenRuimteschip(){
        return groenRuimteschip;
    }   
    
    /**
     * wat is het model van de kogel?
     * @return het model van de kogel
     */
    public Kogel getKogelModel(){
        return kogel;
    }
    
    /**
     * wat is de kleur van de bepaalde kogel?
     * @return de kleur van de kogel
     */
    public Color getkleur(){
        return kogel.getKleur();
    }
    
    /**
     * wat zit er in de arraylist van de kogels?
     * @return de arraylist van de kogels
     */
    public ArrayList<Kogel> getKogels(){
        return kogels;
    }
    
    /**
     * hoeveel kogels zitten er in de verzameling van kogels?
     * @return het aantal kogels
     */
    public int getKogelsAantal(){
       return kogels.size();
    }
    
    /**
     * is het oranje ruimteschip dood?
     * @return true als het oranje ruimteschip dood is
     */
    public boolean getOranjeDood(){
       return oranjeDood;
    }
    
    /**
     * is het groene ruimteschip dood?
     * @return true als het groen ruimteschip dood is
     */
    public boolean getGroenDood(){
        return groenDood;
    }
    
    /**
     * is het oranje ruimteschip aan het schieten?
     * @return true als het oranje ruimteschip aan het schieten is
     */
    public boolean getOranjeSchiet(){
        return oranjeSchiet;
    }
    
    /**
     * is het groene ruimteschip aan het schieten?
     * @return true als het groene ruimteschip aan het schieten is
     */
    public boolean getGroenSchiet(){
        return groenSchiet;
    }
    
    /**
     * is het het geluid gemuted?
     * @return true als het geluid gemuted is
     */
    public boolean getMuteGeluid(){
        return muteGeluid;
    }
    
    /**
     * is een kogel raakgeschoten?
     * @return true als een kogel raakgeschoten is
     */
    public boolean getRaak(){
        return raak;
    }
    
    /**
     * zet de boolean oranjeLinks op true of false
     * @param oranjeLinks gaat het oranje schip naar links of niet?
     */
    public void setOranjeLinks(boolean oranjeLinks){
        this.oranjeLinks = oranjeLinks;
    }
         
    /**
     * zet de boolean oranjeRechts op true of false
     * @param oranjeRechts gaat het oranje schip naar rechts of niet?
     */
    public void setOranjeRechts(boolean oranjeRechts){
        this.oranjeRechts = oranjeRechts;
    }
    
    /**
     * zet de boolean oranjeSchiet op true of false
     * @param oranjeSchiet is het oranje schip aan het schieten of niet
     */
    public void setOranjeSchiet(boolean oranjeSchiet){
        this.oranjeSchiet = oranjeSchiet;
    }
    
    /**
     * zet de boolean groenLinks op true of false
     * @param groenLinks gaat het groene schip naar links of niet?
     */
    public void setGroenLinks(boolean groenLinks){
        this.groenLinks = groenLinks;
    }
    
    /**
     * zet de boolean groenRechts op true of false
     * @param groenRechts gaat het groene schip naar rechts of niet?
     */
    public void setGroenRechts(boolean groenRechts){
        this.groenRechts = groenRechts;
    }
    
    /**
     * zet de boolean groenSchiet op true of false
     * @param groenSchiet is het groene schip aan het schieten of niet
     */
    public void setGroenSchiet(boolean groenSchiet){
        this.groenSchiet = groenSchiet;
    }
    
    /**
     * zet de boolean raak op true of false
     * @param raak is een kogel raakgeschoten of niet
     */
    public void setRaak(boolean raak){
        this.raak = raak;
    }   
}
