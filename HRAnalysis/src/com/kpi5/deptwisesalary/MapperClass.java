package com.kpi5.deptwisesalary;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, Text> {
	private Text department = new Text();
	private Text salary = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
//	System.out.println("Values : "+tokens[0].toString() + " and : "+tokens[8].toString());
			department.set(tokens[8]);
			salary.set(tokens[9]);

			context.write(department, salary);
		}

	}
}