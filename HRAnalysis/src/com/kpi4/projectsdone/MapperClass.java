package com.kpi4.projectsdone;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();
	private IntWritable no_of_projects_completed = new IntWritable();
	int no_of_projects_temp;

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
//	System.out.println("Values : "+tokens[0].toString() + " and : "+tokens[8].toString());
			no_of_projects_temp = Integer.parseInt(tokens[2]);
			department.set(tokens[8]);
			no_of_projects_completed.set(no_of_projects_temp);

			context.write(department, no_of_projects_completed);
		}

	}
}