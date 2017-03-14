package com.topic.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class FileOperation {
	
	private String filepath = "F:/workspace/Topic/src/dbconfig";
//	private String filepath = ".\\src\\dbconfig";
	   
	public Map<String, String> getConfigData(String dbtype) throws FileNotFoundException {
		
		Map<String, String> map = new HashMap<String, String>();
		File file = new File(filepath);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString = new String();
			try {
				while ((tempString = reader.readLine()) != null) {
					int flay = tempString.indexOf(dbtype);
					if (flay > 0) {
						while ((tempString = reader.readLine()) != null) {
							if (tempString.equals("[end]")) {
								return map;
							}
							int point = tempString.indexOf(":");
							String key = tempString.substring(0, point);
							String value = tempString.substring(point + 1,
									tempString.length());
							map.put(key, value);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		FileOperation fileOp = new FileOperation();
		try {
			map = fileOp.getConfigData("mysql");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
	}
}
