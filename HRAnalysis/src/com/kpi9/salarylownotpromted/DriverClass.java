package com.kpi9.salarylownotpromted;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class DriverClass
{
  public static void main(String[] args) throws Exception
  {
    Configuration conf = new Configuration();
//    String[] pathArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
//    if (pathArgs.length < 2)
//    {
//      System.err.println("MR Project Usage: wordcount <input-path> [...] <output-path>");
//      System.exit(2);
//    }
    Job job = new Job(conf, "HR Analysis");
    job.setJarByClass(DriverClass.class);
    job.setMapperClass(MapperClass.class);
    job.setReducerClass(ReducerClass.class);
    
	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(CustomizedValue.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    FileInputFormat.addInputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/hranalysis"));
    FileOutputFormat.setOutputPath(job, new Path("/home/suchetrenge/mydata/data_and_finalJARs/hranalysis_output"));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}