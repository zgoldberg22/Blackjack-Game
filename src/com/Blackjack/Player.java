package com.Blackjack;

public class Player
{
    private String name;
    private int bankroll;
    private Hand hand;
    private int bet;

    /**
     * @param theBankroll (int) that sets the instance variable bankroll to that value
     * Constructs a Player object with a user-entered bankroll, creates a new hand, and sets inital bet to 0
     */
    public Player(int theBankroll, String theName)
    {
        bankroll = theBankroll;
        name = theName;
        hand = new Hand();
        bet = 0;
    }

    /**
     * @return Player's hand
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * @return name of player
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return bankroll
     */
    public int getBankroll()
    {
        return bankroll;
    }

    /**
     * adds back the money the player bet plus the money from the dealer
     */
    public void changeBankroll()
    {
        if(hand.checkBlackjack())
        {
            bet = (bet*3)/2 + bet;
            bankroll += bet;
        }
        else
        {
            bankroll += 2* bet;
        }
    }

    /**
     * adds back the money the player bet
     * only called when it is a push
     */
    public void getBetBack()
    {
        bankroll += bet;
    }

    /**
     * @return bet made by player
     */
    public int getBet()
    {
        return bet;
    }

    /**
     * @param newBet (int) to set the instance variable bet to
     */
    public void setBet(int newBet)
    {
        bet = newBet;
    }

    /**
     * @param newBet (int)
     * subtracts the bet value from the bankroll once the player places the bet
     */
    public void placeBet(int newBet)
    {
        bet = newBet;
        bankroll -= bet;
    }

    /**
     * @ param Card to add to player's hand
     * adds a card to the hand
     */
    public void hit(Card c)
    {
        hand.addCard(c);
    }

    /**
     * keeps hand the same
     */
    public void stand()
    {
        hand = hand;
    }

    /**
     * @ param Card to add to player's hand
     * doubling down doubles the player's bet and adds one card to the hand
     */
    public void doubleDown(Card c)
    {
        bankroll -= bet;
        bet = bet*2;
        hand.addCard(c);
    }

    public String toString()
    {
        return "Player: " + name + "\nBankroll: $" + bankroll;
    }
}