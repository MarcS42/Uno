package Uno2.TestClasses;

import Uno2.UnoCard;
import Uno2.UnoDeck;
import Uno2.UnoSpecialCardsV2;

public class TestUnoDeck {
    
    UnoSpecialCardsV2 spC = new UnoSpecialCardsV2();
    
    public TestUnoDeck() {
        UnoDeck unoDeck1;
        
        unoDeck1 = new UnoDeck("Uno Shuffle");
        for (UnoCard unocard : unoDeck1.getUnocards()) {
            System.out.printf("%-16s"+" ", unocard);
             System.out.printf("%3d%n", UnoCard.scoreCard(unocard));
        }
        System.out.println();
        unoDeck1.shuffle();

        for (UnoCard unocard : unoDeck1.getUnocards()) {
            System.out.printf("%-16s"+" ", unocard);
            System.out.printf("%5s%n", spC.unoCardDrawTwo(unocard));
        }
        System.out.println(unoDeck1.size());
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        TestUnoDeck testUDeck1;
        testUDeck1 = new TestUnoDeck();

    }

}
