package com.example.mad_project01;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static  final String Database_name = "library_mgt_sys";
    public static final int DATABASE_VERSION = 6; // Increase this number if you make changes to the database schema


    public static final String Table_name = "Member_mgt";
    public static final String Table_name2 = "Book_mgt";
    public static  final String Table_name3 =" Book_Copies_mgt";
    public static  final String Table_name4 =" Publisher_mgt";
    public static  final String Table_name5 =" Authors_mgt";


    public static final String col_memCardNo ="mem_card_no";
    public static final String col_memName ="mem_name";
    public static final String col_memAddress ="mem_address";
    public static final String col_memUnpaidDue ="mem_unpaid_due";
    public static final String col_mem_phoneNo ="mem_phone_no";

    // create table for book mgt

    public static final String col_bookId = "book_id";
    public static final String col_bookName = "book_name";
    public static final String col_bookPublisherName = "book_publisher_name";

    public static final String col_branchId = "branch_id";
    public static final String col_accessNo = "access_no";

    public static final String col_pubName = "pub_name";
    public static final String col_pubAddress = "pub_adress";
    public static final String col_pubPhone = "pub_phone";

    public static final String col_authorsName = "authors_name";







    public DBHandler( Context context ) {

        super(context, Database_name, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_name + " (" +
                col_memCardNo + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_memName + " TEXT NOT NULL, " +
                col_memAddress + " TEXT, " +
                col_memUnpaidDue + " REAL DEFAULT 0, " +
                col_mem_phoneNo + " TEXT" +
                ")");


        String createBookTableQuery = "CREATE TABLE " + Table_name2 + " (" +
                col_bookId + " TEXT PRIMARY KEY, " +
                col_bookName + " TEXT, " +
                col_bookPublisherName + " TEXT" +
                ")";
        db.execSQL(createBookTableQuery);

        String createBranchTableQuery = "CREATE TABLE " + Table_name3 + " (" +
                col_branchId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col_accessNo + " TEXT, " +
                col_bookId + " TEXT REFERENCES " + Table_name2 + "(" + col_bookId + ")" + // Assuming you want to reference col_bookId from the previous table
                ")";
        db.execSQL(createBranchTableQuery);

        db.execSQL("CREATE TABLE Publisher_mgt (" +
                col_pubName + " TEXT PRIMARY KEY, " +
                col_pubAddress + " TEXT, " +
                col_pubPhone + " TEXT" +
                ")");

        db.execSQL("CREATE TABLE Authors_mgt (" +
                col_bookId + " TEXT REFERENCES " + Table_name2 + "(" + col_bookId + "), " +
                col_authorsName + " TEXT, " +
                "PRIMARY KEY (" + col_bookId + ", " + col_authorsName + "), " +
                "FOREIGN KEY (" + col_bookId + ") REFERENCES " + Table_name2 + "(" + col_bookId + ")" +
                ")");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name2);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name3);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name4);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name5);
        onCreate(db);

    }
    public boolean insertData(String mem_card_no, String mem_name, String mem_address, String mem_unpaid_due, String mem_phone_no){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_memCardNo, mem_card_no);
        cv.put(col_memName, mem_name);
        cv.put(col_memAddress, mem_address);
        cv.put(col_memUnpaidDue, mem_unpaid_due);
        cv.put(col_mem_phoneNo, mem_phone_no);
        long results = db.insert(Table_name, null, cv);
        if (results == -1){
            return false;
            } else{
            return true;
        }
    }
    public Cursor showdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name, null);
        return  cursor;
    }

    public boolean update(String mem_card_no, String mem_name, String mem_address, String mem_unpaid_due, String mem_phone_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_memCardNo, mem_card_no);
        cv.put(col_memName, mem_name);
        cv.put(col_memAddress, mem_address);
        cv.put(col_memUnpaidDue, mem_unpaid_due);
        cv.put(col_mem_phoneNo, mem_phone_no);
        db.update(Table_name, cv, "mem_card_no = ?",new String[]{mem_card_no});
        return true;
    }
    public Integer delete(String mem_card_no)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete((Table_name), "mem_card_no = ?", new String[]{mem_card_no});
    }
//--------------------------------book mgt------------------------------------------------------------------
    public boolean insertBookData(String book_id, String book_name, String book_publisher_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_bookId, book_id);
        cv.put(col_bookName, book_name);
        cv.put(col_bookPublisherName, book_publisher_name);

        long results = db.insert(Table_name2, null, cv);
        if (results == -1){
            return false;
        }
        else
        {
            return true;
        }

    }

    public Cursor showbookdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name2, null);
        return  cursor;
    }

    public boolean update_book(String book_id, String branch_id, String book_publisher_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_bookId, book_id);
        cv.put(col_branchId, branch_id);
        cv.put(col_bookPublisherName, book_publisher_name);

        db.update(Table_name2, cv, "book_id = ?",new String[]{book_id});
        return true;
    }

    public Integer delete_book(String book_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete((Table_name2), "book_id = ?", new String[]{book_id});
    }
//----------------------------book copy mgt----------------------------------------------------------------
public boolean insertBookCopyData(String book_id, String access_no){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(col_bookId, book_id);
    cv.put(col_accessNo, access_no);

    long result = db.insert(Table_name3, null, cv);
    db.close(); // Close the database connection
    return result != -1; // Return true if insertion successful, false otherwise
}


    public Cursor showbookCopydata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name3, null);
        return  cursor;
    }

    public boolean update_bookCopy(String book_id, String branch_id, String access_no){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_bookId, book_id);
        cv.put(col_branchId, branch_id);
        cv.put(col_accessNo, access_no);

        db.update(Table_name3, cv, "book_id = ?",new String[]{book_id});
        return true;
    }
    public Integer delete_bookCopy(String book_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete((Table_name3), "book_id = ?", new String[]{book_id});
    }
//----------------------------------------pub mgt------------------------------------------------
public boolean pub_insert(String pub_name, String pub_address, String pub_phone){

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(col_pubName, pub_name);
    cv.put(col_pubAddress, pub_address);
    cv.put(col_pubPhone, pub_phone);

    long results = db.insert(Table_name4, null, cv);
    if (results == -1){
        return false;
    } else{
        return true;
    }
}
    public Cursor pub_showdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name4, null);
        return  cursor;
    }

    public boolean pub_update(String pub_name, String pub_address, String pub_phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_pubName, pub_name);
        cv.put(col_pubAddress, pub_address);
        cv.put(col_pubPhone, pub_phone);
        db.update(Table_name4, cv, "pub_name = ?",new String[]{pub_name});
        return true;
    }
    public Integer pub_delete(String pub_name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete((Table_name4), "pub_name = ?", new String[]{pub_name});
    }
    //------------------------------------------Authors mgt------------------------------------------------

    public boolean author_insertData(String book_id,String authors_name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_bookId, book_id);
        cv.put(col_authorsName, authors_name);

        long results = db.insert(Table_name5, null, cv);
        if (results == -1){
            return false;
        } else{
            return true;
        }
    }
    public Cursor author_showdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table_name5, null);
        return  cursor;
    }

    public boolean author_update(String book_id,String authors_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_bookId, book_id);
        cv.put(col_authorsName, authors_name);

        db.update(Table_name5, cv, "book_id = ?",new String[]{book_id});
        return true;
    }

    public Integer author_delete(String book_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete((Table_name5), "book_id = ?", new String[]{book_id});
    }


}