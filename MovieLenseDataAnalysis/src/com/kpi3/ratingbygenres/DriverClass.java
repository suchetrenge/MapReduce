package com.kpi3.ratingbygenres;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.chain.*;

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
//		Job job = Job.getInstance(conf, "Movie Lense");

//		job.setMapperClass(MapperClass.class);
//		job.setReducerClass(ReducerClass.class);
//		
//		job.setMapOutputKeyClass(Text.class);
//		job.setMapOutputValueClass(Text.class);
		
		Configuration mapConf1 = new Configuration(false);
		ChainMapper.addMapper(job, MapperClass.class,
		LongWritable.class, Text.class, Text.class,Text.class,mapConf1);
			
		
		Configuration mapConf2 = new Configuration(false);
		ChainMapper.addMapper(job, ChainMapper1.class,
				Text.class, Text.class, Text.class,CustimizeValue.class,mapConf2);
	
		Configuration reduceConf = new Configuration(false);		
		ChainReducer.setReducer(job, ReducerClass.class, Text.class, CustimizeValue.class,
				 Text.class, Text.class, reduceConf);
		
		Configuration mapConf3 = new Configuration(false);
		ChainReducer.addMapper(job, ChainMapper2.class,
				Text.class, Text.class, Text.class,Text.class,mapConf3);
		
		Configuration mapConf4 = new Configuration(false);
		ChainReducer.addMapper(job, ChainMapper3.class,
				Text.class, Text.class, Text.class,Text.class,mapConf4);
		
		Configuration mapConf5 = new Configuration(false);
		ChainReducer.addMapper(job, ChainMapper4.class,
				Text.class, Text.class, Text.class,IntWritable.class,mapConf5);
		
		Configuration mapConf6 = new Configuration(false);
		ChainReducer.addMapper(job, ChainMapper5.class,
				Text.class, IntWritable.class, Text.class,Text.class,mapConf6);
		
		job.setJarByClass(DriverClass.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/Movies-Data"));
		FileOutputFormat.setOutputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/Movies-Data_output"));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}