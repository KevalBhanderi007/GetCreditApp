package com.earn.money.onlinejob;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.sum;

import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;
import java.util.*;
import java.io.*;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout getamount;
    TextView txtcaptcha, submit_captcha, txt_total_earn_amount, putvalue;
    ImageView refresh_captcha;
    String captcha;

    RelativeLayout invitefnd, rateus, joinus;
    EditText enter_captcha;

    int t_e_amount = 50;
    float e_amount = 0.50f;
    float flt = new Float(t_e_amount);


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        getamount = findViewById(R.id.getamount);
        txtcaptcha = findViewById(R.id.txtcaptcha);
        refresh_captcha = findViewById(R.id.refresh_captcha);
        enter_captcha = findViewById(R.id.enter_captcha);
        submit_captcha = findViewById(R.id.submit_captcha);
        txt_total_earn_amount = findViewById(R.id.txt_total_earn_amount);
        putvalue = findViewById(R.id.putvalue);


        txt_total_earn_amount.setText(t_e_amount +"");
        putvalue.setText("Income :" + e_amount + "₹/ Captcha");


        joinus = findViewById(R.id.joinus);
        rateus = findViewById(R.id.rateus);
        invitefnd = findViewById(R.id.invitefnd);


        captcha = generateCaptcha(7);
        txtcaptcha.setText(captcha);

        refresh_captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captcha = generateCaptcha(7);
                txtcaptcha.setText(captcha);
            }
        });

        submit_captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkCaptcha(captcha, enter_captcha.getText().toString())) {
                    System.out.println("CAPTCHA Matched");

                    flt = Float.parseFloat(txt_total_earn_amount.getText().toString());
                    Log.e("=====dbl", flt + "");
                    Float sum = flt + e_amount;
                    txt_total_earn_amount.setText(sum+"");
                    myEdit.putFloat("total", Float.parseFloat(txt_total_earn_amount.getText().toString()));
                    myEdit.commit();

                    captcha = generateCaptcha(2);
                    txtcaptcha.setText(captcha);
                }
                else {
                    System.out.println("CAPTCHA Not Matched");
                    Toast.makeText(MainActivity.this, "Incorrect Captcha", Toast.LENGTH_SHORT).show();

//                    Toast toast= Toast.makeText(MainActivity.this,"Incorrect Captcha",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
//                    toast.show();
                }


            }
        });

//        String s1 = sharedPreferences.getString("total", txt_total_earn_amount.getText().toString());
//        txt_total_earn_amount.setText(s1);

        Float f1 = sharedPreferences.getFloat("total",Float.parseFloat(txt_total_earn_amount.getText().toString()));
        txt_total_earn_amount.setText(f1+"");





        getamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String num = txt_total_earn_amount.getText().toString();
                Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
                intent.putExtra("number", num);
                startActivity(intent);
            }
        });

        invitefnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "Online job");
                    intent.putExtra("android.intent.extra.TEXT", "\nLet me recommend you this application\n\nhttps://play.google.com/store/apps/details?id=" + MainActivity.this.getPackageName() + "\n\n");
                    MainActivity.this.startActivity(Intent.createChooser(intent, "choose one"));
                } catch (Exception unused) {
                }
            }
        });

        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = MainActivity.this.getPackageName();
                try {
                    MainActivity linkCard_MainActivity = MainActivity.this;
                    linkCard_MainActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (ActivityNotFoundException unused) {
                    MainActivity linkCard_MainActivity2 = MainActivity.this;
                    linkCard_MainActivity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                }
            }

        });


    }

    // Returns true if given two strings are same
    static boolean checkCaptcha(String captcha, String user_captcha) {
        return captcha.equals(user_captcha);
    }

    // Generates a CAPTCHA of given length
    static String generateCaptcha(int n) {
        //to generate random integers in the range [0-61]
        Random rand = new Random(62);

        // Characters to be included
        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Generate n characters from above set and
        // add these characters to captcha.
        String captcha = "";
        while (n-- > 0) {
            int index = (int) (Math.random() * 62);
            captcha += chrs.charAt(index);
        }

        return captcha;
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        double a = sh.getFloat("total", 50.0f);
//        txt_total_earn_amount.setText(String.valueOf(a));
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sharedPreferences.edit();
//        myEdit.putFloat("total",Float.parseFloat(txt_total_earn_amount.getText().toString()));
//        myEdit.apply();
//    }
}

