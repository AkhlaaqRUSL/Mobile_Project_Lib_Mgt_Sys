package com.example.mad_project01;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Book_mgt extends AppCompatActivity {
    DBHandler mydb;
    private EditText book_id_edt, book_name_edt, book_publisher_edt;
    private Button book_add_btn, book_read_btn, book_update_btn, book_delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mgt);

        mydb = new DBHandler(this);

        book_id_edt = findViewById(R.id.book_id);
        book_name_edt = findViewById(R.id.book_name);
        book_publisher_edt= findViewById(R.id.book_publisher_name);
        book_add_btn = findViewById(R.id.book_create);
        book_read_btn =findViewById(R.id.book_read);
        book_update_btn = findViewById(R.id.book_update);
        book_delete_btn = findViewById(R.id.book_delete);
        update_book();
        delete_book();
        showbookdata();

        // Set up click listener for the Create button
        book_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookData();
            }
        });
    }

    // Method to insert book data
    public void insertBookData(){
        Boolean inserted = mydb.insertBookData(book_id_edt.getText().toString(), book_name_edt.getText().toString(), book_publisher_edt.getText().toString());
        if(inserted){
            Toast.makeText(Book_mgt.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Book_mgt.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
        }
    }
    public void showbookdata()
    {
        book_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.showbookdata();
                if(cursor.getCount() == 0){
                    message("Error", "No data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("book_id : " + cursor.getString(0)+"\n")
                            .append("book_name : " + cursor.getString(1)+"\n")
                            .append("book_publisher_name : " + cursor.getString(2)+"\n");
                }
                message("Data", buffer.toString());
            }
        });
    }
    public  void update_book(){
        book_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updated = mydb.update_book(book_id_edt.getText().toString(), book_name_edt.getText().toString(), book_publisher_edt.getText().toString());
                if(updated){
                    Toast.makeText(Book_mgt.this, "Updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Book_mgt.this, "Not Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void message(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title)
                .setMessage(message)
                .show();

    }

    public void delete_book()
    {
        book_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = mydb.delete_book(book_id_edt.getText().toString());
                if (delete>0)
                {
                    Toast.makeText(Book_mgt.this, "Data Deleted!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Book_mgt.this, "Data not Deleted!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
