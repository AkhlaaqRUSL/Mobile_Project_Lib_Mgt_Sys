package com.example.mad_project01;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Authors_mgt extends AppCompatActivity {

    DBHandler mydb;

    private EditText book_id_edt, author_name_edt;

    private Button  a_create_btn, a_read_btn, a_update_btn, a_delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors_mgt);
        mydb = new DBHandler(this);

        book_id_edt = findViewById(R.id.book_id);
        author_name_edt = findViewById(R.id.authors_name);
        a_create_btn = findViewById(R.id.author_create);
        a_read_btn = findViewById(R.id.author_read);
        a_update_btn = findViewById(R.id.author_update);
        a_delete_btn = findViewById(R.id.author_delete);

        author_insertData();
        author_showdata();
        author_update();
        author_delete();


    }
    public void author_insertData(){
        a_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Boolean inserted = mydb.author_insertData(book_id_edt.getText().toString(), author_name_edt.getText().toString());
                    if (inserted) {
                        Toast.makeText(Authors_mgt.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Authors_mgt.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    public void author_showdata()
    {
        a_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.author_showdata();
                if(cursor.getCount() == 0){
                    message("Error", "No daa");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("book_id : " + cursor.getString(0)+"\n")
                            .append("authors_name : " + cursor.getString(1)+"\n");

                }
                message("Data", buffer.toString());
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

    public  void author_update(){
        a_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updated = mydb.author_update(book_id_edt.getText().toString(), author_name_edt.getText().toString());
                if(updated){
                    Toast.makeText(Authors_mgt.this, "Updated", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Authors_mgt.this, "Not Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void author_delete()
    {
        a_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = mydb.author_delete(book_id_edt.getText().toString());
                if (delete>0)
                {
                    Toast.makeText(Authors_mgt.this, "Data Deleted!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Authors_mgt.this, "Data not Deleted!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}