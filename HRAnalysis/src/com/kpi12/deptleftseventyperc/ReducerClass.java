package com.kpi12.deptleftseventyperc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class ReducerClass extends Reducer<Text, IntWritable, Text, FloatWritable> {
	private FloatWritable count = new FloatWritable();
	int total_left;

//	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int valueSum = 0;
		int counter=0;
		float final_per = 5.5f;
		for (IntWritable val : values) {
			counter++;
			valueSum += val.get();
		}
		final_per = (float)valueSum / counter;
		count.set(final_per);
		context.write(key, count);
	}
	

	
}