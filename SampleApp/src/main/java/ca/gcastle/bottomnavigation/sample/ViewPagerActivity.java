package ca.gcastle.bottomnavigation.sample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ca.gcastle.bottomnavigation.listeners.OnChildClickedListener;
import ca.gcastle.bottomnavigation.view.BottomNavigationView;

public class ViewPagerActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    // Whether or not the transition was via a click or not.
    private boolean clickTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        setupBottomNavigationView();
        setupViewPager();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    public void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // When a child is clicked, move viewpager to this tab.
        bottomNavigationView.setOnChildClickedListener(new OnChildClickedListener() {
            @Override
            public void onChildClicked(int child) {
                clickTransition = true;
                viewPager.setCurrentItem(child);
                clickTransition = false;
            }
        });
    }

    public void setupViewPager() {
        // In the XML we set three tabs - Home, Search, Help.
        String[] fragmentTitles = new String[] {
                "Home",
                "Search",
                "Help"
        };

        BlankFragmentPagerAdapter adapter = new BlankFragmentPagerAdapter(getSupportFragmentManager(), fragmentTitles);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        // When ViewPager moves, select that tab.
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(!clickTransition) {
                    bottomNavigationView.animateToChild(position, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
