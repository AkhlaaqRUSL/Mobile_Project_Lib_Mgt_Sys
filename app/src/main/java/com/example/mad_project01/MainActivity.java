package com.example.mad_project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private Button admin_login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditTexts by their IDs
        editText1 = findViewById(R.id.admin_id);
        editText2 = findViewById(R.id.admin_password);

        // Find the button by its ID
        Button button = findViewById(R.id.admin_login_button);

        // Set onClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditTexts
                String adminid = editText1.getText().toString();
                String adminpw = editText2.getText().toString();

                // Check if both EditTexts are not empty
                if (!adminid.isEmpty() && !adminpw.isEmpty()) {
                    // Check if the admin ID and password match the specified values
                    if (adminid.equals("ADMIN") && adminpw.equals("123")) {
                        // Display a toast with the message for successful login
                        Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                        // Move to the next activity
                        Intent intent = new Intent(MainActivity.this, admin_menu.class);
                        startActivity(intent);
                    } else {
                        // Display a toast indicating incorrect credentials
                        Toast.makeText(MainActivity.this, "Incorrect admin ID or password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Display a toast indicating that both EditTexts should be filled
                    Toast.makeText(MainActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
