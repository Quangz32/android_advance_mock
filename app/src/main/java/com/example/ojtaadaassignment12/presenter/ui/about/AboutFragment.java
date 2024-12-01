package com.example.ojtaadaassignment12.presenter.ui.about;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.ojtaadaassignment12.R;
import com.example.ojtaadaassignment12.databinding.FragmentAboutBinding;


public class AboutFragment extends Fragment {

    FragmentAboutBinding binding;

    public AboutFragment() {
        // Required empty public constructor
    }

//    public static AboutFragment newInstance() {
//        AboutFragment fragment = new AboutFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = FragmentAboutBinding.inflate(inflater, container, false);
        binding.webview.loadUrl("https://www.themoviedb.org/about/our-history");
        setupWebView();

        return binding.getRoot();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(new WebViewClient());
    }

}