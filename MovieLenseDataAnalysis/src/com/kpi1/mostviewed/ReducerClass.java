package com.kpi1.mostviewed;

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

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerClass extends Reducer<Text, CustimizeValue, Text, LongWritable> {
	
    private HashMap tmap2 = new HashMap<>();
    
    @Override
    public void setup(Context context) throws IOException, 
                                     InterruptedException 
    { 
        tmap2 = new HashMap<String, Long>(); 
    } 
	
	public void reduce(Text key, Iterable<CustimizeValue> values, Context context)
			throws IOException, InterruptedException {
		String name = "";
		
		long count = 0;
		for (CustimizeValue mow : values) {
			if (mow.getFile_name().toString().contains("ratings")) {
//				System.out.println("IN if : and filename : "+mow.getFile_name().toString());
				count = count + Integer.parseInt(mow.getView_count().toString());
			} else {
//				System.out.println("IN ELSE : " + mow.getFile_name().toString());
				name = mow.getMovie_name().toString();
			}

		}
        tmap2.put(name,count);         

//		context.write(new Text(key + " :: " + name), new LongWritable(count));
	}
	
	
    @Override
    public void cleanup(Context context) throws IOException, 
                                       InterruptedException 
    { 
          
        Map<String, Long> sortedMap = sortByValues(tmap2);
        int size = sortedMap.keySet().size();
        int top_entries = 10;
        int counter = 0;
        for (String key: sortedMap.keySet()) {
            if (counter++ >= size-top_entries) {
            	context.write(new Text(key), new LongWritable(sortedMap.get(key)));
            }
            
        }
    } 
    
    private static HashMap sortByValues(HashMap map) { 
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
             public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                   .compareTo(((Map.Entry) (o2)).getValue());
             }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
   }
}
