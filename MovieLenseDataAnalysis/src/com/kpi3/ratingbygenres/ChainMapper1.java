package com.kpi3.ratingbygenres;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class ChainMapper1 extends Mapper<Text, Text, Text, CustimizeValue> {
	Text movie_name = new Text();
	Text genres = new Text();
	
	IntWritable userid = new IntWritable();
	IntWritable movieid = new IntWritable();
	IntWritable rating = new IntWritable();
	
	Text gender = new Text();
	IntWritable age = new IntWritable();
	IntWritable profession = new IntWritable();
	
	
	public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
//			System.out.println("In chainmapper1");
		CustimizeValue joinWritable = null;
		String[] tokens = value.toString().split("::");
		if (key.toString().contains("ratings")) {
			if (tokens.length == 4) {
			joinWritable = new CustimizeValue();
			userid.set(Integer.parseInt(tokens[0]));
			movieid.set(Integer.parseInt(tokens[1]));
			rating.set(Integer.parseInt(tokens[2]));
			
			joinWritable.setUserid(userid);
			joinWritable.setMovieid(movieid);
			joinWritable.setRatings(rating);
			
			context.write(new Text("userid_"+userid), joinWritable);
			}
			
		}else if(key.toString().contains("users")) {
			if (tokens.length == 5) {
				joinWritable = new CustimizeValue();
				userid.set(Integer.parseInt(tokens[0]));
				gender.set(new Text(tokens[1]));
				age.set(Integer.parseInt(tokens[2]));
				profession.set(Integer.parseInt(tokens[3]));
				joinWritable.setUserid(userid);
				joinWritable.setGender(gender);
				joinWritable.setAge(age);
				joinWritable.setProfession(profession);
				joinWritable.setUser_info_status(new IntWritable(1));
				context.write(new Text("userid_"+userid), joinWritable);
			}
		}else {
			if (tokens.length == 3) {
				joinWritable = new CustimizeValue();
				movieid.set(Integer.parseInt(tokens[0]));
				movie_name.set(new Text(tokens[1]));
				genres.set(new Text(tokens[2]));
				joinWritable.setMovieid(movieid);
				joinWritable.setMovie_name(movie_name);
				joinWritable.setGenres(genres);
				context.write(new Text("movieid_"+movieid), joinWritable);
			}
		}
			
	}
}
