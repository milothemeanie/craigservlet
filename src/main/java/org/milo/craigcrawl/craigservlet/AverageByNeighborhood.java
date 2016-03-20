package org.milo.craigcrawl.craigservlet;

public class AverageByNeighborhood
{
	private long locationid;

	private String neighborhood;
	
	private String parent;

	private double averagesquarefeet;

	private double averageprice;

	/**
	 * @return the locationid
	 */
	public long getLocationid()
	{
		return locationid;
	}

	/**
	 * @param locationid the locationid to set
	 */
	public void setLocationid(long locationid)
	{
		this.locationid = locationid;
	}

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
	 * @return the parent
	 */
	public String getParent()
	{
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	/**
	 * @return the averagesquarefeet
	 */
	public double getAveragesquarefeet()
	{
		return averagesquarefeet;
	}

	/**
	 * @param averagesquarefeet the averagesquarefeet to set
	 */
	public void setAveragesquarefeet(double averagesquarefeet)
	{
		this.averagesquarefeet = averagesquarefeet;
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

}
