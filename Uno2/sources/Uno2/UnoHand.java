package Uno2;

/**
 * 
 */


import java.util.ArrayList;

/**
 * @author MarcSherman
 *
 */
public class UnoHand extends UnoDeck{
    private ArrayList<UnoCard> unohand;
    private String label;
    
    public UnoHand() {
        super();
    }
    
    public UnoHand(String label) {
        super(label);
        this.unohand = new ArrayList<UnoCard>();
    }
    
    public static UnoHand insertionSortUnoHand(UnoHand unohand) {
        for (int i = 1; i < unohand.size(); i++) {
            for(int k= i; (k>0 && (UnoCard.compareCards
             (unohand.getCard(k), unohand.getCard(k - 1)) < 1));
                    k--) {
                unohand.swapCards(k,k-1);
            }
        }
        return unohand;
    }
    
    /**
     * Scoring system unique to Uno is in class UnoCard
     * */
   public int scoreHandUno(UnoHand unohand) {
       UnoCard uC = new UnoCard();
           int sum = 0;
           for(int i = 0; i < unohand.size(); i++) {
               UnoCard unocard = unohand.getCard(i);
               sum += uC.scoreCard(unocard);
           }
           return sum;     
       }
   
   /**Used in UnoV2 displayState()
 * 
 */
public void display() {
       System.out.println(getLabel() + ": ");
       for (int i =0; i < size(); i++) {
           System.out.println(getCard(i));
//           System.out.println(" " + UnoCard.scoreCardUno(getCard(i)));
       }
       System.out.println();
   }
   
   /**Checks if unohand AL is empty
    * @return true if hand is empty
    */
   public boolean empty() {
       return unohand.isEmpty();
   }

//   /*moves all remaining cards to the given CardCollection*/
//   public void dealAll(UnoHand that) {
//       int n = handSize();
//       deal(that, n);
//   }
   
//   /* Deal(that,n) Removes n cards from this.CardCollection, and
//    *  adds n cards to that.CardCollection*/
//   public void deal(UnoHand that, int n) {
//       for (int i=0; i < n; i++) {
//           UnoCard card = (UnoCard) popCard();
//           that.addCard(card);
//       }
//   }
   
   
   
   /**-----------------------------------------
    * Begin Unohand Helper/Utility methods
    */
   
   /**Used in display() here which, in turn, is used 
 * in UnoV2 displayState().
 * 
 * @return
 */
public String getLabel() {
    return label;
}

///**Used in many methods in UnoPlayer, UnoHand, and 
// * UnoV2
// * @param i int index position of desired card in AL
// * @return Card at index posit. i in UnoHand
// */
//public UnoCard getCard(int i) {
//    return unohand.get(i);
//}

/*Used in UnoV2 takeTurn()
 * gets last card from calling CardCollection, 
    * but doesn't remove it
    * So it is like taking a look at the card
    * */
   public UnoCard last() {
       int i = size()-1;
       return getCard(i);
   }
}//End class UnoHand 

