package com.kpi11.empleftexperience;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text experience = new Text();
	private IntWritable avg_satisfaction = new IntWritable();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			String exp_temp;
			if (tokens[6].toString().contains("1")) {
				exp_temp = "experience : "+tokens[4].toString();
				experience.set(exp_temp);
				context.write(experience, new IntWritable(1));
			}
		}

	}
}