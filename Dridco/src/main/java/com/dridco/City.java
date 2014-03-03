package com.dridco;

public class City {
    private String name;
    private boolean isVisited;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

 
    /**
     * @param name
     */
    public City(String name) {
        this.name = name;
        this.isVisited = false;
    }
 
    
    /*
     * override equals and hascode methods only for use of equals
     * */
    @Override
    public boolean equals(Object b) {
        if (b == null || b.getClass() != getClass()) {
            return false;
        }
        City bx = (City)b;
        return this.name.equals(bx.name);
    }
 
    @Override
    public int hashCode() {
        if(this.name == null) return 0;
        return this.name.hashCode();
    }
}