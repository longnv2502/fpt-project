package edu.fpt.assignment.networking;

import edu.fpt.assignment.application.ApiResource;
import edu.fpt.assignment.application.Constant;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitFactory {

    private static Retrofit tMDBRetrofitInstance;
    private static Retrofit lokLokRetrofitInstance;

    private RetrofitFactory() {}

    private static Retrofit getTMDBRetrofitInstance() {
        if (tMDBRetrofitInstance == null) {
            tMDBRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(Constant.TMDB_BASEURL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return tMDBRetrofitInstance;
    }

    private static Retrofit getLokLokRetrofitInstance() {
        if (lokLokRetrofitInstance == null) {
            lokLokRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(Constant.LOKLOK_BASEURL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return lokLokRetrofitInstance;
    }


    public static Retrofit create(ApiResource source) {
        switch (source){
            case TMDB: return getTMDBRetrofitInstance();
            case LOKLOK: return getLokLokRetrofitInstance();
            default: throw new RuntimeException("Not Resource Match Api");
        }
    }
}
