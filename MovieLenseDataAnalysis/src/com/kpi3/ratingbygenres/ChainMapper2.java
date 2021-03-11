package com.kpi3.ratingbygenres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ChainMapper2 extends Mapper<Text, Text, Text, Text> {
//	private HashMap tmap2 = new HashMap<>();
	Map<String, List<String>> multiMap = new HashMap<>();
	private HashMap tmap3 = new HashMap<>();

	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		if (key.toString().contains("movieid")) {
			tmap3.put(key.toString().split("_")[1], value.toString());
		} else {
//			tmap2.put(value.toString().split("::")[3], key.toString() + "::" + value.toString());
//			List<String> values1 = new ArrayList<String>();
//			values1.add(key.toString() + "::" + value.toString());
//			multiMap.put(value.toString().split("::")[3], values1);
			
			addToList(value.toString().split("::")[3],key.toString() + "::" + value.toString());

		}
	}
	
	public synchronized void addToList(String mapKey, String myItem) {
		List<String> itemsList = multiMap.get(mapKey);

		// if list does not exist create it
		if (itemsList == null) {
			itemsList = new ArrayList<String>();
			itemsList.add(myItem);
			multiMap.put(mapKey, itemsList);
		} else {
			// add if item is not already in list
			itemsList.add(myItem);
			multiMap.put(mapKey, itemsList);
		}

	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
//		Map<String, Float> sortedMap2 = tmap2;
		Map<String, String> sortedMap3 = tmap3;
		// val is movieid
		for (String val : sortedMap3.keySet()) {
			List<String> getval = multiMap.get(val);
			
			if (getval != null) {
				for (String demo : getval) {
					context.write(new Text(val), new Text(sortedMap3.get(val) + "::" + demo));
				}
			}
		}
	}
}
