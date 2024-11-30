package com.example.ojtaadaassignment12.presenter.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.ActivityMainBinding;
import com.example.ojtaadaassignment12.databinding.CustomTabBinding;
import com.example.ojtaadaassignment12.domain.model.Reminder;
import com.example.ojtaadaassignment12.domain.model.User;
import com.example.ojtaadaassignment12.domain.usecase.reminder.GetAllReminderUseCase;
import com.example.ojtaadaassignment12.domain.usecase.reminder.InsertReminderUseCase;
import com.example.ojtaadaassignment12.presenter.adapter.ViewPagerAdapter;
import com.example.ojtaadaassignment12.presenter.ui.favourite.FavoriteMoviesFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.container.ListAndDetailContainerFragment;
import com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.detail.MovieDetailViewModel;
import com.example.ojtaadaassignment12.presenter.ui.reminder.ReminderViewModel;
import com.example.ojtaadaassignment12.presenter.ui.setting.SettingFragment;
import com.example.ojtaadaassignment12.presenter.ui.setting.SettingViewModel;
import com.example.ojtaadaassignment12.presenter.ui.user.EditProfileFragment;
import com.example.ojtaadaassignment12.presenter.ui.user.UserProfileViewModel;
import com.example.ojtaadaassignment12.presenter.utils.MyBitmapUtil;
import com.example.ojtaadaassignment12.presenter.utils.MyConstants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainViewModel mainViewModel;

    @Inject
    MovieDetailViewModel movieDetailViewModel;

    @Inject
    SettingViewModel settingViewModel;

    @Inject
    UserProfileViewModel userProfileViewModel;

    @Inject
    ReminderViewModel reminderViewModel;

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
        setupNavigationHeader(); // User Profile

        setupReminder();
        loadReminders();

    }

    private void setupReminder() {
        reminderViewModel.fetchReminders();

//        Reminder reminder1 = new Reminder(
//                1, "movie1", "2020-11-11", 6.9f,
//                "kqjL17yufvn9OVLyXYpvtyrFfak.jpg", 310927);
//        Reminder reminder2 = new Reminder(
//                2, "movie2", "2020-11-11", 6.9f,
//                "kqjL17yufvn9OVLyXYpvtyrFfak.jpg", 512070);
//        Reminder reminder3 = new Reminder(
//                3, "movie3", "2020-11-11", 6.9f,
//                "kqjL17yufvn9OVLyXYpvtyrFfak.jpg", 323790);
//        Reminder reminder4 = new Reminder(
//                4, "movie4", "2020-11-11", 6.9f,
//                "kqjL17yufvn9OVLyXYpvtyrFfak.jpg", 433523);
//
//
//        reminderViewModel.deleteReminder(reminder1);
//        reminderViewModel.deleteReminder(reminder2);
//        reminderViewModel.deleteReminder(reminder3);
//        reminderViewModel.deleteReminder(reminder4);
//
//
//        reminderViewModel.insertReminder(reminder1);
//        reminderViewModel.insertReminder(reminder2);
//        reminderViewModel.insertReminder(reminder3);
//        reminderViewModel.insertReminder(reminder4);
//        reminderViewModel.fetchReminders();

    }

    private void loadReminders() {
        final int NUMBER_OF_REMINDERS = 3;

        TableLayout reminderTableLayout = binding.navigationView.getHeaderView(0)
                                            .findViewById(R.id.reminder_table);

        reminderTableLayout.removeAllViews();

        //Theo dõi Reminder ViewModel để update Upcoming Reminder
        reminderViewModel.getRemindersLiveData().observe(this, reminders -> {
            Log.d("logd.allReminder", reminders.toString());
            List<Reminder> upcomingReminders =
            reminders.stream()
                    .sorted(Comparator.comparingLong(Reminder::getTimestamp)) // Sắp xếp theo timestamp
                    .limit(NUMBER_OF_REMINDERS) // Lấy 3 phần tử đầu tiên
                    .collect(Collectors.toList());

                Log.d("logd.3reminder", "Reminder: " + upcomingReminders.toString());

                for (Reminder reminder : upcomingReminders) {
                    View tableRow = LayoutInflater.from(this)
                            .inflate(R.layout.reminder_item_drawer, null);

                    TextView line1 = tableRow.findViewById(R.id.line_1);
                TextView line2 = tableRow.findViewById(R.id.line_2);

                String line1Text = reminder.getMovieTitle() + " - " +
                        reminder.getMovieReleaseDate().substring(0, 4) + " - " +
                        new DecimalFormat("0.0").format(reminder.getMovieRating()) + "/10";
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(reminder.getTimestamp());

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                String line2Text = year+ "/" + month + "/" + day +" " + hour + ":" + minute;

                line1.setText(line1Text);
                line2.setText(line2Text);
                reminderTableLayout.addView(tableRow);

                }

        });
    }

    private void setupNavigationHeader() {
        userProfileViewModel.getUser(MyConstants.USER_ID);
        userProfileViewModel.getUserLiveData().observe(this, this::showUserProfile);

        //Khi nhấn vào nút Edit
        Button btnEdit = binding.navigationView.getHeaderView(0).findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(view -> {
            drawerLayout.setVisibility(View.GONE);
            showFragment(new EditProfileFragment());
        });

        //Listener khi người dùng edit xong
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                drawerLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    private void showUserProfile(User user) {
        View navHeader = binding.navigationView.getHeaderView(0);
        ImageView ivAvatar = navHeader.findViewById(R.id.nav_avatar);
        TextView tvName = navHeader.findViewById(R.id.nav_name);
        TextView tvEmail = navHeader.findViewById(R.id.nav_email);
        TextView tvDob = navHeader.findViewById(R.id.nav_dob);
        TextView tvGender = navHeader.findViewById(R.id.nav_gender);

        tvName.setText(user.getFullName());
        tvEmail.setText(user.getEmail());
        tvDob.setText(user.getDob());
        tvGender.setText(user.getGender());
        ivAvatar.setImageBitmap(MyBitmapUtil.decodeBase64ToBitmap(user.getAvatar()));
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

        mainViewModel.getToolbarText().observe(this, text -> {
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
        adapter.addFragment(new ListAndDetailContainerFragment(), "Movies");
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
                ) {
                    mainViewModel.getToolbarText().setValue(
                            movieDetailViewModel.getMovieLiveData().getValue().getTitle());
                } else {
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String selectedCategory;

        if (item.getItemId() == R.id.menu_item_popular) {
            selectedCategory = "Popular";
        } else if (item.getItemId() == R.id.menu_item_top_rated) {
            selectedCategory = "Top Rated";
        } else if (item.getItemId() == R.id.menu_item_upcoming) {
            selectedCategory = "Upcoming";
        } else {
            selectedCategory = "Now Playing";
        }

        settingViewModel.setCategoryLiveData(selectedCategory);
        settingViewModel.saveCategory(selectedCategory);

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        drawerLayout.setVisibility(View.GONE);
        findViewById(R.id.edit_profile_fragment).setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.edit_profile_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}
