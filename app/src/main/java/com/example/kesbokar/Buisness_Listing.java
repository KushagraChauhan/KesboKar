package com.example.kesbokar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

public class Buisness_Listing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buisness__listing);
        new Listing().execute();
    }
    public class Listing extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
            DataAdapter dataAdapter=new DataAdapter(Buisness_Listing.this);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(dataAdapter);
        }
    }
}
