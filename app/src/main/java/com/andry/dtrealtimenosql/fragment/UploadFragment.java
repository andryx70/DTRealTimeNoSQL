package com.andry.dtrealtimenosql.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andry.dtrealtimenosql.R;
import com.andry.dtrealtimenosql.model.Artist;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {

    private static final String ARTIST_NODE = "Artists";
    private DatabaseReference databaseReference;
    private TextInputEditText dt1,dt2;
    private Button button;


    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload, container, false);
        dt1 = v.findViewById(R.id.dato1);
        dt2 = v.findViewById(R.id.dato2);
        button =  v.findViewById(R.id.upload_data);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(); //practicarealtimestorage

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dt1_string = dt1.getText().toString();
                String dt2_string = dt2.getText().toString();

                Artist artist = new Artist(databaseReference.push().getKey(),dt1_string,dt2_string);
                databaseReference.child(ARTIST_NODE).child(artist.getId()).setValue(artist);

                Toast.makeText(getActivity(), "Subiendo", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
