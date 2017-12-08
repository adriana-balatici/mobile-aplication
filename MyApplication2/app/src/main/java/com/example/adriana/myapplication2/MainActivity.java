package com.example.adriana.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adriana.myapplication2.domain.Restaurant;
import com.example.adriana.myapplication2.exceptions.RestaurantNotFoundException;
import com.example.adriana.myapplication2.repository.MemoryRepository;
import com.example.adriana.myapplication2.repository.Repository;

public class MainActivity extends AppCompatActivity {
    final Integer REQUEST_CODE = 0;
    private ArrayAdapter adapter;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.repository = new MemoryRepository();

        ListView listView = (ListView) findViewById(R.id.listview1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                //String[] categories = new String[MainActivity.this.repository.getCategories().size()];
               // for (int i = 0; i < MainActivity.this.repository.getCategories().size(); ++i) {
                    //categories[i] = MainActivity.this.repository.getCategories().get(i).toString();
                //}
                //intent.putExtra("categories", categories);
                intent.putExtra("edit", position);
                try {
                    intent.putExtra("name", MainActivity.this.repository.getRestaurant(position).getName());
                    intent.putExtra("location", MainActivity.this.repository.getRestaurant(position).getLocation());
                } catch (RestaurantNotFoundException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        refresh();
    }

    public void refresh() {
        ListView listView = (ListView) findViewById(R.id.listview1);
        this.adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, repository.getRestaurants());
        listView.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }

    public void goToAddIntent(View view) {
        Intent intent =  new Intent(this, EditActivity.class);
        //String[] categories = new String[this.repository.getCategories().size()];
        //for (int i = 0; i < this.repository.getCategories().size(); ++i) {
            //categories[i] = this.repository.getCategories().get(i).toString();
        //}
        //intent.putExtra("categories", categories);
        intent.putExtra("name", "");
        intent.putExtra("location", "");
        intent.putExtra("stars", 0);
        startActivityForResult(intent, REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            try {
                if (data.getIntExtra("edit", -1) == -1) {
                    this.repository.addRestaurant(new Restaurant(
                            data.getStringExtra("name"),
                            data.getStringExtra("location"),
                            data.getIntExtra("stars",1)
                    ));
                } else {
                    this.repository.editRestaurant(
                            data.getIntExtra("edit", -1),
                            new Restaurant(
                                    data.getStringExtra("name"),
                                    data.getStringExtra("location"),
                                    data.getIntExtra("stars", 1)
                            )
                    );
                }
            } catch (RestaurantNotFoundException e) {
                e.printStackTrace();
            }
            this.adapter.notifyDataSetChanged();
        }
    }



}
