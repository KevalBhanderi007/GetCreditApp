package com.earn.money.onlinejob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WithdrawActivity extends AppCompatActivity {

    ImageView imgback;

    TextView withdraw, txt_500, txt_1000, txt_2500, txt_5000, txt_earn_amount;

    EditText show_value;
    RelativeLayout bank, paypal, upi, paytm;

    String num;


    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_withdraw);

        imgback = findViewById(R.id.imgback);
        txt_500 = findViewById(R.id.txt_500);
        txt_1000 = findViewById(R.id.txt_1000);
        txt_2500 = findViewById(R.id.txt_2500);
        txt_5000 = findViewById(R.id.txt_5000);
        show_value = findViewById(R.id.show_value);
        withdraw = findViewById(R.id.withdraw);
        bank = findViewById(R.id.bank);
        paypal = findViewById(R.id.paypal);
        upi = findViewById(R.id.upi);
        paytm = findViewById(R.id.paytm);
        txt_earn_amount = findViewById(R.id.txt_earn_amount);


        txt_500.setText("500.0");
        txt_1000.setText("1000.0");
        txt_2500.setText("2500.0");
        txt_5000.setText("5000.0");
        withdraw.setText("WITHDRAW");

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WithdrawActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        num = intent.getStringExtra("number");
        txt_earn_amount.setText(num + " ");

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            show_value.setHint("Enter Bank Account Number");
//                Toast.makeText(WithdrawActivity.this, " show vjhg jghvh fvh", Toast.LENGTH_SHORT).show();
            }
        });


        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_value.setHint("Enter Paypal Email ID");

            }
        });

       upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_value.setHint("Enter UPI ID");

            }
        });

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_value.setHint("Enter Paytm Number");

            }
        });


        txt_500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withdraw.setText("WITHDRAW 500₹");

            }
        });

        txt_1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withdraw.setText("WITHDRAW 1000₹");

            }
        });

        txt_2500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withdraw.setText("WITHDRAW 2500₹");

            }
        });
          
        txt_5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                withdraw.setText("WITHDRAW 5000₹");

            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             if(txt_earn_amount.getText().toString().equals(txt_500.getText().toString())){
                 Toast.makeText(WithdrawActivity.this, "WITHDRAW", Toast.LENGTH_SHORT).show();
             }
             else if(txt_earn_amount.getText().toString().equals(txt_1000.getText().toString())){
                 Toast.makeText(WithdrawActivity.this, "WITHDRAW", Toast.LENGTH_SHORT).show();
             }
             else if(txt_earn_amount.getText().toString().equals(txt_2500.getText().toString())){
                 Toast.makeText(WithdrawActivity.this, "WITHDRAW", Toast.LENGTH_SHORT).show();
             }
             else if(txt_earn_amount.getText().toString().equals(txt_5000.getText().toString())){
                 Toast.makeText(WithdrawActivity.this, "WITHDRAW", Toast.LENGTH_SHORT).show();
             }
             else {
                 Toast.makeText(WithdrawActivity.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
             }
            }
        });

    }
}
