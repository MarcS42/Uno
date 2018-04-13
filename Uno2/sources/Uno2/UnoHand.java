package Uno2;

/**
 * 
 */


import java.util.ArrayList;
import java.util.Random;

/**
 * @author MarcSherman
 *
 */
public class UnoHand{
    public String label;
    public ArrayList<UnoCard> unohand;

    public UnoHand(String label) {
        this.label = label;
        this.unohand = new ArrayList<UnoCard>();
    }
    
    public UnoCard getCard(int i) {
        return unohand.get(i);
    }
       
    public String getLabel() {
        return label;
    }
    
    public int handSize() {
        return unohand.size();
    }
    
    public void swapCards(int i, int j) {
        UnoCard temp = getCard(i);
        unohand.set(i, getCard(j));
        unohand.set(j, temp);
    }
    
    public static UnoHand insertionSortUnoHand(UnoHand unohand) {
        for (int i = 1; i < unohand.handSize(); i++) {
            for(int k= i; (k>0 && (UnoCard.compareUnoCards
               (unohand.getCard(k), unohand.getCard(k - 1)) < 1)); k--) {
                unohand.swapCards(k,k-1);
            }
        }
        return unohand;
    }
    
    /**
     * Scoring system unique to Uno is in class UnoCard
     * */
   public static int scoreHandUno(UnoHand unohand) {
           int sum = 0;
           for(int i = 0; i < unohand.handSize(); i++) {
               UnoCard unocard = unohand.getCard(i);
               sum += UnoCard.scoreCardUno(unocard);
           }
           return sum;     
       }
       
   
   public void display() {
       System.out.println(getLabel() + ": ");
       for (int i =0; i < handSize(); i++) {
           System.out.println(getCard(i));
//           System.out.println(" " + UnoCard.scoreCardUno(getCard(i)));
       }
       System.out.println();
   }
   
   public void addCard(UnoCard unocard) {
       unohand.add(unocard);
   } 
 
   public boolean empty() {
       return unohand.isEmpty();
   }

   /**Removes AL unocard[i], and shifts all cards 
    * above it to the left
    * @param i int of tgt card in ArrayList
    * @return card removed from specific index posit.
    */   
   public UnoCard popCard(int i) { 
       return unohand.remove(i);
   }
 
   /**"overloaded" Removes top card, no need to shift left
    * @return top/last card
    */
   public UnoCard popCard() { 
       int i = unohand.size() - 1;
       return unohand.remove(i);
   }

/*moves all remaining cards to the given CardCollection*/
   public void dealAll(UnoHand that) {
       int n = handSize();
       deal(that, n);
   }
   
   /* Deal(that,n) Removes n cards from this.CardCollection, and
    *  adds n cards to that.CardCollection*/
   public void deal(UnoHand that, int n) {
       for (int i=0; i < n; i++) {
           UnoCard card = popCard();
           that.addCard(card);
       }
   }
   
   public void shuffle() {
       Random rand = new Random();
       for (int i= handSize()-1; i > 0; i--) {
           int j = rand.nextInt(i);
           swapCards(i,j);
       }
   }
   
   /*gets last card from calling CardCollection, 
    * but doesn't remove it*/
   public UnoCard last() {
       int i = handSize()-1;
       return getCard(i);
   }
   
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        UnoDeck deck = new UnoDeck("UnoHand Test");
//
//        for (UnoCard unocard : deck.getUnoCards()) {
//            System.out.print(unocard + " ");
//            System.out.println(UnoSpecialCardsV2.unoCardWildDrawFour(unocard));
//        }
//        System.out.println();
//        deck.shuffle();
//
//        for (UnoCard unocard : deck.getUnoCards()) {
//            System.out.print(unocard + " ");
//            System.out.println(UnoSpecialCardsV2.unoCardWildDrawFour(unocard));
//        }
//        System.out.println("");
//        
//        UnoHand player1;
//        player1 = new UnoHand("Testhand1");
//        if(player1.empty())System.out.println("empty hand 1");
//        System.out.println("");
//        
//        deck.deal(player1, 7);
//        player1.display();
//        System.out.println(scoreHandUno(player1));
//        
//        UnoHand player2;
//        player2 = new UnoHand("TestHand2");
//        if(player2.empty())System.out.println("empty hand2");
//        System.out.println("");
//        
//        deck.deal(player2, 7);
//        UnoHand.insertionSortUnoHand(player2);
//        player2.display();
//        System.out.println(scoreHandUno(player2));
//        
//        UnoHand player3;
//        player3 = new UnoHand("Testhand3");
//        if(player3.empty())System.out.println("empty hand 3");
//        System.out.println("");
//        
//        deck.deal(player3, 7);
//        UnoHand.insertionSortUnoHand(player3);
//        System.out.println(scoreHandUno(player3));
//        player3.display();
//        
//        
//        UnoHand discardpile;
//        discardpile = new UnoHand("Discard Pile");
//        if(discardpile.empty())
//            System.out.println("Empty discard pile");
//        System.out.println("");
//        
//        deck.deal(discardpile, 1);
//        discardpile.display();
//        System.out.println("");
//        
//        UnoHand drawpile;
//        drawpile= new UnoHand("Draw Pile");
//        if(drawpile.empty())
//            System.out.println("Empty draw pile");
//        System.out.println("");
//        
//        deck.dealAll(drawpile);
//        System.out.println("Draw Pile size: " + drawpile.handSize());
//        System.out.println("");
       
        for(int indexP=0;indexP < 3; indexP++) {
            int indexNextPlayer;
            int indexIncrement=2;
            int playersSize=3;
//            if (skip) {
//                indexIncrement = 2;
//            } else {
//                indexIncrement = 1;
//            }
//            if (clockwise) {
//                indexNextPlayer = (players.indexOf(current) + indexIncrement) % players.size();
//            } else {
                indexNextPlayer = (indexP - indexIncrement) % playersSize;
                if (indexNextPlayer < 0 && indexP==1) {// need to fix this when 3 players, counter clockwise, and skip on currentplayer 1
                        indexNextPlayer = playersSize - indexIncrement +1;// 1-2=-1 => <0 =>3-2 = 1 again
                }else if(indexNextPlayer <0) {
                    indexNextPlayer = playersSize-indexIncrement;
                }
                System.out.println(indexP + " "+ indexNextPlayer);
            }
            
        }


}

