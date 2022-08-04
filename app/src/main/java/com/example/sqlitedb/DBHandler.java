package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "ContactList";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "Contacts";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "Name";

    // below variable id for our course duration column.
    private static final String PHONENUMBER_COL = "PhoneNumber";

    // below variable for our course description column.
    private static final String EMAIL_COL = "Email";

    // below variable is for our course tracks column.
    private static final String ADDRESS_COL = "Address";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONENUMBER_COL + " INTEGER,"
                + EMAIL_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewContact(String name, String phoneNumber, String email, String address) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, name);
        values.put(PHONENUMBER_COL, phoneNumber);
        values.put(EMAIL_COL, email);
        values.put(ADDRESS_COL, address);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the contacts
    public ArrayList<ContactModal> readContacts() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorContacts = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ContactModal> contactModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contactModalArrayList.add(new ContactModal(cursorContacts.getString(1),
                        cursorContacts.getString(2),
                        cursorContacts.getString(3),
                        cursorContacts.getString(4)));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contactModalArrayList;
    }

    // we have created a new method for searching the contacts
    public ArrayList<ContactModal> searchContacts(String Name) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
      //  Cursor cursorContacts = db.rawQuery("SELECT * FROM "
       //         + TABLE_NAME + " where " + " name " + " like '%" + new String[]{Name}
       //         + "%'", null);

        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE name LIKE '%"+ Name +"%'";
            //    "LIKE '%"+ Name +"%'";

        Cursor cursorContacts = db.rawQuery(query, null);

        // on below line we are creating a new array list.
        ArrayList<ContactModal> contactModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
    if (cursorContacts != null)

    {
        if (cursorContacts.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                contactModalArrayList.add(new ContactModal(cursorContacts.getString(1),
                        cursorContacts.getString(2),
                        cursorContacts.getString(3),
                        cursorContacts.getString(4)));
            } while (cursorContacts.moveToNext());
            // moving our cursor to next.
        }
    }
        // at last closing our cursor
        // and returning our array list.
        cursorContacts.close();
        return contactModalArrayList;
    }

    // below is the method for updating our contact
    public void updateContact(String originalName, String Name, String PhoneNumber,
                             String Email, String Address) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, Name);
        values.put(PHONENUMBER_COL, PhoneNumber);
        values.put(EMAIL_COL, Email);
        values.put(ADDRESS_COL, Address);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalName});
        db.close();
    }

    // below is the method for deleting our contact.
    public void deleteContact(String Name) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{Name});
        db.close();
    }


}


