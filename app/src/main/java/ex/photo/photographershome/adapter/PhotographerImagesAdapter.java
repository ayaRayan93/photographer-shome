package ex.photo.photographershome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import ex.photo.photographershome.FullscreenActivity;
import ex.photo.photographershome.R;
import ex.photo.photographershome.model.PhotographerImage;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class PhotographerImagesAdapter extends RecyclerView.Adapter<PhotographerImagesAdapter.ViewHolder> {

    private List<PhotographerImage> DataSet;
    private static Context context;

    public PhotographerImagesAdapter(Context cont, List<PhotographerImage> dataSet) {
        context = cont;
        DataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;


        public ViewHolder(View v) {
            super(v);
            poster = v.findViewById(R.id.image11);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context2 = v.getContext();
                    Intent intent = new Intent(context2, FullscreenActivity.class);
                    intent.putExtra("PhotographerDataset", (Serializable) DataSet);
                    intent.putExtra("postion",getPosition());
                    context2.startActivity(intent);

                }
            });

        }

        public ImageView getPoster() {
            return poster;
        }

    }

    @Override
    public PhotographerImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imagecard, parent, false);

        return new PhotographerImagesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PhotographerImagesAdapter.ViewHolder holder, int position) {
        if (DataSet.get(position) != null) {


            // Feed image
            if (DataSet.get(position).getImage() != null) {
                //download image.
                Picasso.with(context).load(DataSet.get(position).getImage()).into(holder.getPoster());
            }

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}


