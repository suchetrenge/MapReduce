package com.kpi2.mostrated.atleastfourty;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MapperClass extends Mapper<LongWritable, Text, Text, CustimizeValue> {
	String inputFileName;
	IntWritable rating = new IntWritable();

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		FileSplit fileSplit = (FileSplit) context.getInputSplit();
		inputFileName = fileSplit.getPath().getName();
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split("::");
		CustimizeValue joinWritable = null;
		if (inputFileName.contains("ratings")) {
			if (tokens.length == 4) {
				rating.set(Integer.parseInt(tokens[2]));
				joinWritable = new CustimizeValue();
				joinWritable.setView_count(new IntWritable(1));
				joinWritable.setFile_name(new Text(inputFileName));
				joinWritable.setRatings(rating);
				context.write(new Text(tokens[1]), joinWritable);
			}
		} else {
			if (tokens.length == 3) {
				joinWritable = new CustimizeValue();
				joinWritable.setMovie_name(new Text(tokens[1]));
				joinWritable.setFile_name(new Text(inputFileName));
				context.write(new Text(tokens[0]), joinWritable);
			}
		}
	}
}
