package org.milo.craigcrawl.craigservlet.om;


public class AverageByNeighborhood extends HomeRent
{
	private long locationid;

	private String parent;

	private double averagesquarefeet;


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

}
