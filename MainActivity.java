package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtMess;
    Button btnplayAgain;
    //0 is red , 1 is blue
    int player = 0;
    //2 can play
    int[] boardState = {2,2,2,2,2,2,2,2,2};

    boolean isContinue = true;
    int[][] positionWinings = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void playAgain(View view){
        player = 0;
        for (int i = 0; i < boardState.length; i++){
            boardState[i] = 2;
        }

        isContinue = true;
        txtMess.setText("");

        GridLayout gridLayout = findViewById(R.id.glBoard);


        for (int i = 0 ; i < gridLayout.getChildCount(); i++){

            ImageView chess = (ImageView) gridLayout.getChildAt(i);

            chess.setImageResource(0);
        }
        btnplayAgain.setVisibility(View.INVISIBLE);
    }
    public void dropIn(View view) {
        ImageView chess = (ImageView) view;


        int position = Integer.parseInt(chess.getTag().toString());

        if (boardState[position] == 2 && isContinue){

            chess.setTranslationY(-1000f);
            boardState[position] = player;
            if (player == 0) {

                chess.setImageResource(R.drawable.chessx);

                player = 1;
            } else {
                chess.setImageResource(R.drawable.chesso);
                player = 0;
            }
        chess.animate().translationYBy(1000f).rotationYBy(360).setDuration(300);
         for (int[] positionWining : positionWinings){
             if (boardState[positionWining[0]]==boardState[positionWining[1]] &&
                     boardState[positionWining[1]] == boardState[positionWining[2]] &&
                     boardState[positionWining[2]] != 2) {

                 btnplayAgain.setVisibility(View.VISIBLE);
                 String winner;
                 if (boardState[positionWining[2]] == 0) {
                     winner = "Player 1 : X";
                 } else{
                     winner = "Player 2 : O";
             }
                 String mess = "The Winner is " + winner;
                 txtMess.setText(mess);

                 isContinue = false;
             }else {
                 boolean isOver = true;

                 for (int i = 0; i < boardState.length; i++){

                     if (boardState[i] == 2){
                         isOver = false;
                     }
                 }
                 if (isOver){

                     String mess = "There is No Winner! please try later";

                     txtMess.setText(mess);

                     btnplayAgain.setVisibility(View.VISIBLE);
                 }
             }
         }
    }
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMess = findViewById(R.id.txtMess);
        btnplayAgain = findViewById(R.id.btnPlayagain);
    }
}
