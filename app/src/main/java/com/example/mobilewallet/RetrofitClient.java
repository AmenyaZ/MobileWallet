package com.example.mobilewallet;

import com.google.android.gms.common.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

        private static RetrofitClient instance = null;
        private Api myApi;

        private RetrofitClient() {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(String.valueOf(Api.class))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            myApi = retrofit.create(Api.class);
        }

        public static synchronized RetrofitClient getInstance() {
            if (instance == null) {
                instance = new RetrofitClient();
            }
            return instance;
        }

        public Api getMyApi() {
            return myApi;
        }
    }

