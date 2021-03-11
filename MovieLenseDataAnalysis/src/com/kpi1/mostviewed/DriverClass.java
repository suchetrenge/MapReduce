package com.kpi1.mostviewed;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class DriverClass
{
	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
//		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//		if (otherArgs.length != 3)
//		{
//			System.err.println("Usage: Join <in-1> <in-2> <out>");
//			System.exit(2);
//		}
		Job job = new Job(conf, "Movie Lense");
		job.setJarByClass(DriverClass.class);
		job.setMapperClass(MapperClass.class);
		job.setReducerClass(ReducerClass.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CustimizeValue.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		FileInputFormat.addInputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/Movies-Data/movies.dat"));
		FileInputFormat.addInputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/Movies-Data/ratings.dat"));
		FileOutputFormat.setOutputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/Movies-Data_output"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}