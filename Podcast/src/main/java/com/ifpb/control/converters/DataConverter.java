package com.ifpb.control.converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dataConverter")
public class DataConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);

        return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        LocalDate localDate = (LocalDate) value;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = LocalDateTime.from(localDate).format(formatter);
        return data;
    }

}