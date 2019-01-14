package red.txn.webNavi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
    implements  NavigationView.OnNavigationItemSelectedListener {

    static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Drawer
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // NavigationView Listener
        NavigationView nv = findViewById(R.id.navigationView);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        switch (id) {
            case R.id.menuSettings:
                Log.d(TAG, "settings tapped");

                // TODO: start preferenceFlagment

                break;

            case R.id.menuExit:
                Log.d(TAG, "exit tapped");

                // TODO: exit application
                this.finish();
                this.moveTaskToBack(true);
                break;
        }

        // close drawer
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.drawer_menu, menu);
        //return true;
        return false;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        return super.onOptionsItemSelected(item);
//    }


}
