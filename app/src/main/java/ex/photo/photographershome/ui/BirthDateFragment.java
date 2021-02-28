package ex.photo.photographershome.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ex.photo.photographershome.R;
import ex.photo.photographershome.adapter.PhotographerImagesAdapter;

import static ex.photo.photographershome.MainActivity.dataSetBithdate;


public class BirthDateFragment extends Fragment {

    protected PhotographerImagesAdapter photographerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
  //  protected List<PhotographerImage> dataSet;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //dataSet= new ArrayList<>();
        View root=inflater.inflate(R.layout.fragment_birth_date, container, false);
        RecyclerView recyclerView =root.findViewById(R.id.photographerbirthdate_list);
        recyclerView.setHasFixedSize(true);
        if(dataSetBithdate.size()>0) {
            photographerAdapter = new PhotographerImagesAdapter(getContext(), dataSetBithdate);
            recyclerView.setAdapter(photographerAdapter);
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        // Inflate the layout for this fragment

        return root;
    }


}