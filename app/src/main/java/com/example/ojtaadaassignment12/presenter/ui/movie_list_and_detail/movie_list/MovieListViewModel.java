package com.example.ojtaadaassignment12.presenter.ui.movie_list_and_detail.movie_list;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.usecase.movie.GetMoviePagingDataUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class MovieListViewModel extends ViewModel {

    private final GetMoviePagingDataUseCase getMoviePagingDataUC;

    private final CompositeDisposable disposables = new CompositeDisposable();


    private final MutableLiveData<PagingData<Movie>> moviesPagingLiveData = new MutableLiveData<>();

    @Inject
    public MovieListViewModel(
            GetMoviePagingDataUseCase getMoviePagingDataUC
    ) {
        this.getMoviePagingDataUC = getMoviePagingDataUC;
    }


    public MutableLiveData<PagingData<Movie>> getMoviePagingData() {
        return moviesPagingLiveData;
    }

    public void fetchMovies() {
        // Tạo Disposable để quản lý luồng dữ liệu lấy từ Repository
        Disposable disposable = getMoviePagingDataUC.get()
                .subscribeOn(Schedulers.io())                      // Chạy luồng lấy dữ liệu trên luồng I/O
                .observeOn(AndroidSchedulers.mainThread())         // Quan sát và cập nhật dữ liệu trên luồng chính
                .subscribe(
                        moviesPagingLiveData::setValue,      // Thành công: Gán dữ liệu vào MutableLiveData
                        throwable -> Log.d("MovieViewModel", "getMoviePagingData: " + throwable)
                );

        // Thêm Disposable vào CompositeDisposable để quản lý
        disposables.add(disposable);
    }

//    public void toggleFavorite(int position){
//        Movie movie = moviesLiveData.getValue().get(position);
//        movie.setFavorite(!movie.isFavorite());
//        moviesLiveData.getValue().set(position, movie);
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
