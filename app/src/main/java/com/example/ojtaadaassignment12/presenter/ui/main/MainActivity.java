package com.example.ojtaadaassignment12.presenter.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.ActivityMainBinding;
import com.example.ojtaadaassignment12.databinding.CustomTabBinding;
import com.example.ojtaadaassignment12.presenter.adapter.ViewPagerAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.container.ListAndDetailContainerFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail.MovieDetailViewModel;
import com.example.ojtaadaassignment12.presenter.ui.setting.SettingFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainViewModel mainViewModel;

    @Inject
    MovieDetailViewModel movieDetailViewModel;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ActivityMainBinding binding;
    private NavController navController1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel

        ((App) getApplication()).getAppComponent().inject(this);
//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        drawerLayout = binding.drawerLayout;
        toolbar = binding.toolBar;
        tabLayout = binding.tabLayout;
        viewPager = binding.viewPager;

        setupToolbarAndDrawer();
        setupTabAndPage();

    }

    private void setupToolbarAndDrawer() {
        setSupportActionBar(toolbar);

        mainViewModel.getTab1ShowingDetail().observe(this, showingDetail -> {
            setToolbarNavigationIcon(showingDetail ?
                    R.drawable.baseline_arrow_back_24 :
                    R.drawable.baseline_toggle_24);
        });

        toolbar.setNavigationOnClickListener(view -> {
            if (Boolean.TRUE.equals(mainViewModel.getTab1ShowingDetail().getValue())) {
                navController1.navigate(R.id.movieListFragment);
                mainViewModel.getToolbarText().setValue("Movies");
            } else {
                drawerLayout.openDrawer(binding.navigationView);
            }
        });

        mainViewModel.getToolbarText().observe(this, text ->{
            toolbar.setTitle(text);
        });

    }

    public void setToolbarNavigationIcon(int iconResId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiển thị nút back
            actionBar.setHomeAsUpIndicator(iconResId); // Tùy chỉnh icon
        }
    }

    private void setupTabAndPage() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
//        adapter.addFragment(new MovieListFragment(), "Movies");
//        adapter.addFragment(new MovieDetailFragment(), "detail");
        adapter.addFragment(new ListAndDetailContainerFragment(), "Container");
        adapter.addFragment(new FavoriteMoviesFragment(), "Favorites");
        adapter.addFragment(new SettingFragment(), "Setting");
        adapter.addFragment(new Fragment(), "Blank");
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                int tabPosition = tab.getPosition();
                String[] tabTitles = {"Movies", "Favorite", "Setting", "About"};
                if (tab.getPosition() == 0 &&
                        Boolean.TRUE.equals(mainViewModel.getTab1ShowingDetail().getValue())
                ){
                    mainViewModel.getToolbarText().setValue(
                            movieDetailViewModel.getMovieLiveData().getValue().getTitle());
                } else{
                    mainViewModel.getToolbarText().setValue(tabTitles[tab.getPosition()]);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

    public void setNavControllerTab1(NavController navController) {
        this.navController1 = navController;
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
                return false;
            }
        });
        return true;
    }




}
