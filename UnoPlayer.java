package Uno2;


/**Class Player encapsulates player strategy for Uno,
 * While class UnoV2 creates and maintains the state of 
 * the game.
 */


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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand
     * 
     * @return hand
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
     * @param previous card
     * @return match from hand
     */
    public UnoCard searchForMatch(UnoCard prev) {
        
       //case where previous card was a 
      //        special card(wild, or WD4)
        if(UnoSpecialCardsV2.unoSpecialCard(prev)) {
          //Tests to determine 1) which special card previous was; and
            if(UnoSpecialCardsV2.unoCardWild(prev)) {
                int unoCardTgtColor = UnoV2.getWildColor();
         // 2) does player have a card to match it.      
                for (int i = hand.handSize()-1; i >=0; i--) {
                    UnoCard unocard = hand.getCard(i);
                    if (unocard.getColor() == unoCardTgtColor 
                            || (unocard.getRank() > 24)) { 
                        return hand.popCard(i);
                    }
                }
              return null;
              
            }
            // Same comments as above
            if(UnoSpecialCardsV2.unoCardWildDrawFour(prev)) {
                int unoCardTgtColor = UnoV2.getWildColor();
                System.out.println("unoCardTgtColor into WD4 SearchForMatch " 
                         + UnoCard.getColors()[unoCardTgtColor]);
                
                for (int i = hand.handSize()-1; i >=0;i--) {
                    UnoCard unocard = hand.getCard(i);
                    if ((unocard.getColor() == unoCardTgtColor) 
                            || unocard.getRank() > 24) {
                        return hand.popCard(i); //had problem bug just because I forgot the 'i' in popCard...
                    }
                }
              return null;
            }            
        } //end special card prev, search for match
        
        for (int i = 0; i < hand.handSize(); i++) {
            UnoCard unocard = hand.getCard(i);
            
/**      Runs thru hand looks for regular wild cards, plays 
 *          them first*/
            if (unocard.getRank() > 24 && unocard.getRank() < 29) { 
                return hand.popCard(i);               
/**       Look for skip,reverse, D2, plays them next */
            } else if((unocard.getRank() > 18 && unocard.getRank() < 25) 
                    && UnoCard.unoCardsMatch(unocard, prev)) {
                return hand.popCard(i);
            }
        }
/**     After 'filters'
 *        above, only cases are unocard < 19(normal cards 0 
 *        to 9} or unocard wild Draw4.  
 *      Sort cards that are not special cards 
 *      or regular wild cards to play highest first 
 */                
      UnoHand.insertionSortUnoHand(hand);    
      
        // search from end of hand as hand sorted ascending
        for (int i = hand.handSize() - 1; i >= 0; i--) { 
                UnoCard unocard = hand.getCard(i);
                if (unocard.getRank() <= 19 
                        && UnoCard.unoCardsMatch(unocard, prev)) {
                    return hand.popCard(i);
                 // all else fails, play DrawFour
                }else if (unocard.getColor() > 3) { 
                   return hand.popCard(i);
                } 
        }
      return null;
    } // End searchForMatch
    
    public UnoCard drawForMatch(UnoV2 uno, UnoCard prev) {
        while (true) {
            UnoCard unocard = uno.draw();
            System.out.println(name + " draws " + unocard);
            if (UnoCard.unoCardsMatch(unocard, prev)) { 
                return unocard;  
                // "return statement gets u out of while(true) loop
            }
            hand.addCard(unocard);
        }
    } //End drawForMatch
    
} // End UnoPlayer Class
