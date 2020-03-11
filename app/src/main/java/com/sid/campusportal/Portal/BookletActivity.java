package com.sid.campusportal.Portal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sid.campusportal.Classes.Book_info;
import com.sid.campusportal.R;

import java.util.ArrayList;

public class BookletActivity extends AppCompatActivity {

    private ListView bookview;
    private DatabaseReference mDatabase;
    ArrayList<String> booklist = new ArrayList<>();
    private String idd = null,value=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklet);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("campusportal").child("fe").child("booklets");
        bookview = findViewById(R.id.listview);

        final ArrayAdapter<String> booklistadapter  = new ArrayAdapter<String>(BookletActivity.this,R.layout.booklet_list,R.id.text_subject,booklist);
        bookview.setAdapter(booklistadapter);
        Toast.makeText(getApplicationContext(),"Loading.....",Toast.LENGTH_SHORT).show();

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {
                // value = dataSnapshot.getValue(String.class);
                 idd =  dataSnapshot.getKey();
                booklist.add(idd);
                booklistadapter.notifyDataSetChanged();


                bookview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(position >= 0)
                        {
                            String val = booklistadapter.getItem(position);
                            value = dataSnapshot.getValue(String.class);
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(value));
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                            request.setTitle("PDF Download");
                            request.setDescription("Downloading File ......");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,value);
                            DownloadManager manager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                            manager.enqueue(request);
                            Toast.makeText(getApplicationContext(),"Downloading.....",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 booklistadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                booklistadapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                booklistadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                booklistadapter.notifyDataSetChanged();
            }
        });

    }



}
