package android.tugcekolcu.firstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tugcekolcu on 28.01.2018.
 */

public class PersonDatabase extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "AndroidTR";

    // Contacts table name
    private static final String TABLE_PERSON = "Person";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_AGE = "age";


    public PersonDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String people_table = "CREATE TABLE " + TABLE_PERSON + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_SURNAME + " TEXT,"
                + KEY_AGE + " TEXT)";

        db.execSQL(people_table);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        onCreate(db);
    }


    //CRUD OPERATIONS

    // Adding new person
    public void addPerson(Person p) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getName()); // Person Name
        values.put(KEY_SURNAME, p.getSurname()); // Person Surname
        values.put(KEY_AGE, p.getAge()); // Person Age
        db.insert(TABLE_PERSON, null, values);
        db.close(); // Closing database connection
    }

    // Getting single person
    public Person getPerson(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PERSON, new String[]{KEY_ID,
                        KEY_NAME, KEY_SURNAME, KEY_AGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Person p = new Person(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return p;

    }

    // Getting All People
    public List<Person> getAllList() {
        List<Person> pList = new ArrayList<Person>();
        String selectQuery = "SELECT  * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person p = new Person(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3));                // Adding contact to list
                pList.add(p);
            } while (cursor.moveToNext());
        }

        return pList;

    }

    // Getting person Count
    public int getPersonCount() {

        String countQuery = "SELECT  * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single person
    public int updatePerson(Person p) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, p.getName()); // Person Name
        values.put(KEY_SURNAME, p.getSurname()); // Person Surname
        values.put(KEY_AGE, p.getAge()); // Person Age


        // updating row
        return db.update(TABLE_PERSON, values, KEY_ID + " = ?",
                new String[]{String.valueOf(p.getId())});
    }

    // Deleting single person
    public void deletePerson(int pID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSON, KEY_ID + " = ?",
                new String[]{String.valueOf(pID)});
        db.close();

    }


}
