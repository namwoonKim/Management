package com.example.management;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter_recipe_information {
    protected static final String TAG = "recipe_information";

    private final String TABLE_NAME = "recipe_information";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter_recipe_information(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter_recipe_information createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter_recipe_information open() throws SQLException {
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

            Recipe_information ri = null;

            List riList = new ArrayList();

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                while( mCur.moveToNext() ) {

                    ri = new Recipe_information();

                    ri.setRecipe_code(mCur.getInt(0));
                    ri.setName(mCur.getString(1));
                    ri.setIntroduce(mCur.getString(2));
                    ri.setType_code(mCur.getString(3));
                    ri.setType(mCur.getString(4));
                    ri.setFood_type_code(mCur.getString(5));
                    ri.setFood_type(mCur.getString(6));
                    ri.setcooking_time(mCur.getString(7));
                    ri.setCalorie(mCur.getString(8));
                    ri.setAmount(mCur.getString(9));
                    ri.setDifficulty(mCur.getString(10));
                    ri.setMaterial_type(mCur.getString(11));
                    ri.setPrice_type(mCur.getString(12));

                    riList.add(ri);
                }

            }
            return riList;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
