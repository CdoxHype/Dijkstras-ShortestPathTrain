package main;

import data_structures.ArrayList;
import data_structures.HashTableSC;
import data_structures.SimpleHashFunction;
import interfaces.List;
import interfaces.Map;
import interfaces.Stack;
import interfaces.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrainStationManager {

	
	Map<String,List<Station>> stations = new HashTableSC<>(10,new SimpleHashFunction<>());
	
	Map<String,Station> shortestRoutes =  new HashTableSC<>(10,new SimpleHashFunction<>());
	Stack<Station> toVisit; //Need to be visited
	Set<Station> visited;
	Station currentStation; // Pop from stack work with it and add to set and continue 


	public TrainStationManager(String station_file) {
		List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/" + station_file))) {
            String line;
			br.readLine();//skip header
            while ((line = br.readLine()) != null) {
            	String [] parts = line.split(",");
				String stationName = parts[0];
				String neighborName = parts[1];
				int distance = Integer.parseInt(parts[2]);

				//if StationName is already in the map, add the neigbor to the list
				if(stations.containsKey(stationName)){
					stations.get(stationName).add(new Station(neighborName,distance));
				}else{
					//Create a new list with the neighbor and add it to the map
					List<Station> neighbors = new ArrayList<>();
					neighbors.add(new Station(neighborName,distance));
					stations.put(stationName,neighbors);
				}

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void findShortestDistance() {
				
	}

	public void sortStack(Station station, Stack<Station> stackToSort) {
		
	}
	
	public Map<String, Double> getTravelTimes() {
		// 5 minutes per kilometer
		// 15 min per station
	}


	public Map<String, List<Station>> getStations() {
		
	}


	public void setStations(Map<String, List<Station>> cities) {
		
	}


	public Map<String, Station> getShortestRoutes() {
		
	}


	public void setShortestRoutes(Map<String, Station> shortestRoutes) {
		
	}
	
	/**
	 * BONUS EXERCISE THIS IS OPTIONAL
	 * Returns the path to the station given. 
	 * The format is as follows: Westside->stationA->.....stationZ->stationName
	 * Each station is connected by an arrow and the trace ends at the station given.
	 * 
	 * @param stationName - Name of the station whose route we want to trace
	 * @return (String) String representation of the path taken to reach stationName.
	 */
	public String traceRoute(String stationName) {
		// Remove if you implement the method, otherwise LEAVE ALONE
		throw new UnsupportedOperationException();
	}

}