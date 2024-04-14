package main;

import data_structures.ArrayList;

import data_structures.HashTableSC;
import data_structures.SimpleHashFunction;
import data_structures.LinkedStack;
import data_structures.HashSet;
import interfaces.List;
import interfaces.Map;
import interfaces.Stack;
import interfaces.Set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class TrainStationManager {

	
	Map<String,List<Station>> stations = new HashTableSC<>(20,new SimpleHashFunction<String>());
	
	Map<String,Station> shortestRoutes =  new HashTableSC<>(20,new SimpleHashFunction<String>());
	Stack<Station> toVisit = new LinkedStack<Station>(); //Need to be visited
	Set<Station> visited = new HashSet();
	Station currentStation; // Pop from stack work with it and add to set and continue 


	public TrainStationManager(String station_file) {
			System.out.println("-------------ONE ITERATION------------------------");
	       try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/" + station_file))) {
	           String line;
	           br.readLine();//skip header
	           while ((line = br.readLine()) != null) {
	        	   String [] parts = line.split(",");
	        	   String stationName = parts[0];
	        	   String neighborName = parts[1];
	        	   int distance = Integer.parseInt(parts[2]);
	        	   System.out.print(stationName + ", ");
	        	   System.out.print(neighborName + ", ");
	        	   System.out.print(distance + "\n");
	        	   //if StationName is already in the map, add the neigbor to the list
	        	   if(stations.containsKey(stationName)){
	        		   List<Station> existingList = stations.get(stationName);
	        		   existingList.add(new Station(neighborName,distance));
	        		   stations.put(stationName,existingList);
	        		   neighborsConectionChecker(stationName,neighborName,distance);
	        	   }else{
	        		   //Create a new list with the neighbor and add it to the map
	        		   List<Station> neighbors = new ArrayList<Station>();
	        		   Station newStation = new Station(neighborName,distance);
	        		   neighbors.add(newStation);
	        		   stations.put(stationName,neighbors);
		        	   //Check if neighbor is already connected to the station
	        		   neighborsConectionChecker(stationName,neighborName,distance);
	        	   }

	        	   
	           }	

	       } catch (IOException e) {

	           e.printStackTrace();

	       }
	}
	
	public void neighborsConectionChecker(String stationName,String neighborName, int distance) {
	   //Check if neighborn is already connected to the station
	   if(stations.containsKey(neighborName)) {
		   List<Station> existingNList = stations.get(neighborName);
		   Station newConection = new Station(stationName,distance);
		   existingNList.add(newConection);
		   stations.put(neighborName,existingNList);
	   }else { //If the neighborn does not exist then we create a new station to the neighbor and set his neighbor the current station
		   List<Station> neighbors = new ArrayList<Station>();
		   Station newStation = new Station(stationName,distance);
		   neighbors.add(newStation);
		   stations.put(neighborName,neighbors);
	   }
	}

	private void findShortestDistance() {
				
	}

	public void sortStack(Station station, Stack<Station> stackToSort) {
		
	}
	
	public Map<String, Double> getTravelTimes() {
		// 5 minutes per kilometer
		// 15 min per station
		return new HashTableSC<String,Double>(1,new SimpleHashFunction<>());
	}


	public Map<String, List<Station>> getStations() {
		return stations;
	}


	public void setStations(Map<String, List<Station>> cities) {
		
	}


	public Map<String, Station> getShortestRoutes() {
		return new HashTableSC<String,Station>(1,new SimpleHashFunction<>());
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