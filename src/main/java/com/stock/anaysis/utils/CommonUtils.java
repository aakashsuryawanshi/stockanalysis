package com.stock.anaysis.utils;

import java.util.List;
import java.util.logging.Logger;

import org.patriques.input.technicalindicators.Interval;
import org.patriques.output.timeseries.data.StockData;

import com.stock.anaysis.common.CommonConstants;

public class CommonUtils {
	static Logger logger = Logger.getLogger("CommonUtils");
	
	public static void printStockData(StockData data){
		logger.info("Time: " + data.getDateTime().toString());
		logger.info("Open: " + data.getOpen());
		logger.info("Close: " + data.getClose());
		logger.info("High: " + data.getHigh());
		logger.info("Low: " + data.getLow());
		logger.info("Volumn: " + data.getVolume());
		logger.info("");
	}
	
	public static void printStockData(List<StockData> data){
		for(StockData stock : data){
			printStockData(stock);
		}
	}
	
	public static void printStockParameter(StockData data, String parameter){
		switch(parameter){
		case CommonConstants.PARAMETER_CLOSE:
			logger.info("Close: " + data.getClose());
			break;
		case CommonConstants.PARAMETER_OPEN:
			logger.info("Open: " + data.getOpen());
			break;
		case CommonConstants.PARAMETER_LOW:
			logger.info("Low: " + data.getLow());
			break;
		case CommonConstants.PARAMETER_HIGH:
			logger.info("High: " + data.getHigh());
			break;
		case CommonConstants.PARAMETER_VOLUMN:
			logger.info("Volumn: " + data.getVolume());
			break;
		case CommonConstants.PARAMETER_TIME:
			logger.info("Time: " + data.getDateTime().toString());
			break;
		case CommonConstants.INDIAN_TIME:
			logger.info("Time: " + TimeUtils.convertToIndianTime(data.getDateTime()));
			break;
		default:
			logger.info("Parameter not found");
			break;
		}
	}
	
	public static void printStockParameter(List<StockData> data, String parameter){
		for(StockData stock : data){
			printStockParameter(stock, parameter);
		}
	}
	
	public static Interval getInterval(int interval){
		switch(interval){
		case 1:
			return Interval.ONE_MIN;
		case 5:
			return Interval.FIVE_MIN;
		case 15:
			return  Interval.FIFTEEN_MIN;
		case 30:
			return Interval.THIRTY_MIN;
		case 60:
			return Interval.SIXTY_MIN;
		default:
			logger.info("Interval not available for number: " + interval);
			return null;
		}
	}
	
	public static org.patriques.input.timeseries.Interval getTimeSeriesInterval(int interval){
		switch(interval){
		case 1:
			return org.patriques.input.timeseries.Interval.ONE_MIN;
		case 5:
			return org.patriques.input.timeseries.Interval.FIVE_MIN;
		case 15:
			return  org.patriques.input.timeseries.Interval.FIFTEEN_MIN;
		case 30:
			return org.patriques.input.timeseries.Interval.THIRTY_MIN;
		case 60:
			return org.patriques.input.timeseries.Interval.SIXTY_MIN;
		default:
			logger.info("Interval not available for number: " + interval);
			return null;
		}
	}
}
