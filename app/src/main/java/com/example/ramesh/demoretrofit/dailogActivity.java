package com.example.ramesh.demoretrofit;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class dailogActivity extends AppCompatActivity {
    private Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailog);
        buttonClick = (Button) findViewById(R.id.buttonClick);
        buttonClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Create customdialog dialog object
                final Dialog dialog = new Dialog(dailogActivity.this);
                // Include dialog.xml file
                dialog.setContentView(R.layout.customdialog);
                // Set dialog title
                dialog.setTitle("Custom Dialog");

                // set values for customdialog dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("Registrion is Complated");
                ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                image.setImageResource(R.drawable.verified);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the customdialog dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });


            }

        });


    }
}
