package Uno2;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Uno.java encapsulates the state of the game
 * @author MarcSherman
 *
 */
public class UnoV2 {
    private ArrayList<UnoPlayer> players;
    private UnoPlayer player;
    private Scanner in;
    private UnoHand drawPile;
    private UnoHand discardPile;
    private static boolean clockwise;
    private static boolean skip;
    private boolean firstPass;
    private static int wildColor;
    UnoSpecialCardsV2 spC = new UnoSpecialCardsV2();
    UnoHand uH = new UnoHand();
    
    /**
     * Constructor of Uno
     * Sets up initial game State.
     * Gets Player input from user
     * Initializes Player instances and ArrayList<Player> objects
     * Start with a deck, shuffle it, 
     *      deal the hands to the players, 
     * put initial card into the discard pile, then deal rest 
     *         of deck to draw pile.
     * Initialize key game variables starting values.
     */
    public UnoV2() { //Uno Constructor
        in = new Scanner(System.in);
        players = new ArrayList<UnoPlayer>();

        int handSize = 7;
        int numberPlayers;
//        String playerName;

        UnoDeck deck = new UnoDeck("Deck");
        deck.shuffle();

//        System.out.println("Enter the number of players this game: ");
//        numberPlayers = in.nextInt();
          numberPlayers = 4; //remove later
        for (int i = 0; i < numberPlayers; i++) {
            
//            System.out.println("Enter players' names separated by a  space: ");
//            playerName = in.next();
            UnoPlayer newPlayer = new UnoPlayer("P " +i);
            deck.deal(newPlayer.getHand(), handSize);
            UnoHand.insertionSortUnoHand(newPlayer.getHand());
            players.add(newPlayer);
        }
        
        discardPile = new UnoHand("Discards");
        deck.deal(discardPile, 1);
//     1st card cannot be wild
        while (discardPile.getCard(0).getColor()>3){
            deck.addCard(discardPile.popCard());
            deck.shuffle();
            deck.deal(discardPile, 1);
        }

// initialize state of key game variables
        player = players.get(0);
        clockwise = true;
        skip = false;
        firstPass = true;
        wildColor=4;
        
        drawPile = new UnoHand("Draw Pile");
        deck.dealAll(drawPile);
    }//End constructor
    
    public static boolean getDirectionOfPlay() {
        return clockwise;
    }
    
    public UnoPlayer getPlayer(UnoPlayer player) {
        return player;
    }
    
    public void setPlayer(UnoPlayer player) {
       this.player = player;
    }
    
    public static boolean getSkip() {
        return skip;
    }
    
  
    public static boolean toggleDirectionOfPlay() {
        if(clockwise) {
            clockwise = false;
        }else {
            clockwise = true;
        }
        return clockwise;
    }
   
   public static int getWildColor() {
       return wildColor;
   }
    
    /**
     * Displays the current State of the Game
     */
    public void displayState() {
        System.out.println("");
        if(clockwise) {
            System.out.println("Direction of play is clockwise.");
        }else {
            System.out.println("Direction of play is counter-clockwise.");
        }
        System.out.println("wildColor= " + wildColor+ " Skip= " + skip + " firstPass = " + firstPass);
        System.out.println("NextPlayer = " + player.getName());
        
        for (UnoPlayer player:players) {
            UnoHand.insertionSortUnoHand(player.getHand());
            player.getHand().display();
        }
        
        discardPile.display();
        System.out.println("Draw Pile:");
        System.out.println(drawPile.size() + " cards");
        System.out.println("");
    } // End displayState()
    
