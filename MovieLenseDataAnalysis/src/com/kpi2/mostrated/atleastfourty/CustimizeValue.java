package com.kpi2.mostrated.atleastfourty;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;


public class CustimizeValue implements Writable {
	private IntWritable view_count;
	private Text movie_name;
	private Text file_name;
	private IntWritable ratings;
	
	public CustimizeValue() {
		// TODO Auto-generated constructor stub
		view_count = new IntWritable(0);
		movie_name = new Text("");
		file_name = new Text("");
		ratings = new IntWritable(0);
	}
	
	public CustimizeValue(int view_count, String file_name,int ratings) {
		this.view_count = new IntWritable(view_count);
		this.file_name = new Text(file_name);
		this.ratings = new IntWritable(ratings);
	}
	
	public CustimizeValue(String movie_name, String file_name) {
		this.movie_name = new Text(movie_name);
		this.file_name = new Text(file_name);
	}
	
	
	
	public IntWritable getView_count() {
		return view_count;
	}

	public Text getMovie_name() {
		return movie_name;
	}

	public Text getFile_name() {
		return file_name;
	}

	
	
	public IntWritable getRatings() {
		return ratings;
	}

	public void setRatings(IntWritable ratings) {
		this.ratings = ratings;
	}

	public void setView_count(IntWritable view_count) {
		this.view_count = view_count;
	}

	public void setMovie_name(Text movie_name) {
		this.movie_name = movie_name;
	}

	public void setFile_name(Text file_name) {
		this.file_name = file_name;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
			view_count.readFields(in);
			movie_name.readFields(in);
			file_name.readFields(in);
			ratings.readFields(in);
		
	}
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		view_count.write(out);
		movie_name.write(out);
		file_name.write(out);
		ratings.write(out);
	}
	
	
	
}