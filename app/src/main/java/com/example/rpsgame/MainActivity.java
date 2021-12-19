package com.example.rpsgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //declaring everything we need to change while running program
    ImageButton stone_B,paper_B,scissors_B,rest;
    ImageView img_comp,img_user;
    TextView userScore,compScore;
    TextView restartText;
    Button exitBtn;
    @SuppressLint("UseSwitchCompatOrMaterialCode")//getting rid of some annoyying errors
    Switch aSwitch;

    //initializing global variables

    int usr=0;
    int cmp=0;
    int sw=1;

    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //matching with ids
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_comp = findViewById (R.id.compChoiceimg);
        img_user = findViewById (R.id.userChoiceimg);
        stone_B = findViewById(R.id.choosestone);
        scissors_B = findViewById(R.id.choosescissors);
        paper_B = findViewById(R.id.choosepaper);
        userScore = findViewById(R.id.userscore);
        compScore = findViewById(R.id.compscore);
        rest = findViewById(R.id.restartButton);
        r= new Random();
        restartText=findViewById(R.id.restartTe);
        exitBtn =findViewById(R.id.exiting);
        aSwitch= findViewById(R.id.switch1);


        //this sees if anything has changed..if it has it switches global variable sw (1 to 2 ;2 to 1)
        // next it gets passed into the result class
        // there the two cases are sorted by an if statement

        aSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (sw==1)
                sw=2;
            else
                sw=1;
        });

        //first all 3 additional features are gone
        aSwitch.setVisibility(View.GONE);
        rest.setVisibility(View.GONE);
        exitBtn.setVisibility(View.GONE);
        restartText.setVisibility(View.GONE);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


        rest.setOnClickListener(view -> {                                       //restart code
            usr=0;
            cmp=0;
            userScore.setText(String.valueOf(usr));
            compScore.setText(String.valueOf(cmp));

        });


        stone_B.setOnClickListener(view -> {
            //userChoice= Stone
            img_user.setImageResource(R.mipmap.rok3);

            result(0);

        });


        paper_B.setOnClickListener(view -> {
            //userChoice = Paper
            img_user.setImageResource(R.mipmap.paper);
            result(1);


        });


        scissors_B.setOnClickListener(view -> {
            //userChoice = Scissors
            img_user.setImageResource(R.mipmap.scissors);
            result(2);


        });

    }

    @SuppressLint("SetTextI18n")
    public void result(int u){                                  //result class ..2 cases in if else statement

        TextView output =findViewById(R.id.judge);
        int cpu =r.nextInt(3);

       if(sw==1){

            if (cpu==0){
               //compChoice=Stone
               img_comp.setImageResource(R.mipmap.rok3);
               if(u==0){
                   output.setText("Its a draw");}
               else if(u==1){
                       output.setText("Paper covers Stone\n You win!");
               userScore.setText(String.valueOf(++usr));
               }

               else{
                   output.setText("Scissors is smashed by Stone \n You lose");
                   compScore.setText(String.valueOf(++cmp));

               }
           }
            else if (cpu==1){
                //compChoice = Paper
                img_comp.setImageResource(R.mipmap.paper);
               if(u==0){
                   output.setText("Stone is covered by Paper. \n You lose");
                   compScore.setText(String.valueOf(++cmp));
               }
               else if(u==1){
                   output.setText("Its a draw");}
               else{
                   output.setText("Scissors cuts Paper \n You win!");
                   userScore.setText(String.valueOf(++usr));
               }
            }
            else {
                //compChoice = Scissors
                img_comp.setImageResource(R.mipmap.scissors);
                   if(u==0){
                       output.setText("Stone smashes Scissors . \n You win!");
                       userScore.setText(String.valueOf(++usr));
                   }
                   else if(u==1){
                       output.setText("Paper is cut by Scissors. \n You lose");
                       compScore.setText(String.valueOf(++cmp));
                   }
                   else{
                       output.setText("Its a draw");}


            }


       }
       else
       {
           if (cpu==0){
               //compChoice=Stone
               img_comp.setImageResource(R.mipmap.rok3);
               if(u==0){
                   output.setText("Your Stone just happens to be stronger It destroys the other to pieces\n You win");
                   userScore.setText(String.valueOf(++usr));
               }
               else if(u==1){
                   output.setText("Paper covers Stone\n You win!");
                   userScore.setText(String.valueOf(++usr));
               }

               else{
                   output.setText("Scissors sends death rays and vapourises stone \n You win");
                   userScore.setText(String.valueOf(++usr));

               }
           }
           else if (cpu==1){
               //compChoice = Paper
               img_comp.setImageResource(R.mipmap.paper);
               if(u==0){
                   output.setText("Stone is rubbed and it instantaneously lights paper on fire. \n You win");
                   userScore.setText(String.valueOf(++usr));
               }
               else if(u==1){
                   output.setText("Your paper is of higher quality. It has a sharper edge , it cuts right through the other one\n You win");
                   userScore.setText(String.valueOf(++usr));
               }
               else{
                   output.setText("Scissors cuts Paper \n You win!");
                   userScore.setText(String.valueOf(++usr));
               }
           }
           else {
               //compChoice = Scissors
               img_comp.setImageResource(R.mipmap.scissors);
               if(u==0){
                   output.setText("Stone smashes Scissors . \n You win!");
                   userScore.setText(String.valueOf(++usr));
               }
               else if(u==1){
                   output.setText("Paper sues Scissors. Wins the case of exploitation, domestic Violence against Scissors in court \n You win");
                   userScore.setText(String.valueOf(++usr));
               }
               else{
                   output.setText("Your Scissors obviously has a sharper edge. Shreds the other one to pieces\n You win");
                   userScore.setText(String.valueOf(++usr));
               }


           }
       }


       if(usr>=5||cmp>=5)                                       //the 3 extra features to be visible only to loyal clients
       {  exitBtn.setVisibility(View.VISIBLE);                  //those who reached score of 5 :)
          rest.setVisibility(View.VISIBLE);
          aSwitch.setVisibility(View.VISIBLE);
           restartText.setVisibility(View.VISIBLE);
       }


   }
}
//ACHINTHYA HEBBAR S
//F2021A7PS1457P