package com.dridco;

import java.util.ArrayList;
import java.util.Hashtable;
 
public class TrainsGraph {
	
    public Hashtable<City, Route> routes;
 
    public TrainsGraph() {
        this.routes = new Hashtable<City, Route>();
    }
 
    /*
     * return the distance of a given specific path (List of cities)
     * */
    public int distanceBetween(ArrayList<City> cities) throws Exception {

        int totalDistance = 0;
        int numOfStopsMade = 0;
        int cityIndex = 0;

        /*
         * we dont need to iterate through the last element of the list as we get the next element 
         * of the cities list on each iteration.
         * */
        for (int i=0; i < cities.size()-1; i++) {
        	
        	/*
        	 * check if city exists in the graph
        	 * */
            if(routes.containsKey(cities.get(cityIndex))) {
                Route route = routes.get(cities.get(cityIndex));

                /*
                 * while was used instead of if, because after getting the first route for the first city 
                 * and if its not the one desired, then we must get the next route
                 * */
                while(route != null) {
                	/*
                	 * if the destination of the route equals to the next element on the 
                	 * cities list, then we get distance.
                	 * */
                    if(route.getDestination().equals(cities.get(cityIndex + 1))) {
                        totalDistance = totalDistance + route.getDistance();
                        numOfStopsMade++;
                        break;
                    }
                    /*
                     * try again with the next posible route.
                     * */
                    route = route.getNextPosibleRoute();
                }
            } else {
            	/*
            	 * if one of the cities does not exist on the list of routes, then "SIN RUTA" message is shown.
            	 * */
                throw new Exception("SIN RUTA");
            }
            cityIndex++;
        }
        
        /*
         * if the number of paths taken are different from the quantity of citis minus one,
         * then there is a part of the route that does not exist.
         * for example: if there are 3 cities, there should be 2 routes if numOfPathsTaken is less
         * than 2, then a route to one of those 3 cities does not exist
         * */
        if(numOfStopsMade != cities.size() - 1) {
        	/*
        	 * if one of the cities does not exist on the list of routes, then "SIN RUTA" message is shown.
        	 * */
            throw new Exception("SIN RUTA");
        }
 
        return totalDistance;
    }
    
    
    /*
     * find the quantity of routes from city "from" to city "to"
     * */
    public int findRoutes(City from, City to, int numOfStopsMade, int maxNumOfStops) throws Exception{
        int numOfRoutes = 0;
        boolean hasNextPosibleRoute = true;
        /*
         * Check if start and end nodes exists in route table
         * */
        if(routes.containsKey(from) && routes.containsKey(to)) {
        	
            /*
             * Check if numOfStopsMade +1 (considerating the next stop) is lower or equal to maxStops
             * */
            if(numOfStopsMade + 1 <= maxNumOfStops) {
	            /*
	             * From city should be setted as visited
	             * */
	
	            Route destinationRoute = routes.get(from);
	            
	            while(hasNextPosibleRoute) {
	            	numOfStopsMade++;
	                /* If destination of destinationRoute equals the "to" city, then we increment the cantRoutes
	                 * and keep looking for other paths with the same quantity of stops
	                 * */
	                if(destinationRoute.getDestination().equals(to)) {
	                	numOfRoutes++;
	                	/*
		                 * try again with the next posible route.
		                 * */
		                if (destinationRoute.getNextPosibleRoute() != null) {
	                		destinationRoute = destinationRoute.getNextPosibleRoute();
	                	} else {
	                		/*
	                		 * if there is no next posible route, while loop should end
	                		 * */
	                		hasNextPosibleRoute = false;
	                	}
	                    continue;
	                } else {
	                /*
	                 * if destionation does not match, then we make a recursive call to see if continuing by this route there 
	                 * is chance to get to the "to" City
	                 * */
	                	if(!destinationRoute.getDestination().isVisited()) {
	                		numOfRoutes = numOfRoutes + findRoutes(destinationRoute.getDestination(), to, numOfStopsMade, maxNumOfStops);
		                    numOfStopsMade--;
	                	}
	                }
	                
	                /*
	                 * try again with the next posible route.
	                 * */
	                if (destinationRoute.getNextPosibleRoute() != null) {
                		destinationRoute = destinationRoute.getNextPosibleRoute();
                	} else {
                		/*
                		 * if there is no next posible route, while loop should end
                		 * */
                		hasNextPosibleRoute = false;
                	}
	            }
        	}
        } else {
        	/*
        	 * if one of the cities does not exist on the list of routes, then "SIN RUTA" message is shown.
        	 * */
            throw new Exception("SIN RUTA");
        }
 
        return numOfRoutes;
    }


