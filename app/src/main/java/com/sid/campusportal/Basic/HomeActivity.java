package com.sid.campusportal.Basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.sid.campusportal.Portal.BookletActivity;
import com.sid.campusportal.Portal.MarksActivity;
import com.sid.campusportal.Portal.ScheduleActivity;
import com.sid.campusportal.Portal.SolutionsActivity;
import com.sid.campusportal.Portal.TimetableActivity;
import com.sid.campusportal.Portal.VideoActivity;
import com.sid.campusportal.R;

public class HomeActivity extends AppCompatActivity {


    Button time,schedule,booklet,solution,video,marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Onclick();
    }

    public void init()
    {
        time=findViewById(R.id.btn_time_table);
        schedule=findViewById(R.id.btn_schedule);
        booklet=findViewById(R.id.btn_booklet);
        solution=findViewById(R.id.btn_test_solution);
        video=findViewById(R.id.btn_video);
        marks=findViewById(R.id.btn_marks);
    }
    public void Onclick()
    {
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimetableActivity.class);
                startActivity(intent);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });

        booklet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookletActivity.class);
                startActivity(intent);
            }
        });

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SolutionsActivity.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MarksActivity.class);
                startActivity(intent);
            }
        });
    }
}
