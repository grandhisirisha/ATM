package com.example.mlrit.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText pin;
    Button submit;
    EditText enteramnt;
    Button deposit;
    Button withdrawl;
    Button balenq;
    Button exit;
    TextView t;
    long bal=10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pin=(EditText)findViewById(R.id.pin);
        submit=(Button)findViewById(R.id.submit);
        enteramnt=(EditText)findViewById(R.id.enter_amount);
        deposit=(Button)findViewById(R.id.deposit);
        withdrawl=(Button)findViewById(R.id.withdrawl);
        balenq=(Button)findViewById(R.id.bal_enquiry);
        exit=(Button)findViewById(R.id.exit);
        t=(TextView)findViewById(R.id.t);

        deposit.setVisibility(View.INVISIBLE);
        withdrawl.setVisibility(View.INVISIBLE);
        balenq.setVisibility(View.INVISIBLE);
        exit.setVisibility(View.INVISIBLE);
        t.setVisibility(View.INVISIBLE);
        enteramnt.setVisibility(View.INVISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p=pin.getText().toString();
                pin.setText("");
                if (p.isEmpty()){

                pin.setError("EMPTY");
                }
                else {
                    int p1=Integer.parseInt(p);
                    if (p1==1234) {
                        deposit.setVisibility(view.VISIBLE);
                        withdrawl.setVisibility(view.VISIBLE);
                        balenq.setVisibility(view.VISIBLE);
                        exit.setVisibility(view.VISIBLE);
                        t.setVisibility(view.VISIBLE);
                        enteramnt.setVisibility(view.VISIBLE);
                        submit.setVisibility(view.INVISIBLE);
                        pin.setVisibility(view.INVISIBLE);

                    }
                    else {
                    pin.setError("Invalid Password");
                          }
                    }
                }

        });

        withdrawl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ss=enteramnt.getText().toString();
                if(ss.isEmpty())
                {
                    enteramnt.setError("Empty");
                }
                else
                {
                    long amnt=Long.parseLong(ss);
                    if(amnt>bal)
                    {
                        t.setText("no funds");
                    }
                    else if(amnt%100==0){
                        bal=bal-amnt;
                        t.setText(""+bal);
                       SmsManager sm=SmsManager.getDefault();
                        sm.sendTextMessage("9912951669",null,"You have withdrawn:"+amnt+"Current Balance:"+bal,null,null);
                    }
                    else {
                        t.setText("invalid entry");
                    }
                }


                }

                });
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=enteramnt.getText().toString();

                if (em.isEmpty())
                {
                    enteramnt.setError("Enter amount to deposit");
                }
                else {
                    long l= Long.parseLong(em);
                bal=l+bal;
                    enteramnt.setText("");
                    SmsManager sm=SmsManager.getDefault();
                    sm.sendTextMessage("9912951669",null,"Your account hasbeen credited with amount of:"+l+"Current Balance:"+bal,null,null);
                    t.setText("Current balance:"+bal);
                }

                }
            });
        balenq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setText("Current balance:"+bal);
            }
        });
    }

            }



