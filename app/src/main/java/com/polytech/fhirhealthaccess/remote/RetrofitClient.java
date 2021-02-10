package com.polytech.fhirhealthaccess.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  RetrofitClient permet de créer un client Retrofit afin de faire appel à l'API et de convertir
 *  les données récupérées, depuis le serveur Fhir. On utilise GsonConverterFactory afin de
 *  convertir les données récupérées sous format JSON en objets JAVA, et inversement.
 *
 *  @version 1.0
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
