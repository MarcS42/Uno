package Uno2;

public class TestUnoDeck {
    
    UnoSpecialCardsV2 spC = new UnoSpecialCardsV2();
    UnoCard uC = new UnoCard();
    
    public TestUnoDeck() {
        UnoDeck unoDeck1;
        
        unoDeck1 = new UnoDeck("Uno Shuffle");
        for (UnoCard unocard : unoDeck1.getUnocards()) {
            System.out.print(unocard + " ");
             System.out.println(" " + uC.scoreCard(unocard) + " ");
        }
        System.out.println();
        unoDeck1.shuffle();

        for (int i = 0; i < unoDeck1.getUnocards().size(); i++) {
            System.out.print(unoDeck1.getUnocards().get(i) + " ");
            System.out.println(spC.unoCardDrawTwo(unoDeck1.getUnocards().get(i)));
        }
        System.out.println(unoDeck1.size());
    }
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        TestUnoDeck testUDeck1;
        testUDeck1 = new TestUnoDeck();

    }

}
