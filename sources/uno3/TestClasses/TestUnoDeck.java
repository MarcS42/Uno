package uno3.TestClasses;

import uno3.UnoCard;
import uno3.UnoDeck;
import uno3.UnoSpecialCardsV2;

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
        UnoDeck.cloneDeck(unoDeck1);
        
        UnoDeck repeat = UnoDeck.deserializeUnoDeck();
        
        System.out.printf("%-15s:%-15s:%n",unoDeck1.getLabel(),repeat.getLabel());
        for (int i = 0; i < unoDeck1.size()-1; i++) {
            System.out.printf("%-15s\t%-15s%n", 
                    unoDeck1.getCard(i),repeat.getCard(i));
        }
        System.out.println(unoDeck1.size());
        System.out.println();
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        TestUnoDeck testUDeck1;
        testUDeck1 = new TestUnoDeck();

    }

}
