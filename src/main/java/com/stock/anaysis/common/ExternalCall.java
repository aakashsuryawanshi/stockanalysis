package com.stock.anaysis.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.patriques.output.technicalindicators.MACD;
import org.patriques.output.technicalindicators.data.MACDData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.analysis.entities.MACDInfo;
import com.stock.analysis.entities.StockData;

public class ExternalCall {

	public static String callApi(String urlString) {
		try {
			System.out.println(urlString);
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			//System.out.println(conn.getResponseMessage());
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = new String();
			String line;
			//System.out.println("Output from Server .... \n");
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
	
	public static String getURL(String key, String stockName, String function, String interval, boolean outputSize){
		String url = CommonConstants.baseUrl + CommonConstants.functionUrl + function + CommonConstants.symbolUrl + stockName + CommonConstants.intervalUrl + interval;
		if(outputSize){
			url += CommonConstants.outputSiceUrl + "full";
		}
		else{
			url += CommonConstants.outputSiceUrl + "compact";
		}
		url += CommonConstants.keyUrl + key;
		return url;
	}
	public static StockData latestValue(String stockName, String interval){
		String url = getURL(CommonConstants.API_KEY, stockName,"TIME_SERIES_INTRADAY", interval, false);
		String result = callApi(url);
		System.out.println(result);
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(result);
		JsonElement tam = jo.get("Technical Analysis: (60min)");
		if(tam == null){
			tam = jo.get("Time Series (30min)");
		}
		if(tam == null){
			tam = jo.get("Time Series (15min)");
		}
		if(tam == null){
			tam = jo.get("Time Series (5min)");
		}
		if(tam == null){
			tam = jo.get("Time Series (1min)");
		}
		jo = tam.getAsJsonObject();
		Object[] timestamp = jo.keySet().toArray();
		String latestTime = (String) timestamp[0];
		StockData data = new StockData();
		data.setTimestamp(latestTime);
		data.setOpen(jo.get(latestTime).getAsJsonObject().get("1. open").getAsDouble());
		data.setHigh(jo.get(latestTime).getAsJsonObject().get("2. high").getAsDouble());
		data.setLow(jo.get(latestTime).getAsJsonObject().get("3. low").getAsDouble());
		data.setClose(jo.get(latestTime).getAsJsonObject().get("4. close").getAsDouble());
		data.setVolumn(jo.get(latestTime).getAsJsonObject().get("5. volume").getAsDouble());
		System.out.println(new Gson().toJson(data).toString());
		return data;
	}
	
	public static MACDInfo macdLatest(String stockName, String interval){
		String url = getURL(CommonConstants.API_KEY, stockName,"MACD", interval, false);
		url += "&series_type=open";
		String result = callApi(url);
		System.out.println(result);
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(result);
		JsonElement tam = jo.get("Technical Analysis: MACD");
		jo = tam.getAsJsonObject();
		Object[] timestamp = jo.keySet().toArray();
		MACDInfo info = new MACDInfo();
		String latestTime = (String) timestamp[0];
		info.setTime(latestTime);
		info.setMacd(jo.get(latestTime).getAsJsonObject().get("MACD").getAsDouble());
		info.setHist(jo.get(latestTime).getAsJsonObject().get("MACD_Hist").getAsDouble());
		info.setSignal(jo.get(latestTime).getAsJsonObject().get("MACD_Signal").getAsDouble());
		System.out.println(new Gson().toJson(info).toString());
		return info;
	}
	
	public static void main(String[] args){
		//ExternalCall.macdLatest("NSE:INFY", "1min");
		ExternalCall.latestValue("NSE:INFY", "1min");
	}
}
