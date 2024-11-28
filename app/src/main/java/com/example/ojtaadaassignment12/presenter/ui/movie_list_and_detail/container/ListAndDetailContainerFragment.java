package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.container;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ojtaadaassignment12.App;
import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.FragmentListAndDetailContainerBinding;
import com.example.ojtaadaassignment12.presenter.ui.main.MainActivity;
import com.example.ojtaadaassignment12.presenter.ui.main.MainViewModel;

public class ListAndDetailContainerFragment extends Fragment {
    private MainViewModel mainViewModel;
    private FragmentListAndDetailContainerBinding binding;
    private NavController navController;

    public ListAndDetailContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListAndDetailContainerBinding.inflate(inflater, container, false);


        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.list_and_detail_container);
        navController = navHostFragment.getNavController();

        MainActivity mainActivity = (MainActivity) requireActivity();

        mainActivity.setNavControllerTab1(navController);

        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (navDestination.getId() == R.id.movieDetailFragment) {
                Log.d("logd.container", "navigate to detail");
                mainViewModel.getTab1ShowingDetail().setValue(true);
            } else {
                Log.d("logd.container", "navigate to list");
                mainViewModel.getTab1ShowingDetail().setValue(false);
            }
        });
    }

}
