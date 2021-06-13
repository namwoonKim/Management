package com.example.management;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter_recipe_material {
    protected static final String TAG = "recipe_material";

    private final String TABLE_NAME = "recipe_material";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter_recipe_material(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter_recipe_material createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter_recipe_material open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public List getTableData() {
        try {
            String sql ="SELECT * FROM " + TABLE_NAME;

            Recipe_material rm = null;

            List rmList = new ArrayList();

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                while( mCur.moveToNext() ) {

                    rm = new Recipe_material();

                    rm.setRecipe_code(mCur.getInt(0));
                    rm.setOrder(mCur.getInt(1));
                    rm.setMaterial_name(mCur.getString(2));
                    rm.setMaterial_capacity(mCur.getString(3));
                    rm.setMaterial_type_code(mCur.getString(4));
                    rm.setMaterial_type(mCur.getString(5));

                    rmList.add(rm);
                }

            }
            return rmList;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
