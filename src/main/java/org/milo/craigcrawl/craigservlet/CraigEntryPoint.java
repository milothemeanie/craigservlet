package org.milo.craigcrawl.craigservlet;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import flexjson.JSONSerializer;

@Singleton
@Path("/craigrent")
public class CraigEntryPoint
{

    @GET
    @Path("/tree")
    @Produces(MediaType.APPLICATION_JSON)
	public String treeMapAverage()
	{
        final JSONSerializer serializer = new JSONSerializer();
        serializer.exclude("*.class");
        serializer.prettyPrint(true);
    	final AverageCraigRent average = new AverageCraigRent();
		
		return serializer.deepSerialize(average.retrieveAverageByNeighborhood());

	}

}
