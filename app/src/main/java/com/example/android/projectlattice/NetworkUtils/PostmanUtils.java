package com.example.android.projectlattice.NetworkUtils;

public class PostmanUtils {

    private PostmanUtils(){}

    //TODO:Add the base URL for the network operation.
    public static final String BASE_URL = "";

    public static PostmanService getService(){
        return RetrofitClient.getClient(BASE_URL).create(PostmanService.class);

    }
}
