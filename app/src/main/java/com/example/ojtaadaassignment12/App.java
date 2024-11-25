package com.example.ojtaadaassignment12;

import android.app.Application;

import com.example.ojtaadaassignment12.data.di.AppComponent;
import com.example.ojtaadaassignment12.data.di.AppModule;
import com.example.ojtaadaassignment12.data.di.DaggerAppComponent;

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
