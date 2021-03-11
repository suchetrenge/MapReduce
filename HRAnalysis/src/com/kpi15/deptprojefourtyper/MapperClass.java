package com.kpi15.deptprojefourtyper;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text department = new Text();
	private IntWritable projects = new IntWritable();
	private IntWritable total_projects = new IntWritable();
	int total_pro_temp=0;
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			department.set(tokens[8]);
			projects.set(Integer.parseInt(tokens[2]));
			total_pro_temp+=Integer.parseInt(tokens[2]);
			context.write(department, projects);
		}	
	}
	@Override
	public void cleanup(Context context) throws IOException, InterruptedException {
		total_projects.set(total_pro_temp);
		context.write(new Text("total"), total_projects);
	}
}