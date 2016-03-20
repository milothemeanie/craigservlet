package org.milo.craigcrawl.craigservlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.milo.craigcrawl.craigservlet.om.HomeRent;
import org.milo.craigcrawl.craigservlet.om.RentByBedroom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PricePerBedroom implements AverageChart
{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PricePerBedroom.class);

	@Override
	public List<HomeRent> retrieveAverage()
	{
		final List<HomeRent> avgList = new ArrayList<>();

		try (final Connection con = CrawlDataSource.getInstance().getConnection();
				final Statement statement = con.createStatement())
		{
			final StringBuilder query = new StringBuilder();
			query.append("select neighborhood, bedroom , avg(price) as price from homerent hr ");
			query.append(tableJoins());
			query.append(" union all ");
			query.append("select 'Average' , bedroom, avg(price) as price from homerent hr ");
			query.append(tableJoins());

			final ResultSet rs = statement.executeQuery(query.toString());

			while (rs.next())
			{
				final RentByBedroom avg = new RentByBedroom();
				avg.setNeighborhood(rs.getString("neighborhood"));
				avg.setBedroom(rs.getInt("bedroom"));
				avg.setAverageprice(rs.getDouble("price"));
				
				avgList.add(avg);
			}

			rs.close();

		}
		catch (final SQLException e)
		{
			LOGGER.error("Failed to retrieve average" , e);
		}

		return avgList;
	}
	
	private String tableJoins()
	{
		final StringBuilder join = new StringBuilder();
		join.append("inner join addressneighborhood ad ON hr.id = ad.homerentid ");
		join.append("inner join location lo ON ad.locationid = lo.id ");
		join.append("where parentid = 0 ");
		join.append("and lo.city = 'Oklahoma City' ");
		join.append("and hr.invalid = false ");
		join.append("and bedroom is not null ");
		join.append("and bedroom < 5");
		join.append("and hr.price is not null ");
		join.append("group by neighborhood,bedroom ");
		
		return join.toString();
	}

}
