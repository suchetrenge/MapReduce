# Movie Lens Data Analysis
## Objective
The objective of the project is to analyze movies dataset and solve below mentioned KPIs. The dataset contains following files:
The first file contains Movie Information:
### File 1: movies (MovieID::Title::Genres)

Titles are identical to titles provided by the IMDB (including year of release) 
- Genres are pipe-separated and are selected from the following genres: 
1.	Action 
2.	Adventure 
3.	Animation 
4.	Children's 
5.	Comedy 
6.	Crime 
7.	Documentary 
8.	Drama 
9.	Fantasy 
10.	Film-Noir 
11.	Horror 
12.	Musical 
13.	Mystery 
14.	Romance 
15.	Sci-Fi 
16.	Thriller 
17.	War 
Western 
- Some MovieIDs do not correspond to a movie due to accidental duplicate entries and/or test entries 
- Movies are mostly entered by hand, so errors and inconsistencies may exist 

The second file contains rating information:
### File 2 : ratings (UserID::MovieID::Rating::Timestamp)
- UserIDs range between 1 and 6040 
- MovieIDs range between 1 and 3952 
- Ratings are made on a 5-star scale (whole-star ratings only) 
- Timestamp is represented in seconds since the epoch as returned by time(2) 

The third file contains users information: 
File 3: users: (UserID::Gender::Age::Occupation::Zip-code)
- Gender is denoted by a "M" for male and "F" for female 
- Age is chosen from the following ranges: 
1.	1: "Under 18" 
2.	18: "18-24" 
3.	25: "25-34" 
4.	35: "35-44" 
5.	45: "45-49" 
6.	50: "50-55" 
7.	56: "56+" 

- Occupation is chosen from the following choices: 
0: "other" or not specified 
1: "academic/educator" 
2: "artist" 
3: "clerical/admin" 
4: "college/grad student" 
5: "customer service" 
6: "doctor/health care" 
7: "executive/managerial" 
8: "farmer" 
9: "homemaker" 
10: "K-12 student" 
11: "lawyer" 
12: "programmer" 
13: "retired" 
14: "sales/marketing" 
15: "scientist" 
16: "self-employed" 
17: "technician/engineer" 
18: "tradesman/craftsman" 
19: "unemployed" 
20: "writer" 

Develop MapReduce programs to solve following KPIs: 
### 1.	Top ten most viewed movies with their movies Name (Ascending or Descending order) 
### Solution: 
The solution for this KPI is kept under folder solution->kpi1.
In this, we have used only one Mapper class and one reducer class. The output value of the mapper class is Customized value CustimizeValue.java.

Package and class information:
Package name: com.kpi1.mostviewed
Mapper class: MapperClass.java
Reducer class: ReducerClass.java
Customize Data type class: CustimizeValue.java

•	In mapper class, we have read the two files namely ratings and movies. The output of the mapper is given to the input to reducer class.
•	In this, the output key for the mapper class is movieID and the value is customized data type class.
•	Based on the filenames, if the data is from the ratings file, then we are storing only two values in the customised class.
1.	View count
2.	File name
•	Similarly, if the is coming from the movies class, then we are storing only two values in the customized class.
1.	Movie name
2.	File name
•	At the reducer, based on the movieID we are storing the view count of each movie in the local data type called HashMap. This HashMap is getting sorted in cleanup function based on the values (view count) and thus we are getting the top 10 most viewed movies with name.


### 2. Top twenty rated movies (Condition: The movie should be rated/viewed by at least 40 users) 
### Solution: 
The solution for this KPI is kept under folder solution->kpi2.
In this, we have used only one Mapper class and one reducer class. The output value of the mapper class is Customized value CustimizeValue.java.

Package and class information:
Package name: com.kpi2.mostrated.atleastfourty
Mapper class: MapperClass.java
Reducer class: ReducerClass.java
Customize Data type class: CustimizeValue.java

•	In mapper class, we have read the two files namely ratings and movies. The output of the mapper is given to the input to reducer class.
•	In this, the output key for the mapper class is movieID and the value is customized data type class.
•	Based on the filenames, if the data is from the ratings file, then we are storing only two values in the customised class.
1.	View count
2.	File name
3.	Ratings
•	Similarly, if the is coming from the movies class, then we are storing only two values in the customized class.
1.	Movie name
2.	File name
•	In Reducer, we are sorting the results based on the view count and storing it into the local datatype called HashMap. The result is sorted based on the view count, the view count must be at least 40, then only the entry is getting added into the HashMap.
•	In the cleanup function, the HashMap is got sorted based on the values, and thus we are getting the top 20 rated movies which is viewed by at least 40 peoples.

### 3. We wish to know how have the genres ranked by Average Rating, for each profession and age group. The age groups to be considered are: 18-35, 36-50 and 50+. 
### Solution: 
The solution for this KPI is kept under folder solution->kpi3.
In this, we have used Mapper chaining as well as reducer chaining. We have used 2 mapper classes in mapper chaining and 1 reducer, 4 mapper classes in the reducer chaining.

Package, class information and flow:
Package name: com.kpi3.ratingbygenres
Mapper chaining class 1: MapperClass.java
Mapper chaining class 2: ChainMapper1.java
Reducer class: ReducerClass.java
Reducer chaining mapper class 1: ChainMapper2.java
Reducer chaining mapper class 2: ChainMapper3.java
Reducer chaining mapper class 3: ChainMapper4.java
Reducer chaining mapper class 4: ChainMapper5.java
Customize Data type class: CustimizeValue.java

•	In the MapperClass.java that is the first mapper class in the Mapper chaining, we are reading the input from all the files (from movies, ratings and users) as it is. The output key from this class is filename and output value is all the data we are reading from the input files.
•	The input for the ChainMapper1.java class is coming from the MapperClass.java. In this class we are dividing the data based on two keys. The data which is coming from the ratings and users files, for them we are setting the output key as “userid_<actiual_user_id>” and the data which is coming from the movies files for them we are setting the output key as “movieid_<actual_movie_id>”.
•	The output of the ChainMapper1.java class is given to the Reducer class named ReducerClass.java.
•	Based on the same movie_id and user_id the data got merged in the Reducer and then send it to mapper named ChainMapper2.java. Here, with the user_id, we are also adding the list of movies he/she watched in the output value.
•	In ChainMapper2.java , the data is getting sorted based on the movieid and moviename. The output key for this mapper contains the movieid_moviename.
•	In the ChainMapper3.java, it is just used to replace all the “|” with “:” and the output is send to ChainMapper4.java mapper.
•	In ChainMapper4.java class, the data is got sorted based on the age group, profession and genres and the value is set as ratings. The output is then given to the final ChainMapper5.java where we get the final result i.e. genres ranked by rating profession and age group.
