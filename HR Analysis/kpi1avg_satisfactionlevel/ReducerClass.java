package com.kpi1avg_satisfactionlevel;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable count = new FloatWritable();

	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {
		// gurukul [1 1 1 1 1 1....]
		int counter = 0;
		float valueSum = 0;
		float avg_sum = 5.5f;
		for (FloatWritable val : values) {
			counter++;
			valueSum += val.get();
		}
		avg_sum = valueSum / counter;
		count.set(avg_sum);
		context.write(key, count);
	}
}