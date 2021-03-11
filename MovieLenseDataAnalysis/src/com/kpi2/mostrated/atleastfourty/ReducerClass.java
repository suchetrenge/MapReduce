package com.kpi2.mostrated.atleastfourty;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, CustimizeValue, Text, FloatWritable> {

	private HashMap tmap2 = new HashMap<>();

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		tmap2 = new HashMap<String, Float>();
	}

	public void reduce(Text key, Iterable<CustimizeValue> values, Context context)
			throws IOException, InterruptedException {
		String name = "";

		Integer count = 0;
		Integer rating = 0;
		float avg_rating = 5.5f;
		for (CustimizeValue mow : values) {
			if (mow.getFile_name().toString().contains("ratings")) {

				count = count + Integer.parseInt(mow.getView_count().toString());
				rating = rating + Integer.parseInt(mow.getRatings().toString());
				
			} else {

				name = mow.getMovie_name().toString();
			}

		}

		if (count >= 40) {
			avg_rating = rating.floatValue()/count.floatValue();
			tmap2.put(name, avg_rating);
		}

//		context.write(new Text(key + " :: " + name + " :: "+rating+" value is :"), new LongWritable(count));
	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {

		Map<String, Float> sortedMap = sortByValues(tmap2);
		int size = sortedMap.keySet().size();
		int top_entries = 20;
		int counter = 0;
		for (String key : sortedMap.keySet()) {
			if (counter++ >= size - top_entries) {
            	context.write(new Text(key), new FloatWritable(sortedMap.get(key)));
			}

		}
	}

	private static HashMap sortByValues(HashMap map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
}
