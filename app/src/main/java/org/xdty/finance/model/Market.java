package org.xdty.finance.model;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ty on 15-9-4.
 */
public interface Market {

    @GET("/price/{id}")
    List<MarketPrice> price(
            @Path("id") String id,
            @Query("timeFrame") String timeFrame
    );

}
