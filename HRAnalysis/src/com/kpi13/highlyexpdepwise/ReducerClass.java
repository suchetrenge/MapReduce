package com.kpi13.highlyexpdepwise;

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
		int max_num = 0;
		for (IntWritable val : values) {
			if (val.get() > max_num) {
				max_num = val.get();
			}
		}
		count.set(max_num);
		context.write(key, count);
	}
}