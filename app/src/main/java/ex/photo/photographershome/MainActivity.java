package ex.photo.photographershome;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import ex.photo.photographershome.json.Parser;
import ex.photo.photographershome.model.Photographer;
import ex.photo.photographershome.model.PhotographerImage;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static public Photographer photographer;
    static public List<PhotographerImage> dataSetBithdate;
    static public List<PhotographerImage> dataSetother;
    static public List<PhotographerImage> dataSetwedding;
    static public List<PhotographerImage> dataSetgraduation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photographer= (Photographer) getIntent().getSerializableExtra("Photographer");
        TextView name=findViewById(R.id.id_name);
        TextView location=findViewById(R.id.location);
        ImageView propic=findViewById(R.id.profilepic);

        name.setText(photographer.getPhotographerName());
        location.setText(photographer.getLocation());
        Picasso.with(this).load(photographer.getProfileImage()).into(propic);
        dataSetBithdate=new ArrayList<>();
        dataSetgraduation=new ArrayList<>();
        dataSetother=new ArrayList<>();
        dataSetwedding=new ArrayList<>();
        initiateRefresh2("BirthDate");
        initiateRefresh("Wedding");
        initiateRefresh1("Graduation");
        initiateRefresh3("Other");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_wedding, R.id.navigation_graduation, R.id.navigation_birthdate, R.id.navigation_other)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    public  void initiateRefresh(String catagory)
    {
        String Url="";

        Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerImages.php?id="+photographer.getPhotographer_ID()+" && catagory='"+catagory+"'";

        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet();
                Iterator iterator = Parser.parseStringToJsonforPhotographerImages(response).iterator();
                while (iterator.hasNext()){
                    PhotographerImage photographer = (PhotographerImage) iterator.next();
                    dataSetwedding.add(photographer);

                   // photographerAdapter.notifyItemInserted(dataSet.size() - 1);
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
    public  void initiateRefresh1(String catagory)
    {
        String Url="";

        Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerImages.php?id="+photographer.getPhotographer_ID()+" && catagory='"+catagory+"'";

        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet1();
                Iterator iterator = Parser.parseStringToJsonforPhotographerImages(response).iterator();
                while (iterator.hasNext()){
                    PhotographerImage photographer = (PhotographerImage) iterator.next();
                    dataSetgraduation.add(photographer);

                    // photographerAdapter.notifyItemInserted(dataSet.size() - 1);
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
    public  void initiateRefresh2(String catagory)
    {
        String Url="";

        Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerImages.php?id="+photographer.getPhotographer_ID()+" && catagory='"+catagory+"'";

        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet2();
                Iterator iterator = Parser.parseStringToJsonforPhotographerImages(response).iterator();
                while (iterator.hasNext()){
                    PhotographerImage photographer = (PhotographerImage) iterator.next();
                    dataSetBithdate.add(photographer);

                    // photographerAdapter.notifyItemInserted(dataSet.size() - 1);
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
    public  void initiateRefresh3(String catagory)
    {
        String Url="";

        Url="http://ayaalirayan-001-site1.dtempurl.com/PhotographerImages.php?id="+photographer.getPhotographer_ID()+" && catagory='"+catagory+"'";

        RequestQueue queue = Volley.newRequestQueue(this);
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
                clearDataSet3();
                Iterator iterator = Parser.parseStringToJsonforPhotographerImages(response).iterator();
                while (iterator.hasNext()){
                    PhotographerImage photographer = (PhotographerImage) iterator.next();
                    dataSetother.add(photographer);

                    // photographerAdapter.notifyItemInserted(dataSet.size() - 1);
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
        if (dataSetwedding != null){
            dataSetwedding.clear();
           // photographerAdapter.notifyDataSetChanged();
        }
    }
    private void clearDataSet1()
    {
        if (dataSetgraduation != null){
            dataSetgraduation.clear();
            // photographerAdapter.notifyDataSetChanged();
        }
    }
    private void clearDataSet2()
    {
        if (dataSetBithdate != null){
            dataSetBithdate.clear();
            // photographerAdapter.notifyDataSetChanged();
        }
    }
    private void clearDataSet3()
    {
        if (dataSetother != null){
            dataSetother.clear();
            // photographerAdapter.notifyDataSetChanged();
        }
    }
}