package com.example.aakash.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivityD extends AppCompatActivity {
    ImageView dice,dice2;
    Button hold,roll,reset;
    int userOverallScore,computerTurnScore,computerOverallScore;
    TextView userRound,userOverall,computerRound,computerOverall;
    private int userTurnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_d);
        dice=(ImageView)findViewById(R.id.Dice);
        dice.setImageResource(R.drawable.one);
        dice2=(ImageView)findViewById(R.id.Dice2);
        dice2.setImageResource(R.drawable.one);
        hold=(Button)findViewById(R.id.hold);
        roll=(Button)findViewById(R.id.roll);
        reset=(Button)findViewById(R.id.roll);
        userRound=(TextView)findViewById(R.id.UserRoundScore);
        userOverall=(TextView)findViewById(R.id.UserOverall);
        computerRound=(TextView)findViewById(R.id.ComputerRoundScore);
        computerOverall=(TextView)findViewById(R.id.ComputerOverall);
        reset();
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.hold:hold();break;
            case R.id.roll:roll(); break;
            case R.id.reset: reset(); break;
        }
    }

    public void roll()
    {   hold.setEnabled(true);
        int i=new Random().nextInt(6)+1;
        int k=new Random().nextInt(6)+1;
        switchCase(i,dice); switchCase(k,dice2);

        if(i==1 && k==1)
        {
            Toast.makeText(this,"User ... Doomed ...", Toast.LENGTH_SHORT).show();
            userOverallScore=0;
            userTurnScore=0;
            userRound.setText(String.valueOf(userTurnScore));
            userOverall.setText("0");
            Log.e("VAlue of i and k ",String.valueOf(i)+" , "+String.valueOf(k));
            computerTurn();
        }
        else if(i==1 || k==1)
        {  Toast.makeText(this,"user hit 0",Toast.LENGTH_SHORT).show();
            userTurnScore=0;
            userRound.setText(String.valueOf("0"));
            Log.e("VAlue of i and k ",String.valueOf(i)+" , "+String.valueOf(k));
            computerTurn();
        }
        else if (i==k)
        {
            Log.e("VAlue of i and k ",String.valueOf(i)+" , "+String.valueOf(k));
            Toast.makeText(this,"Double hit ,press roll again",Toast.LENGTH_SHORT).show();
            hold.setEnabled(false);
        }
        else
        {
            Log.e("VAlue of i and k ",String.valueOf(i)+" , "+String.valueOf(k));
            Log.e("user score",String.valueOf(userTurnScore));
            userRound.setText(String.valueOf(userTurnScore));
        }

    }
    public void switchCase(int i,ImageView temp)
    {
        switch (i)
        {
            case 1: temp.setImageResource(R.drawable.one);
                userTurnScore=0;
                break;
            case 2: temp.setImageResource(R.drawable.two);
                userTurnScore +=2;
                break;
            case 3:temp.setImageResource(R.drawable.three);
                userTurnScore +=3;
                break;
            case 4:  temp.setImageResource(R.drawable.four);
                userTurnScore +=4;
                break;
            case 5:  temp.setImageResource(R.drawable.five);
                userTurnScore +=5;
                break;
            case 6:   temp.setImageResource(R.drawable.six);
                userTurnScore +=6;
        }
    }

    public void hold()
    {
        userOverallScore+=userTurnScore;
        userOverall.setText(String.valueOf(userOverallScore));
        Log.e("User overall score",String.valueOf(userOverallScore));
        userTurnScore=0;
        userRound.setText("0");
        if(userOverallScore>100)
        {
            Toast.makeText(this,"User wins",Toast.LENGTH_LONG).show();
            hold.setEnabled(false);
            roll.setEnabled(false);
            return;
        }
        computerTurn();
    }
    public void reset()
    {
        userOverallScore=0;userTurnScore=0;
        computerOverallScore=0;computerTurnScore=0;
        userRound.setText("0");
        userOverall.setText("0");
        computerOverall.setText("0");
        computerRound.setText("0");
        hold.setEnabled(true);
        roll.setEnabled(true);
        Toast.makeText(this,"Game Reset ",Toast.LENGTH_SHORT).show();
        dice.setImageResource(R.drawable.one);
        dice2.setImageResource(R.drawable.one);

    }

    public void computerTurn()
    {
        Toast.makeText(this,"Computers turn",Toast.LENGTH_SHORT).show();
        computerTurnScore=0;
        int i,k;
        computerRound.setText("0");
        while(computerTurnScore<25)
        {

            i=new Random().nextInt(6)+1;
            k=new Random().nextInt(6)+1;
            Log.e("Computer  i and k ",String.valueOf(i)+" , "+String.valueOf(k));
            if(i==1 && k==1)
            {
                Toast.makeText(this,"Computer  Doomed ...",Toast.LENGTH_SHORT).show();
                computerOverallScore=0;
                computerTurnScore=0;
                computerRound.setText(String.valueOf(computerTurnScore));
                break;
            }
            else if(i==1 || k==1)
            {  Toast.makeText(this,"computer hit 0 ",Toast.LENGTH_SHORT).show();
                computerRound.setText(String.valueOf(computerTurnScore));
                break;
            }
            else
            { computerTurnScore+=i+k;
                computerRound.setText(String.valueOf(computerTurnScore));
            }

        }
        computerOverallScore+=computerTurnScore;

        computerOverall.setText(String.valueOf(computerOverallScore));
        if(computerOverallScore>100)
        {
            Toast.makeText(this,"Computer wins",Toast.LENGTH_LONG).show();
            hold.setEnabled(false);
            roll.setEnabled(false);
        }

    }

}

