package edu.fpt.assignment.networking;

import edu.fpt.assignment.dto.HomePageResult;
import edu.fpt.assignment.dto.ListResult;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.dto.MovieMedia;
import edu.fpt.assignment.dto.ResponseObject;
import edu.fpt.assignment.dto.SearchCriteria;
import edu.fpt.assignment.dto.SearchResult;
import edu.fpt.assignment.enums.DefinitionCode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TheLokLokApiService {
    @GET("homePage/getHome")
    Call<ResponseObject<HomePageResult>> getHome(@Query("navigationId") int navigationId,
                                                 @Query("page") int page,
                                                 @Query("size") int size);

    @GET("movieDrama/get")
    Call<ResponseObject<MovieDetail>> getMovieDetail(@Query("category") int category,
                                                     @Query("id") int id);

    @GET("media/previewInfo")
    Call<ResponseObject<MovieMedia>> getMovieMedia(@Query("category") int category,
                                                   @Query("contentId") int contentId,
                                                   @Query("definition") DefinitionCode definition,
                                                   @Query("episodeId") int episodeId);

    @POST("search/v1/searchWithKeyWord")
    Call<ResponseObject<ListResult<SearchResult>>> searchLeaderboard(@Body SearchCriteria searchCriteria);

}
