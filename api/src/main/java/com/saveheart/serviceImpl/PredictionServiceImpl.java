package com.saveheart.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.saveheart.PredictionService;
import com.saveheart.dto.PredictionRequestDto;
import com.saveheart.enums.ErrorEnum;
import com.saveheart.enums.PredictionResultEnum;
import com.saveheart.exception.CustomException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Service
public class PredictionServiceImpl implements PredictionService {

	@Override
	public String getPredictionData(PredictionRequestDto predictionRequestDto) {
		String result = "";
		try {
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, new Gson().toJson(predictionRequestDto));
			Request request = new Request.Builder().url("http://127.0.0.1:5000/result").method("POST", body)
					.addHeader("Content-Type", "application/json").build();
			Response response = client.newCall(request).execute();
			int code = response.code();
			if(code == 200) {
				ResponseBody responseBody = response.body();
				result = responseBody.string();
				System.out.println("result ::::  "+result);
				if(!(result.equalsIgnoreCase(PredictionResultEnum.NOT_SAFE.getMessage()) || 
						result.equalsIgnoreCase(PredictionResultEnum.SAFE.getMessage()))) {
					throw new CustomException(ErrorEnum.INVALID_RESPONSE,HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
