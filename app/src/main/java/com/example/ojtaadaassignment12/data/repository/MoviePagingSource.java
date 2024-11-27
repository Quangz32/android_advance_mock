package com.example.ojtaadaassignment12.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MoviePagingSource extends RxPagingSource<Integer, Movie> {

    private final String category;
    private final String apiKey;
    MovieRepository movieRepository;
    FavoriteMovieRepository favoriteMovieRepository;

    public MoviePagingSource(String category, String apiKey, MovieRepository movieRepository, FavoriteMovieRepository favoriteMovieRepository) {
        this.category = category;
        this.apiKey = apiKey;
        this.movieRepository = movieRepository;
        this.favoriteMovieRepository = favoriteMovieRepository;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        // Lấy giá trị của trang hiện tại, nếu loadParams không có giá trị thì mặc định là trang 1
        int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

        // Lấy dữ liệu từ API và từ Database
        return movieRepository.getMovies(category, apiKey, page)
                .subscribeOn(Schedulers.io())
                .flatMap(movies -> {
                    // Chuyển Observable thành Single và lấy dữ liệu từ database
                    return favoriteMovieRepository.getFavoriteMovieIds()
                            .firstOrError()  // Convert Observable to Single
                            .map(favoriteMovieIds -> {
                                // Kết hợp dữ liệu từ API và database (chỉ đánh dấu phim yêu thích)
                                for (Movie movie : movies) {
                                    movie.setFavorite(favoriteMovieIds.contains(movie.getId()));
                                }
                                return movies;
                            });
                })
                .map(movies -> toLoadResult(movies, page, 100))
                .onErrorReturn(throwable -> {
                    Log.e("MoviePagingSource", "Error loading data", throwable);
                    return new LoadResult.Error<>(throwable);
                });
    }



    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> pagingState) {
        return 0;
    }

    // Phương thức chuyển đổi danh sách kết quả thành đối tượng LoadResult
    private LoadResult<Integer, Movie> toLoadResult(List<Movie> results, Integer page, int totalPages) {
        return new LoadResult.Page<>(
                results,                          // Danh sách kết quả trả về
                page == 1 ? null : page - 1,      // Trang trước (null nếu là trang đầu tiên)
                page < totalPages ? page + 1 : null  // Trang sau (null nếu là trang cuối cùng)
        );
    }

}
