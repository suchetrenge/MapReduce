package com.kpi3.ratingbygenres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ChainMapper5 extends Mapper<Text, IntWritable, Text, Text> {

	HashMap<String, ArrayList<Integer>> items = new HashMap<String, ArrayList<Integer>>();
	Integer val_integer;
	int val_int;
	String key_str;

	public void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
		val_int = value.get();
		val_integer = new Integer(val_int);
		key_str = key.toString();
		addToList(key_str, val_integer);
	}

	public synchronized void addToList(String mapKey, Integer myItem) {
		List<Integer> itemsList = items.get(mapKey);

		// if list does not exist create it
		if (itemsList == null) {
			itemsList = new ArrayList<Integer>();
			itemsList.add(myItem);
			items.put(mapKey, (ArrayList<Integer>) itemsList);
		} else {
			// add if item is not already in list
			itemsList.add(myItem);
			items.put(mapKey, (ArrayList<Integer>) itemsList);
		}

	}

	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		
		for (String key : items.keySet()) {
			List<Integer> itemsList = items.get(key);
			int count;
			float avg = 5.5f;
			int sum = 0;
			int intval;
			String str_val;
			Text value = new Text();
			Text key_set = new Text();
			int size = itemsList.size();
			
			
			if(size == 1) {
				intval = itemsList.get(0).intValue();
				str_val = String.valueOf(intval);
				value.set(str_val);
				key_set.set(key);
				context.write(key_set, value);
				
			}else {
				
				for(int i=0;i<size;i++) {
					sum = sum + itemsList.get(i).intValue();
				}
				avg = (float) sum/size;
				str_val = String.valueOf(avg);
				key_set.set(key);
				value.set(str_val);
				context.write(key_set, value);
			}
		}

	}
}
