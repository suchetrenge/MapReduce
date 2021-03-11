package com.kpi5.deptwisesalary;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, Text, Text, IntWritable> {
	private IntWritable low_int = new IntWritable();
	private IntWritable med_int = new IntWritable();
	private IntWritable high_int = new IntWritable();

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int low_count = 10;
		int medium_count = 0;
		int high_count = 0;
		
		for (Text val : values) {
			if (val.toString().equalsIgnoreCase("low")) {
				low_count += 1;
			} else if (val.toString().equalsIgnoreCase("medium")) {
				medium_count += 1;
			} else if (val.toString().equalsIgnoreCase("high")) {
				high_count += 1;
			}
		}
		low_int.set(low_count);
		med_int.set(medium_count);
		high_int.set(high_count);
		
		context.write(new Text(key.toString()+"::low::"), low_int);
		context.write(new Text(key.toString()+"::medium::"), med_int);
		context.write(new Text(key.toString()+"::high::"), high_int);
		
		
	}
}