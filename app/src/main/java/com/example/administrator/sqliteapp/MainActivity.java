package com.example.administrator.sqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText e1,e2,e3,e4;
    Button b,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        Data();
    }
    public void Data()
    {
         e1=(EditText)findViewById(R.id.editText);
         e2=(EditText)findViewById(R.id.editText2);
         e3=(EditText)findViewById(R.id.editText3);
         e4=(EditText)findViewById(R.id.editText4);
         b=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
                if (isInserted ==true)
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(MainActivity.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error","Nothing found");
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID :" + res.getString(0) + "\n");
                        buffer.append("Name :" + res.getString(1) + "\n");
                        buffer.append("Surname :" + res.getString(2) + "\n");
                        buffer.append("Marks :" + res.getString(3) + "\n\n");
                    }


                   showMessage("Data",buffer.toString());
                }
            }
        });

       b3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isUpdate = myDb.UpdateData(e4.getText().toString(), e1.getText().toString(), e2.getText().toString(), e3.getText().toString());
               if(isUpdate==true){
                   Toast.makeText(MainActivity.this, "Data is updated", Toast.LENGTH_SHORT).show();
               }
               else
                   Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
           }
       });


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.DeleteData(e4.getText().toString());
                if(deletedRows>0)
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(MainActivity.this, "Data is not deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alert = builder.create();
        builder.setCancelable(false);
        alert.setTitle(title);
        alert.setMessage(Message);
        alert.show();
    }

}
