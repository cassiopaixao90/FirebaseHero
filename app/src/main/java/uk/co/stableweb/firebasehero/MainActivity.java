package uk.co.stableweb.firebasehero;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Initialize the FirebaseAnalytics variable
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Obtain the Firebase Analytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //Sets whether analytics collection is enabled for this app on this device.
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds.
        mFirebaseAnalytics.setMinimumSessionDuration(20000);


        if(checkWhetherFirstOpen() == 1){
            showPoll();
            setAppFirstOpenValue(2); // To show the dialog later
        }else{
            setAppFirstOpenValue(1);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showPoll(){
        CharSequence colors[] = new CharSequence[] {"Yes", "No", "Need Improvements", "Rate the app"};

        final Bundle bundle = new Bundle();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you like this app?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Thanks for the feedback!", Toast.LENGTH_LONG).show();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(which));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Yes");
                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "Thanks for the feedback!", Toast.LENGTH_LONG).show();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(which));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "No");
                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Thanks for the feedback!", Toast.LENGTH_LONG).show();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(which));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Need Improvements");
                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "Thanks for the feedback!", Toast.LENGTH_LONG).show();
                        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(which));
                        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Rate the app");
                        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text");
                        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                        // To start play store
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Thank you!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        builder.show();
    }

    // Checks if the app start for the first time
    private int checkWhetherFirstOpen() {
        // If this method returns 1, it means first time app open
        // If it returns another number other than 1, it means this is not first time.
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = sharedPref.getInt("first_open", 0);

        return defaultValue;
    }

    private void setAppFirstOpenValue(int number){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("first_open", number);
        editor.commit();
    }


}
