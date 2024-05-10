package com.example.mad_project01;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Members_mgt extends AppCompatActivity {
    DBHandler mydb;
    private EditText mem_card_no_edt, mem_name_edt, mem_address_edt, mem_unpaid_edt, mem_phone_edt;


    private Button add_mem_btn, mem_update_btn, mem_show_btn, mem_delete_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_mgt);

        mydb = new DBHandler(this);


        // initializing all our variables.
        mem_card_no_edt = findViewById(R.id.card_no);
        mem_name_edt = findViewById(R.id.member_name);
        mem_address_edt = findViewById(R.id.address);
        mem_unpaid_edt = findViewById(R.id.unpaid_due);
        mem_phone_edt = findViewById(R.id.phone_no);
        add_mem_btn = findViewById(R.id.mem_create);
        mem_show_btn = findViewById(R.id.mem_read);
        mem_update_btn = findViewById(R.id.mem_update);
        mem_delete_btn = findViewById(R.id.mem_delete);
        insertData();
        showdata();
        update();
        delete();

    }
    //--------------------------------------------------Member Mgt ------------------------------------------------------------------------------------------------
        public void insertData(){
            add_mem_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 Boolean inserted = mydb.insertData(mem_card_no_edt.getText().toString(), mem_name_edt.getText().toString(), mem_address_edt.getText().toString(), mem_unpaid_edt.getText().toString(), mem_phone_edt.getText().toString());
                 if(inserted){
                     Toast.makeText(Members_mgt.this, "Data is Inserted", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Toast.makeText(Members_mgt.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                 }
                }
            });
        }
        public void showdata()
        {
            mem_show_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Cursor cursor = mydb.showdata();
                   if(cursor.getCount() == 0){
                       message("Error", "No daa");
                       return;
                   }
                   StringBuffer buffer = new StringBuffer();
                   while(cursor.moveToNext()){
                       buffer.append("mem_card_no : " + cursor.getString(0)+"\n")
                               .append("mem_name : " + cursor.getString(1)+"\n")
                               .append("mem_address : " + cursor.getString(2)+"\n")
                               .append("mem_unpaid_due : " + cursor.getString(3)+"\n")
                               .append("mem_phone_no : " + cursor.getString(4)+"\n");
                   }
                   message("Data", buffer.toString());
                }
            });
        }
        public void delete()
        {
            mem_delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer delete = mydb.delete(mem_card_no_edt.getText().toString());
                    if (delete>0)
                    {
                        Toast.makeText(Members_mgt.this, "Data Deleted!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Members_mgt.this, "Data not Deleted!!", Toast.LENGTH_SHORT).show();

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

        public  void update(){
        mem_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Boolean updated = mydb.update(mem_card_no_edt.getText().toString(), mem_name_edt.getText().toString(), mem_address_edt.getText().toString(), mem_unpaid_edt.getText().toString(), mem_phone_edt.getText().toString());
                    if(updated){
                        Toast.makeText(Members_mgt.this, "Updated", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(Members_mgt.this, "Not Updated", Toast.LENGTH_SHORT).show();

                    }
            }
        });
        }


    }



