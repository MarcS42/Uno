package Uno2;

public class TestUnoHand {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//      UnoDeck deck = new UnoDeck("UnoHand Test");
//
//      for (UnoCard unocard : deck.getUnoCards()) {
//          System.out.print(unocard + " ");
//          System.out.println(UnoSpecialCardsV2.unoCardWildDrawFour(unocard));
//      }
//      System.out.println();
//      deck.shuffle();
//
//      for (UnoCard unocard : deck.getUnoCards()) {
//          System.out.print(unocard + " ");
//          System.out.println(UnoSpecialCardsV2.unoCardWildDrawFour(unocard));
//      }
//      System.out.println("");
//      
//      UnoHand player1;
//      player1 = new UnoHand("Testhand1");
//      if(player1.empty())System.out.println("empty hand 1");
//      System.out.println("");
//      
//      deck.deal(player1, 7);
//      player1.display();
//      System.out.println(scoreHandUno(player1));
//      
//      UnoHand player2;
//      player2 = new UnoHand("TestHand2");
//      if(player2.empty())System.out.println("empty hand2");
//      System.out.println("");
//      
//      deck.deal(player2, 7);
//      UnoHand.insertionSortUnoHand(player2);
//      player2.display();
//      System.out.println(scoreHandUno(player2));
//      
//      UnoHand player3;
//      player3 = new UnoHand("Testhand3");
//      if(player3.empty())System.out.println("empty hand 3");
//      System.out.println("");
//      
//      deck.deal(player3, 7);
//      UnoHand.insertionSortUnoHand(player3);
//      System.out.println(scoreHandUno(player3));
//      player3.display();
//      
//      
//      UnoHand discardpile;
//      discardpile = new UnoHand("Discard Pile");
//      if(discardpile.empty())
//          System.out.println("Empty discard pile");
//      System.out.println("");
//      
//      deck.deal(discardpile, 1);
//      discardpile.display();
//      System.out.println("");
//      
//      UnoHand drawpile;
//      drawpile= new UnoHand("Draw Pile");
//      if(drawpile.empty())
//          System.out.println("Empty draw pile");
//      System.out.println("");
//      
//      deck.dealAll(drawpile);
//      System.out.println("Draw Pile size: " + drawpile.handSize());
//      System.out.println("");
     
      for(int indexP=0;indexP < 3; indexP++) {
          int indexNextPlayer;
          int indexIncrement=2;
          int playersSize=3;
//          if (skip) {
//              indexIncrement = 2;
//          } else {
//              indexIncrement = 1;
//          }
//          if (clockwise) {
//              indexNextPlayer = (players.indexOf(current) + indexIncrement) % players.size();
//          } else {
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
