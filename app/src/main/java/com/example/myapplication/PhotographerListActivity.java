package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapter.PhotographerAdapter;
import com.example.myapplication.json.Parser;
import com.example.myapplication.model.Photographer;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PhotographerListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    protected PhotographerAdapter photographerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Photographer> dataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photographer_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        dataSet= new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.photographer_list);
        recyclerView.setHasFixedSize(true);
        photographerAdapter = new PhotographerAdapter(this,dataSet);
        recyclerView.setAdapter(photographerAdapter);
        mLayoutManager = new GridLayoutManager(this,1);
        // Set the color scheme of the SwipeRefreshLayout by providing 4 color resource ids
        //noinspection ResourceAsColor
        EditText editText=findViewById(R.id.editTextTextPersonName);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                initiateRefresh(editable.toString());
            }
        });


        initiateRefresh("");
    }

    public  void initiateRefresh(String name)
    {
        String Url="";
        if(name!="")
        {
            Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerListSearchByName.php?Name="+name;
        }
        else
        {
            Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerList.php";
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet();
                Iterator iterator = Parser.parseStringToJson(response).iterator();
                while (iterator.hasNext()){
                    Photographer photographer = (Photographer) iterator.next();
                    dataSet.add(photographer);
                    photographerAdapter.notifyItemInserted(dataSet.size() - 1);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        queue.add(strReq);

    }

    private void clearDataSet()
    {
        if (dataSet != null){
            dataSet.clear();
            photographerAdapter.notifyDataSetChanged();
        }
    }
}