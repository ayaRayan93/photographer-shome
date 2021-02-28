package ex.photo.photographershome.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import ex.photo.photographershome.R;

import static ex.photo.photographershome.MainActivity.photographer;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TextView face=root.findViewById(R.id.id_facebook);
        TextView insta=root.findViewById(R.id.id_insta);
        TextView behance=root.findViewById(R.id.id_behance);
        TextView whats=root.findViewById(R.id.id_whats);

        face.setText(photographer.getFacebook());
        face.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                face.setTextColor(R.color.design_default_color_primary);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(face.getText().toString()));
                startActivity(browserIntent);
                face.setTextColor(R.color.main);
            }
        });

        insta.setText(photographer.getInstagram());
        insta.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                insta.setTextColor(R.color.design_default_color_primary);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(insta.getText().toString()));
                startActivity(browserIntent);
                insta.setTextColor(R.color.main);
            }
        });
        behance.setText(photographer.getBehance());
        behance.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                behance.setTextColor(R.color.design_default_color_primary);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(behance.getText().toString()));
                startActivity(browserIntent);
                behance.setTextColor(R.color.main);
            }
        });
        whats.setText(photographer.getWhatsApp());
        whats.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                whats.setTextColor(R.color.design_default_color_primary);
                String url = "https://api.whatsapp.com/send?phone="+whats.getText();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                whats.setTextColor(R.color.main);
            }
        });
        Button b=root.findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone="+whats.getText();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return root;
    }
    public  void connect(String Url)
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);


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
}