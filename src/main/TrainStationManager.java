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
	Set<String> visited = new HashSet(); // put is as string


	public TrainStationManager(String station_file) {
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
	       
	       // Call setInitialDistances to initialize shortestRoutes map
	       setInitialDistances();
	       
	       // Push the starting station onto the toVisit stack
	       toVisit.push(new Station("Westside", 0));
	       
	       // Now, you can call findShortestDistance method
	       findShortestDistance();
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
	    
	    while(!toVisit.isEmpty() || visited.size() != stations.size()) {
		    Station currentStation = toVisit.pop();
		    visited.add(currentStation.getCityName());
		    //lets access his neighborns
		    List<Station> currentNei = stations.get(currentStation.getCityName());
		    
		    //lets check what neighborns we need to add to the toVisit list
		    for(int i = 0; i < currentNei.size(); i++) {
		    	if(!visited.isMember(currentNei.get(i).getCityName())) { //If the station is not in the visited set we add it to the stack toVisit
		    		sortStack(currentNei.get(i),toVisit); // Add and sort at the same time
		    	}
		    }
		    
		    for(Station stat : currentNei) { // iter the neighbors and do the calc for each one 
		    	Station shortestStation = shortestRoutes.get(currentStation.getCityName());
		    	int newDistance = stat.getDistance() + shortestStation.getDistance();
		    	
		    	if(newDistance < shortestRoutes.get(stat.getCityName()).getDistance()) { //If is less we change it in the shortestMap
		    		shortestRoutes.get(stat.getCityName()).setDistance(newDistance); // set the distance
		    		shortestRoutes.get(stat.getCityName()).setCityName(currentStation.getCityName());// set the name which from we access this 
		    	}	    	
		    }   
		    
	    }	    
	}
	
	public void setInitialDistances() {
	    List<String> listOfStations = stations.getKeys();
	    int initialDistance = Integer.MAX_VALUE;
	    
	    // Initialize shortestRoutes map with default distances
	    for (String station : listOfStations) {
	        shortestRoutes.put(station, new Station("Westside", initialDistance));
	    }
	    
	    // Initialize the distance for "Westside" to 0
	    shortestRoutes.put("Westside", new Station("Westside", 0));
	}


	//Also add the station to the stack :)
	public void sortStack(Station station, Stack<Station> stackToSort) {
	    Stack<Station> temp = new LinkedStack<>();

	    // Edge case: if stackToSort is empty, just push station onto it
	    if (stackToSort.isEmpty()) {
	        stackToSort.push(station);
	        return; // Exit the method
	    }

	    // Move elements shorter distance to temp stack
	    while (!stackToSort.isEmpty() && station.getDistance() > stackToSort.top().getDistance()) {
	        temp.push(stackToSort.pop());
	    }

	    // Add the given station to the correct place in stackToSort
	    stackToSort.push(station);

	    // Push temp values back to stackToSort
	    while (!temp.isEmpty()) {
	        stackToSort.push(temp.pop());
	    }
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
		stations = cities;
	}


	public Map<String, Station> getShortestRoutes() {
		return shortestRoutes;
	}


	public void setShortestRoutes(Map<String, Station> shortestRoutes) {
		this.shortestRoutes = shortestRoutes;
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