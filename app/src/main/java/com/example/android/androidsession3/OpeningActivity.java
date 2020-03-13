package com.example.android.androidsession3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OpeningActivity extends AppCompatActivity {

    static final String URL = "https://api.imgflip.com/get_memes";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        final StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(getApplicationContext(), MemesActivity.class);
                intent.putExtra("memes", response);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Errorenous", error.toString());
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}