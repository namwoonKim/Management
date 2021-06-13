package com.example.management;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe_order extends Dialog {
    private Context context;

    public List<Recipe_information> ri;
    public List<Recipe_material> rm;
    public List<Recipe_process> rp;

    int key;

    public Recipe_order(@NonNull Context context, List<Recipe_information> ri, List<Recipe_material> rm, List<Recipe_process> rp, int key) {
        super(context);
        this.context = context;
        this.ri = ri;
        this.rm = rm;
        this.rp = rp;
        this.key = key;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_order);

        TextView name = (TextView) findViewById(R.id.recipe_name);
        TextView order = (TextView) findViewById(R.id.recipe_order);
        HashMap<Integer, String> explanation = new HashMap<Integer, String>();

        for(Recipe_information recipe_info: ri) {
            if(recipe_info.recipe_code == key) {
                name.setText(recipe_info.name);
                break;
            }
        }

        for(Recipe_process recipe_pro: rp) {
            if(recipe_pro.recipe_code == key) {
                explanation.put(recipe_pro.order, recipe_pro.explanation);
            }
        }

        for(int i = 1; i <= explanation.size(); i++) {
            for(int k : explanation.keySet()) {
                if(k == i) {
                    order.append(Integer.toString(i) + ". ");
                    order.append(explanation.get(k) + "\n");
                }
            }
        }

    }
}
