package com.Blackjack;
import java.util.*;

public class Shoe
{
    private ArrayList<Card> shoe;
    private int numDecks;

    /**
     * Constructs a Shoe object
     */
    public Shoe(int theNumDecks)
    {
        numDecks = theNumDecks;
        shoe = new ArrayList<Card>();
        for(int i = 0; i < numDecks; i++)
        {
            Deck d = new Deck();
            ArrayList<Card> deck = d.getDeck();
            for(int j = 0; j < 52; j++)
            {
                shoe.add(deck.get(j));
            }
        }
    }

    public ArrayList<Card> getShoe()
    {
        return shoe;
    }

    /**
     * @return Card on top of shoe list (at index 0)
     */
    public Card drawCard()
    {
        Card temp = shoe.get(0);
        shoe.remove(0);
        return temp;
    }

    /**
     * @return number of cards in the shoe
     */
    public int getNumCards()
    {
        return shoe.size();
    }

    /**
     * @return number of decks in the shoe
     */
    public int getNumDecks()
    {
        return numDecks;
    }

    /**
     * shuffles cards in shoe and resets it with the original amount of cards
     */
    public void shuffle()
    {
        ArrayList<Card> temp = new ArrayList<Card>();
        ArrayList<Card> perm = new ArrayList<Card>();
        for(int i = 0; i < numDecks; i++)
        {
            Deck d = new Deck();
            ArrayList<Card> deck = d.getDeck();
            for(int j = 0; j < 52; j++)
            {
                temp.add(deck.get(j));
            }
        }
        for(int i = 0; i < (numDecks*52); i++)
        {
            int rand = (int)(Math.random()*temp.size());
            perm.add(temp.get(rand));
            temp.remove(rand);
        }
        shoe = perm;
    }

    /**
     * shuffles cards left in the shoe after a certain number of cards are left
     */
    public void reshuffle()
    {
        ArrayList<Card> temp = new ArrayList<Card>();
        ArrayList<Card> perm = new ArrayList<Card>();
        for(int i = 0; i < numDecks; i++)
        {
            Deck d = new Deck();
            ArrayList<Card> deck = d.getDeck();
            for(int j = 0; j < 52; j++)
            {
                temp.add(deck.get(j));
            }
        }
        for(int i = 0; i < (numDecks*52); i++)
        {
            int rand = (int)(Math.random()*temp.size());
            perm.add(temp.get(rand));
            temp.remove(rand);
        }
        shoe = perm;
    }

    /**
     * @return String representation of a Shoe object
     */
    public String toString()
    {
        String cheese = "";
        for(int i = 0; i < shoe.size(); i++)
        {
            cheese += shoe.get(i).getRank() + " of " + shoe.get(i).getSuit() + " \n";
        }
        return cheese;
    }
}