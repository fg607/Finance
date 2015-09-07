package org.xdty.finance.model;

import com.google.gson.annotations.Expose;

public class Price {

    @Expose
    private String date;
    @Expose
    private Double value;

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(Double value) {
        this.value = value;
    }

}