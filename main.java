package com.example.bens.blackjack;


import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;

import java.util.Random;

public class main extends AppCompatActivity {

    int i_dealercardsvalue = 0;
    int i_playercardsvalue = 0;
    public static Card[][] deckOfCards = new Card[4][13];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        final EditText text_dealercardsvalue = findViewById (R.id.dealercardsvalue);
        final EditText text_playercardsvalue = findViewById (R.id.playercardsvalue);
        final ImageView imageViewDealer = (ImageView) findViewById (R.id.imageViewDealer);
        final ImageView imageViewPlayer = (ImageView) findViewById (R.id.imageViewPlayer);

        final Button btn_playerhit = (Button) findViewById (R.id.playerhit);
        final Button btn_playerstay = (Button) findViewById (R.id.playerstay);
        final Button btn_newgame = (Button) findViewById (R.id.NewGame);

        setDeckOfCards();

        btn_newgame.setOnClickListener (new View.OnClickListener (){
            public void onClick(View view){
                i_dealercardsvalue=0;
                i_playercardsvalue=0;
                imageViewDealer.setImageResource (R.drawable.blackjack_hh);
                imageViewPlayer.setImageResource (R.drawable.pixel);

                text_dealercardsvalue.setText ("0");
                text_playercardsvalue.setText ("0");
                text_dealercardsvalue.setTextColor(Color.BLACK);
                text_playercardsvalue.setTextColor(Color.BLACK);

                final TextView textViewPlayer = findViewById (R.id.textViewPlayer);
                final TextView textViewDealer = findViewById (R.id.textViewDealer);
                textViewPlayer.setText (null);
                textViewDealer.setText (null);


                btn_newgame.setVisibility (View.GONE);
                btn_playerhit.setVisibility (View.VISIBLE);
                btn_playerstay.setVisibility (View.VISIBLE);
            }
        });

