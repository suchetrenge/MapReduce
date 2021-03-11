package com.kpi10.perempleftsal;

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
		int low_sal_count = 0;
		int med_sal_count = 0;
		int high_sal_count = 0;
		float low_left_count = 5.5f;
		float med_left_count = 5.5f;
		float high_left_count = 5.5f;
		
		float sat_level = 5.5f;
		float avg_sat_level = 5.5f;
		
		float avg_eval = 5.5f;
		float avg_eval_final = 5.5f;
		
		int left_count = 0;
		
		String avg_sat_level_str;
		String avg_hrs_str;
		String left_count_str;
		String left_count_low_str;
		String left_count_med_str;
		String left_count_high_str;
		
		for (CustomizedValue val : values) {
			counter++;
			sat_level = sat_level + Float.parseFloat(val.getSatisfaction_level().toString());
			avg_eval = avg_eval + Float.parseFloat(val.getlast_eval().toString());
			left_count = left_count + Integer.parseInt(val.getEmp_left().toString());	
			if(val.getSalary().toString().contains("low")) {
				low_sal_count+=1;
			}else if(val.getSalary().toString().contains("medium")) {
				med_sal_count+=1;
			}else if(val.getSalary().toString().contains("high")) {
				high_sal_count+=1;
			}
			
			
		}
		
		avg_sat_level = sat_level / counter;
		avg_eval_final = avg_eval / counter;
		
		avg_sat_level_str = String.valueOf(avg_sat_level);
		avg_hrs_str = String.valueOf(avg_eval_final);
		left_count_str = String.valueOf(left_count);
		
		low_left_count = (float)low_sal_count/ counter;
		med_left_count = (float)med_sal_count/ counter;
		high_left_count = (float)high_sal_count/ counter;
		left_count_low_str = String.valueOf(low_left_count);
		left_count_med_str = String.valueOf(med_left_count);
		left_count_high_str = String.valueOf(high_left_count);
		
		
		
		final_val.set(avg_sat_level_str+"::"+avg_hrs_str+":low_salary_left:"+left_count_low_str+":med_salary_left:"+left_count_med_str+":high_salary_left:"+left_count_high_str);
		
		context.write(key, final_val);
	}
}