    /*
     * find the shortest route from city "from" to city "to"
     * */
    public int findShortestRoute(City from, City to, int distance, int shortestRoute) throws Exception{

    	boolean hasNextPosibleRoute = true;
    	
        if(routes.containsKey(from) && routes.containsKey(to)) {

            from.setVisited(true);
            Route destinationRoute = routes.get(from);
            while(hasNextPosibleRoute) {
                
            	/*	OPTIMAZE THIS CODE	*/
            	
                /*
                 * if destination city equals the "to" city then we return the distance, because its the shortest distance
                 * */
                if (destinationRoute.getDestination().equals(to)){
                	distance = distance + destinationRoute.getDistance();
                } else {
                	/*
                	 * if not, and if it is not visited, then we accumulate the distance value
                	 * */
                	if (!destinationRoute.getDestination().isVisited()){
                		distance = distance + destinationRoute.getDistance();
                	}
                }
 
                /*
                 * if destination equals "to" city, then we compare the distance of the route,
                 * if its shorter than the shortest so far then we assign it.
                 * */
                if(destinationRoute.getDestination().equals(to)) {
                    if(shortestRoute == 0 || distance < shortestRoute) {
                        shortestRoute = distance;
                    }
                    /*
                     * set isVisited to false in order to enable it again
                     * */
                    from.setVisited(false);
                    return shortestRoute;
                } else {

                	/*
                	 * if destinationRoute wasnt visited yet, then we use a recursive call to keep looking for
                	 * the shortest distance from this route.
                	 * */
	                if(!destinationRoute.getDestination().isVisited()) {
	                    shortestRoute = findShortestRoute(destinationRoute.getDestination(), to, distance, shortestRoute);
	                    /*
	                     * decrease the destinationRoute distance
	                     * */
	                    distance = distance - destinationRoute.getDistance();
	                }
	                /*
	                 * try again with the next posible route.
	                 * */
	                if (destinationRoute.getNextPosibleRoute() != null) {
                		destinationRoute = destinationRoute.getNextPosibleRoute();
                	} else {
                		/*
                		 * if there is no next posible route, while loop should end
                		 * */
                		hasNextPosibleRoute = false;
                	}
                }
            }
        } else {
        	/*
        	 * if one of the cities does not exist on the list of routes, then "SIN RUTA" message is shown.
        	 * */
            throw new Exception("SIN RUTA");
        }
 
        /*
         * reseting isVisited state before exiting the method
         * */
        from.setVisited(false);
        return shortestRoute;
 
    }
 
    
    /*
     * find the quantity of routes between two cities with a distance lower than the provided
     * */
    public int findRoutesWithDistanceLowerThanProvided(City from, City to, int distance, int maxDistance) throws Exception{

    	int numOfRoutes = 0;
    	boolean hasNextPosibleRoute = true;
    	
        /*
         * initial check, from and to cities must exist in the routes list
         * */
        if(routes.containsKey(from) && routes.containsKey(to)) {
            
            Route destinationRoute = routes.get(from);
            while(hasNextPosibleRoute) {
                distance = distance + destinationRoute.getDistance(); 
                
                /*
                 * if distance is lower than the max distance we keep looking for different routes to the "to" city,
                 * */
                if(distance <= maxDistance) {
                	/*
                	 * if a match is found, we increment the count of found routes and 
                	 * keep looking as there could be more combinations with the route just found
                	 * */
                    if(destinationRoute.getDestination().equals(to)) {
                        numOfRoutes++;
                        /*
                         * route found, increment counter and make a recursive call to keep looking for others routes
                         * */
                        numOfRoutes = numOfRoutes + findRoutesWithDistanceLowerThanProvided(destinationRoute.getDestination(), to, distance, maxDistance);
                        /*
    	                 * try again with the next posible route.
    	                 * */
    	                if (destinationRoute.getNextPosibleRoute() != null) {
                    		destinationRoute = destinationRoute.getNextPosibleRoute();
                    	} else {
                    		/*
                    		 * if there is no next posible route, while loop should end
                    		 * */
                    		hasNextPosibleRoute = false;
                    	}
                        continue;
                    } else {
                        numOfRoutes = numOfRoutes + findRoutesWithDistanceLowerThanProvided(destinationRoute.getDestination(), to, distance, maxDistance);
                        /*
                         * decrease the distance that could not end in the "to" city
                         * */
                        distance = distance - destinationRoute.getDistance();
                    }
                } else {
                	distance = distance - destinationRoute.getDistance();
                }
                /*
                 * try again with the next posible route.
                 * */
                if (destinationRoute.getNextPosibleRoute() != null) {
            		destinationRoute = destinationRoute.getNextPosibleRoute();
            	} else {
            		/*
            		 * if there is no next posible route, while loop should end
            		 * */
            		hasNextPosibleRoute = false;
            	}
            }
        } else {
        	/*
        	 * if one of the cities does not exist on the list of routes, then "SIN RUTA" message is shown.
        	 * */
            throw new Exception("SIN RUTA");
        }
 
        return numOfRoutes;
 
    }    
}