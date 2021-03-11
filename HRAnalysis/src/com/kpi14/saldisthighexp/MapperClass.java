package com.kpi14.saldisthighexp;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, Text> {
	private Text department = new Text();
	private Text saplusexp = new Text();
	
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			department.set(tokens[8]);
			saplusexp.set(tokens[4].toString()+"::"+tokens[9].toString());
		
			context.write(department, saplusexp);
		}

	}
}