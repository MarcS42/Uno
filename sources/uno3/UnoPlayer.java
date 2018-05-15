package uno3;

import java.util.ArrayList;

/**Class Player encapsulates player strategy for Uno,
 * While class Uno creates and maintains the state of the game.
 */

/**
 * @author MarcSherman
 *
 */
public class UnoPlayer {
    
    private String name;
    private UnoHand hand;
    
    public UnoPlayer(String name) {
        ArrayList<UnoCard> playerHand = new ArrayList<>();
        this.name = name;
        this.hand = new UnoHand(name, playerHand);
    }
    
    /**
     * Gets the player's name
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand
     * 
     * @return
     */
    public UnoHand getHand() {
        return hand;
    }
     
    /**The heart of the game: first searchForMatch in your hand,
     * then if no match, drawForMatch
     * @param uno Card played against previous card
     * @param prev - previous card played
     * @return
     */
    public UnoCard play(UnoV2 uno, UnoCard prev) {
        UnoCard unocard = searchForMatch(prev);
        if (unocard == null) {
            unocard = drawForMatch(uno, prev);
        }
        return unocard;  
    }
    
    /**
     * Searches existing hand for match to Card prev.
     * Starts by checking if previous card was a special card
     * @param prev card
     * @return match from hand
     */
    public UnoCard searchForMatch(UnoCard prev) {
        UnoSpecialCardsV2 spC = new UnoSpecialCardsV2();
        
        if(spC.uCardWldorWD4(prev)) {
            
            //Is Card to Match wild?
            if(spC.unoCardWild(prev)) {
                int unoCardTgtColor = UnoV2.getWildColor();
                for (int i = hand.size()-1; i >=0; i--) {
                    UnoCard unocard = hand.getCard(i);
                    if (unocard.getColor() == unoCardTgtColor || 
                            (spC.unoCardWild(unocard))) { 
                        return hand.popCard(i);
                    }
                }
              return null;
            }
            
          //Is Card to Match WD4?
            if(spC.unoCardWildDrawFour(prev)) {
                int unoCardTgtColor = UnoV2.getWildColor();
                System.out.println("unoCardTgtColor into WD4 "
                        + "SearchForMatch " 
                        + UnoCard.getColors()[unoCardTgtColor]);
                for (int i = hand.size()-1; i >=0;i--) {
                    UnoCard unocard = hand.getCard(i);
                    if ((unocard.getColor() == unoCardTgtColor)//not sure about this for wd4 
                            || spC.uCardWldorWD4(unocard)) {
                        return hand.popCard(i); //had problem bug just because I forgot the 'i' in popCard...
                    }
                }
              return null;
            }            
        } //end special card prev == WildorWD4 searchForMatch
        
        for (int i = 0; i < hand.size(); i++) {
            UnoCard unocard = hand.getCard(i);
/**      Runs thru hand looks for regular wild cards, plays 
 *            them first
 *            */
            if (spC.unoCardWild(unocard)) { 
                return hand.popCard(i);               
/**       Look for special cards, plays them next */
            } else if((spC.specialNotWild(unocard)) && 
                    UnoCard.cardsMatch(unocard, prev)) {
                return hand.popCard(i);
            }
        }
        
/**     After 'filters above, only cases are unocard < 19 
 *         or unocard wild Draw4  
        sort cards that are not special cards or 
        regular wild cards to play highest first 
        */                
      UnoHand.insertionSortUnoHand(hand);    
      
        for (int i = hand.size() - 1; i >= 0; i--) { 
            // search from end of hand as hand sorted ascending
                UnoCard unocard = hand.getCard(i);
                if (unocard.getRank() <= 19 
                        && UnoCard.cardsMatch(unocard, prev)) 
                {
                    return hand.popCard(i);
                }else if (unocard.getColor() > 3) { 
                    // all else fails, play DrawFour
                   return hand.popCard(i);
                } 
        }
      return null;
    } // End searchForMatch
    
    public UnoCard drawForMatch(UnoV2 uno, UnoCard prev) {
        while (true) {
            UnoCard unocard = uno.draw();
            System.out.println(name + " draws " + unocard);
            if (UnoCard.cardsMatch(unocard, prev)) { 
                return unocard;  
                // "return statement gets u out of while(true) loop
            }
            hand.addCard(unocard);
        }
    } //End drawForMatch
} // End UnoPlayer Class
