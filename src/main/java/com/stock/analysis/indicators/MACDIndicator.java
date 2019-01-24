package com.stock.analysis.indicators;

import java.util.List;
import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TechnicalIndicators;
import org.patriques.input.technicalindicators.FastPeriod;
import org.patriques.input.technicalindicators.SeriesType;
import org.patriques.input.technicalindicators.SignalPeriod;
import org.patriques.input.technicalindicators.SlowPeriod;
import org.patriques.input.technicalindicators.TimePeriod;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.technicalindicators.MACD;
import org.patriques.output.technicalindicators.data.MACDData;
import org.patriques.output.timeseries.data.StockData;

import com.stock.analysis.entities.MACDInfo;
import com.stock.analysis.utils.AudioUtil;
import com.stock.analysis.utils.CommonUtils;
import com.stock.analysis.utils.TimeUtils;
import com.stock.anaysis.common.CommonConstants;
import com.stock.anaysis.common.ExternalCall;

public class MACDIndicator {

	double boughtValue = 0.0d;

	public void execute(String stockName, int interval, int slow, int fast) {
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(CommonConstants.API_KEY,
				CommonConstants.TIME_OUT);
		TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
		List<StockData> stockData = TimeSeriesExample.execute(stockName, interval);
		Result result = new Result();
		result.setIndicator("MACD");
		result.setSymbol(stockName);
		result.setStockName(CommonUtils.getCompanyBySymbol(stockName.substring(0, stockName.lastIndexOf("."))).getName());
		result.setStockValue(stockData.get(0).getClose());
		try {
			MACD response = technicalIndicators.macd(stockName, CommonUtils.getInterval(interval), TimePeriod.of(10),
					SeriesType.CLOSE, FastPeriod.of(12), SlowPeriod.of(26), SignalPeriod.of(9));
			List<MACDData> data = response.getData();
			if (data.get(0).getHist() > 0.1) {
				System.out.println(stockName + " " + TimeUtils.convertToIndianTime(stockData.get(0).getDateTime())
						+ " Buy at price: " + stockData.get(0).getClose());
				AudioUtil.notifyBuySignal();
				boughtValue = stockData.get(0).getClose();
			} else if (data.get(0).getHist() < 0 && boughtValue < stockData.get(0).getClose()) {
				System.out.println(stockName + " " + TimeUtils.convertToIndianTime(stockData.get(0).getDateTime())
						+ " Sell at price: " + stockData.get(0).getClose());
				boughtValue = 0.0d;
				AudioUtil.notifySellSignal();
			}
		} catch (AlphaVantageException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
	}

	public Result getMACDHist(String stockName, int interval) {
		try {
			AlphaVantageConnector apiConnector = new AlphaVantageConnector(CommonConstants.API_KEY,
					CommonConstants.TIME_OUT);
			TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
			List<StockData> stockData = TimeSeriesExample.execute(stockName, interval);
			Result result = new Result();
			result.setIndicator("MACD");
			result.setSymbol(stockName);
			result.setStockName(CommonUtils.getCompanyBySymbol(stockName.substring(0, stockName.lastIndexOf("."))).getName());
			if(stockData != null && stockData.size() > 0){
				result.setTime(TimeUtils.convertToIndianTime(stockData.get(0).getDateTime()));
				result.setStockValue(stockData.get(0).getClose());
			}
			else{
				System.out.println("Stock Data is Empty at MACDIndicator line no. 73");
			}
			MACD response = null;
			if(CommonUtils.getInterval(interval) != null && technicalIndicators != null && stockName != null){
				response = technicalIndicators.macd(stockName, CommonUtils.getInterval(interval), TimePeriod.of(10),
						SeriesType.CLOSE, FastPeriod.of(12), SlowPeriod.of(26), SignalPeriod.of(9));
			}
			else{
				System.out.println("Time Interval is null at MACDIndicator line no 81");
			}
			List<MACDData> data = null;
			if(response != null){
				data = response.getData();
			}
			if (data != null && data.size()> 0 && data.get(0).getHist() > 0.0) {
				result.setValue(data.get(0).getHist());
				result.setSignal("buy");
			} else if (data != null && data.size() > 0 && data.get(0).getHist() <= 0.0) {
				result.setValue(data.get(0).getHist());
				result.setSignal("sell");
			}
			else if(data == null || data.size() == 0){
				System.out.println("MACD Data is empty at line MACDIndicator line no 86");
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void executeForHistory(String stockName, int interval, int slow, int fast) {
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(CommonConstants.API_KEY,
				CommonConstants.TIME_OUT);
		TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
		List<StockData> stockData = TimeSeriesExample.execute(stockName, interval);
		try {
			MACD response = technicalIndicators.macd(stockName, CommonUtils.getInterval(interval), TimePeriod.of(10),
					SeriesType.CLOSE, FastPeriod.of(12), SlowPeriod.of(26), SignalPeriod.of(9));
			List<MACDData> data = response.getData();
			boolean buyFlag = false;
			double total = 0;
			double boughtAt = 0;
			double size = (stockData.size() < data.size()) ? stockData.size() : data.size();
			for (int i = ((int) size - 1); i > -1; i--) {
				System.out.println(TimeUtils.convertToIndianTime(stockData.get(i).getDateTime()) + " "
						+ data.get(i).getHist() + " " + stockData.get(i).getClose());
				/*
				 * if (data.get(i).getHist() > 0.1 && !buyFlag) {
				 * System.out.println(stockName + " " +
				 * TimeUtils.convertToIndianTime(stockData.get(i).getDateTime())
				 * + " Buy at price: " + stockData.get(i).getClose()); boughtAt
				 * = stockData.get(i).getClose(); buyFlag = true; } else if
				 * (data.get(i).getHist() < 0 && buyFlag && boughtAt <
				 * stockData.get(i).getClose()) { System.out.println(stockName +
				 * " " +
				 * TimeUtils.convertToIndianTime(stockData.get(i).getDateTime())
				 * + " Sell at price: " + stockData.get(i).getClose()); total +=
				 * (stockData.get(i).getClose() - boughtAt); boughtAt = 0.0d;
				 * buyFlag = false; }
				 */
			}
			System.out.println("Total: " + total);

		} catch (AlphaVantageException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
	}
	
	public Result getMACDHistCustom(String stockName, int interval) {
		try {
			Result result = new Result();
			result.setIndicator("MACD");
			result.setSymbol(stockName);
			//result.setStockName(CommonUtils.getCompanyBySymbol(stockName).getName());
			result.setStockName(CommonUtils.getCompanyBySymbol(stockName.substring(0, stockName.lastIndexOf("."))).getName());
			com.stock.analysis.entities.StockData stockData = ExternalCall.latestValue(stockName, interval + "min");
			if(stockData != null){
				result.setTime(TimeUtils.convertToIndianTime(stockData.getTimestamp()));
				result.setStockValue(stockData.getClose());
			}
			else{
				System.out.println("Stock Data is Empty at MACDIndicator line no. 73");
			}
			MACDInfo data = ExternalCall.macdLatest(stockName, interval + "min");
			if (data != null && data.getHist() > 0.01) {
				result.setValue(data.getHist());
				result.setSignal("buy");
			} else if (data != null && data.getHist() <= 0.01) {
				result.setValue(data.getHist());
				result.setSignal("sell");
			}
			else if(data == null){
				System.out.println("MACD Data is empty at line MACDIndicator line no 86");
			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
