package com.Blackjack;

import java.util.*;
public class Hand
{
    private ArrayList<Card> hand;

    /**
     * Constructs a Hand object
     */
    public Hand()
    {
        hand = new ArrayList<Card>();
    }

    /**
     * @return ArrayList of Card objects in hand
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * @return int of number of cards in hand
     */
    public int cardsInHand()
    {
        return hand.size();
    }

    /**
     * @ param Card to add to the hand
     * adds one card to the hand ArrayList
     */
    public void addCard(Card c)
    {
        hand.add(c);
    }

    /**
     * @return int of value of all cards in hand
     * when getting the value of the hand, this method also switches the value of
     * an ace card if the total value is over 21
     */
    public int getHandValue()
    {
        int sum = 0;
        //first adds all non-ace card values to sum, and adds the index of the aces to the ArrayList aces
        ArrayList<Integer> aces = new ArrayList<Integer>();
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank().equalsIgnoreCase("ace"))
            {
                aces.add(i);
            }
            else
            {
                sum += hand.get(i).getValue();
            }
        }

        int aceSum = 0;
        for(int i = 0; i < aces.size(); i++)
        {
            aceSum +=  hand.get(aces.get(i)).getValue();
        }
        //checks if sum + sum of ace cards = 21
        //before I just had what was in the else statement and it kept switching card values
        if(aceSum + sum == 21)
        {
            sum += aceSum;
        }
        else
        {
            //goes through each ace value in hand and checks if it needs to switch values
            int numAces = aces.size();
            for(int i = 0; i < aces.size(); i++)
            {
                if(aces.size() > 1)
                {
                    if(sum + hand.get(aces.get(i)).getValue() > 21)
                    {
                        hand.get(aces.get(i)).switchAceValue();
                        sum += hand.get(aces.get(i)).getValue();
                    }
                    else if(sum + hand.get(aces.get(i)).getValue() <= 21)
                    {
                        //checks if sum + 11 (default ace value) + rest of the number of aces as 1 is greater than 21
                        //if this is true, switches ace value to 1
                        if(numAces - 1 + sum + hand.get(aces.get(i)).getValue() > 21)
                        {
                            hand.get(aces.get(i)).switchAceValue();
                            sum += hand.get(aces.get(i)).getValue();
                        }
                        else
                        {
                            sum += hand.get(aces.get(i)).getValue();
                        }
                    }
                }
                else if(sum + hand.get(aces.get(i)).getValue() > 21)
                {
                    hand.get(aces.get(i)).switchAceValue();
                    sum += hand.get(aces.get(i)).getValue();
                }
                else
                {
                    sum += hand.get(aces.get(i)).getValue();
                }
            }
        }
        return sum;
    }

    /**
     * @return true if the hand is greater than 21 and false otherwise
     */
    public boolean checkBust()
    {
        if(this.getHandValue() > 21)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return true if hand is equal to 21 with 2 cards in hand
     */
    public boolean checkBlackjack()
    {
        if(this.getHandValue() == 21 && hand.size() == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * @return true if hand is equal to 21 and there are more than 2 cards in the hand
     */
    public boolean check21()
    {
        if(this.getHandValue() == 21 && hand.size() > 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * removes all the cards in the ArrayList
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * @return String representation of Cards in ArrayList shoe
     */
    public String toString()
    {
        String cheese = "\tTotal Hand Value: " + this.getHandValue() + "\n\tCards in hand: ";
        for(int i = 0; i < hand.size(); i++)
        {
            cheese += "\n\t\t" + hand.get(i).getRank() + " of " + hand.get(i).getSuit() + " : " + hand.get(i).getValue();
        }
        return cheese;
    }
}