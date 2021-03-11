package com.kpi1avg_satisfactionlevel;

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
    Job wcJob = Job.getInstance(conf, "MapReduce HRAnalysis");
    wcJob.setJarByClass(DriverClass.class);
    wcJob.setMapperClass(MapperClass.class);
    wcJob.setCombinerClass(ReducerClass.class);
    wcJob.setReducerClass(ReducerClass.class);
    wcJob.setOutputKeyClass(Text.class);
    wcJob.setOutputValueClass(FloatWritable.class);
//    for (int i = 0; i < pathArgs.length - 1; ++i)
//    {
//      FileInputFormat.addInputPath(wcJob, new Path(pathArgs[i]));
//    }
    FileInputFormat.addInputPath(wcJob, new Path("/home/suchetrenge/mydata/data_and_finalJARs/hranalysis"));
    FileOutputFormat.setOutputPath(wcJob, new Path("/home/suchetrenge/mydata/data_and_finalJARs/hranalysis_output"));
    System.exit(wcJob.waitForCompletion(true) ? 0 : 1);
  }
}