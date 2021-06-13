package com.example.management;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe_recommendation extends Dialog {
    private Context context;

    public List<Recipe_information> ri;
    public List<Recipe_material> rm;
    public List<Recipe_process> rp;

    public ArrayList<FoodItem> data1 = null;
    public ArrayList<FoodItem> data2 = null;

    public Recipe_recommendation(@NonNull Context context, List<Recipe_information> ri, List<Recipe_material> rm, List<Recipe_process> rp, ArrayList<FoodItem> data1, ArrayList<FoodItem> data2) {
        super(context);
        this.context = context;
        this.ri = ri;
        this.rm = rm;
        this.rp = rp;

        this.data1 = data1;
        this.data2 = data2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_recommendation);

        LinearLayout layout = (LinearLayout)findViewById(R.id.recommendation_layout);
        List<String> mat_name = new ArrayList();
        List<Integer> recipe_code = new ArrayList();
        HashMap<Integer, Integer> recipe_code_count = new HashMap<Integer, Integer>();
        HashMap<Integer, String> recipe_information = new HashMap<Integer, String>();

        for(FoodItem fi : data1) {
            String str = fi.getName();
            mat_name.add(str);
        }

        for(Recipe_material recipe_mat : rm) {
            for(String str : mat_name) {
                if(str.equals(recipe_mat.material_name)) {
                    recipe_code.add(recipe_mat.recipe_code);
                }
            }
        }

        for(int i = 0; i < recipe_code.size(); i++) {
            if(recipe_code_count.containsKey(recipe_code.get(i))) {
                recipe_code_count.put(recipe_code.get(i), recipe_code_count.get(recipe_code.get(i)) + 1);
            }
            else {
                recipe_code_count.put(recipe_code.get(i), 1);
            }
        }

        for(Recipe_information recipe_info : ri) {
            recipe_information.put(recipe_info.recipe_code, recipe_info.name);
        }

        for(int key : recipe_code_count.keySet()) {
            int i = recipe_code_count.get(key);
            if(i >2 && recipe_information.get(key) != null) {
                Button btn = new Button(context);
                btn.setText(recipe_information.get(key));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Recipe_order ro = new Recipe_order(context, ri, rm, rp, key);
                        ro.show();
                        dismiss();
                    }
                });
                layout.addView(btn);
            }
        }

    }
}
