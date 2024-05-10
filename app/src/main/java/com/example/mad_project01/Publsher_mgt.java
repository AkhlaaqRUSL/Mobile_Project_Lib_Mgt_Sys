package com.example.mad_project01;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Publsher_mgt extends AppCompatActivity {

    DBHandler mydb;
    private EditText pub_num_edt, pub_add_edt, pub_phone_edt;

    private Button pub_create_btn, pub_read_btn, pub_update_btn, pub_delete_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publsher_mgt);
        mydb = new DBHandler(this);

        pub_num_edt = findViewById(R.id.pub_name);
        pub_add_edt = findViewById(R.id.pub_add);
        pub_phone_edt = findViewById(R.id.pub_phone);
        pub_create_btn = findViewById(R.id.pub_create);
        pub_read_btn = findViewById(R.id.pub_read);
        pub_update_btn = findViewById(R.id.pub_update);
        pub_delete_btn = findViewById(R.id.pub_delete);

        pub_insert();
        pub_showdata();
        pub_update();
        pub_delete();



    }
    public void pub_insert(){
        pub_create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean inserted = mydb.pub_insert(pub_num_edt.getText().toString(), pub_add_edt.getText().toString(), pub_phone_edt.getText().toString());
                if(inserted){
                    Toast.makeText(Publsher_mgt.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Publsher_mgt.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void pub_showdata()
    {
        pub_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mydb.pub_showdata();
                if(cursor.getCount() == 0){
                    message("Error", "No daa");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()){
                    buffer.append("pub_name : " + cursor.getString(0)+"\n")
                            .append("pub_address : " + cursor.getString(1)+"\n")
                            .append("pub_phone : " + cursor.getString(2)+"\n");

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

    public void pub_update(){
        pub_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updated = mydb.pub_update(pub_num_edt.getText().toString(), pub_add_edt.getText().toString(), pub_phone_edt.getText().toString());
                if(updated){
                    Toast.makeText(Publsher_mgt.this, "Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Publsher_mgt.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void pub_delete()
    {
        pub_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer delete = mydb.pub_delete(pub_num_edt.getText().toString());
                if (delete>0)
                {
                    Toast.makeText(Publsher_mgt.this, "Data Deleted!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Publsher_mgt.this, "Data not Deleted!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}