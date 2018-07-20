package pass.commuter.os.commuterpass;

/**
 *  Created on: 25-03-2016.
 *	Author: Harish Mohan
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="cpommuter.db";
    public static final int DATABASE_VERSION=1;
    public static final String table="user";
    public static final String usname="username";
    public static final String fsname="fullname";
    public static final String esmail="email";
    public static final String psswd="password";
    public static final String pshone="phone";
    public static final String asddress="address";
    public static final String ssource="source";
    public static final String sdestin="destination";
    public static final String sfare="fare";
    public static final String sdatey="date";
    public static final String sconcess="concession";
    public static final String Create="CREATE TABLE user ("+fsname+" TEXT,"+usname+" TEXT,"+psswd+" TEXT,"+esmail+" TEXT,"+pshone+" TEXT,"+asddress+" TEXT,"+ssource+" TEXT,"+sdestin+" TEXT,"+sfare+" INTEGER,"+sdatey+" TEXT,"+sconcess+" TEXT)";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table);
        onCreate(db);
    }
}
