package com.kpi13.highlyexpdepwise;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();
	private IntWritable experience = new IntWritable();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			department.set(tokens[8]);
			experience.set(Integer.parseInt(tokens[4].toString()));
		
			context.write(department, experience);
		}

	}
}