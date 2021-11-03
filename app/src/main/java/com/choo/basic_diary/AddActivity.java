package com.choo.basic_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    EditText title_input, diary_input ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        diary_input = findViewById(R.id.diary_input);
    }

    @Override
    public void onBackPressed() {
        if(isEmpty(diary_input) && isEmpty(title_input)) {
            Toast.makeText(this, "Diary seems empty", Toast.LENGTH_SHORT).show();
        }else{
            MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
            myDB.addDiary(title_input.getText().toString().trim(),
                    diary_input.getText().toString().trim());
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        }
        super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                if(isEmpty(diary_input) && isEmpty(title_input)) {
                    Toast.makeText(this, "Diary is empty", Toast.LENGTH_SHORT).show();
                }else{
                    MyDataBaseHelper myDB = new MyDataBaseHelper(AddActivity.this);
                    myDB.addDiary(title_input.getText().toString().trim(),
                            diary_input.getText().toString().trim());
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public String getDate(){
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;
    }
    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}