package com.patrick.bitvilltenologies.nbacard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
TextView textView;
String myResponse;
ListView lv;
    ArrayList<HashMap<String,String>> arrayList;

   // private RecyclerView TeamRV;
   // private ArrayList<RecyclerData> recyclerDataArrayList;
   // private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        lv=(ListView)findViewById(R.id.lv);
        arrayList=new ArrayList<>();


        // initializing our variables.
        //TeamRV = findViewById(R.id.idRVCourse);
        progressBar = findViewById(R.id.idPBLoading);
        // creating new array list.
      //  recyclerDataArrayList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();
        String url = "https://balldontlie.io/api/v1/teams";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if(response.isSuccessful())

                {

                    myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                progressBar.setVisibility(View.GONE);
                                JSONObject reader = new JSONObject(myResponse);
                                JSONArray allteam = reader.getJSONArray("data"); // get the whole json array list
                                for(int i = 0;i<allteam.length();i++)
                                {
                                    JSONObject Teams = allteam.getJSONObject(i);
                                    String abbreviation = Teams.getString("abbreviation");
                                    String city = Teams.getString("city");
                                    String conference = Teams.getString("conference");
                                    String division = Teams.getString("division");
                                    String full_name = Teams.getString("full_name");
                                    String name = Teams.getString("name");

                                    HashMap<String,String> data = new HashMap<>();

                                    data.put("abbreviation",abbreviation);
                                    data.put("city",city );
                                    data.put("conference",conference);
                                    data.put("division",division);
                                    data.put("full_name",full_name);
                                    data.put("name",name);

                                    arrayList.add(data);

                                    ListAdapter adapter = new SimpleAdapter(MainActivity.this,arrayList,R.layout.customcard
                                            ,new String[]{"abbreviation","city","conference","division","full_name","name"},new int[]{R.id.abbreviation,
                                            R.id.city,R.id.conference,R.id.division,R.id.full_name,R.id.name});
                                    lv.setAdapter(adapter);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

    }

}