        btn_playerhit.setOnClickListener (new View.OnClickListener (){
            public void onClick(View view){

                Card objCard = getCardDeal();
                i_playercardsvalue+= getCardValue(objCard);

                changeTextPlayer ("\n" + objCard.toString ());

                //----------- 52 cards as example for ace's (52 x 4 = 208 variants)

                if(objCard.getSuitValue().contains ("DIAMOND")) {
                    imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    if(objCard.getFaceValue().contains ("ACE"))
                        imageViewPlayer.setImageResource (R.drawable.ace_diamond);
                    else if(objCard.getFaceValue().contains ("TWO"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("THREE"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("FOUR"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("FIVE"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("SIX"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("SEVEN"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("EIGHT"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("NINE"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("TEN"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("JACK"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("QUEEN"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);
                    else if(objCard.getFaceValue().contains ("KING"))
                        imageViewPlayer.setImageResource (R.drawable.all_diamond);

                }
                else if(objCard.getSuitValue().contains ("CLUB")) {
                    imageViewPlayer.setImageResource (R.drawable.all_club);
                    if(objCard.getFaceValue().contains ("ACE"))
                        imageViewPlayer.setImageResource (R.drawable.ace_club);
                }
                else if(objCard.getSuitValue().contains ("HEART")) {
                    imageViewPlayer.setImageResource (R.drawable.all_heart);
                    if(objCard.getFaceValue().contains ("ACE"))
                        imageViewPlayer.setImageResource (R.drawable.ace_heart);
                }
                else if(objCard.getSuitValue().toUpperCase ().contains ("SPADE")) {
                    imageViewPlayer.setImageResource (R.drawable.all_spade);
                    if(objCard.getFaceValue().contains ("ACE"))
                        imageViewPlayer.setImageResource (R.drawable.ace_spade);
                }



                imageViewDealer.setImageResource (R.drawable.pixel);

                String strPlayer =  Integer.toString (i_playercardsvalue);

                text_playercardsvalue.setText (strPlayer);
                if(i_playercardsvalue > 21) {
                    changeTextPlayer ("\n" + "You're gone BUST!");
                    text_playercardsvalue.setTextColor(Color.RED);
                }

            }
        });

        btn_playerstay.setOnClickListener (new View.OnClickListener (){
            public void onClick(View view){
                boolean IsDealerHit = true;
                imageViewDealer.setImageResource (R.drawable.blackjack_hh);
                imageViewPlayer.setImageResource (R.drawable.blackjack_hh);

                changeTextPlayer ("\n" + "You should STAND.");

                if(i_playercardsvalue == 0){
                    changeTextPlayer ("\n" + "Please push the button 'HIT'");
                    return;
                }

                while(IsDealerHit){

                        Card objCard = getCardDeal();
                        i_dealercardsvalue+= getCardValue(objCard);
                        changeTextDealer ("\n" + objCard.toString ());
                        String strDealer =  Integer.toString (i_dealercardsvalue);
                        text_dealercardsvalue.setText (strDealer);

                        if(objCard.getSuitValue().contains ("DIAMOND"))
                            imageViewDealer.setImageResource (R.drawable.all_diamond);
                        else if(objCard.getSuitValue().contains ("CLUB"))
                            imageViewDealer.setImageResource (R.drawable.all_club);
                        else if(objCard.getSuitValue().contains ("HEART"))
                            imageViewDealer.setImageResource (R.drawable.all_heart);
                        else if(objCard.getSuitValue().toUpperCase ().contains ("SPADE"))
                            imageViewDealer.setImageResource (R.drawable.all_spade);

                        // delay(); // not work delay and change the images


                    if (i_dealercardsvalue > 21 || (i_playercardsvalue > 21 && i_dealercardsvalue < i_playercardsvalue)) {
                        IsDealerHit = false;
                    } else if (i_dealercardsvalue > i_playercardsvalue)
                        IsDealerHit = false;



                }

                imageViewDealer.setImageResource (R.drawable.blackjack_hh);


                if(i_dealercardsvalue > 21) {
                    changeTextDealer ("\n" + "The Dealer has gone BUST!");
                    text_dealercardsvalue.setTextColor(Color.RED);
                }


                // you won
                if((i_playercardsvalue <= 21 && i_dealercardsvalue < i_playercardsvalue)
                        || (i_playercardsvalue <= 21 && i_dealercardsvalue > 21) ){
                    imageViewPlayer.setImageResource (R.drawable.win);
                    imageViewDealer.setImageResource (R.drawable.pixel);
                    changeTextPlayer ("\n" + "You won!");
                }
                //AI won
                else if((i_dealercardsvalue <= 21 && i_dealercardsvalue >= i_playercardsvalue) || (i_playercardsvalue > 21 && i_dealercardsvalue <= 21)) {
                    imageViewPlayer.setImageResource (R.drawable.pixel);
                    imageViewDealer.setImageResource (R.drawable.win);
                    changeTextDealer ("\n" + "The Dealer won!");

                }


                btn_newgame.setVisibility (View.VISIBLE);
                btn_playerhit.setVisibility (View.GONE);
                btn_playerstay.setVisibility (View.GONE);

                /*
                for (int i = 0; i < deckOfCards.length; i++)
                    for (int j = 0; j < deckOfCards[i].length; j++) {
                        changeTextPlayer ("\n" + deckOfCards[i][j]);
                    }
                */

            }
        });

    }
    public void delay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    public static void setDeckOfCards(){
        int count = 0;
        Random rand = new Random();

        // fill the arrays
        for (int i = 0; i < deckOfCards.length; i++)
            for (int j = 0; j < deckOfCards[i].length; j++) {
                deckOfCards[i][j] = new Card(j+1,i+1);
            }
         // shuffle the array
        while (count < 1000)
        {
            int a = rand.nextInt(4);
            int b = rand.nextInt(13);
            for (int i = 0; i < deckOfCards.length; i++)
            {
                for (int j = 0; j < deckOfCards[i].length; j++)
                {
                    Card temp = deckOfCards[i][j];
                    deckOfCards[i][j] = deckOfCards[a][b];
                    deckOfCards[a][b] = temp;
                }
            }
            count++;
        }
    }
    public Card getCardDeal(){
        Card  objCard;
        int i = 0, j = 0;
        boolean flagBreak = false;

        for (i = 0; i < deckOfCards.length; i++)
        {
            for (j = 0; j < deckOfCards[i].length; j++)
            {
                if(deckOfCards[3][12] == null)
                    setDeckOfCards();
                if (deckOfCards[i][j] != null)
                {
                    flagBreak = true;
                    break;
                }
            }
            if (flagBreak)
            {
                break;
            }
        }
        objCard = deckOfCards[i][j];
        deckOfCards[i][j] = null;
        return objCard;
    }

    public int getCardValue(Card objCard){
        int intsum = 0;
        boolean b_IsAce = false;
        if (objCard.getFace() > 10) {
            intsum += 10;
        }
        else if (objCard.getFace() == 1)// plus 11 for Ace
        {
            b_IsAce = true;
            intsum += 11;
        }
        else
        {
            intsum += objCard.getFace();
        }
        if (intsum > 21 && b_IsAce == true)
        {
            b_IsAce = false;
            intsum -= 10;
        }
        return intsum;
    }

    public void changeTextPlayer(String input) {
        final TextView textView = findViewById (R.id.textViewPlayer);
        final ScrollView mScrollView = findViewById (R.id.scrollPlayer);
        textView.append (input);

        mScrollView.post (new Runnable () {
            public void run() {
                mScrollView.smoothScrollTo (0, textView.getBottom ());
            }
        });
    }
    public void changeTextDealer(String input) {
        final TextView textView = findViewById (R.id.textViewDealer);
        final ScrollView mScrollView = findViewById (R.id.scrollDealer);
        textView.append (input);

        mScrollView.post (new Runnable () {
            public void run() {
                mScrollView.smoothScrollTo (0, textView.getBottom ());
            }
        });
    }

}
