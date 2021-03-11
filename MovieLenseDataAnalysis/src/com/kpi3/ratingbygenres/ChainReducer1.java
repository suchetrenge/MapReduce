package com.kpi3.ratingbygenres;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.SortedMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ChainReducer1 extends Reducer<Text, IntWritable, Text, Text> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int valueSum = 0;
		int count = 0;
		float avg = 5.5f;
		for (IntWritable val : values) {
			valueSum += val.get();
			count++;
		}
		avg = valueSum / count;
		context.write(key, new Text(String.valueOf(avg)));
	}
}
