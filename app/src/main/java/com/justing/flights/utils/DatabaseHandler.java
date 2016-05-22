package com.justing.flights.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.justing.flights.objects.User;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "FlightsAppDb";

	// Table names
	private static final String TABLE_USERS = "users";
	private static final String TABLE_FLIGHTS = "flights";

	// Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_USER_ID = "user_id";
	private static final String KEY_FLIGHT_ID = "flight_id";

	private static final String KEY_EMAIL = "email";
	private static final String KEY_PASS = "pass";
	private static final String KEY_FN = "firstName";
	private static final String KEY_LN = "lastName";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT,"
				+ KEY_PASS + " TEXT," + KEY_FN + " TEXT," + KEY_LN + " TEXT)";
		db.execSQL(CREATE_USERS_TABLE);

		String CREATE_FLIGHTS_TABLE = "CREATE TABLE " + TABLE_FLIGHTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_ID + " INTEGER,"
				+ KEY_FLIGHT_ID + " INTEGER)";
		db.execSQL(CREATE_FLIGHTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLIGHTS);

		// Create tables again
		onCreate(db);
	}

/*
	// Adding new event                                     ########################### EVENTS ################################
	void addEvent(Event e) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAKEN, e.getTaken());
		values.put(KEY_SPACES, e.getSpaces());
		values.put(KEY_CATEGORY, e.getCategory());
		values.put(KEY_TITLE, e.getTitle());
		values.put(KEY_DESCRIPTION, e.getDescription());
		values.put(KEY_ADDRESS, e.getAddress());
		values.put(KEY_TIME, e.getTime());
		// values.put(KEY_, e.get);

		// Inserting Row
		db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single event
	Event getEvent(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
				KEY_TAKEN, KEY_SPACES, KEY_CATEGORY, KEY_TITLE,
				KEY_DESCRIPTION, KEY_ADDRESS, KEY_TIME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Event e = new Event(Integer.parseInt(cursor.getString(0)),
				Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor
						.getString(2)), Integer.parseInt(cursor.getString(3)),
				cursor.getString(4), cursor.getString(5), cursor.getString(6),
				cursor.getString(7));
		return e;
	}

	// Updating single contact
	public int updateEvent(Event e) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TAKEN, e.getTaken());
		values.put(KEY_SPACES, e.getSpaces());
		values.put(KEY_CATEGORY, e.getCategory());
		values.put(KEY_TITLE, e.getTitle());
		values.put(KEY_DESCRIPTION, e.getDescription());
		values.put(KEY_ADDRESS, e.getAddress());
		values.put(KEY_TIME, e.getTime());

		// updating row
		return db.update(TABLE_USERS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(e.getID()) });
	}

	// Deleting single event
	public void deleteEvent(Event e) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_ID + " = ?",
				new String[] { String.valueOf(e.getID()) });
		db.close();
	}

	// Getting events Count
	public int getEventsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_USERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();
		return count;
	}

    */


	//                                    ########################### USERS ################################
	public void addUser(User u) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, u.getEmail());
		values.put(KEY_PASS, u.getPassword());
		values.put(KEY_FN, u.getFirstName());
		values.put(KEY_LN, u.getLastName());

		// Inserting Row
		db.insert(TABLE_USERS, null, values);
		db.close(); // Closing database connection
	}

    public User getUser(String email) {
		SQLiteDatabase db = this.getReadableDatabase();
		User u = null;

		Cursor c = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_EMAIL, KEY_PASS, KEY_FN, KEY_LN },
				KEY_EMAIL + "=?", new String[] { String.valueOf(email) }, null, null,
				null, null);

        // not safe (SQL injection)
       // Cursor c = db.rawQuery("SELECT * FROM ? WHERE ? = ?", new String[]{TABLE_USERS, KEY_EMAIL, email});

		if (c != null && c.getCount() != 0) {
			c.moveToFirst();
			u = new User(Integer.parseInt(c.getString(0)),
					c.getString(1), c.getString(2), c.getString(3), c.getString(4), null);
			c.close();
		}

		return u;
	}
/*
	// Updating single contact
	public int updateProfile(Profile p) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, p.getUser());
		values.put(KEY_PASS, p.getPass());
		values.put(KEY_FN, p.getFN());
		values.put(KEY_LN, p.getLN());
		values.put(KEY_ABOUT, p.getAbout());
		values.put(KEY_AGE, p.getAge());
		values.put(KEY_ISPHOTO, BoolToInt(p.isPhoto()));

		// updating row
		return db.update(TABLE_FLIGHTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(p.getID()) });
	}
*/
    /*
	// Deleting single event
	public void deleteProfile(Profile p) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FLIGHTS, KEY_ID + " = ?",
				new String[] { String.valueOf(p.getID()) });
		db.close();
	}
*/
    /*
	// Getting events Count
	public int getProfilesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_FLIGHTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);

		int count = cursor.getCount();
		cursor.close();
		return count;
	}
*/
	
	
	//  ********************************** FUNCTION BANK **************************************
	
	private int BoolToInt(boolean arg) {

		int flag = (arg) ? 1 : 0;
		return flag;
	}

    private boolean IntToBool(int arg) {

		Boolean flag = (arg == 1) ? true : false;
		return flag;
	}

    private String ArrayToString(String[] array) {
		String str = "";
		for (int i = 0; i < array.length; i++) {

			str = str + array[i];

			if (i < array.length - 1)
				str = str + ",";
		}
		return str;
	}

    private String[] StringToArray(String str) {
		String[] array = str.split(",");
		return array;
	}

	/*
	 * // Getting All Contacts public List<Contact> getAllContacts() {
	 * List<Contact> contactList = new ArrayList<Contact>(); // Select All Query
	 * String selectQuery = "SELECT  * FROM " + TABLE_USERS;
	 * 
	 * SQLiteDatabase db = this.getWritableDatabase(); Cursor cursor =
	 * db.rawQuery(selectQuery, null);
	 * 
	 * // looping through all rows and adding to list if (cursor.moveToFirst())
	 * { do { Contact contact = new Contact();
	 * contact.setID(Integer.parseInt(cursor.getString(0)));
	 * contact.setName(cursor.getString(1));
	 * contact.setPhoneNumber(cursor.getString(2)); // Adding contact to list
	 * contactList.add(contact); } while (cursor.moveToNext()); }
	 * 
	 * // return contact list return contactList; }
	 */
}