package org.milo.craigcrawl.craigservlet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.milo.craigcrawl.craigservlet.om.DistrictBedroom;
import org.milo.craigcrawl.craigservlet.om.HomeRent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
		final AverageChart average = new DollarPerSquareFt();

		return serializer.deepSerialize(average.retrieveAverage());

	}

	@GET
	@Path("/combochart")
	@Produces(MediaType.APPLICATION_JSON)
	public String comboChartBedroom()
	{
		final JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.prettyPrint(true);
		final AverageChart average = new PricePerBedroom();

		return serializer.deepSerialize(convertToDistrictBedroom(average.retrieveAverage()));
	}

	private List<DistrictBedroom> convertToDistrictBedroom(
			final List<HomeRent> rentList)
	{
		final Multimap<Integer, HomeRent> multi = ArrayListMultimap.create();

		for (final HomeRent homerent : rentList)
		{
			multi.put(homerent.getBedroom(), homerent);
		}

		final List<DistrictBedroom> districtList = new ArrayList<>();
		for (int key : multi.keySet())
		{
			final DistrictBedroom district = new DistrictBedroom();
			for (final HomeRent rent : multi.get(key))
			{
				district.setBedroom(key);
				final String neighborhood = StringUtils.trim(StringUtils.remove(rent.getNeighborhood(), "Oklahoma City"));
				switch (neighborhood)
				{
					case "Northwest":
						district.setNorthwest(rent.getAverageprice());
						break;
					case "Northeast":
						district.setNortheast(rent.getAverageprice());
						break;
					case "Central":
						district.setCentral(rent.getAverageprice());
						break;
					case "Southwest":
						district.setSouthwest(rent.getAverageprice());
						break;
					case "South":
						district.setSouth(rent.getAverageprice());
						break;
					case "Southeast":
						district.setSoutheast(rent.getAverageprice());
						break;
					case "Average":
						district.setAverage(rent.getAverageprice());
						break;
					 default:
					       throw new IllegalArgumentException();

				}
			}
			districtList.add(district);
		}

		return districtList;

	}
}
