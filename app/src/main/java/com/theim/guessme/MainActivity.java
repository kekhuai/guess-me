package com.theim.guessme;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private boolean newGame;

    private int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText editText = findViewById(R.id.guess_input_text);
        if (answer < 1000 || newGame) {
            RequestBody requestBody = createRequestBody();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.random.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RandomService randomService = retrofit.create(RandomService.class);
            Call<ResponseBody> responseBodyCall = randomService.getRandomNumber(requestBody);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.body() != null) {
                        ResponseBody.Result result = response.body().getResult();
                        answer = result.getRandom().getData().get(0);
                        checkAnswer(editText);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            checkAnswer(editText);
        }
    }

    private RequestBody createRequestBody() {
        RequestBody requestBody = new RequestBody();
        requestBody.setId(9163);
        requestBody.setJsonrpc("2.0");
        requestBody.setMethod("generateIntegers");
        RequestBody.Params params = requestBody.new Params();
        params.setApiKey("00000000-0000-0000-0000-000000000000");
        params.setBase(10);
        params.setMax(9999);
        params.setMin(1000);
        params.setN(1);
        params.setReplacement(true);
        requestBody.setParams(params);
        return requestBody;
    }

    private void checkAnswer(EditText editText) {
        try {
            int guess = Integer.parseInt(editText.getText().toString());
            if (guess == answer) {
                Toast.makeText(this, String.format(getResources().getString(R.string.correct_message), answer), Toast.LENGTH_SHORT).show();
                editText.setText("");
                Toast.makeText(this, R.string.new_game, Toast.LENGTH_SHORT).show();
                newGame = true;
            } else {
                String moreOrLess = guess < answer ? "น้อย" : "มาก";
                Toast.makeText(this, String.format(getResources().getString(R.string.incorrect_message), moreOrLess), Toast.LENGTH_SHORT).show();
                newGame = false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.invalid_input_message, Toast.LENGTH_SHORT).show();
        }
    }
}
