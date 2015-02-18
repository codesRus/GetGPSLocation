package com.example.getgpslocation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button btnGetLocation;
	GPSTracker gps;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        // change ActionBar title
        getSupportActionBar().setTitle("Grub Locator");

        setContentView (R.layout.activity_main);

        final TextView coordView = (TextView) findViewById(R.id.coordinates);

        btnGetLocation = (Button) findViewById(R.id.show_location);
        
        btnGetLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(
                            getApplicationContext(),
                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                    + longitude, Toast.LENGTH_LONG).show();

                    coordView.setText("\nLatitide: " + latitude
                                    + "\nLongitude: " + longitude);

                } else {
                    gps.showSettingsAlert();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String newLine = System.getProperty("line.separator");

        switch (item.getItemId()){
            case R.id.action_map:
                // do stuff
                Toast.makeText(this, "Mapping...", Toast.LENGTH_SHORT).show();
                 return true;
            case R.id.action_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
