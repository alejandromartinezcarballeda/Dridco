package com.dridco;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import org.junit.BeforeClass;


public class TrainsGraphTest {
    static TrainsGraph graph;
    static City a, b, c, d, e;
 
    /*
     * Setup of graph
     * */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		graph = new TrainsGraph();

		a = new City("A");
		b = new City("B");
		c = new City("C");
		d = new City("D");
		e = new City("E");

		/*Graph Input: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/

		/*
		 * define first the last route to set it as a next posible route 
		 * */
		Route routeAE = new Route(a, e, 7, null);
		Route routeAD = new Route(a, d, 5, routeAE);
		Route routeAB = new Route(a, b, 5, routeAD);
		/*
		 * routeAD will be the next posible route for routeAB,
		 * so A will have 2 next posible routes different from routeAB
		 * */
		graph.routes.put(a, routeAB);
		
		Route routeBC = new Route(b, c, 4, null);
		graph.routes.put(b, routeBC);
		
		Route routeCE = new Route(c, e, 2, null);
		Route routeCD = new Route(c, d, 8, routeCE);
		graph.routes.put(c, routeCD); 
		
		Route routeDE = new Route (d, e, 6, null);
		Route routeDC = new Route (d, c, 8, routeDE);
		graph.routes.put(d, routeDC);
		
		Route routeEB = new Route(e, b, 3, null);
		graph.routes.put(e, routeEB);
	    
	}
 
    @Test
    public void testGetDistanceABC() throws Exception {
        ArrayList<City> routes = new ArrayList<City>(); 
        routes.add(a);
        routes.add(b);
        routes.add(c);
        assertEquals(9, graph.distanceBetween(routes));
    }

    @Test
    public void testGetDistanceAD() throws Exception {
        ArrayList<City> route = new ArrayList<City>(); 
        route.add(a);
        route.add(d);
        assertEquals(5, graph.distanceBetween(route));
    }
 
    @Test
    public void testGetDistanceADC() throws Exception  {
        ArrayList<City> route = new ArrayList<City>(); 
        route.add(a);
        route.add(d);
        route.add(c);
        assertEquals(13, graph.distanceBetween(route));
    }
 
    @Test
    public void testGetDistanceAEBCD() throws Exception  {
        ArrayList<City> route = new ArrayList<City>(); 
        route.add(a);
        route.add(e);
        route.add(b);
        route.add(c);
        route.add(d);
        assertEquals(22, graph.distanceBetween(route));
    }
 
    @Test(expected=Exception.class)
    public void testGetDistanceAED() throws Exception  {
        ArrayList<City> route = new ArrayList<City>(); 
        route.add(a);
        route.add(e);
        route.add(d);
        /*
         * it doesnt matter the expected number result as the exception is expected to be thrown
         * */
        assertEquals(1000, graph.distanceBetween(route));
    }
 
    
    
    
    @Test
    public void testFindRoutesCC3() throws Exception {
        int numStops = graph.findRoutes(c, c, 0, 3);
        assertEquals(2, numStops);
    }
 
    @Test
    public void testFindRoutesAC4() throws Exception {
        int numStops = graph.findRoutes(a, c, 0, 4);
        assertEquals(3, numStops);
    }
 
    
    
    
    
    
    
    
    
    @Test
    public void testFindShortestRouteAC() throws Exception {
        int shortestRoute = graph.findShortestRoute(a, c, 0, 0);
        assertEquals(9, shortestRoute);
    }
    
    @Test
    public void testFindShortestRouteBB() throws Exception {
        int shortestRoute = graph.findShortestRoute(b, b, 0, 0);
        assertEquals(9, shortestRoute);
    }
 
    
    
    
    
    @Test
    public void testFindRoutesWithDistanceLowerThanProvidedCC30() throws Exception {
        int numRoutesWithin = graph.findRoutesWithDistanceLowerThanProvided(c, c, 0, 30);
        assertEquals(7, numRoutesWithin);
    }

 
}