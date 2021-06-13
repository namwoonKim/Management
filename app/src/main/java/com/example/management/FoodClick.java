package com.example.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;


public class FoodClick extends Activity {
    public List<User> foodList;
    String clickname;
    String nameoffood;

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        foodList = mDbHelper.getTableData();

        mDbHelper.close();;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadDB();

        setContentView(R.layout.food_clicked);

        Intent intent = getIntent();

        TextView name=(TextView) findViewById(R.id.food_name);
        TextView date=(TextView) findViewById(R.id.food_date);
        TextView amount=(TextView) findViewById(R.id.food_amount);
        TextView kcal=(TextView) findViewById(R.id.food_kcal);
        TextView protein=(TextView) findViewById(R.id.food_protein);
        TextView fat=(TextView) findViewById(R.id.food_fat);
        TextView carb=(TextView) findViewById(R.id.food_carb);

        name.setText(intent.getStringExtra("name"));
        date.setText(intent.getStringExtra("date"));

        for (int i = 0; i < 52940; i++) {
            clickname = name.getText().toString();
            nameoffood = foodList.get(i).getName();
            if (clickname.equals(nameoffood)) {
                amount.setText(foodList.get(i).getAmount() + "g");
                kcal.setText(foodList.get(i).getKcal() + "kcal");
                protein.setText(foodList.get(i).getProtein() + "g");
                fat.setText(foodList.get(i).getFat() + "g");
                carb.setText(foodList.get(i).getCarb() + "g");
            }
            else if (clickname == null || !(clickname.equals(nameoffood))) { continue; }
        }
    }
}