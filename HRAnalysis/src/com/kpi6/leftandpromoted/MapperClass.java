package com.kpi6.leftandpromoted;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			if (tokens[6].contains("1") && tokens[7].contains("1")) {
				department.set(tokens[8]);

				context.write(department, new IntWritable(1));
			}
		}

	}
}