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

        for (UnoCard unocard : unoDeck1.getUnocards()) {
            System.out.printf("%-16s"+" ", unocard);
            System.out.printf("%5s%n", spC.unoCardDrawTwo(unocard));
        }
        System.out.println(unoDeck1.size());
        System.out.println();
        
        System.out.println("Repeat: ");
        UnoDeck repeat = UnoDeck.deserializeUnoDeck();
        UnoDeck.printDeck(repeat.getUnocards());
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        TestUnoDeck testUDeck1;
        testUDeck1 = new TestUnoDeck();

    }

}
