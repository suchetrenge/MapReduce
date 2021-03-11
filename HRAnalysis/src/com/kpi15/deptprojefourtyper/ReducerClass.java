package com.kpi15.deptprojefourtyper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerClass extends Reducer<Text, IntWritable, Text, FloatWritable> {
	private IntWritable count = new IntWritable();
	
	private HashMap tmap2 = new HashMap<>();

	@Override
	public void setup(Context context) throws IOException, InterruptedException {
		tmap2 = new HashMap<String, Integer>();
	}
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		// gurukul [1 1 1 1 1 1....]
		int valueSum = 0;
		for (IntWritable val : values) {
			valueSum += val.get();
		}
		
		tmap2.put(key.toString(), valueSum);
		
//		context.write(key, count);
	}
	
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {

		Map<String, Integer> sortedMap = (tmap2);
		int total_projects = sortedMap.get("total");
//		System.out.println("Total Projects: "+total_projects);
		sortedMap.remove("total");
		for (String key : sortedMap.keySet()) {
			float dep_val = sortedMap.get(key).floatValue();
			float final_val = dep_val / total_projects;
//			System.out.println("dept : "+key+" and val : "+dep_val);
//			if (final_val >= 0.40) {
            	context.write(new Text(key), new FloatWritable(final_val));
//			}

		}
	}
	
}