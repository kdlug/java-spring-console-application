package com.github.kdlug;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

public class ConsoleArgs {
    @NotNull(message = "Should be not empty")
    private String type;

    @NotNull(message = "Should be not empty")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(Date date) {
        this.dateFrom = new Date(date.getTime());
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date date) {
        this.dateTo = new Date(date.getTime());
    }

    @Override
    public String toString() {
        return "ConsoleArgs{" +
                "type='" + type + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
