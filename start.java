package com.example.bens.blackjack;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn_RulesBlackJackURL = (Button) findViewById (R.id.RulesBlackJackURL);
        btn_RulesBlackJackURL.setOnClickListener (new View.OnClickListener (){
            public void onClick(View view){
                Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse ("https://en.wikipedia.org/wiki/Blackjack"));
                startActivity (intent);

            }

            });
    }
    public void startGame(View view){
        startActivity(new Intent (getApplicationContext(), main.class));
    }
}