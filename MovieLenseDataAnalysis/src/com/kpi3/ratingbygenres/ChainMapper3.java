package com.kpi3.ratingbygenres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ChainMapper3 extends Mapper<Text, Text, Text, Text> {

	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split("::");
		if (tokens[1].contains("|")) {
			String temp = tokens[1].replace("|",":");
			String[] genres = temp.split(":");
			for (String data : genres) {
				context.write(new Text(data), value);
			}
		} else {
			context.write(new Text(tokens[1]), value);
		}
	}
//
//	@Override
//	public void cleanup(Context context) throws IOException, InterruptedException {
//
//	}
}