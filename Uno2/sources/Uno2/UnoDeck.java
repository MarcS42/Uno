package Uno2;

import java.util.ArrayList;
import java.util.Random;

public class UnoDeck {
    private String label;
    private ArrayList<UnoCard> unocards;
    
    /**
     * Constructor - UnoDeck with wild cards
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
        for (int rank = 25; rank < UnoCard.RANKS.length; rank++) {
            int color = 4; // Black for wild cards
            addCard(new UnoCard(color, rank));
        }

    } // End Constructor
    
    public String toString() {
        return label + ":" + unocards.toString();
    }
    
    public String getLabel() {
        return label;
    }
    
    public ArrayList<UnoCard> getUnoCards() {
        return unocards;
    }
    
    public UnoCard getCard(int i) {
        return unocards.get(i);
    }
    
    /** Deal(that,n) Removes n cards from this.UnoDeck, and
     *  adds n cards to that.UnoDeck*/
    public void deal(UnoHand that, int n) {
        for (int i=0; i < n; i++) {
            UnoCard unocard = popCard();
            that.addCard(unocard);
        }
    }
    
    /**moves all remaining cards to the given UnoDeck
     * 
     **/
    public void dealAll(UnoHand that) {
        int n = size();
        deal(that, n);
    }
    
    public void addCard(UnoCard unocard) {
        unocards.add(unocard);
    }
    
    public UnoCard popCard(int i) { // removes unocard[i], and shifts all cards above to left
        return unocards.remove(i);
    }

    public UnoCard popCard() { // "overloaded" removes top card, no need to shift left
        int i = unocards.size() - 1;
        return unocards.remove(i);
    }
    
    /*gets last card from calling UnoDeck, but doesn't remove it*/
    public UnoCard last() {
        int i = size()-1;
        return getCard(i);
    }

    public boolean empty() {
        return unocards.size() == 0;
    }
  
    public void printDeck(ArrayList<UnoCard> unocards) {
        for(int i=0; i < unocards.size(); i++) {
            System.out.println(getCard(i));
        }
    }
    
    public void swapCards(int i, int j) {
        UnoCard temp = getCard(i);
        unocards.set(i, getCard(j));
        unocards.set(j, temp);
    }
    
    public void shuffle() {
        Random rand = new Random();
        for (int i= size()-1; i > 0; i--) {
            int j = rand.nextInt(i);
            swapCards(i,j);
        }
    }
    
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

