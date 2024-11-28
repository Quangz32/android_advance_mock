package com.example.ojtaadaassignment12.data.repository;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.ojtaadaassignment12.domain.model.Movie;
import com.example.ojtaadaassignment12.domain.repository.FavoriteMovieRepository;
import com.example.ojtaadaassignment12.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieRepositoryPaging {

    //    @Inject
    public MovieRepository movieRepository;
    //
//    @Inject
    public FavoriteMovieRepository favoriteMovieRepository;

    @Inject
    public MovieRepositoryPaging(MovieRepository movieRepository, FavoriteMovieRepository favoriteMovieRepository) {
        this.movieRepository = movieRepository;
        this.favoriteMovieRepository = favoriteMovieRepository;
    }

    // Phương thức trả về dữ liệu phim dạng phân trang dưới dạng Flowable
    public Flowable<PagingData<Movie>> getMovies(CoroutineScope coroutineScope) {
        // Tạo một đối tượng Pager để cấu hình việc tải dữ liệu phân trang
        Pager<Integer, Movie> pager = new Pager<>(
                new PagingConfig(
                        5,  // Số lượng item trên mỗi trang (pageSize)
                        2,  // Số lượng item sẽ được tải trước khi người dùng cuộn đến cuối (prefetchDistance)
                        false,  // false: Không hiển thị các item tạm thời trước khi tải dữ liệu hoàn chỉnh
                        5,  // Số lượng item tải ban đầu (initialLoadSize)
                        10  // Số lượng item tối đa mà Paging giữ trong bộ nhớ đệm (maxSize)
                        // maxSize tiêu chuẩn = pageSize + (2 * prefetchDistance)
                ),
                () -> new MoviePagingSource("popular", "e7631ffcb8e766993e5ec0c1f4245f93", movieRepository, favoriteMovieRepository)
                // Cung cấp nguồn dữ liệu phân trang từ MovieDataSource
        );

        // Chuyển đổi Pager thành Flowable bằng cách sử dụng PagingRx
        return PagingRx.cachedIn(PagingRx.getFlowable(pager), coroutineScope);
//        return PagingRx.getFlowable(pager);
    }
}
