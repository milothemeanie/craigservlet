package org.milo.craigcrawl.craigservlet;

import java.util.List;

import org.milo.craigcrawl.craigservlet.om.HomeRent;

public interface AverageChart
{
	List<HomeRent> retrieveAverage();
}
