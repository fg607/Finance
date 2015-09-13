package org.xdty.finance.model;

/**
 * Created by ty on 15-9-13.
 */
public enum MarketTime {

    DAY("1_DAY"),
    MONTH("1_MONTH"),
    YEAR("1_YEAR"),
    FIVE_YEAR("5_YEAR");

    private final String text;

    MarketTime(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
