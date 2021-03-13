package ex.photo.photographershome;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import ex.photo.photographershome.adapter.PhotographerAdapter;
import ex.photo.photographershome.json.Parser;
import ex.photo.photographershome.model.Photographer;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PhotographerListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    protected PhotographerAdapter photographerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Photographer> dataSet;
    protected List<String> listLocations;
    protected List<String> listSelectedLocations;
    ChipGroup chipGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_photographer_list);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle(getTitle());

            chipGroup = findViewById(R.id.chip_group_main);
            chipGroup.setVisibility(View.GONE);
            listLocations=new ArrayList<>();
            listSelectedLocations=new ArrayList<>();
            locations();
            chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup group, int checkedId) {
                    chipGroup.check(checkedId);
                    Chip chip= (Chip) chipGroup.getChildAt(checkedId);
                    chip.getText();
                    listSelectedLocations.add(chip.getText().toString());
                    initiateRefresh("",listSelectedLocations);
                }
            });
            ImageView imageView=findViewById(R.id.imageView5);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chipGroup.getVisibility()==View.VISIBLE)
                    {
                        chipGroup.setVisibility(View.GONE);

                    }
                    else
                    {
                        chipGroup.setVisibility(View.VISIBLE);
                    }
                }
            });


            dataSet= new ArrayList<>();

            RecyclerView recyclerView = findViewById(R.id.photographer_list);
            recyclerView.setHasFixedSize(true);
            photographerAdapter = new PhotographerAdapter(this,dataSet);
            recyclerView.setAdapter(photographerAdapter);
            mLayoutManager = new LinearLayoutManager(this);
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
                    initiateRefresh(editable.toString(),listSelectedLocations);
                }
            });

            initiateRefresh("",listSelectedLocations);

        }
        catch(Exception ex)
        {}
    }

    public  void initiateRefresh(String name,List<String> listSelectedItems )
    {
        String Url="";
        if(name!="")
        {
            Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerListSearchByName.php?Name="+name;
        }
        else if(listSelectedItems.size()>0)
        {
            String str="";
            for (int i=0;i<listSelectedItems.size()-1;i++)
            {
                str+="'"+listSelectedItems.get(i)+"',";
            }
            str+="'"+listSelectedItems.get(listSelectedItems.size()-1)+"'";
            Url="http://ayaalirayan-001-site1.dtempurl.com/searchByLocation.php?LocationList="+str;
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
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to volley request queue
        queue.add(strReq);

    }
    public   void locations()
    {
        String Url="http://ayaalirayan-001-site1.dtempurl.com/Locations.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                listLocations.clear();
                 listLocations = Parser.parseStringToJsonforPhotographerLocations(response);
                for (int i=0;i<listLocations.size();i++) {
                    Chip chip = new Chip(PhotographerListActivity.this);
                    chip.setText(listLocations.get(i));
                    chip.setChipBackgroundColorResource(R.color.white);
                    chip.setCloseIconVisible(false);
                    chip.setTextColor(getResources().getColor(R.color.black));
                    chip.setCheckable(true);
chip.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        chip.getText();
        if(chip.isChecked()) {

            listSelectedLocations.add(chip.getText().toString());
            initiateRefresh("", listSelectedLocations);
        }
        else
        {
            for (int i=0;i<listSelectedLocations.size();i++)
            {
                if(listSelectedLocations.get(i)==chip.getText().toString())
                    listSelectedLocations.remove(i);
            }
            initiateRefresh("", listSelectedLocations);
        }
    }
});

                    chipGroup.addView(chip);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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