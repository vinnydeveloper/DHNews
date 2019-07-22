package br.com.dhnews.data.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    public static final String API_KEY  = "4da8ff924e6643659c34ace3a7f3d98b";
    public static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(getUnsafeOKHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;

    }

    public static OkHttpClient.Builder getUnsafeOKHttpClient(){

        return null;
    }

    // Retornamos a instancia da API criada com o retrofit
    public static ApiClient getApiService() {
        return getApiClient().create(ApiClient.class);
    }



}
