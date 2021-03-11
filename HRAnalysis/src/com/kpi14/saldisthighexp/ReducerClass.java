package com.kpi14.saldisthighexp;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, Text, Text, Text> {
	private Text count = new Text();

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		// gurukul [1 1 1 1 1 1....]
		int max_num = 0;
		String sal = null;
		int temp_exp;
		for (Text val : values) {
			String[] tokens = val.toString().split("::");
			temp_exp = Integer.parseInt(tokens[0]);
			if (temp_exp > max_num) {
				max_num = temp_exp;
				sal = tokens[1];
			}
		}
		count.set(String.valueOf(max_num)+"::"+sal.toString());
		context.write(key, count);
	}
}