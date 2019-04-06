package com.ifpb.control.converters;

import com.ifpb.model.domain.Enum.Sexo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sexoConverter")
public class SexoConverter extends EnumConverter {

    public SexoConverter(){
        super(Sexo.class);
    }

}
