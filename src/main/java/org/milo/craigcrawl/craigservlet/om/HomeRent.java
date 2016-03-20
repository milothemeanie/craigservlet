package org.milo.craigcrawl.craigservlet.om;

public abstract class HomeRent
{
	private String neighborhood;
	
	private double averageprice;
	
	private int bedroom;
	
	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood()
	{
		return neighborhood;
	}

	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}
	
	/**
	 * @return the averageprice
	 */
	public double getAverageprice()
	{
		return averageprice;
	}

	/**
	 * @param averageprice the averageprice to set
	 */
	public void setAverageprice(double averageprice)
	{
		this.averageprice = averageprice;
	}

	/**
	 * @return the bedroom
	 */
	public int getBedroom()
	{
		return bedroom;
	}

	/**
	 * @param bedroom the bedroom to set
	 */
	public void setBedroom(int bedroom)
	{
		this.bedroom = bedroom;
	}
}
