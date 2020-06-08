package vn.edu.ntu.giakhiem.observerpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

import vn.edu.ntu.giakhiem.item.IDatePicker;
import vn.edu.ntu.giakhiem.item.ITimePicker;
import vn.edu.ntu.giakhiem.item.MyDatePicker;
import vn.edu.ntu.giakhiem.item.MyTimePicker;

public class MainActivity extends AppCompatActivity implements IDatePicker, ITimePicker {

    ImageButton imbDate1, imbTime1, imbDate2;
    EditText edtDate1, edtDate2, edtTime1;
    MyDatePicker myDatePicker;
    MyTimePicker myTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addViews();
        addEvents();
    }

    private void addViews(){
        edtDate1 = findViewById(R.id.edtDate1);
        edtTime1 = findViewById(R.id.edtTime1);
        edtDate2 = findViewById(R.id.edtDate2);

        imbDate1 = findViewById(R.id.imbDate1);
        imbDate2 = findViewById(R.id.imbDate2);
        imbTime1 = findViewById(R.id.imbTime1);

        myDatePicker = new MyDatePicker(MainActivity.this, Calendar.getInstance());
        myTimePicker = new MyTimePicker(MainActivity.this, Calendar.getInstance());
        myDatePicker.attach(this);
        myTimePicker.attach(this);
    }


    private void addEvents(){
        imbDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatePicker.showPicker(1);
            }
        });

        imbDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatePicker.showPicker(2);
            }
        });

        imbTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTimePicker.showPicker(1);
            }
        });
    }


    @Override
    public void onClickGetDate(int year, int month, int day, int index) {
        String s = day + "/" + (month + 1) + "/" + year;
        switch (index){
            case 1 :
                edtDate1.setText(s);
                break;
            case 2 :
                edtDate2.setText(s);
                break;
        }
    }

    @Override
    public void onClickGetTime(int hour, int minute, int index) {
        String s = hour + " giờ " + minute + " phút ";
        switch (index){
            case 1:
                edtTime1.setText(s);
                break;
        }
    }
}
