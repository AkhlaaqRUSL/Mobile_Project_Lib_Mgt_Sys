package com.example.mad_project01;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BookCopies_mgt extends AppCompatActivity {
    DBHandler mydb;

    private EditText book_id_et, book_branch_et, book_access_et;

    private Button bc_add_btn, bc_read_btn, bc_update_btn, bc_delete_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_copies_mgt);

        mydb = new DBHandler(this);

        book_id_et = findViewById(R.id.book_id);
        book_branch_et = findViewById(R.id.book_branch);
        book_access_et = findViewById(R.id.book_access);
        bc_add_btn = findViewById(R.id.book_copy_create);
        bc_read_btn = findViewById(R.id.book_copy_read);
        bc_update_btn = findViewById(R.id.book_copy_update);
        bc_delete_btn = findViewById(R.id.book_copy_delete);
        insertBookCopyData();
        showbookCopydata();
        update_bookCopy();
        delete_bookCopy();

    }

    public void insertBookCopyData(){
        bc_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean inserted = mydb.insertBookCopyData(book_id_et.getText().toString(),  book_access_et.getText().toString());
                if(inserted){
                    Toast.makeText(BookCopies_mgt.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BookCopies_mgt.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void showbookCopydata()
    {
        bc_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.showbookCopydata();
                if(cursor.getCount() == 0){
                    message("Error", "No data");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("book_id : " + cursor.getString(0)+"\n")
                            .append("branch_id : " + cursor.getString(1)+"\n")
                            .append("access_no : " + cursor.getString(2)+"\n");
                }
                message("Data", buffer.toString());
            }
        });
    }
    public  void update_bookCopy(){
        bc_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updated = mydb.update_bookCopy(book_id_et.getText().toString(), book_branch_et.getText().toString(), book_access_et.getText().toString());
                if(updated){
                    Toast.makeText(BookCopies_mgt.this, "Updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(BookCopies_mgt.this, "Not Updated", Toast.LENGTH_SHORT).show();

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
    public void delete_bookCopy()
    {
        bc_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = mydb.delete_bookCopy(book_id_et.getText().toString());
                if (delete>0)
                {
                    Toast.makeText(BookCopies_mgt.this, "Data Deleted!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BookCopies_mgt.this, "Data not Deleted!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}