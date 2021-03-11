package com.kpi6.leftandpromoted;

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
		int sum = 0;
		for (IntWritable val : values) {;
			sum += val.get();
		}
		count.set(sum);
		context.write(key, count);
	}
}