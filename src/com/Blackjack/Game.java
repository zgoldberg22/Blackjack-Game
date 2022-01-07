package com.Blackjack;
import java.util.Scanner;

public class Game
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = sc.nextLine();

        System.out.println("Enter starting bankroll (enter integer amount): ");
        int bank = sc.nextInt();

        Player p1 = new Player(bank, name);
        Dealer d1 = new Dealer(p1);

        System.out.println("How many decks would you like to use?: ");
        int numDecks = sc.nextInt();

        //everything happens within the start method in the dealer class
        d1.start(numDecks);
    }
}