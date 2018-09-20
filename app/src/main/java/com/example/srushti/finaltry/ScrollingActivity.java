package com.example.srushti.finaltry;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ScrollingActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText sugarcaneSowingDate,sugarcaneSowingArea;
    Button sugarcaneEnter;
    Button btnviewAll ;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new DatabaseHelper(this);

        sugarcaneSowingDate = (EditText)findViewById(R.id.sugarcanesowingdate);

        sugarcaneSowingArea = (EditText)findViewById(R.id.suagarcanesowingarea);
        sugarcaneEnter = (Button)findViewById(R.id.sugarcanesowingenter);
        btnviewAll = (Button) findViewById(R.id.button_viewAll);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        AddData();
        viewAll();
        sugarcaneSowingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                int year=c.get(Calendar.YEAR);

                DatePickerDialog dialog=new DatePickerDialog(ScrollingActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"/"+month+"/"+year;
                sugarcaneSowingDate.setText(date);
            }
        };
    }


    public  void AddData() {
        sugarcaneEnter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Calendar calendar =Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,19);
                        calendar.set(Calendar.MINUTE,24);
                        calendar.set(Calendar.SECOND,30);


                        Intent intent=new Intent(getApplicationContext(),Notification_receiver.class);
                        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);*/

                        boolean isInserted = myDb.insertData(sugarcaneSowingDate.getText().toString(),sugarcaneSowingArea.getText().toString());
                        sugarcaneSowingArea.setText("");
                        sugarcaneSowingDate.setText("");
                        if(isInserted ==true)
                        {
                            Toast.makeText(ScrollingActivity.this, "Data  Inserted", Toast.LENGTH_LONG).show();
                            /*Cursor res = myDb.getAllData();
                            if (res.getCount() == 0) {
                                // show message
                                showMessage("Error", "Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("SugarcaneDate :" + res.getString(0) + "\n");
                                buffer.append("SugarcaneArea :" + res.getString(1) + "\n\n\n");
                            }

                            // Show all data
                            showMessage("Data", buffer.toString());

                            Calendar calendar =Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY,1);
                            calendar.set(Calendar.MINUTE,10);
                            calendar.set(Calendar.SECOND,30);


                            Intent intent=new Intent(getApplicationContext(),Notification_receiver.class);
                            PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                            AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);*/

                        }
                        else {
                            Toast.makeText(ScrollingActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("SugarcaneDate :"+ res.getString(0)+"\n");
                            buffer.append("SugarcaneArea :"+ res.getString(1)+"\n\n\n");
                             }

                        // Show all data
                        showMessage("Data",buffer.toString());*/
                        Intent intent = new Intent(ScrollingActivity.this,Viewall.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
/*
DatabaseHelper myDb;
    EditText editName,editSurname,editMarks;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editMarks = (EditText)findViewById(R.id.editText_Marks);
        btnAddData = (Button)findViewById(R.id.button_add);
        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString() );
                        if(isInserted =true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
 */