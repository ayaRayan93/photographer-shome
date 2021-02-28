package ex.photo.photographershome;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.IdRes;
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
            ImageView imageView=findViewById(R.id.imageView5);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chipGroup.getVisibility()==View.VISIBLE)
                    {
                        chipGroup.setVisibility(View.INVISIBLE);
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
                    initiateRefresh(editable.toString());
                }
            });


           // initiateRefresh("");

            listLocations=new ArrayList<>();
            listLocations.add("Cairo");
            listLocations.add("Assuit");
            listLocations.add("Alexandria ");
            listLocations.add("Beheira");
            listLocations.add("Cairo");
            listLocations.add("Assuit");
            listLocations.add("Alexandria ");
            listLocations.add("Beheira");
            for (int i=0;i<listLocations.size();i++) {
                Chip chip = new Chip(this);
                chip.setText(listLocations.get(i));
                chip.setChipBackgroundColorResource(R.color.white);
                chip.setCloseIconVisible(false);
                chip.setTextColor(getResources().getColor(R.color.black));
                chip.setCheckedIconEnabled(true);
                chipGroup.addView(chip);
            }
       //     chipGroup.setSingleSelection(false);
            chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup chipGroup, @IdRes int i) {
                    Chip chip = chipGroup.findViewById(i);
                    chipGroup.check(i);
                   // chip.setChecked(true);
                }
            });
           // locations();

        }
        catch(Exception ex)
        {}
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
                    Chip chip = new Chip(getBaseContext());
                    chip.setText(listLocations.get(i));
                    chip.setChipBackgroundColorResource(R.color.white);
                    chip.setCloseIconVisible(true);
                    chip.setTextColor(getResources().getColor(R.color.black));


                    ChipGroup chipGroup = findViewById(R.id.chip_group_main);

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