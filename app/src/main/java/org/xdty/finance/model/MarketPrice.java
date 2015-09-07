package org.xdty.finance.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ty on 15-9-4.
 */
public class MarketPrice {

    @Expose
    private String id;
    @Expose
    private List<Price> price = new ArrayList<Price>();
    @Expose
    private Long timeZoneOffset;
    @Expose
    private String nyTradeStartTime;
    @Expose
    private String nyTradeEndTime;
    @Expose
    private Long priceMinDecimals;
    @Expose
    private String lastUpdateDate;
    @Expose
    private Double lastPrice;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The price
     */
    public List<Price> getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(List<Price> price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The timeZoneOffset
     */
    public Long getTimeZoneOffset() {
        return timeZoneOffset;
    }

    /**
     *
     * @param timeZoneOffset
     * The timeZoneOffset
     */
    public void setTimeZoneOffset(Long timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
    }

    /**
     *
     * @return
     * The nyTradeStartTime
     */
    public String getNyTradeStartTime() {
        return nyTradeStartTime;
    }

    /**
     *
     * @param nyTradeStartTime
     * The nyTradeStartTime
     */
    public void setNyTradeStartTime(String nyTradeStartTime) {
        this.nyTradeStartTime = nyTradeStartTime;
    }

    /**
     *
     * @return
     * The nyTradeEndTime
     */
    public String getNyTradeEndTime() {
        return nyTradeEndTime;
    }

    /**
     *
     * @param nyTradeEndTime
     * The nyTradeEndTime
     */
    public void setNyTradeEndTime(String nyTradeEndTime) {
        this.nyTradeEndTime = nyTradeEndTime;
    }

    /**
     *
     * @return
     * The priceMinDecimals
     */
    public Long getPriceMinDecimals() {
        return priceMinDecimals;
    }

    /**
     *
     * @param priceMinDecimals
     * The priceMinDecimals
     */
    public void setPriceMinDecimals(Long priceMinDecimals) {
        this.priceMinDecimals = priceMinDecimals;
    }

    /**
     *
     * @return
     * The lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     *
     * @param lastUpdateDate
     * The lastUpdateDate
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     *
     * @return
     * The lastPrice
     */
    public Double getLastPrice() {
        return lastPrice;
    }

    /**
     *
     * @param lastPrice
     * The lastPrice
     */
    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

}
