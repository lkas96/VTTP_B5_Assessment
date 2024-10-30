package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//omg help i cannot no time, bye bye me deadass
//too many nested maps, confusion is real.

//IMPORT TO USE THE FUNCTIONS
// import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	// GLOBAL VARIABLES
	static List<BikeEntry> bikeEntryList = new ArrayList<>();

	public static void processing(List<BikeEntry> bikeEntryList) {
		// Do the count shit
		// Into hashmap bla bla
		// Decide on hasmap format now hmmmm
		// NESTED HASH SEASON -> MONTH -> DAY [ALL OTHERs]
		// Map<Season, Map<Month, Map<Day, ArrayList<>>>> nestedMap = new HashMap<>();
		Map<Integer, Map<Integer, Map<Integer, ArrayList<String>>>> seasonMap = new HashMap<>();
		Map<Integer, Map<Integer, ArrayList<String>>> monthMap = new HashMap<>();
		Map<Integer, ArrayList<String>> dayMap = new HashMap<>();

		// Create season hasmap outer hasmap <SEASON, MAP2>
		for (BikeEntry beLines : bikeEntryList) {
			System.out.println("MAPPING SEASONS FIRST OUTER MAP");

			if (!seasonMap.containsKey(beLines.getSeason())) { // add a new season entry first
				// nestedMap.put(beLines.getSeason())

				if (!monthMap.containsKey(beLines.getMonth())) {

					if (!dayMap.containsKey(beLines.getWeekday())) {
						// String[] toAdd = {String.valueOf(beLines.isHoliday()),
						// String.valueOf(beLines.getWeather()), String.valueOf((beLines.getRegistered()
						// + beLines.getCasual()))};
						ArrayList<String> temp = new ArrayList<>();
						temp.add(String.valueOf((beLines.getRegistered() + beLines.getCasual())));
						dayMap.put(beLines.getWeekday(), temp);
					} else {

					}

					// put month + daymap
					monthMap.put(beLines.getMonth(), dayMap);
				}

				seasonMap.put(beLines.getMonth(), monthMap);

			} else {

				seasonMap.put(beLines.getMonth(), monthMap);
			}

		}

		// Map<String, Integer> map = new HashMap<String, Integer>();
		// for (String s : strings) {

		// if (!map.containsKey(s)) { // first time we've seen this string
		// map.put(s, 1);
		// } else {
		// int count = map.get(s);
		// map.put(s, count + 1);
		// }
		// }

	}

	public static void main(String[] args) throws IOException {

		// Read the CSV into a variable
		// CSV FORMAT : IGNORE FIRST LINE
		// season,mnth,holiday,weekday,weathersit,temp,hum,windspeed,casual,registered
		// 3,8,0,6,1,0.678333,0.603333,0.177867,2827,5038

		// HARDCODE THE FILE IN AS PER RUN.SH DOES NOT TAKE IN ARGUMENTS
		FileReader reader = new FileReader("task01/day.csv");
		BufferedReader bReader = new BufferedReader(reader);

		String lines;

		// To skip processing first line
		Integer lineCounter = 0;

		System.out.println(bReader.readLine());

		while ((lines = bReader.readLine()) != null) {

			// //Do something to skip first line

			// // // Process each csv line
			// lines = bReader.readLine();

			// // Delimiter is comma
			String[] linesCol = lines.split(",");

			// Testing line output
			// System.out.println(Arrays.toString(linesCol));

			// //Add each line into bikeEntryList
			BikeEntry be = new BikeEntry(linesCol[0], linesCol[1], linesCol[2], linesCol[3], linesCol[4], linesCol[5],
					linesCol[6], linesCol[7], linesCol[8], linesCol[9]);
			bikeEntryList.add(be);
		}

		// Print out all entries
		bikeEntryList.forEach(li -> System.out.println(li));
		System.out.println("done all lines");

		System.out.println((bikeEntryList.get(0).getSeason())); // Prints line 0 in days ignoring header
		System.out.println((bikeEntryList.get(1)));

		// Do the word count nested map
		processing(bikeEntryList);


		//cannot, just hard count the entries.
		// Item count print?
		// bikeEntryList.stream()
		// 		.filter(season -> bikeEntryList.getSeason().equals("fall"))
		// 		.count();

	}
}
