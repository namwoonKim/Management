package com.example.management;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomDialog extends Dialog {

    private Button positiveButton;
    private Button negativeButton;
    private EditText editName;
    private EditText editDate;
    private Context context;
    public List<User> userList;

    private CustomDialogListener customDialogListener;

    EditText edt1, edt2;
    String Fname, Fdate;
    private ArrayList<FoodItem> data = null;
    FoodAdapter adapter;


    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    interface  CustomDialogListener{
        void onPositiveClicked(String name, String date);
        void onNegativeClicked();

    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        final EditText input = (EditText)findViewById(R.id.food_name_insert);
        input.setPrivateImeOptions("defaultInputmode=korean; ");

        positiveButton = (Button)findViewById(R.id.okButton);
        negativeButton = (Button) findViewById(R.id.cancelButton);
        editName = (EditText) findViewById(R.id.food_name_insert);
        editDate = (EditText) findViewById(R.id.date_insert);


        positiveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String date = editDate.getText().toString();

                customDialogListener.onPositiveClicked(name, date);
                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) findViewById(R.id.date_insert);
        et_date.setText(sdf.format(myCalendar.getTime()));
    }

}
