package com.stock.anaysis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExternalCall {

	public static String callApi(String urlString) {
		try {
			System.out.println(urlString);
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			System.out.println(conn.getResponseMessage());
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = new String();
			String line;
			System.out.println("Output from Server .... \n");
			while ((line = br.readLine()) != null) {
				output += line;
			}

			conn.disconnect();
			return output;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getURL(String stockName, String function, String interval, boolean outputSize){
		String url = CommonConstants.baseUrl + CommonConstants.functionUrl + function + CommonConstants.symbolUrl + stockName + CommonConstants.intervalUrl + interval;
		if(outputSize){
			url += CommonConstants.outputSiceUrl + "full";
		}
		else{
			url += CommonConstants.outputSiceUrl + "compact";
		}
		url += CommonConstants.keyUrl + CommonConstants.API_KEY;
		return url;
	}
	public static String timeSerice(String stockName, String interval){
		String url = getURL(stockName,"TIME_SERIES_INTRADAY", interval, false);
		String result = callApi(url);
		System.out.println(result);
		return result;
	}
}
