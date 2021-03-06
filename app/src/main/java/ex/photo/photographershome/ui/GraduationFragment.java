package ex.photo.photographershome.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ex.photo.photographershome.R;
import ex.photo.photographershome.adapter.PhotographerImagesAdapter;

import static ex.photo.photographershome.MainActivity.dataSetgraduation;

public class GraduationFragment extends Fragment {

    protected PhotographerImagesAdapter photographerAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_graduation, container, false);
        RecyclerView recyclerView =root.findViewById(R.id.photographergraduation_list);
        recyclerView.setHasFixedSize(true);
        if(dataSetgraduation.size()>0) {
            photographerAdapter = new PhotographerImagesAdapter(getContext(), dataSetgraduation);
            recyclerView.setAdapter(photographerAdapter);
            GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
            recyclerView.setLayoutManager(mLayoutManager);
        }
        return root;
    }
}