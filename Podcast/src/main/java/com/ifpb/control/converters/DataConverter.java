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

        LocalDate localDate = null;
        if(data!=null && !data.isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            localDate = LocalDate.parse(data, formatter);
        }

        return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String data = null;

        if(value!=null){
            LocalDate localDate = (LocalDate) value;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            data = LocalDate.from(localDate).format(formatter);
        }

        return data;
    }

}