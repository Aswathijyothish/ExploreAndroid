package com.ashgeek.exploreandroid;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Retrofit retrofit;
    private List<Posts> posts = null;
    private PostAdapter postAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView.setText("Started ");
       // Picasso.get().load().into();
        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();

        ConnectInterface connectInterface = retrofit.create(ConnectInterface.class);
        Call<JsonObject> call = connectInterface.getPosts();
        textView.setText("Inside here");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (!response.isSuccessful()) {
                    textView.setText("The error code is " + response.code());
                    posts = null;
                } else {
                    //posts = response.body();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                textView.setText("It failed and the reason is " + t.getMessage());
                posts = null;
            }
        });

      /*  call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("The error code is " + response.code());
                    posts = null;
                } else {
                    posts = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                textView.setText("It failed and the reason is " + t.getMessage());
                posts = null;
            }
        });
*/

        if (null != posts) {
            postAdapter = new PostAdapter(posts, MainActivity.this);
            recyclerView.setAdapter(postAdapter);
        }
        Toast.makeText(this, "Returned Null", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPosts();
    }

    private void getPosts() {

        posts=new ArrayList<Posts>();
        Posts p1=new Posts();
        p1.setId(1000);
        p1.setUserId(1000);
        p1.setTitle("Aswathi");
       // p1.setText("Super");

        Posts p2=new Posts();
        p2.setId(1001);
        p2.setUserId(1);
        p2.setTitle("Jyothish");
       // p2.setText("Awesome");
        posts.add(p1);
        posts.add(p2);

    }
}
