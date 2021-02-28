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

import static ex.photo.photographershome.MainActivity.dataSetother;


public class OtherFragment extends Fragment {

    protected PhotographerImagesAdapter photographerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_other, container, false);
        RecyclerView recyclerView =root.findViewById(R.id.photographerother_list);
        recyclerView.setHasFixedSize(true);
        if(dataSetother.size()>0) {
            photographerAdapter = new PhotographerImagesAdapter(getContext(), dataSetother);
            recyclerView.setAdapter(photographerAdapter);
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        return  root;
    }
}