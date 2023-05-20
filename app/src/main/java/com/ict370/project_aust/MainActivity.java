package com.ict370.project_aust;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements myadapter.onNoteListener {
    RecyclerView recview;
    myadapter adapter;
    Button up;
    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        up=(Button) findViewById(R.id.uploadB);
        recview=(RecyclerView)findViewById(R.id.recview);
        mLayoutManager=new LinearLayoutManager(this);
        recview.setLayoutManager(mLayoutManager);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        FirebaseRecyclerOptions<uploadinfo> options =
                new FirebaseRecyclerOptions.Builder<uploadinfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images"), uploadinfo.class)
                        .build();


        adapter=new myadapter(options,this);
        recview.setAdapter(adapter);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<uploadinfo> options =
                new FirebaseRecyclerOptions.Builder<uploadinfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images").orderByChild("imageName").startAt(s).endAt(s+"\uf8ff"), uploadinfo.class)
                        .build();

        adapter=new myadapter(options,this);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        FirebaseRecyclerOptions<uploadinfo> options =
                new FirebaseRecyclerOptions.Builder<uploadinfo>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images"), uploadinfo.class)
                        .build();
        Toast.makeText(getApplicationContext(),"full description option coming soon",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
