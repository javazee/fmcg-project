package ru.retail.expert.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LoadShipmentsDto {
    private List<LocalDate> dates;
    private List<String> chains;
    private List<Long> products;

    @Override
    public String toString() {
        return "LoadShipmentsDto{" +
                "dates=" + dates +
                ", chains=" + chains +
                ", products=" + products +
                '}';
    }
}
