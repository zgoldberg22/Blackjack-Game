package com.Blackjack;

import java.util.*;
public class Deck
{
    private ArrayList<Card> deck;

    /**
     * Constructs a Deck object with 52 cards
     */
    public Deck()
    {
        deck = new ArrayList<Card>();

        String[] suits = {"Spades", "Diamonds", "Clubs", "Hearts"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for(int s = 0; s < suits.length; s++)
        {
            if(s == 0 || s == 1)
            {
                for(int r = 0; r < ranks.length; r++)
                {
                    deck.add(new Card(suits[s], ranks[r]));
                }
            }
            else
            {
                for(int r = ranks.length - 1; r >= 0; r--)
                {
                    deck.add(new Card(suits[s], ranks[r]));
                }
            }

        }
    }

    /**
     * @return ArrayList of Card objects
     */
    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    /**
     * @return the number of cards in the ArrayList deck
     */
    public int numCards()
    {
        return deck.size();
    }

    /**
     * @return Card from top of deck (at index 0 in array)
     *          and removes the card from the ArrayList of cards
     */
    public Card drawCard()
    {
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }

    /**
     * @return String representation of Deck object
     */
    public String toString()
    {
        String cheese = "";
        for(int i = 0; i < deck.size(); i++)
        {
            cheese += deck.get(i).getRank() + " of " + deck.get(i).getSuit() + " \n";
        }
        return cheese;
    }
}