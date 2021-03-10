package com.kpi2.emp_left;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();
	private IntWritable employee_left_status = new IntWritable();
	int emp_left_temp;

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
//	System.out.println("Values : "+tokens[0].toString() + " and : "+tokens[8].toString());
			emp_left_temp = Integer.parseInt(tokens[6]);
			department.set(tokens[8]);
			employee_left_status.set(emp_left_temp);

			context.write(department, employee_left_status);
		}

	}
}