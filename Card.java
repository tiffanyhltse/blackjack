package com.example.bens.blackjack;

import java.util.*;
import java.lang.*;

public class Card
{
    //https://en.wikipedia.org/wiki/Suit_(cards)
    public static final int SPADE = 1, // PIK
                            HEART = 2, // CHERV
                            DIAMOND = 3, //BUBA
                            CLUB = 4; // KREUZ
    public static final int ACE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    private int SUITVALUE, RANKVALUE;
    Random rand = new Random();

    public Card()
    {
        this.SUITVALUE = rand.nextInt(4)+1;
        this.RANKVALUE = rand.nextInt(13)+1;
    }
    public Card(int facevalue, int suitvalue)
    {
        this.SUITVALUE = suitvalue;
        this.RANKVALUE = facevalue;
    }

    public int getFace()
    {
        return this.RANKVALUE;
    }
    public int getSuit()
    {
        return this.SUITVALUE;
    }
    public String getFaceValue()
    {
        String FV;
        if (this.getFace() % 2 == 0)
        {
            if (this.getFace() == 2)
            {
                FV = "TWO";
            }
            else if (this.getFace() == 4)
            {
                FV = "FOUR";
            }
            else if (this.getFace() == 6)
            {
                FV = "SIX";
            }
            else if (this.getFace() == 8)
            {
                FV = "EIGHT";
            }
            else if (this.getFace() == 10)
            {
                FV = "EIGHT";
            }
            else
            {
                FV = "QUEEN";
            }
        }
        else
        {
            if (this.getFace() == 1)
            {
                FV = "ACE";
            }
            else if (this.getFace() == 3)
            {
                FV = "THREE";
            }
            else if (this.getFace() == 5)
            {
                FV = "FIVE";
            }
            else if (this.getFace() == 7)
            {
                FV = "SEVEN";
            }
            else if (this.getFace() == 9)
            {
                FV = "NINE";
            }
            else if (this.getFace() == 11)
            {
                FV = "JACK";
            }
            else //if (this.getFace() == 13)
            {
                FV = "KING";
            }
        }
        return FV;
    }
    public String getSuitValue()
    {
        String SV;
        if (this.getSuit() % 2 == 0)
        {
            if (this.getSuit() == 2)
            {
                SV = "DIAMOND";
            }
            else
            {
                SV = "SPADE";
            }
        }
        else
        {
            if (this.getSuit() == 1)
            {
                SV = "CLUB";
            }
            else
            {
                SV = "HEART";
            }
        }
        return SV;
    }
    public boolean isHigherThan(Card card2)
    {
        if (this.getFace() != 1 && card2.getFace() != 1)
        {
            if (this.getFace() > card2.getFace())
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
            if (this.getFace() == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    public boolean isHigherThan(Card card2, boolean aceHigh)
    {
        if (aceHigh == false)
        {
            if (this.getFace() > card2.getFace())
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
            if (this.getFace() == 1 && this.getFace() < card2.getFace())
            {
                return true;
            }
            else if (card2.getFace() == 1 && card2.getFace() < this.getFace())
            {
                return false;
            }
        }
        return false;
    }
    public String toString()
    {
        return this.getFaceValue()+" of "+this.getSuitValue();
    }
}
