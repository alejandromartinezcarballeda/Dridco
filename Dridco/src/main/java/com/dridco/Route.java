package com.dridco;

public class Route {
    
	private City origin;
    
	private City destination;
	
	private int distance;
	
	private Route nextPosibleRoute;
	
    /**
	 * @return the origin
	 */
	public City getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(City origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public City getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(City destination) {
		this.destination = destination;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the next Route
	 */
	public Route getNextPosibleRoute() {
		return nextPosibleRoute;
	}

	/**
	 * @param next the next to set
	 */
	public Route setNextPosibleRoute(Route next) {
		this.nextPosibleRoute = next;
		return this;
	}
 
    /**
     * @param origin
     * @param destination
     * @param distance
     * @param nextPosibleRoute
     */
    public Route(City origin, City destination, int distance, Route nextPosibleRoute) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.nextPosibleRoute = nextPosibleRoute;
    }

}