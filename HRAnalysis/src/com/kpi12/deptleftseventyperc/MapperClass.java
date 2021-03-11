package com.kpi12.deptleftseventyperc;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();
	private IntWritable left_status = new IntWritable();
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			String exp_temp;

				department.set(tokens[8]);
				left_status.set(Integer.parseInt(tokens[6].toString()));
				
				context.write(department, left_status);
		}

	}
}