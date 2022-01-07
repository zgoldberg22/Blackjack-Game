package com.Blackjack;
import java.util.Scanner;

public class Dealer
{
    private Hand hand;
    private Player player;
    private Shoe shoe;
    private int numRounds;
    private int playerChoice;
    private boolean wantToPlay = true;
    private boolean firstTurn = false;
    private String playerName;

    /**
     * @param p (Player): The player playing against the dealer
     * constructs a Dealer object with a new Hand and new Shoe
     */
    public Dealer(Player p)
    {
        player = p;
        playerName = player.getName();
        hand = new Hand();
    }

    /**
     * @return dealer's hand
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * reshuffles shoe if there is 20% of the cards left or less
     */
    public void reshuffleShoe()
    {
        if(shoe.getNumCards() == (.2*(shoe.getNumDecks()*52)))
        {
            shoe.reshuffle();
        }
    }

    /**
     * deals 2 cards to player and dealer hand
     */
    public void dealStartCards()
    {
        player.getHand().addCard(shoe.drawCard());
        hand.addCard(shoe.drawCard());
        player.getHand().addCard(shoe.drawCard());
        hand.addCard(shoe.drawCard());
    }


    /**
     * Dealer's turn: adds card to Dealer's hand while the hand value is less than 17
     */
    public void dealersTurn()
    {
        System.out.println("\nDealer's turn...");
        while(hand.getHandValue() < 17)
        {
            System.out.println("Dealer hits");
            hand.addCard(shoe.drawCard());
        }
        System.out.println("Dealer stands");
    }

    public boolean checkPush()
    {
        if((player.getHand().getHandValue() != 0) && player.getHand().getHandValue() == hand.getHandValue() && !(player.getHand().checkBust()) && !(hand.checkBust()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * clears the player and dealers hand and returns the bet to the player
     * called when checkPush() returns true
     */
    public void push()
    {
        player.getBetBack();
        player.getHand().clearHand();
        hand.clearHand();
    }

    /**
     * pays player back and prints a win statement
     * only called when player's hand wins
     */
    public void playerWin()
    {
        player.changeBankroll();
        System.out.println(playerName + " won! \tCurrent Bankroll: " + player.getBankroll());
    }

    /**
     * tells player they lost and prints current bankroll
     * only called when player's hand loses
     */
    public void playerLose()
    {
        System.out.println(playerName + " lost!" + "\tCurrent Bankroll: " + player.getBankroll());
        //System.out.println("Dealer Hand Value: " + hand.getHandValue() + "\nCurrent Bankroll: "+ player.getBankroll());
    }

    /**
     * Executes the action of the player's choice based on the value of the playerChoice instance variable
     *      1 = hit
     *      2 = stand
     *      3 = double down
     */
    public void playerChoiceAction()
    {
        if(playerChoice == 1)
        {
            System.out.println(playerName + " chooses to hit...");
            player.hit(shoe.drawCard());
            printHands();
            if(player.getHand().check21() == true || player.getHand().checkBust() == true)
            {
                playerChoice = 4;
                //this stops the while loop in the start method
            }
        }
        else if(playerChoice == 2)
        {
            System.out.println(playerName + " chooses to stand...");
            player.stand();
            printHands();
        }
        else if(playerChoice == 3)
        {
            System.out.println(playerName + " chooses to double down...");
            player.doubleDown(shoe.drawCard());
            printHands();
        }
    }

    /**
     * asks the player for user input on their choice
     * calls playerChoiceAction() to execute the choice
     */
    public void playerTurn()
    {
        Scanner s = new Scanner(System.in);
        if(firstTurn == true)
        {
            System.out.println("Enter 1 to hit, 2 to stand, or 3 to double down: ");
            playerChoice = s.nextInt();
            playerChoiceAction();
        }
        else
        {
            System.out.println("Enter 1 to hit or 2 to stand: ");
            playerChoice = s.nextInt();
            playerChoiceAction();
        }
    }

    /**
     * @return true if player and/or dealer get blackjack
     * called in start() method to check if player should go
     */
    public boolean checkForBlackjack()
    {
        if(firstTurn == true)
        {
            if(player.getHand().checkBlackjack() && hand.checkBlackjack())
            {
                return true;
            }
            else if(player.getHand().checkBlackjack())
            {
                return true;
            }
            else if(hand.checkBlackjack())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * checks through all possible results that could happen in a round
     * checks if push or if player wins or loses
     */
    public void checkResults()
    {
        if(firstTurn == true)
        {
            if(player.getHand().checkBlackjack() && hand.checkBlackjack())
            {
                System.out.println("\n" + playerName + " and dealer both got blackjack. Push...");
            }
            else if(player.getHand().checkBlackjack())
            {
                System.out.println("\n" + playerName + " got blackjack!");
                playerWin();
            }
            else if(hand.checkBlackjack())
            {
                System.out.println("\nDealer got blackjack.");
                playerLose();
            }
        }
        else if(player.getHand().getHandValue() == hand.getHandValue())
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            System.out.println(playerName + " and dealer tie. Push...");
        }
        else if(player.getHand().checkBust())
        {
            System.out.println("\n" + playerName + " busts with a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            playerLose();
        }
        else if(hand.checkBust())
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer busts with a hand value of " + hand.getHandValue() + "\n");
            playerWin();
        }
        else if(player.getHand().cardsInHand() == 5 && !(hand.check21()))
        {
            System.out.println("\n" + playerName + " wins (5 card Charlie)");
            playerWin();
        }
        else if(player.getHand().check21() == true)
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            playerWin();
        }
        else if(hand.check21() == true)
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            playerLose();
        }
        //if playerChoice == doubleDown
        else if(playerChoice == 3)
        {
            if(player.getHand().checkBust() == false && player.getHand().getHandValue() > hand.getHandValue())
            {
                System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
                System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
                playerWin();
            }
            else if(player.getHand().checkBust() == true)
            {
                System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
                System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
                System.out.println("\n" + playerName + " busts");
                playerLose();
            }
            else if(player.getHand().checkBust() == false && player.getHand().getHandValue() < hand.getHandValue())
            {
                System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
                System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
                playerLose();
            }
        }
        else if(player.getHand().getHandValue() < hand.getHandValue())
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            playerLose();
        }
        else if(player.getHand().getHandValue() > hand.getHandValue())
        {
            System.out.println("\n" + playerName + " has a hand value of " + player.getHand().getHandValue());
            System.out.println("Dealer has a hand value of " + hand.getHandValue() + "\n");
            playerWin();
        }
    }

    /**
     * prints the player's hand and the dealer's first card
     * called after each time a player chooses a move
     */
    public void printHands()
    {
        String cheese = "\n" + playerName + "'s Hand: \n" + player.getHand().toString()
                + "\nDealer's Card in Hand: " + hand.getHand().get(0).toString();
        System.out.println(cheese);
    }

    /**
     * asks for user input to change bet before each round
     * sets the bet of the player to value entered
     */
    public void askChangeBet()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nHow much would you like to bet this round? (enter integer amount): ");
        int newBet = sc.nextInt();
        player.setBet(newBet);
    }

