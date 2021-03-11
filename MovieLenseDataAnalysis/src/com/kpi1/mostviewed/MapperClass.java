package com.kpi1.mostviewed;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MapperClass extends Mapper<LongWritable, Text, Text, CustimizeValue> {
	String inputFileName;

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
//				System.out.println("IN if :" + tokens[1] + " and :" + inputFileName);
				joinWritable = new CustimizeValue();
				joinWritable.setView_count(new IntWritable(1));
				joinWritable.setFile_name(new Text(inputFileName));
				context.write(new Text(tokens[1]), joinWritable);
			}
		} else {
			if (tokens.length == 3) {
//				System.out.println("IN else :" + tokens[0] + " and :" + inputFileName);
				joinWritable = new CustimizeValue();
				joinWritable.setMovie_name(new Text(tokens[1]));
				joinWritable.setFile_name(new Text(inputFileName));
				context.write(new Text(tokens[0]), joinWritable);
			}
		}
	}
}
