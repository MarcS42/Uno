package Uno2;



public class UnoCard {
    
        // Class Variables that are: a) shared, and b) immutable (constants) 
        private static final String[] RANKS = {"0", "1", "1", "2", "2", "3", "3", "4", "4", "5", "5", "6", "6",
                "7", "7", "8", "8", "9", "9", "Skip", "Skip", "Reverse", "Reverse",
                "DrawTwo", "DrawTwo", "Wild", "Wild", "Wild", "Wild", "WildDraw4", "WildDraw4","WildDraw4","WildDraw4"};
        /**
         * @return the ranks
         */
        public static String[] getRanks() {
            return RANKS;
        }

        private static final String[] COLORS = {"Yellow", "Blue", "Green", "Red", "Black"};

        /**
         * @return COLORS[] array
         */
        public static String[] getColors() {
            return COLORS;
        }

        
        private final int color; // 'final' keeps guys from messing with these and
        private final int rank;        // can only assign values within a constructor

    

    public UnoCard() { // zero-args Constructor
        this.color=0;
        this.rank=0;
    }
    
    public UnoCard(int color, int rank) {
        this.color= color; //5 colors: y,blue,g,r, black
     // 27 ranks (0 plus 2 of each other card including skip, 
        // reverse, drawTwo, wild, wildDraw4)  
        this.rank = rank; 
    }
    
    public int getColor() {
        return this.color;
    }

    public int getRank() {
        return this.rank;
    }
    
 /** With a toString() method present, System.out.println 
  * will auto use toString()
* (becuz it overrides built-in toString)   
*/    
    public static void printCard(UnoCard unocard) {
        System.out.println(unocard);
    }
    
    @Override
    public String toString() {
        String s = getColors()[this.color] + " " + RANKS[this.rank];
        return s;
    }
    
    /**Determines if card1 matches card2
     * @param unocard1 Card that is potential match
     * @param unocard2 Prev. card you are trying to match
     * @return
     */
// unocard2 = PREV.Card
    public static boolean unoCardsMatch(UnoCard unocard1, UnoCard unocard2) { 
        int unoCardTgtColor = 0;
        if (unocard2.getRank() > 24) { //Prev was wild and declared tgtColor
            unoCardTgtColor = UnoV2.getWildColor();
            if (unoCardTgtColor == unocard1.color) {
                return true; // > 24 => wild or wd4
                 }
        }
        if (unocard1.color > 3) {
            return true; 
        } else if (unocard2.color == unocard1.color || 
                unoCardRankConversion(unocard2) == unoCardRankConversion(unocard1)) {
            return true;
            }
        return false;
    }// end unoCardsMatch utility method
    
    /**
     * Positive number means c1 > c2
     * @param unocard1
     * @param unocard2
     * @return
     */
    public static int compareUnoCards(UnoCard unocard1, UnoCard unocard2) {
        if (unoCardRankConversion(unocard1) > unoCardRankConversion(unocard2)) {
            return 1;
        } else if (unoCardRankConversion(unocard1) == unoCardRankConversion(unocard2)) {
            return 0;
        }
        return -1;
    }// End compareCards Utility
    
/**
 * Problem is there are 2 of each card, so need to convert array index number to comparable 
 * value so that, for example a yellow 5 where 5 is 1st 5 in Array index, matches second 5 
 * that will have higher Array index number.    
 * @param unocard
 * @return int representing the comparable rank of the Card
 */
    public static int unoCardRankConversion(UnoCard unocard) {
        int value = 0;
        if(unocard.rank % 2 == 0) {
            value = unocard.rank / 2;
        } else {
            value = unocard.rank / 2 +1;
        }
        return value;
    }
    
    /**
     * Card level scoring by Uno rules
     * Called from UnoHand
     * @param card
     * @return integer score of Hand
     */
    public static int scoreCardUno(UnoCard unocard) {
        int cardScore = 0;
        int color = unocard.getColor();
        if (color > 3) {
            cardScore = -50;
        }
        int rank = unocard.getRank();
        if (rank > 18 && rank < 26) {
            cardScore = -20;
        } else if (rank < 20) {
            if (rank % 2 == 0) {
                cardScore = -rank / 2;
            } else {
                cardScore = -rank / 2 - 1;
            }

        }
        return cardScore;
    }
    
    public static void main(String[] args) {
//        for(int i=0; i<4; i++) {
//            for(int j= 19; j<= 32; j++) {
//                System.out.println(new UnoCard(i,j));
//            }
//        }
        
        UnoCard card0000 = new UnoCard();
        System.out.println(card0000);
        UnoCard card0110 = new UnoCard(1,10);
        System.out.println(card0110.getColor() + " " + card0110.getRank());
        UnoCard card0220 = new UnoCard(2,20);
        System.out.println("Uno card equals card0110"  +" "+ card0110);
        System.out.println("Uno card equals card0220"  +" "+ card0220);
        System.out.println(compareUnoCards(card0110,card0220));

    }

}// End class UnoCard

