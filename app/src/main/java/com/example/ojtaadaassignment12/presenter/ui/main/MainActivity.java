package com.example.ojtaadaassignment12.presenter.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.ActivityMainBinding;
import com.example.ojtaadaassignment12.databinding.CustomTabBinding;
import com.example.ojtaadaassignment12.presenter.adapter.ViewPagerAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie.MovieListFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = binding.drawerLayout;
        toolbar = binding.toolBar;
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;

        setupTabAndPage();
        setupToolbarAndDrawer();


    }

    private void setupToolbarAndDrawer() {
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupTabAndPage() {
        // Setup ViewPager2 Adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new MovieListFragment(), "Movies");
        adapter.addFragment(new FavoriteMoviesFragment(), "Favorites");
        adapter.addFragment(new Fragment(), "Blank");
        adapter.addFragment(new Fragment(), "Blank");
        viewPager.setAdapter(adapter);

        // Connect TabLayout with ViewPager2, and set custom tab icon,title,badge
        int[] icons = {R.drawable.baseline_home_24,
                R.drawable.baseline_favorite_24,
                R.drawable.baseline_settings_24,
                R.drawable.baseline_info_24};

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    CustomTabBinding tabBinding = CustomTabBinding.inflate(getLayoutInflater());
                    tabBinding.tabTitle.setText(adapter.getFragmentTitle(position));
                    tabBinding.tabIcon.setImageResource(icons[position]);
                    tab.setCustomView(tabBinding.getRoot());
                }
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nav, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

//                FavouriteFragment ff = (FavouriteFragment) myFragments.get(1);
//                ff.doSearch(newText);
                return false;
            }
        });
        return true;
    }
}
