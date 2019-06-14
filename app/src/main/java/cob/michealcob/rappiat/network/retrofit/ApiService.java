package cob.michealcob.rappiat.network.retrofit;


import cob.michealcob.rappiat.network.retrofit.response.ApiResponse;
import cob.michealcob.rappiat.network.retrofit.response.VideoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<ApiResponse>  getPopularResponse(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<ApiResponse>  getTopResponse(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<ApiResponse>  getUpcomingResponse(
            @Query("api_key") String apiKey
    );


    @GET("movie/{movie_id}/videos")
    Call<VideoResponse>  getVideoId(
            @Path("movie_id") String productId,
            @Query("api_key") String apiKey
    );
}
