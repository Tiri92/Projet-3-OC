package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    public static final String BUNDLE_ID = "bundle_id";
    public static final String NEIGHBOUR_KEY = "neighbour_key";
    public static final String Facebook_Link_All = "www.facebook.fr/";

    @BindView(R.id.activity_profile_picture)
    ImageView mPhoto;
    @BindView(R.id.activity_neighbour_name)
    TextView mName;
    @BindView(R.id.activity_number_phone)
    TextView mNumberPhone;
    @BindView(R.id.activity_neighbour_home)
    TextView mAdress;
    @BindView(R.id.activity_about_me)
    TextView mAboutMe;
    @BindView(R.id.activity_neighbour_name2)
    TextView mName2;
    @BindView(R.id.activity_about_me_title)
    TextView mAboutMeTitle;
    @BindView(R.id.activity_facebook_link)
    TextView mFacebookLink;
    @BindView(R.id.Button_For_Add_Favoris)
    FloatingActionButton mFavoritesButton;

    private Neighbour mNeighbourFromBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
        Intent intent = getIntent();
        Bundle mBundle = intent.getBundleExtra(BUNDLE_ID);
        mNeighbourFromBundle = (Neighbour) mBundle.getSerializable(NEIGHBOUR_KEY);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String Good_Facebook_Result = Facebook_Link_All + mNeighbourFromBundle.getName();

        Glide.with(this).load(mNeighbourFromBundle.getAvatarUrl())
                // .apply(RequestOptions.
                .into(mPhoto);


        mName.setText(mNeighbourFromBundle.getName());
        mNumberPhone.setText(mNeighbourFromBundle.getPhoneNumber());
        mAdress.setText(mNeighbourFromBundle.getAddress());
        mAboutMe.setText(mNeighbourFromBundle.getAboutMe());
        mName2.setText(mNeighbourFromBundle.getName());
        mFacebookLink.setText(Good_Facebook_Result);
        mFavoritesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switchIsFavorite();
            }
        });
    }

    /**
     * We add the neighbour on favorite according to the fact that he is or he's not, if he's not
     * we delete him from FavoritesNeighbours by  clicking on the mFavoritesButton
     */
    private void switchIsFavorite() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighourById(mNeighbourFromBundle.getId());
        if (neighbour.getIsFavorite()) {
            neighbour.setIsFavorite(false);
            Toast.makeText(getApplicationContext(), R.string.toast_removed_from_favorite, Toast.LENGTH_SHORT).show();
        } else {
            neighbour.setIsFavorite(true);
            Toast.makeText(getApplicationContext(), R.string.toast_added_to_favorite, Toast.LENGTH_SHORT).show();
        }
    }



}