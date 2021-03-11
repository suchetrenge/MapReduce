package com.kpi8.salarylow;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

public class CustomizedValue implements Writable{
	
	private FloatWritable satisfaction_level = new FloatWritable();
	private IntWritable avg_monthly_hrs = new IntWritable();
	private IntWritable emp_left = new IntWritable();
	
	
	
	public FloatWritable getSatisfaction_level() {
		return satisfaction_level;
	}

	public void setSatisfaction_level(FloatWritable satisfaction_level) {
		this.satisfaction_level = satisfaction_level;
	}

	public IntWritable getAvg_monthly_hrs() {
		return avg_monthly_hrs;
	}

	public void setAvg_monthly_hrs(IntWritable avg_monthly_hrs) {
		this.avg_monthly_hrs = avg_monthly_hrs;
	}

	public IntWritable getEmp_left() {
		return emp_left;
	}

	public void setEmp_left(IntWritable emp_left) {
		this.emp_left = emp_left;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		satisfaction_level.readFields(in);
		avg_monthly_hrs.readFields(in);
		emp_left.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		satisfaction_level.write(out);
		avg_monthly_hrs.write(out);
		emp_left.write(out);
	}

}
