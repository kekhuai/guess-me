package com.theim.guessme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RandomService {

    @POST(value = "json-rpc/1/invoke")
    Call<ResponseBody> getRandomNumber(@Body RequestBody requestBody);
}
