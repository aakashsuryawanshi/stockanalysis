package com.stock.analysis.indicators;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

import com.stock.analysis.utils.CommonUtils;
import com.stock.analysis.utils.TimeUtils;
import com.stock.anaysis.common.CommonConstants;

public class TimeSeriesExample {

	static Logger logger = Logger.getLogger("TimeSeriesExample");

	public static List<StockData> execute(String stockName, int interval) {
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(CommonConstants.API_KEY_2,
				CommonConstants.TIME_OUT);
		TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

		try {
			IntraDay response = stockTimeSeries.intraDay(stockName, CommonUtils.getTimeSeriesInterval(interval),
					OutputSize.FULL);
			Map<String, String> metaData = response.getMetaData();
			logger.info("Information: " + metaData.get("1. Information"));
			logger.info("Stock: " + metaData.get("2. Symbol"));

			List<StockData> stockData = response.getStockData();
			return stockData;
		} catch (AlphaVantageException e) {
			e.printStackTrace();
			System.out.println("something went wrong");
		}
		return null;
	}

	public static void main(String[] args) {
		List<StockData> stockData = TimeSeriesExample.execute("INFY.NS", 1);
		for(StockData data : stockData){
			System.out.println(data.getDateTime() + " " + TimeUtils.convertToIndianTime(data.getDateTime()) + " " + data.getClose());
		}
		//MACDIndicator macd = new MACDIndicator();
		//macd.executeForHistory("INFY.NS", 1, 0, 0);
		//macd.execute("INFY.NS", 15, 0, 0);
	}
}
