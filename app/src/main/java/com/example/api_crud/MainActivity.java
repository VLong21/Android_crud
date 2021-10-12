package com.example.api_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.api_crud.Model.TaskModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RequestQueue requestQueue;
    private SwipeRefreshLayout refresh;
    private ArrayList<TaskModel> taskModel = new ArrayList<>();
    private JsonArrayRequest arrayRequest;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private ActivityAdapter activityAdapter;
    private String url = "http://10.0.2.2:3000/task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh = (SwipeRefreshLayout) findViewById(R.id.swipedown);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        dialog = new Dialog(this);
        refresh.setOnRefreshListener(this);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                taskModel.clear();
                getData();
            }
        });

    }

    private void getData() {
        refresh.setRefreshing(true);
        arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        TaskModel task = new TaskModel();
                        task.setName(jsonObject.getString("name"));
                        task.setDone(jsonObject.getBoolean("isdone"));
                        Log.v("log:",jsonObject.getString("name"));
                        Log.v("log:", String.valueOf(jsonObject.getBoolean("isdone")));
                        taskModel.add(task);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(taskModel);
                refresh.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);
    }

    private void adapterPush(ArrayList<TaskModel> taskModel) {
        activityAdapter = new ActivityAdapter(this, taskModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(activityAdapter);
    }

    @Override
    public void onRefresh() {

    }
}