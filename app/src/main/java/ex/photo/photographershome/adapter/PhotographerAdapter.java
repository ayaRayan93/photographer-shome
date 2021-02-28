package ex.photo.photographershome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ex.photo.photographershome.MainActivity;
import ex.photo.photographershome.R;
import ex.photo.photographershome.model.Photographer;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class PhotographerAdapter  extends RecyclerView.Adapter<PhotographerAdapter.ViewHolder> {

    private List<Photographer> DataSet;
    private static Context context;

    public PhotographerAdapter(Context cont, List<Photographer> dataSet)
    {
        context=cont;
        DataSet = dataSet;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
       ImageView poster;
       TextView title;
       TextView text;

        public ViewHolder(View v)
        {
            super(v);
            poster=v.findViewById(R.id.imageView2);
            title=v.findViewById(R.id.id_text);
            text=v.findViewById(R.id.content);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                       Context context2 = v.getContext();
                        Intent intent = new Intent(context2, MainActivity.class);
                        intent.putExtra("Photographer", (Serializable) DataSet.get(getPosition()));
                        context2.startActivity(intent);

                }
            });

        }

        public ImageView getPoster() {
            return poster;
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getText() {
            return text;
        }

        public void setText(TextView text) {
            this.text = text;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photographer_list_content, parent, false);

        return  new PhotographerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (DataSet.get(position) != null)
        {

            holder.getTitle().setText(DataSet.get(position).getPhotographerName());
            holder.getText().setText(DataSet.get(position).getLocation());


            // Feed image
            if (DataSet.get(position).getProfileImage() != null)
            {
                //download image.
                Picasso.with(context).load(DataSet.get(position).getProfileImage()).into(holder.getPoster());
            }

        }
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }
}