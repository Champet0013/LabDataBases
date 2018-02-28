package com.example.ist_mac_01.labdatabases;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ist-mac-01 on 2/28/2018 AD.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactsManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "contacts";

    private static final String KEY_ID = "id" ;
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_LINE_ID = "line_id";
    SQLiteDatabase db;

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "+TABLE_CONTACTS +"("+
                KEY_ID +" INTEGER PRIMARY KEY, "+
                KEY_NAME + " TEXT NOT NULL UNIQUE,"+
                KEY_PH_NO +" TEXT NOT NULL UNIQUE" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    private static final String DATABASE_ALTER_CONTACT_1 = "ALTER TABLE "
            +TABLE_CONTACTS + " ADD COLUMN " + KEY_LINE_ID + " string;";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <2){
            db.execSQL(DATABASE_ALTER_CONTACT_1);
        }
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact._name);
        values.put(KEY_PH_NO,contact._phone_number);

        long insert = db.insert(TABLE_CONTACTS,null,values);

        db.close();
    }
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_CONTACTS,
                new String[]{KEY_ID,KEY_NAME,KEY_PH_NO}
                ,KEY_ID+ "=?"
                ,new String[] {String.valueOf(id)}
                ,null
                ,null
                ,null
                ,null);

        if(cursor !=null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2));
        return contact;

    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FORM "+ TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact._id = Integer.parseInt(cursor.getString(0));
                contact._name = cursor.getString(1);
                contact._phone_number = cursor.getString(2);
                contactList.add(contact);
            }while (cursor.moveToNext());

        }
        return  contactList;
        }
    public int updateContact(Contact contact){
        return 0;
    }
    public void deleteContact(Contact contact){

    }
}
