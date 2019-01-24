package com.stock.anaysis.common;

public class CommonConstants {
	
	public static final String API_KEY_1 = "ZK58MA9LJ3W2OJKA";
	public static final String API_KEY_2 = "VP5UMYZGXUTAFLPP";
	public static final int TIME_OUT = 6000;
	
	public static final String PARAMETER_CLOSE = "close";
	public static final String PARAMETER_OPEN = "open";
	public static final String PARAMETER_HIGH = "high";
	public static final String PARAMETER_LOW = "low";
	public static final String PARAMETER_VOLUMN = "volumn";
	public static final String PARAMETER_TIME = "time";
	public static final String INDIAN_TIME = "indian time";
	
	public static final int HOUR_BUFFER = 10;
	public static final int MINUTE_BUFFER = 30;
	
	public static final String baseUrl = "https://www.alphavantage.co/query?";
	public static final String symbolUrl = "&symbol=";
	public static final String functionUrl = "&function=";
	public static final String intervalUrl ="&interval=";
	public static final String outputSiceUrl = "&outputsize=";
	public static final String keyUrl = "&apikey=";
	
	public static final String BUY_SIGNAL = "buy";
	public static final String SELL_SIGNAL = "sell";
	
	public static final String MACD_INDICATOR = "MACD";
	public static final String RSI_INDICATOR = "RSI";
}
