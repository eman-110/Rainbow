package com.example.rainbow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class DescriptionFragment extends Fragment {
    public DescriptionFragment() {
        // Required empty public constructor
    }

    ImageView appleImage, bgImage, blackimg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);


        appleImage = view.findViewById(R.id.appleImage);
        bgImage = view.findViewById(R.id.bgImage);
        blackimg = view.findViewById(R.id.blackimg);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762320/apple_ljiuyq.png")
                .into(appleImage);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762353/description_bg_kidfcw.png")
                .into(bgImage);

        Picasso.get()
                .load("https://res.cloudinary.com/dgtk4rthy/image/upload/v1752762318/blank_img_suxvrj.png")
                .into(blackimg);


        return view;
    }
}

