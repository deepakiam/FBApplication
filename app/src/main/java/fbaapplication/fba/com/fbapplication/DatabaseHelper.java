package fbaapplication.fba.com.fbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

/**
 * Created by glenmenezes on 10/10/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="FBApplication.db";
    public static final String TABLE_NAME_A="Notifications";
    public static final String TABLE_NAME_B="Groups";
    public static final String TABLE_NAME_C="Items";
    public static final String TABLE_NAME_D="Keywords";

    public static final String COL_A_1="id";
    public static final String COL_A_2="text_value";
    public static final String COL_A_3="image_no";
    public static final String COL_A_4="user_id";
    public static final String COL_A_5="group_no";
    public static final String COL_A_6="post_id";
    public static final String COL_A_7="timestamp";

    public static final String COL_B_1="id";
    public static final String COL_B_2="user_id";
    public static final String COL_B_3="group_no";

    public static final String COL_C_1="id";
    public static final String COL_C_2="user_id";
    public static final String COL_C_3="item";
    public static final String COL_C_4="group_no";

    public static final String COL_D_1="id";
    public static final String COL_D_2="category";
    public static final String COL_D_3="keyword";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Notifications (id integer primary key autoincrement,text_value text, image_no integer, user_id text, group_no integer, post_id integer, timestamp datetime default CURRENT_TIMESTAMP)");
        db.execSQL("create table Groups (id integer primary key autoincrement,user_id text, group_no integer)");
        db.execSQL("create table Items (id integer primary key autoincrement,user_id text, item integer, group_no integer)");
        db.execSQL("create table Keywords (id integer primary key autoincrement,category text, keyword text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Notifications");
        db.execSQL("drop table if exists Groups");
        db.execSQL("drop table if exists Items");
        db.execSQL("drop table if exists Keywords");

    }

    public boolean insertDataIntoNotifications(String text_value, int image_no, String user_id, int group_no, int post_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_A_2, text_value);
        contentValues.put(COL_A_3, image_no);
        contentValues.put(COL_A_4, user_id);
        contentValues.put(COL_A_5, group_no);
        contentValues.put(COL_A_6, post_id);
        long ret=db.insert(TABLE_NAME_A,null,contentValues);

        if(ret < 0)
            return false;
        else
            return true;

    }

    public boolean insertDataIntoGroups(String user_id, int group_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_B_2, user_id);
        contentValues.put(COL_B_3, group_no);
        long ret=db.insert(TABLE_NAME_B,null,contentValues);

        if(ret < 0)
            return false;
        else
            return true;

    }

    public boolean insertDataIntoItems(String user_id, String item, int group_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_C_2, user_id);
        contentValues.put(COL_C_3, item);
        contentValues.put(COL_C_4, group_no);

        long ret=db.insert(TABLE_NAME_C,null,contentValues);

        if(ret < 0)
            return false;
        else
            return true;

    }

    public boolean insertDataIntoKeywords(String category, String keyword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_D_2, category);
        contentValues.put(COL_D_3, keyword);

        long ret=db.insert(TABLE_NAME_D,null,contentValues);

        if(ret < 0)
            return false;
        else
            return true;

    }

    public Cursor getAllDataNotifications(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_A,null);
        return res;

    }

    public Cursor getAllDataGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_B,null);
        return res;

    }

    public Cursor getAllDataItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_C,null);
        return res;

    }

    public Cursor getAllDataKeywords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_D,null);
        return res;

    }

    public Cursor getDataNotifications(String user_id, int group_no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_A + " where user_id = " + COL_A_4 + " and group_no = " + COL_A_5,null);
        return res;

    }

    public Cursor getDataGroups(String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_B + " where user_id = " + COL_B_2,null);
        return res;

    }

    public Cursor getDataItems(String user_id, int group_no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_C + " where user_id = " + COL_C_2 + " and group_no = " + COL_C_4,null);
        return res;

    }

    public Cursor getDataKeywords(String category){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME_D + " where category = " + COL_D_2,null);
        return res;

    }


}



