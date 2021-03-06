package com.bountiedapp.bountied.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bountiedapp.bountied.R;
import com.bountiedapp.bountied.model.StaticStrings;
import com.squareup.picasso.Picasso;

/**
 * Created by mprovost on 8/26/2016.
 */
public class FullImage extends AppCompatActivity {

    // endpoint on our server where the possible found images are stored
    private static final String M_BOUNTY_IMAGES_BASE_URL = StaticStrings.BASE_URL + "foundimages/";

    // these are just static strings used to get imageurl data from intent
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_IMAGEURL = "EXTRA_IMAGEURL";

    // UI element
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // set the view from xml file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        // set the toolbar from the xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // this is a method that allows older versions of
        // android to display a return arrow to appear
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get reference to the imageview
        mImageView = (ImageView)findViewById(R.id.full_image);

        // get the image URL and add .jpg to it
        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);
        String imageUrl = extras.getString(EXTRA_IMAGEURL) + ".jpg";

        // create a URI with which to go get the image from our database
        Uri uri = Uri.parse(M_BOUNTY_IMAGES_BASE_URL + imageUrl);

        // download image from our database and put it into the imageview
        Picasso.with(this).load(uri).into(mImageView);

    }

    // creates the options menu of icons in the upper right hand corner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    // controls what happens when the icons in the menu (upper right hand corner) are clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:
                finish();
                return true;
            // Launch the correct Activity here
            case R.id.action_place:
                Intent placeIntent = new Intent(this, PlaceBounty.class);
                startActivity(placeIntent);
                return true;
            case R.id.action_hunt:
                Intent huntIntent = new Intent(this, BountyHunt.class);
                startActivity(huntIntent);
                return true;
            case R.id.action_placed:
                Intent placedIntent = new Intent(this, BountiesPlaced.class);
                startActivity(placedIntent);
                return true;
            case R.id.action_hunts:
                Intent huntsIntent = new Intent(this, HuntsInProgress.class);
                startActivity(huntsIntent);
                return true;
            case R.id.action_accepted:
                Intent acceptedIntent = new Intent(this, BountiesAccepted.class);
                startActivity(acceptedIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
