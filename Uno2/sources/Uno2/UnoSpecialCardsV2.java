package Uno2;


import java.util.Random;

public class UnoSpecialCardsV2 extends UnoCard {
        public static boolean specialCard;
        public static boolean skip;
        public static boolean reverse;
        public static boolean drawTwo;
        public static boolean wild;
        public static boolean wildDrawFour;
        
        public UnoSpecialCardsV2(int color, int rank) {
            super(color,  rank);
            }
        
        /**
         * SpecialCard true/false    
         * @param unocard
         * @return
         */
        public static boolean unoSpecialCard(UnoCard unocard) {
            specialCard = false;
            if(unocard.getRank() > 18) {
                specialCard = true;
            }
            return specialCard;
        }
        
        /**
         * skip true/false
         * @param unocard
         * @return
         */
        public static boolean unoCardSkip(UnoCard unocard){
            skip = false;
            if (unocard.getRank() > 18 && unocard.getRank()<21) {
                skip=true;
            }
            return skip;
        }
        /**
         * Reverse = reverse+skip
         * @param unocard
         * @return
         */
        public static boolean unoCardReverse(UnoCard unocard){
            reverse = false;
            if (unocard.getRank() > 20 && unocard.getRank()<23) {
                reverse = true;
            }
            return reverse;
        }

        /**
         * DrawTwo = drawTwo
         * @param unocard
         * @return
         */
        public static boolean unoCardDrawTwo(UnoCard unocard){
            drawTwo = false;
            if (unocard.getRank() > 22 && unocard.getRank()<25) {
                drawTwo=true;
            }
            return drawTwo;
        }
        
        public static int randomColor() {
            Random randomColor = new Random();
            return randomColor.nextInt(4);
        }

        /**
         * Wild = wild + user input color
         * @param unocard
         * @return
         */
        public static boolean unoCardWild(UnoCard unocard){
            wild = false;
            if (unocard.getRank() > 24 && unocard.getRank()<29) {
                wild = true; 
            }
            return wild;
        }
        
    /**
     * WildDrawFour=wild+user input color+drawFour+skip
     * @param unocard
     * @return
     */
        public static boolean unoCardWildDrawFour(UnoCard unocard){
            wildDrawFour = false;
            if (unocard.getRank() > 28 && unocard.getRank()<=32) {
                wildDrawFour = true;  
            }
            return wildDrawFour;
        }
} // end UnoSpecialCardsV2 Class


