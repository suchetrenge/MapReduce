package com.kpi10.perempleftsal;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class CustomizedValue implements Writable{
	
	private FloatWritable satisfaction_level = new FloatWritable();
	private FloatWritable last_eval = new FloatWritable();
	private IntWritable emp_left = new IntWritable();
	private Text salary = new Text();
	
	
	public FloatWritable getSatisfaction_level() {
		return satisfaction_level;
	}

	public void setSatisfaction_level(FloatWritable satisfaction_level) {
		this.satisfaction_level = satisfaction_level;
	}

	public FloatWritable getlast_eval() {
		return last_eval;
	}

	public void setlast_eval(FloatWritable last_eval) {
		this.last_eval = last_eval;
	}

	public IntWritable getEmp_left() {
		return emp_left;
	}

	public void setEmp_left(IntWritable emp_left) {
		this.emp_left = emp_left;
	}

	public Text getSalary() {
		return salary;
	}

	public void setSalary(Text salary) {
		this.salary = salary;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		satisfaction_level.readFields(in);
		last_eval.readFields(in);
		emp_left.readFields(in);
		salary.readFields(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		satisfaction_level.write(out);
		last_eval.write(out);
		emp_left.write(out);
		salary.write(out);
	}

}
