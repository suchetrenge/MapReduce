package com.kpi8.salarylow;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, CustomizedValue> {
	private Text department = new Text();
	private FloatWritable satisfaction_level = new FloatWritable();
	private IntWritable avg_monthly_hrs = new IntWritable();
	private IntWritable emp_left = new IntWritable();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			if (tokens[9].toString().contains("low")) {
				department.set(tokens[8]);
				CustomizedValue CustomClass_obj = new CustomizedValue();

				satisfaction_level.set(Float.parseFloat(tokens[0]));
				avg_monthly_hrs.set(Integer.parseInt(tokens[3]));
				emp_left.set(Integer.parseInt(tokens[6]));

				CustomClass_obj.setSatisfaction_level(satisfaction_level);
				CustomClass_obj.setAvg_monthly_hrs(avg_monthly_hrs);
				CustomClass_obj.setEmp_left(emp_left);

				context.write(department, CustomClass_obj);
			}
		}

	}
}