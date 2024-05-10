package com.example.mad_project01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class admin_menu extends AppCompatActivity {

    private Button button_admin_menu1, button_admin_menu2, button_admin_menu3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        Button button = findViewById(R.id.button_admin_menu1);
        Button button1 = findViewById(R.id.button_admin_menu2);
        Button button2 = findViewById(R.id.button_admin_menu3);
        Button button3 = findViewById(R.id.button_admin_menu4);
        Button button4 = findViewById(R.id.button_admin_menu5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_menu.this, Members_mgt.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_menu.this, Book_mgt.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_menu.this, BookCopies_mgt.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_menu.this, Publsher_mgt.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_menu.this, Authors_mgt.class);
                startActivity(intent);
            }
        });


    }


}
