package com.kpi1avg_satisfactionlevel;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, FloatWritable> {
	private Text department = new Text();
	private FloatWritable avg_satisfaction = new FloatWritable();
	Float avg_sat_float;

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
//	System.out.println("Values : "+tokens[0].toString() + " and : "+tokens[8].toString());
			avg_sat_float = Float.parseFloat(tokens[0].toString());
			department.set(tokens[8]);
			avg_satisfaction.set(avg_sat_float);

			context.write(department, avg_satisfaction);
		}

	}
}