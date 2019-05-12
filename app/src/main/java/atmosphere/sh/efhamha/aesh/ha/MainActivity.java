package atmosphere.sh.efhamha.aesh.ha;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import atmosphere.sh.efhamha.aesh.ha.Fragments.ArticlesFragment;
import atmosphere.sh.efhamha.aesh.ha.Fragments.InfoFragment;
import atmosphere.sh.efhamha.aesh.ha.Fragments.NotificationsFragment;
import atmosphere.sh.efhamha.aesh.ha.Fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView navigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);

        fragmentManager = getSupportFragmentManager();

        Fragment articlesFragment = new ArticlesFragment();
        loadFragment(articlesFragment);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.articles:
                        Fragment articlesFragment = new ArticlesFragment();
                        loadFragment(articlesFragment);
                        return true;
                    case R.id.notification:
                        Fragment notificationsFragment = new NotificationsFragment();
                        loadFragment(notificationsFragment);
                        return true;
                    case R.id.info:
                        Fragment infoFragment = new InfoFragment();
                        loadFragment(infoFragment);
                        return true;
                    case R.id.profile:
                        Fragment profileFragment = new ProfileFragment();
                        loadFragment(profileFragment);
                        return true;
                }
                return false;
            }
        });
    }

    public void loadFragment(Fragment fragment)
    {
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        getSupportFragmentManager().popBackStack();
        // Commit the transaction
        fragmentTransaction.commit();
    }

    private long exitTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finishAffinity();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        doExitApp();
    }
}
