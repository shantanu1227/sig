package com.android.personalca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class databasehandler {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT = "amount";
    
    private static final String TAG = "databasehandler";
    
    private static final String DATABASE_NAME = "account";
    private static final String DATABASE_TABLE = "titles";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
    		 "create table titles (_id integer primary key autoincrement, "
    			        + "name text not null," 
    			        + "amount text not null );";
        
    private final Context context; 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public databasehandler(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
  //---opens the database---
    public databasehandler open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
  //---insert a title into the database---
    public long insertAccount(String name, int amount) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_AMOUNT,amount);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    
  //---deletes a particular title---
    public boolean deleteAccount(long rowid) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + 
        		"=" + rowid, null) > 0;
    }
    
    public Cursor getdata(String name) throws SQLException {
    	   Cursor cursor =db.query(DATABASE_TABLE,new String[] {"_id", "name", "amount"},"name LIKE ?" ,new String[]{name+"%"},null,null,null);
    	   if (cursor != null) {
               cursor.moveToFirst();
           }
    	   return cursor;
    }
    
    
    //---retrieves all the titles---
    public Cursor getAllAccounts() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID,
        		KEY_NAME,
        		KEY_AMOUNT}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    
 //---retrieves a particular title---
    public Cursor getAccount(long rowid) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_NAME, 
                		KEY_AMOUNT,
                		}, 
                		KEY_ROWID + "=" + rowid, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public boolean updateAccount(long rowid,String name, 
    	    int amount)
    	    {
    	     	
    			ContentValues args = new ContentValues();
    	        args.put(KEY_NAME,name);
    			args.put(KEY_AMOUNT, amount);
                return db.update(DATABASE_TABLE, args, 
                KEY_ROWID + "=" + rowid, null) > 0;
    	    }
   
}
