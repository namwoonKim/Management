package com.example.management;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class deletefood extends Dialog {
    private Button positiveButton;
    private Button negativeButton;
    private EditText denu;
    private Context context;


    private deletefood.CustomDialogListener customDialogListener;

    EditText edt;
    String number;
    private ArrayList<FoodItem> data = null;
    FoodAdapter adapter;

    public deletefood(@NonNull Context context) {
        super(context);
        this.context = context;
    }



    interface CustomDialogListener{
        void onPositiveClicked(String number);
        void onNegativeClicked();

    }

    public void setDialogListener(deletefood.CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteitem);

        final EditText deletenumber = (EditText) findViewById(R.id.food_delete);
        deletenumber.setPrivateImeOptions("defaultInputmode=number; ");

        positiveButton = (Button) findViewById(R.id.obtn);
        negativeButton = (Button) findViewById(R.id.cbtn);

        denu = (EditText) findViewById(R.id.food_delete);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = denu.getText().toString();

                customDialogListener.onPositiveClicked(number);
                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { dismiss(); }
        });
    }
}
