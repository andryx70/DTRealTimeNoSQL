package com.andry.dtrealtimenosql.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andry.dtrealtimenosql.R;
import com.andry.dtrealtimenosql.model.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {

    private static final String ARTIST_NODE = "Artists";
    private DatabaseReference databaseReference;
    private ListView lstArtist;
    private ArrayAdapter arrayAdapter;
    private List<String> artistName;
    private List<Artist> artists;


    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        lstArtist = view.findViewById(R.id.lstArtist);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        artistName = new ArrayList<>();
        artists = new ArrayList<>();
        arrayAdapter = new ArrayAdapter <> (getActivity(), android.R.layout.simple_list_item_1, artistName);
        lstArtist.setAdapter(arrayAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(ARTIST_NODE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistName.clear();
                artists.clear();
                if (dataSnapshot.exists()){
                   for (DataSnapshot snapshot: dataSnapshot.getChildren() ) {
                       Artist artist = snapshot.getValue(Artist.class);
                           artistName.add(artist.getName());
                           artists.add(artist);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lstArtist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String idArtist = artists.get(position).getId();
                artists.remove(position);
                artistName.remove(position);

                databaseReference.child(ARTIST_NODE).child(idArtist).removeValue();
                return true;
            }
        });
    }
}
