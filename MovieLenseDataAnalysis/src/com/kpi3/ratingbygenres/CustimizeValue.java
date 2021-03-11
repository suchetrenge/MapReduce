package com.kpi3.ratingbygenres;

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
	private Text genres;
	private IntWritable profession;
	private IntWritable age;
	private IntWritable userid;
	private IntWritable movieid;
	private Text gender;
	private IntWritable user_info_status;
	
	public CustimizeValue() {
		// TODO Auto-generated constructor stub
		view_count = new IntWritable(0);
		movie_name = new Text("");
		file_name = new Text("");
		ratings = new IntWritable(0);
		genres = new Text("");
		profession = new IntWritable(0);
		age = new IntWritable(0);
		userid = new IntWritable(0);
		movieid = new IntWritable(0);
		gender = new Text("");
		user_info_status = new IntWritable(0);
	}

	public CustimizeValue(int view_count, String file_name, int ratings) {
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

	public Text getGenres() {
		return genres;
	}

	public void setGenres(Text genres) {
		this.genres = genres;
	}

	public IntWritable getProfession() {
		return profession;
	}

	public void setProfession(IntWritable profession) {
		this.profession = profession;
	}

	public IntWritable getAge() {
		return age;
	}

	public void setAge(IntWritable age) {
		this.age = age;
	}

	public IntWritable getUserid() {
		return userid;
	}

	public void setUserid(IntWritable userid) {
		this.userid = userid;
	}
	
	
	public IntWritable getMovieid() {
		return movieid;
	}

	public void setMovieid(IntWritable movieid) {
		this.movieid = movieid;
	}
	
	public Text getGender() {
		return gender;
	}

	public void setGender(Text gender) {
		this.gender = gender;
	}
	
	

	public IntWritable getUser_info_status() {
		return user_info_status;
	}

	public void setUser_info_status(IntWritable user_info_status) {
		this.user_info_status = user_info_status;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		view_count.readFields(in);
		movie_name.readFields(in);
		file_name.readFields(in);
		ratings.readFields(in);
		genres.readFields(in);
		profession.readFields(in);
		age.readFields(in);
		userid.readFields(in);
		movieid.readFields(in);
		gender.readFields(in);
		user_info_status.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		view_count.write(out);
		movie_name.write(out);
		file_name.write(out);
		ratings.write(out);
		genres.write(out);
		profession.write(out);
		age.write(out);
		userid.write(out);
		movieid.write(out);
		gender.write(out);
		user_info_status.write(out);
	}

}