    /**
     * Is game over?
     * @return
     */
    public boolean isDone() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHand().empty()) {
                return true;
            }
        }
        return false;
    }
    
    /** For when drawPile runs out of cards.
     * Removes top card from discardPile, makes discardPile 
     * the drawPile and then shuffles
     * it; adds top card to new discardPile
     */
    public void reshuffle() {
//    remove top card from discard pile
        UnoCard prev = (UnoCard) discardPile.popCard(); 
//    transfer remaining discard pile to new draw pile
        discardPile.dealAll(drawPile);
//    shuffle new draw pile        
        drawPile.shuffle();
//    add back the orig top card to the new draw pile
        discardPile.addCard(prev);
    }
    
    /**
     * Draws a Card from drawPile provided drawPile not empty 
     * @return
     */
    public UnoCard draw() {
        if (drawPile.empty()) {
            reshuffle();
        }
        return (UnoCard) drawPile.popCard();
    }
    
    /**
     * This is for when PREV card is WD4
     * Checks if next player has wd4, if not draws 4
     * and keeps track of players if 2 WD4s are played in a row.
     * @param player
     * @param wd4CardsPlayed
     */
    public UnoPlayer drawWildDrawFour(UnoPlayer player, int wd4CardsPlayed) {
        do {
            boolean wd4MatchCheck = false;
            int i = 0;
            int counter = 0;
            do {
                if (counter == 0)
                    i = player.getHand().size() - 1;
                UnoCard next = player.getHand().getCard(i);
                if (spC.unoCardWildDrawFour(next)) { // check to see if he has D2
                    wd4MatchCheck = true;
                    wd4CardsPlayed++;
                    System.out.println(player.getName() + " plays " + next);
                    next = (UnoCard) player.getHand().popCard(i);
                    discardPile.addCard(next);
                    player = nextPlayer(player);
                    displayState();
                }
                counter++;
                i--;
            } while (!wd4MatchCheck && i > -1);

            /**
             * When you get to this If Check you know that 
             * there are no more WD4 matches and player will
             * be picking cards and then returning from
             * drawWildDrawFour() to SpecialCardWD4 IF statement
             */
            if (!wd4MatchCheck) {
                for (int j = 0; j < 4 * wd4CardsPlayed; j++) {
                    UnoCard unocard = draw();
                    System.out.println(player.getName() + " draws " + unocard);
                    player.getHand().addCard(unocard);
                }
            /**
             * Need to use setPlayer() as 'players' running 
             * thru drawWildDrawFour() do not have any 
             * scope access to player on record in  
             * SpecialCard(prev) waterfall filters. 
             * 
             * So if nextPlayer() advances you more than one 
             * player via playing follow-on WD4 card, player 
             * in takeTurn() <> sync with player coming out 
             * of drawWildDrawFour().         
             */
                System.out.println("");
                if (wd4CardsPlayed > 1) {
                    setPlayer(player);
                }
                wd4CardsPlayed=0;
                return player;
            } 
         }while (wd4CardsPlayed >= 1); 
      /** This return never gets hit, but if you pull it, 
       * get problems with drawTurn() bug detection
       *   looking for return statement
       */
         return player; 
    }//End wildDrawFour()
        
    /**
     * card PREV was DrawTwo checks if next player has D2, 
     * if not draws2;
     * if so, plays D2 and keeps track of how many D2s have 
     * been played 
     * in a row
     */
    public UnoPlayer drawTwo(UnoPlayer player, int d2CardsPlayed) {
     
     do {
        boolean d2MatchCheck = false;
        int i = 0;
        int counter = 0;
        do {
            if (counter == 0)
                i = player.getHand().size() - 1;
            UnoCard next = player.getHand().getCard(i);
            if (spC.unoCardDrawTwo(next)) { // check to see if he has D2
                d2MatchCheck = true;
                d2CardsPlayed++;
                System.out.println(player.getName() + " plays " + next);
                next = (UnoCard) player.getHand().popCard(i);
                discardPile.addCard(next);
                player = nextPlayer(player);
                displayState();
            }
            counter++;
            i--;
        } while (!d2MatchCheck && i > -1);

        /**
         * When you get to this If Check you know that there are no more D2
         * matches and player will be picking cards and then returning from
         * drawTwo() to SpecialCardD2 IF statement
         */
        if (!d2MatchCheck) {
            for (int j = 0; j < 2 * d2CardsPlayed; j++) {
                UnoCard unocard = draw();
                System.out.println(player.getName() + " draws " + unocard);
                player.getHand().addCard(unocard);
            }
            /**
             * Need to use setPlayer() as 'players' running thru drawTwo() do 
             * not have any scope access to player on record in SpecialCard(prev) 
             * waterfall filters. 
             * So if nextPlayer() advances you more than one 
             * player via playing follow-on D2 card, player in takeTurn() <> 
             * sync with player coming out of drawTwo().         
             */
            System.out.println("");
            if (d2CardsPlayed > 1) {
                setPlayer(player);
            }
            d2CardsPlayed=0;
            return player;
        } 
     }while (d2CardsPlayed >= 1); 
  /** This return never gets hit, but if you pull it, get problems with
   *  drawTurn() bug detection looking for return statement
   */
     return player; 
    }//End drawTwo()

    /**
     * Pick random wild card color
     * @return int color
     */
    public int unoWildCardColor() {
        int color = spC.randomColor();
        return color;
    }
    
    public void waitForUser() {// Waits until you hit enter
        in.nextLine();
    }
    
    public void takeTurn() {
        System.out.println("Player into TakeTurn() = " + player.getName());
        
        UnoCard prev = discardPile.last();
        
        /**
         * Start firstPass && Special
         */
        if (firstPass && 
                spC.unoSpecialCard(prev)) {
            firstPass = false;
            if (spC.unoCardSkip(prev)) {
                player = players.get(players.indexOf(player)+2);
            }
            if (spC.unoCardReverse(prev)) {
                UnoV2.toggleDirectionOfPlay();
                player = players.get(players.size()-1);
            }
        } //End firstPass && Special tests
        
        /**
         * Start actions for previous card = Special
         */
        if(spC.unoSpecialCard(prev)) {
            if (spC.unoCardDrawTwo(prev)) {
                
                int d2CardsPlayed = 1;
                drawTwo(player, d2CardsPlayed);
                
                player = nextPlayer(player);
            }
                        
            if (spC.unoCardWildDrawFour(prev)) {
                
                int wd4CardsPlayed = 1;
                drawWildDrawFour(player, wd4CardsPlayed);
                
                player = nextPlayer(player);
            }
          prev = discardPile.last();
        }//End actions for previous card = Special

        
        System.out.println("nextPlayer() = " + player.getName() + " prev card = " + prev);
        UnoCard next = player.play(this, prev);

        
        /**
         * Actions needed when NEXT card is special card
         */
        if (spC.unoSpecialCard(next)) {
            if (spC.unoCardSkip(next)) {
                skip = true;
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next);
                System.out.println();
                player = nextPlayer(player);
            }
            if (spC.unoCardReverse(next)) {
                UnoV2.toggleDirectionOfPlay();
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next);
                System.out.println();
                player = nextPlayer(player);
            }
            if(spC.unoCardWild(next)) {
                wildColor=unoWildCardColor();
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next + 
                        " Match "+ UnoCard.getColors()[wildColor]);
                System.out.println(); 
                player = nextPlayer(player);
            }
            if(spC.unoCardWildDrawFour(next)) {
                wildColor=unoWildCardColor();
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next + 
                        " Match "+ UnoCard.getColors()[wildColor]);
                System.out.println();
                player = nextPlayer(player);
            }
            if (spC.unoCardDrawTwo(next)) {
                discardPile.addCard(next);
                System.out.println(player.getName() + " plays " + next);
                System.out.println();
                player = nextPlayer(player);
            }

        }//End special card next
        
        if (!spC.unoSpecialCard(next)) {
            discardPile.addCard(next);
            System.out.println(player.getName() + " plays " + next);
            System.out.println();
            player = nextPlayer(player);            
        }

    } //End takeTurn(UnoPlayer)
    
    public void playGame() {
        
        //keep playing until there's a winner (no cards left in hand)
        while(!isDone()) {
            displayState();
//            waitForUser();
            takeTurn();
        }
        
//      Display final score.
        System.out.println("Final Scores:");
        for (int i= 0; i <players.size(); i++) {
        System.out.println(players.get(i).getName() + " scores " + uH.scoreHandUno(players.get(i).getHand()));
        }
    }
    
    /*//**
    * Change Players
    * Uses ArrayList method indexOf(Obj) to return current players index #
    * Uses modulus: (current player index + 1) mod players ArrayList size
    * @param current
    * @return
    */
    public UnoPlayer nextPlayer(UnoPlayer current) {
        int indexNextPlayer;
        int indexIncrement;
//        System.out.println("Player into (current) nextPlayer "+ current.getName());
//        System.out.println("Skip = "+ skip);
//        System.out.println("Clockwise = "+ clockwise);
        
        if (skip) {
            indexIncrement = 2;
        } else {
            indexIncrement = 1;
        }
        
        if (clockwise) {
            indexNextPlayer = (players.indexOf(current) + indexIncrement) % players.size();
        } else {
            indexNextPlayer = (players.indexOf(current) - indexIncrement) % players.size();
            if (indexNextPlayer < 0 && players.indexOf(current) == 1) {// need to fix this when 3
                                                                       // players, counter
                                                                       // clockwise, and skip on
                                                                       // currentplayer 1
                indexNextPlayer = players.size() - indexIncrement + 1;// 1-2=-1 => <0 =>3-2 = 1
                                                                      // again
            } else if (indexNextPlayer < 0) {
                indexNextPlayer = players.size() - indexIncrement;
            }
            
        }
      firstPass=false;  
      skip = false;
        
//        System.out.println("nextPlayer out = "+ players.get(indexNextPlayer).getName());
      return players.get(indexNextPlayer);   
    }

    public static void main(String[] args) {
//        UnoV2 game = new UnoV2();
//        game.playGame();

    }

}

