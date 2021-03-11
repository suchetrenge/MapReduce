package com.kpi10.perempleftsal;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;

public class MapperClass extends Mapper<LongWritable, Text, Text, CustomizedValue> {
	private Text department = new Text();
	private FloatWritable satisfaction_level = new FloatWritable();
	private FloatWritable last_eval = new FloatWritable();
	private IntWritable emp_left = new IntWritable();
	private Text salary = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		if (key.get() != 0) {
			String[] tokens = value.toString().split(","); // Dividing String into tokens
			if (tokens[6].toString().contains("1")) {
				department.set(tokens[8]);
				CustomizedValue CustomClass_obj = new CustomizedValue();

				satisfaction_level.set(Float.parseFloat(tokens[0]));
				last_eval.set(Float.parseFloat(tokens[1]));
				emp_left.set(Integer.parseInt(tokens[6]));
				salary.set(tokens[9]);
				
				CustomClass_obj.setSatisfaction_level(satisfaction_level);
				CustomClass_obj.setlast_eval(last_eval);
				CustomClass_obj.setEmp_left(emp_left);
				CustomClass_obj.setSalary(salary);
				
				
				context.write(department, CustomClass_obj);
			}
		}

	}
}