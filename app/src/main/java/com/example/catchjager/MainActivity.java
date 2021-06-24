package com.example.catchjager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    Runnable runnable;
    Handler handler;
    Runnable runnable1;
    Handler handler1;
    Intent intent;
    int seconds;
    int score;
    ImageButton button;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        seconds = 10;
        score = 0;

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};
        Timer();
        HideImages();

    }

    public void Timer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (seconds > 0) {
                    textView.setText("Time: " + seconds);
                    seconds--;
                    textView.setText("Time: " + seconds);
                    handler.postDelayed(runnable, 1000);
                }
                else{
                    textView.setText("Time off.");
                    handler1.removeCallbacks(runnable1);
                    for (ImageView image : imageArray) {
                        image.setVisibility(View.VISIBLE);
                        image.setEnabled(false);
                    }
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    alert.setTitle("Game Over");
                    alert.setMessage("Do you want to restart?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"Game Over...", Toast.LENGTH_LONG);
                        }
                    });
                    alert.show();
                }
            }
        };
        handler.post(runnable);
    }

    public void Click(View view) {
        //
        score++;
        textView2.setText("Score: " + score);
    }

    public void HideImages() {
        handler1 = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler1.postDelayed(this, 800);
            }
        };
        handler1.post(runnable1);
    }
}