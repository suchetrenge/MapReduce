package com.kpi3.avgmonthlyhrs;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, FloatWritable> {
	private Text department = new Text();
	private FloatWritable avg_monthly_hrs_float = new FloatWritable();
	Float avg_monthly_hrs_tmp;

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
//	System.out.println("Values : "+tokens[0].toString() + " and : "+tokens[8].toString());
			avg_monthly_hrs_tmp = Float.parseFloat(tokens[3].toString());
			department.set(tokens[8]);
			avg_monthly_hrs_float.set(avg_monthly_hrs_tmp);

			context.write(department, avg_monthly_hrs_float);
		}

	}
}