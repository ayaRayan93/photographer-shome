package ex.photo.photographershome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import ex.photo.photographershome.model.PhotographerImage;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    public List<PhotographerImage> dataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        dataSet=new ArrayList<>();
        dataSet= (List<PhotographerImage>) getIntent().getSerializableExtra("PhotographerDataset");
        int pos=getIntent().getIntExtra("postion",0);
        Gallery gallery=(Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new imgAdapter(this));
        gallery.setSelection(pos);
    }
    public class imgAdapter extends BaseAdapter
    {
        private Context context;
        public imgAdapter(Context context)
        {
            this.context=context;
        }

        @Override
        public int getCount() {
            return dataSet.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView=new ImageView(context);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            imageView.setMinimumWidth(displayMetrics.widthPixels);
            if (dataSet.get(position).getImage() != null) {
                //download image.
                Picasso.with(context).load(dataSet.get(position).getImage()).into(imageView);
            }
            return imageView;
        }
    }


}