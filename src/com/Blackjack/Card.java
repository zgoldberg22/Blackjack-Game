package com.Blackjack;

public class Card
{
    private String suit;
    private String rank;
    private boolean switchAce;

    /**
     * Constructs a Card object with a suit and rank
     */
    public Card(String theSuit, String theRank)
    {
        suit = theSuit;
        rank = theRank;
        switchAce = false;
    }

    /**
     * @return the card's suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * @return the card's rank ("Ace", "Queen", etc)
     */
    public String getRank()
    {
        return rank;
    }

    /**
     * @return the card's value based on its rank
     * Default ace value to 11
     * Returns -1 if error
     */
    public int getValue()
    {
        if(rank.equals("2"))
        {
            return 2;
        }
        else if(rank.equals("3"))
        {
            return 3;
        }
        else if(rank.equals("4"))
        {
            return 4;
        }
        else if(rank.equals("5"))
        {
            return 5;
        }
        else if(rank.equals("6"))
        {
            return 6;
        }
        else if(rank.equals("7"))
        {
            return 7;
        }
        else if(rank.equals("8"))
        {
            return 8;
        }
        else if(rank.equals("9"))
        {
            return 9;
        }
        else if(rank.equals("10") || rank.equalsIgnoreCase("jack") || rank.equalsIgnoreCase("queen") || rank.equalsIgnoreCase("king"))
        {
            return 10;
        }
        else if (rank.equalsIgnoreCase("ace") && (switchAce == false))
        {
            return 11;
        }
        else if (rank.equalsIgnoreCase("ace") && (switchAce == true))
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    /**
     * changes switchAceValue to true if card should switch ace value from 11 to 1
     */
    public void switchAceValue()
    {
        if(this.getValue() == 11)
        {
            switchAce = true;
        }
        else
        {
            switchAce = false;
        }
    }

    /**
     * @return String representation of Card Object
     */
    public String toString()
    {
        return "" + this.getRank() + " of " + this.getSuit() +
                " (Value: " + this.getValue() + ")";
    }
}