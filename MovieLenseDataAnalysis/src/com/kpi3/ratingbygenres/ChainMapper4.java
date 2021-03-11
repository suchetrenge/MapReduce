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

public class ChainMapper4 extends Mapper<Text, Text, Text, IntWritable> {

//	Map<String, Map<String, Map<Integer, List<Integer>>>> one_index = new HashMap<String, Map<String, Map<Integer, List<Integer>>>>();
//	Map<Integer, List<Integer>> inner_index = new HashMap<Integer, List<Integer>>();
//	Map<String, Map<Integer, List<Integer>>> inner_index_1 = new HashMap<String, Map<Integer, List<Integer>>>();
IntWritable rating = new IntWritable();
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split("::");
		int age = Integer.parseInt(tokens[4]);
		if (age >= 18) {
			String age_group;
			int profession = Integer.parseInt(tokens[5]);
			int rating_tmp = Integer.parseInt(tokens[7]);
			rating.set(rating_tmp);
			if (age >= 18 && age <= 35) {
				age_group = "18-35";
			} else if (age >= 36 && age <= 50) {
				age_group = "36-50";
			} else {
				age_group = "50+";
			}
//			System.out.println("Age group : "+age_group +" and age : "+age);
			context.write(new Text(key+"::"+age_group+"::"+String.valueOf(profession)), rating);
//			// profession and rating
//			inner_index.put(profession, rating);
//			// age
//			inner_index_1.put(age_group, inner_index);
//			// genres
//			one_index.put(key.toString(), inner_index_1);
		}
	}

//	@Override
//	public void cleanup(Context context) throws IOException, InterruptedException {
//		for(String map1_key: one_index.keySet()) {
//			for(String map2_key: inner_index_1.keySet()) {
//				
//			}
//			
//		}
//
//	}
}
