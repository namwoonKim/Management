package com.example.management;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter_recipe_process {
    protected static final String TAG = "recipe_process";

    private final String TABLE_NAME = "recipe_process";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter_recipe_process(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter_recipe_process createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter_recipe_process open() throws SQLException {
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

            Recipe_process rp = null;

            List rpList = new ArrayList();

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                while( mCur.moveToNext() ) {

                    rp = new Recipe_process();

                    rp.setRecipe_code(mCur.getInt(0));
                    rp.setOrder(mCur.getInt(1));
                    rp.setExplanation(mCur.getString(2));

                    rpList.add(rp);
                }

            }
            return rpList;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
