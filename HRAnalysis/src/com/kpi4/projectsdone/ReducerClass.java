package com.kpi4.projectsdone;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable count = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		// gurukul [1 1 1 1 1 1....]
		int valueSum = 0;
		for (IntWritable val : values) {
			valueSum += val.get();
		}
		System.out.println("key : "+key +" and value : "+valueSum);
		count.set(valueSum);
		context.write(key, count);
	}
}