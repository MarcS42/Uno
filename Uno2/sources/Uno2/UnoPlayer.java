package Uno2;


/**Class Player encapsulates player strategy for Uno,
 * While class Uno creates and maintains the state of the game.
 */
import Uno2.UnoSpecialCardsV2;

/**
 * @author MarcSherman
 *
 */
public class UnoPlayer {
    
    private String name;
    private UnoHand hand;
    
    public UnoPlayer(String name) {
        this.name = name;
        this.hand = new UnoHand(name);
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
     
    public UnoCard play(UnoV2 uno, UnoCard prev) {
        UnoCard unocard = searchForMatch(prev);
        if (unocard == null) {
            unocard = drawForMatch(uno, prev);
        }
        return unocard;  
    }
    
    /**
     * Searches existing hand for match to Card prev.
     * 
     * @param prev card
     * @return match from hand
     */
    public UnoCard searchForMatch(UnoCard prev) {
        
        if(UnoSpecialCardsV2.unoSpecialCard(prev)) {
            if(UnoSpecialCardsV2.unoCardWild(prev)) {
                int unoCardTgtColor = UnoSpecialCardsV2.randomColor();
                for (int i = hand.handSize()-1; i >=0; i--) {
                    UnoCard unocard = hand.getCard(i);
                    if (unocard.getColor() == unoCardTgtColor || 
                            (UnoSpecialCardsV2.unoCardWild(unocard))) { 
                        return (UnoCard) hand.popCard(i);
                    }
                }
              return null;
              
            }
            if(UnoSpecialCardsV2.unoCardWildDrawFour(prev)) {
                int unoCardTgtColor = UnoSpecialCardsV2.randomColor();
                System.out.println("unoCardTgtColor into WD4 "
                        + "SearchForMatch " 
                        + UnoCard.getColors()[unoCardTgtColor]);
                for (int i = hand.handSize()-1; i >=0;i--) {
                    UnoCard unocard = hand.getCard(i);
                    if ((unocard.getColor() == unoCardTgtColor) 
                            || unocard.getRank() > 24) {
                        return (UnoCard) hand.popCard(i); //had problem bug just because I forgot the 'i' in popCard...
                    }
                }
              return null;
            }            
        } //end special card prev search for match
        
        for (int i = 0; i < hand.handSize(); i++) {
            UnoCard unocard = hand.getCard(i);
/**      Runs thru hand looks for regular wild cards, plays 
 *            them first
 *            */
            if (UnoSpecialCardsV2.unoCardWild(unocard)) { 
                return (UnoCard) hand.popCard(i);               
/**       Look for special cards, plays them next */
            } else if((UnoSpecialCardsV2.specialNotWild(unocard)) && 
                    UnoCard.cardsMatch(unocard, prev)) {
                return (UnoCard) hand.popCard(i);
            }
        }
/**     After 'filters above, only cases are unocard < 19 
 *         or unocard wild Draw4  
        sort cards that are not special cards or 
        regular wild cards to play highest first */                
      UnoHand.insertionSortUnoHand(hand);    
      
        for (int i = hand.handSize() - 1; i >= 0; i--) { 
            // search from end of hand as hand sorted ascending
                UnoCard unocard = hand.getCard(i);
                if (unocard.getRank() <= 19 
                        && UnoCard.cardsMatch(unocard, prev)) 
                {
                    return (UnoCard) hand.popCard(i);
                }else if (unocard.getColor() > 3) { 
                    // all else fails, play DrawFour
                   return (UnoCard) hand.popCard(i);
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
