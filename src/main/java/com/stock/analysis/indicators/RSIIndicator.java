package com.stock.analysis.indicators;

import java.util.ArrayList;
import java.util.List;

import org.patriques.AlphaVantageConnector;
import org.patriques.TechnicalIndicators;
import org.patriques.input.technicalindicators.SeriesType;
import org.patriques.input.technicalindicators.TimePeriod;
import org.patriques.output.technicalindicators.RSI;
import org.patriques.output.technicalindicators.data.IndicatorData;

import com.stock.analysis.utils.CommonUtils;
import com.stock.analysis.utils.TimeUtils;
import com.stock.anaysis.common.CommonConstants;

public class RSIIndicator {

	public static List<IndicatorData> execute(String stockName, int interval, int timePeriod) {
		if (timePeriod == 0) {
			timePeriod = 14;
		}
		AlphaVantageConnector apiConnector = new AlphaVantageConnector(CommonConstants.API_KEY,
				CommonConstants.TIME_OUT);
		TechnicalIndicators technicalIndicators = new TechnicalIndicators(apiConnector);
		try {
			RSI rsi = technicalIndicators.rsi(stockName, CommonUtils.getInterval(interval), TimePeriod.of(timePeriod),
					SeriesType.CLOSE);
			List<IndicatorData> data = rsi.getData();
			/*
			 * for(IndicatorData d : data){ System.out.println(d.getDateTime() +
			 * " " + d.getData()); }
			 */
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IndicatorData>();
	}

	public static List<Result> getRsiSignals(String symbol, List<IndicatorData> data, int overbought, int oversold) {
		List<Result> results = new ArrayList<Result>();
		for (IndicatorData d : data) {
			if (d.getData() < oversold) {
				Result r = new Result(symbol, CommonConstants.RSI_INDICATOR, Double.toString(d.getData()),
						TimeUtils.convertToIndianTime(d.getDateTime()), CommonConstants.BUY_SIGNAL);
				results.add(r);
			}
			else if(d.getData() > overbought){
				Result r = new Result(symbol, CommonConstants.RSI_INDICATOR, Double.toString(d.getData()),
						TimeUtils.convertToIndianTime(d.getDateTime()), CommonConstants.SELL_SIGNAL);
				results.add(r);
			}
		}
		return results;
	}
}
