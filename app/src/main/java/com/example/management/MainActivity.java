package com.example.management;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private static final String SETTINGS_PLAYER_JSON = "settings_item_json";
    private static final String SETTINGS_PLAYER_JSON_2 = "settings_item_json_2";
    private static final String SETTINGS_PLAYER_JSON_3 = "settings_item_json_3";
    private static final String SETTINGS_PLAYER_JSON_4 = "settings_item_json_4";


    ArrayList<String> fname = new ArrayList<>();
    ArrayList<String> fname2 = new ArrayList<>();
    ArrayList<String> fdate = new ArrayList<>();
    ArrayList<String> fdate2 = new ArrayList<>();

    public List<Recipe_information> ri;
    public List<Recipe_material> rm;
    public List<Recipe_process> rp;

    public List<User> userList;
    private ArrayList<FoodItem> data = null;
    private ArrayList<FoodItem> data1 = null; // 냉장, 디폴트
    private ArrayList<FoodItem> data2 = null; // 냉동
    FoodAdapter adapter;
    ListView listView;
    int cnt = 1;

    private void initLoadDB() {
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();
        userList = mDbHelper.getTableData();
        mDbHelper.close();;

        DataAdapter_recipe_information mDbHelper_ri = new DataAdapter_recipe_information(getApplicationContext());
        mDbHelper_ri.createDatabase();
        mDbHelper_ri.open();
        ri = mDbHelper_ri.getTableData();
        mDbHelper_ri.close();

        DataAdapter_recipe_material mDbHelper_rm = new DataAdapter_recipe_material(getApplicationContext());
        mDbHelper_rm.createDatabase();
        mDbHelper_rm.open();
        rm = mDbHelper_rm.getTableData();
        mDbHelper_rm.close();

        DataAdapter_recipe_process mDbHelper_rp = new DataAdapter_recipe_process(getApplicationContext());
        mDbHelper_rp.createDatabase();
        mDbHelper_rp.open();
        rp = mDbHelper_rp.getTableData();
        mDbHelper_rp.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoadDB();

        fname = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON);
        fdate = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_2);
        fname2 = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_3);
        fdate2 = getStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_4);

        data = new ArrayList<>();
        data1 = new ArrayList<>();
        data2 = new ArrayList<>();

        for(int i = 0; i < fname.size(); i++) {
            data.add(new FoodItem(fname.get(i), fdate.get(i)));
        }
        /*for(int i = 0; i < fname2.size(); i++) {
            data2.add(new FoodItem(fname2.get(i), fdate2.get(i)));
        }*/

        data1.addAll(data);

        listView = (ListView) findViewById(R.id.food_listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new FoodAdapter(this, R.layout.food_item, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), FoodClick.class);
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("date", data.get(position).getDate());
                startActivity(intent);
            }
        });

        Button btn = (Button)findViewById(R.id.recipe_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe_search rs = new Recipe_search(MainActivity.this);
                rs.show();
            }
        });

        Button btn1 = (Button)findViewById(R.id.recipe_recommendation);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe_recommendation rr = new Recipe_recommendation(MainActivity.this, ri, rm, rp, data1, data2);
                rr.show();
            }
        });

        findViewById(R.id.food_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Foodplus();
            }
        });

        findViewById(R.id.food_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDelete();
            }
        });


    }
    public void FoodDelete() {
        deletefood dedialog = new deletefood(this);
        dedialog.setDialogListener(new deletefood.CustomDialogListener() {
            @Override
            public void onPositiveClicked(String number) {
                int deletenumber = Integer.parseInt(number);
                try {
                    data.remove(deletenumber-1);
                }
                catch (Exception e){
                    dedialog.dismiss();
                }
                adapter.notifyDataSetChanged();
                if (cnt == 1) {
                    data1.clear();
                    data1.addAll(data);
                    fname.clear();
                    fdate.clear();
                    for(int i = 0; i < data.size(); i++){
                        fname.add(data.get(i).getName());
                        fdate.add(data.get(i).getDate());
                    }
                }
                else if (cnt == 2) {
                    data2.clear();
                    data2.addAll(data);
                    fname2.clear();
                    fdate2.clear();
                    for(int i = 0; i < data.size(); i++){
                        fname2.add(data.get(i).getName());
                        fdate2.add(data.get(i).getDate());
                    }
                }
            }

            @Override
            public void onNegativeClicked() {

            }
        });
        dedialog.show();
    }

    public void Foodplus() {
        CustomDialog dialog = new CustomDialog(this);
        dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
            @Override
            public void onPositiveClicked(String name, String date) {
                FoodItem abs = new FoodItem(name, date);
                data.add(abs);
                adapter.notifyDataSetChanged();
                if (cnt == 1) {
                    data1.clear();
                    data1.addAll(data);
                    fname.add(name);
                    fdate.add(date);
                }
                else if (cnt == 2) {
                    data2.clear();
                    data2.addAll(data);
                    fname2.add(name);
                    fdate2.add(date);
                }
            }

            @Override
            public void onNegativeClicked() {

            }
        });
        dialog.show();
    }

    public void getList(View v) { // 냉장, 냉동 디스플레이 나누기
        switch (v.getId()) {
            case R.id.button3:
                if (cnt == 1) {
                    data.clear();
                    data.addAll(data1);
                    adapter.notifyDataSetChanged();
                    break;
                }
                else if (cnt != 1) {
                    cnt = 1;
                    data.clear();
                    data.addAll(data1);
                    adapter.notifyDataSetChanged();
                    break;
                }

            case R.id.button9:
                if (cnt == 2) {
                    data.clear();
                    data.addAll(data2);
                    adapter.notifyDataSetChanged();
                    break;
                }
                else if (cnt != 2) {
                    cnt = 2;
                    data.clear();
                    data.addAll(data2);
                    adapter.notifyDataSetChanged();
                    break;
                }
        }
    }
    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();

        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }

        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }

        editor.commit();
    }

    private ArrayList getStringArrayPref(Context context, String key) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
        ArrayList urls = new ArrayList();

        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);

                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return urls;
    }

    @Override
    protected void onPause() {
        super.onPause();

        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON, fname);
        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_2, fdate);
        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_3, fname2);
        setStringArrayPref(getApplicationContext(), SETTINGS_PLAYER_JSON_4, fdate2);
        Log.d(TAG, "Put json");
    }

    public void setbtn(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}

