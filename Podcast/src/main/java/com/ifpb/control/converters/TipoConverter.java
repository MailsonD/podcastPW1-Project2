package com.ifpb.control.converters;

import com.ifpb.model.domain.Enum.Tipo;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("tipoConverter")
public class TipoConverter extends EnumConverter {

    public TipoConverter(){
        super(Tipo.class);
    }

}
