package Uno2;

import java.util.ArrayList;
import java.util.Random;

public class UnoDeck {
    private String label;
    private ArrayList<UnoCard> unocards;
    
    /**
     * Constructor - UnoDeck with wild cards
     * Initializes ArrayList<UnoCard> unocards deck
     * Adds non-wild cards of Y,B, G, R colors to deck
     * Adds Wild and WD4 to deck
     * 
     * @param label
     */
    public UnoDeck(String label) {
        this.label = label;
        this.unocards = new ArrayList<UnoCard>();

        for (int color = 0; color <= 3; color++) {
            for (int rank = 0; rank <= 24; rank++) {
                addCard(new UnoCard(color, rank));
            }
        }
        /**
         * Here is where wild cards and DrawFour are added
         */
        for (int rank = 25; rank < UnoCard.getRanks().length; rank++) {
            int color = 4; // Black for wild cards
            addCard(new UnoCard(color, rank));
        }

    } // End Constructor
    
    public String getLabel() {
        return label;
    }
    
    public ArrayList<UnoCard> getUnoCards() {
        return unocards;
    }
    
    /**Return UnoCard at specific index position 
     * from AL unocards
     * @param i AL integer position of desired card
     * @return UnoCard at position i in AL unocards [the deck]
     */
    public UnoCard getCard(int i) {
        return unocards.get(i);
    }
    
    /** Deal(that,n) Removes n cards from this.UnoDeck, and
     *  adds n cards to that.UnoHand*/
    public void deal(UnoHand that, int n) {
        for (int i=0; i < n; i++) {
            UnoCard unocard = popCard();
            that.addCard(unocard);
        }
    }
    
    /**moves all remaining cards to that given UnoHand
     * 
     **/
    public void dealAll(UnoHand that) {
        int n = size();
        deal(that, n);
    }
    
    /** 
     * Same method in UnoHand Class, but need it here for 
     * direct class access / utility helper method in deck 
     * deal().
     * Removes top card, no need to shift left
     * @return top/last card
     */
    public UnoCard popCard() { 
        int i = unocards.size() - 1;
        return unocards.remove(i);
    }
    
    /**Checks if unocards AL is empty
     * @return true if deck is empty
     */
    public boolean empty() {
        return unocards.size() == 0;
    }
  
    public void printDeck(ArrayList<UnoCard> unocards) {
        for(int i=0; i < unocards.size(); i++) {
            System.out.println(getCard(i));
        }
    }
    
    @Override
    public String toString() {
        return label + ":" + unocards.toString();
    }

    /**Shuffles deck by generating random integer j,
     * Running through deck, and then 
     * swapping current card i with card at
     * randomly generated posit. j
     * 
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i= size()-1; i > 0; i--) {
            int j = rand.nextInt(i);
            swapCards(i,j);
        }
    }
    
    /** Swaps card posit's, using set(index, card) 
     * in unocards AL deck
     * @param i 1st card
     * @param j 2nd card
     */
    public void swapCards(int i, int j) {
        UnoCard temp = getCard(i);
        unocards.set(i, getCard(j));
        unocards.set(j, temp);
    }

    /**Used in Constructor
     * @param unocard: card to be added to deck
     */
    public void addCard(UnoCard unocard) {
        unocards.add(unocard);
    }

    /**Used in dealAll(UnoHand)
     * @return size of unocards deck
     */
    public int size() {
        return unocards.size();
    }

    public static void main(String[] args) {
        UnoDeck unoDeck1;
        unoDeck1 = new UnoDeck("Uno Shuffle");
        for(UnoCard unocard:unoDeck1.unocards) {
            System.out.print(unocard +" ");
            System.out.print(" " + UnoCard.scoreCardUno(unocard) + " ");
            System.out.println(UnoSpecialCardsV2.unoCardDrawTwo(unocard));
            }
        System.out.println();
        unoDeck1.shuffle();

        for(int i=0; i < unoDeck1.unocards.size(); i++) {
            System.out.print(unoDeck1.unocards.get(i) + " ");
            System.out.println(UnoSpecialCardsV2.unoCardDrawTwo(unoDeck1.unocards.get(i)));
        }
    System.out.println(unoDeck1.size());
    }

}

