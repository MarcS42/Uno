package Uno2;

import java.util.ArrayList;
import java.util.Random;

public class UnoDeck extends CardDeck {
    protected ArrayList<UnoCard> unocards;
    protected String label;
    
    public ArrayList<UnoCard> getUnocards() {
        return unocards;
    }
    
   @Override
    public String getLabel() {
        return label;
    }
    
    /**Constructor used for UnoHand
     * @param label
     * @param unocards
     */
    public UnoDeck(String label, ArrayList<UnoCard> unocards) {
        this.label = label;
        this.unocards = unocards;
    }
    
    /**
     * Constructor - UnoDeck with wild cards
     * Initializes ArrayList<UnoCard> unocards 
     * deck
     * Adds non-wild cards of Y,B, G, R colors to deck
     * Adds Wild and WD4 to deck
     * 
     * @param label
     */
    public UnoDeck(String label) {
        this.label = label;
        unocards = new ArrayList<UnoCard>();

        cardDeckBuilder(24, 3);
        
        /**
         * Here is where wild cards and DrawFour are added
         */
        for (int rank = 25; rank < UnoCard.getRanks().length; rank++) {
            int color = 4; // Black for wild cards
            addCard(new UnoCard(color, rank));
        }
    } // End Constructor
    
    /**Trying to cleanup constructor
     * abstract in CardDeck because Card is abstract
     * and therefor cannot be instantiated
     * @see Uno2.CardDeck#cardDeckBuilder(int, int)
     */
    protected void cardDeckBuilder(int rankMax, int colorMax) {
        for (int color = 0; color <= colorMax; color++) {
            for (int rank = 0; rank <= rankMax; rank++) {
                addCard(new UnoCard(color, rank));
            }
        }
    }
    
    /** Deal(that,n) Removes n cards from 
     * this.UnoDeck, and
     *  adds n cards to that.UnoHand
     *  */
    public void deal(UnoHand that, int n) {
        for (int i=0; i < n; i++) {
            UnoCard unocard = popCard();
            that.addCard(unocard);
        }
    }
    
    /**Used in UnoV2 Class Constructor 
     * (drawPile)
     * moves all remaining cards to that 
     * given UnoHand
     * 
     **/
    public void dealAll(UnoHand that) {
        int n = size();
        deal(that, n);
    }
    
    /**
  * Begin Helper Utility Methods
  */   
    
    public void printDeck(ArrayList<UnoCard> unocards) {
        
        for(UnoCard card:unocards) {
            System.out.println(card);
        }
    }
    
    @Override
    public String toString() {
        return getLabel() + ":" + unocards.toString();
    }

    /**Return UnoCard at specific index position 
     * from AL unocards.
     * Used in SwapCard(int, int) helper method.
     * @param i AL integer position of desired card
     * @return UnoCard at position i in AL unocards [the deck]
     */
    public UnoCard getCard(int i) {
        return unocards.get(i);
    }
    
    /**
     * Used in reshuffle of discardPile when discardPile becomes drawPile. These
     * 'Piles' are UnoHands
     * 
     */
    @Override
    public void shuffle() {
        Random rand = new Random();
        for (int i = size() - 1; i > 0; i--) {
            int j = rand.nextInt(i);
            swapCards(i, j);
        }
    }
    
    @Override
    public void swapCards(int i, int j) {
        UnoCard temp = getCard(i);
        unocards.set(i, getCard(j));
        unocards.set(j, temp);
    }
    
    public void addCard(UnoCard card) {
        unocards.add(card);
    }
    
    /**
     * Checks if unocards AL is empty
     * 
     * @return true if hand is empty
     */
    public boolean empty() {
        return getUnocards().isEmpty();
    }

    /**Removes AL unoCard[i], and shifts all cards 
     * above it to the left
     * 
     * Used in UnoV2 and UnoPlayer
     * 
     * @param i int of tgt card in ArrayList
     * @return card removed from specific index posit.
     */
 public UnoCard popCard(int i) { 
        return unocards.remove(i);
    }
    
    /**Used in draw, deal(Hand, int), and 
     *      UnoV2 Constructor.
     * "Overloaded" 
     * Removes top card, no need to shift left
     * @return top/last card
     */
    public UnoCard popCard() { 
           int i = size() - 1;
           return unocards.remove(i);
       }
    
    /**Used in dealAll(UnoHand) and many 
     * For Control Loops
     * @return size of unocards deck
     */
    public int size() {
        return unocards.size();
    }
    
    /*
     * Used in UnoV2 takeTurn() gets last card from calling CardCollection, but
     * doesn't remove it So it is like taking a look at the card
     */
    public UnoCard last() {
        int i = size() - 1;
        return getCard(i);
    }
}

