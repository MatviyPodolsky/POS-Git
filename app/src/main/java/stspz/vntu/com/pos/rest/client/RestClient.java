package stspz.vntu.com.pos.rest.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stspz.vntu.com.pos.rest.service.ApiService;

/**
 * Created by Alexander on 29.02.2016.
 */

public class RestClient {

    public static final String SERVER_URL = "http://192.168.99.101:8888/pos/";

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private static final OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private static final ApiService apiService = retrofit.create(ApiService.class);

    public static ApiService getApiService() {
        return apiService;
    }

}
