package com.stock.analysis.utils;

public class ConfigurationManger {

	public static String getConfigurationFolderPath(){
		String projectHome = System.getenv("stockAnalysisProps");
		System.out.println(projectHome);
		return null;
	}
}
