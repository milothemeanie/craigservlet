package org.milo.craigcrawl.craigservlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.milo.craigcrawl.craigservlet.om.AverageByNeighborhood;
import org.milo.craigcrawl.craigservlet.om.HomeRent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DollarPerSquareFt implements AverageChart
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DollarPerSquareFt.class);
	
	private Map<Long, String> allLocations()
	{
		Map<Long, String> locations = new HashMap<>();

		try (final Connection con = CrawlDataSource.getInstance()
				.getConnection();
				final Statement statement = con.createStatement())
		{
			String query = "select id, neighborhood from location where parentid is not null ";

			final ResultSet rs = statement.executeQuery(query);

			while (rs.next())
			{
				locations.put(rs.getLong("id"), rs.getString("neighborhood"));
			}

			rs.close();

		}
		catch (final SQLException e)
		{
			LOGGER.error("Failed to retrieve locations" , e);
		}

		return locations;

	}


	@Override
	public List<HomeRent> retrieveAverage()
	{
		final List<HomeRent> avgList = new ArrayList<>();

		final AverageByNeighborhood root = new AverageByNeighborhood();
		root.setNeighborhood("Oklahoma City");
		root.setParent(null);
		root.setAveragesquarefeet(0d);
		root.setAverageprice(0d);

		avgList.add(root);
		
		final Map<Long, String> allLocations = allLocations();

		try (final Connection con = CrawlDataSource.getInstance()
				.getConnection();
				final Statement statement = con.createStatement())
		{
			final StringBuilder sb = new StringBuilder();
			sb.append("select neighborhood, (sum(squareft) / sum(price)) as price, count(1) as squareft, parentid from homerent hr ");
			sb.append("inner join addressneighborhood ad ON hr.id = ad.homerentid ");
			sb.append("inner join location lo ON ad.locationid = lo.id ");
			sb.append("where lo.city = 'Oklahoma City' ");
			sb.append("and hr.invalid = false ");
			sb.append("and hr.squareft is not null ");
			sb.append("and hr.price is not null ");
			sb.append("and neighborhood != 'Cougar Ridge' ");
			
			sb.append("group by neighborhood, parentid ");

			final ResultSet rs = statement.executeQuery(sb.toString());

			while (rs.next())
			{
				final AverageByNeighborhood neighborhood = new AverageByNeighborhood();
				neighborhood.setNeighborhood(rs.getString("neighborhood"));
				neighborhood.setAveragesquarefeet(rs.getDouble("squareft"));
				neighborhood.setAverageprice(rs.getDouble("price"));

				long parentid = rs.getLong("parentid");
				if (parentid > 0)
				{
					neighborhood.setParent(allLocations.get(parentid));
				}
				else
				{
					neighborhood.setParent("Oklahoma City");
				}

				avgList.add(neighborhood);
			}

			rs.close();

		}
		catch (final SQLException e)
		{
			LOGGER.error("Failed to retrieve Average" , e);
		}

		return avgList;
	}

}