    /**
     * asks for user input if player would like to play another round
     * changes instance variable wantToPlay to true or false based on user input
     */
    public void askPlayAgain()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWould you like to keep playing? (yes or no): ");
        String keepPlaying = sc.nextLine();
        wantToPlay = keepPlaying.equalsIgnoreCase("yes");
        if(wantToPlay)
        {
            reshuffleShoe();
            player.getHand().clearHand();
            hand.clearHand();
        }
    }

    /**
     * @param numDecks (int): number of decks the user wants to use (user input needed from Game method)
     * starts a round of blackjack until the user decides to stop playing or until their bankroll is too low
     */
    public void start(int numDecks)
    {
        boolean okayBet = true;
        while(wantToPlay)
        {
            askChangeBet();
            if(player.getBankroll() == 0 || player.getBankroll() - player.getBet() <= 0)
            {
                if(player.getBankroll() == 0)
                {
                    wantToPlay = false;
                    System.out.println("You do not have enough money left to play another round");
                    okayBet = false;
                }
                else if(player.getBankroll() - player.getBet() <= 0)
                {
                    while(player.getBankroll() - player.getBet() <= 0)
                    {
                        System.out.println("You do not have enough money left to play a round with betting $" + player.getBet());
                        Scanner s = new Scanner(System.in);
                        System.out.println("Enter new bet: ");
                        int newBet = s.nextInt();
                        player.setBet(newBet);
                        okayBet = true;
                    }
                }

            }
            if(okayBet == true)
            {
                if(numRounds == 0)
                {
                    shoe = new Shoe(numDecks);
                    //shuffles shoe 5 times
                    for(int i = 0; i < 5; i++)
                    {
                        shoe.shuffle();
                    }
                    numRounds++;
                    System.out.println("\nInitial Money in Bank: $" + player.getBankroll());
                    int bet = player.getBet();
                    player.placeBet(bet);
                    System.out.println("Player bets $" + player.getBet() + "\nBankroll after bet: $" + player.getBankroll());
                    dealStartCards();
                    printHands();
                    firstTurn = true;
                }
                else
                {
                    numRounds++;
                    reshuffleShoe();
                    int bet = player.getBet();
                    player.placeBet(bet);
                    System.out.println("\nPlayer bets $" + player.getBet() + "\nBankroll after bet: $" + player.getBankroll());
                    dealStartCards();
                    printHands();
                    firstTurn = true;
                }
                if(checkForBlackjack())
                {
                    checkResults();
                }
                else
                {
                    System.out.println("\nPlayer's turn: ");
                    playerTurn();
                    firstTurn = false;
                    while(playerChoice == 1 && !(player.getHand().checkBust()) && !(player.getHand().check21()))
                    {
                        playerTurn();
                    }
                    if(player.getHand().checkBust())
                    {
                        checkResults();
                    }
                    else
                    {
                        dealersTurn();
                        checkResults();
                    }
                }
                if(checkPush())
                {
                    wantToPlay = true;
                    push();
                }
                else
                {
                    askPlayAgain();
                }
            }
        }
        System.out.println("\nThanks for playing!");
    }
}