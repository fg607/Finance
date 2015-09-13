package org.xdty.finance.model;

/**
 * Created by ty on 15-9-13.
 */
public enum MarketID {

    USDCNH("USDCNH:CUR"),
    USDCNY("USDCNY:CUR"),
    EURCNY("EURCNY:CUR"),
    SHCOMP("SHCOMP:IND");

    private final String text;

    MarketID(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
