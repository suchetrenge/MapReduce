package com.kpi8.salarylow;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, CustomizedValue, Text, Text> {
	private FloatWritable count = new FloatWritable();
	private Text final_val = new Text();
	
	public void reduce(Text key, Iterable<CustomizedValue> values, Context context)
			throws IOException, InterruptedException {
		int counter = 0;
		float sat_level = 5.5f;
		float avg_sat_level = 5.5f;
		
		float avg_hrs = 5.5f;
		float avg_hrs_final = 5.5f;
		
		int left_count = 0;
		
		String avg_sat_level_str;
		String avg_hrs_str;
		String left_count_str;
		
		
		for (CustomizedValue val : values) {
			counter++;
			sat_level = sat_level + Float.parseFloat(val.getSatisfaction_level().toString());
			avg_hrs = avg_hrs + Float.parseFloat(val.getAvg_monthly_hrs().toString());
			left_count = left_count + Integer.parseInt(val.getEmp_left().toString());	
		}
		
		avg_sat_level = sat_level / counter;
		avg_hrs_final = avg_hrs / counter;
		
		avg_sat_level_str = String.valueOf(avg_sat_level);
		avg_hrs_str = String.valueOf(avg_hrs_final);
		left_count_str = String.valueOf(left_count);
		
		final_val.set(avg_sat_level_str+"::"+avg_hrs_str+"::"+left_count_str);
		
		context.write(key, final_val);
	}
}