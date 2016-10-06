package com.waytechs.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.waytechs.model.enums.CostUOM;
import com.waytechs.model.enums.MaintType;
import com.waytechs.model.enums.YesNo;

@Converter(autoApply=true)  
public class MaintTypeConverter implements AttributeConverter<MaintType, String> { 

	@Override
	public String convertToDatabaseColumn(MaintType attribute) {
		System.out.println("convertToDatabaseColumn: " + attribute);
		switch (attribute) {
		case CORRECTIVO:
			return "C";
		case CORRECTIVO_URGENTE:
			return "U";
		case PREVENTIVO:
			return "P";		 
		default:
			throw new IllegalArgumentException("convertToDatabaseColumn Unknown: " + attribute);
		}
	}

	@Override
	public MaintType convertToEntityAttribute(String dbData) {
		System.out.println("convertToEntityAttribute: " + dbData);
		if(dbData != null){
			switch (dbData) {
			case "C":
				return MaintType.CORRECTIVO;
			case "U":
				return MaintType.CORRECTIVO_URGENTE;
			case "P":
				return MaintType.PREVENTIVO;   
			default:
				throw new IllegalArgumentException("convertToEntityAttribute Unknown: " + dbData);
			}	
		}else{
			return null; 	
		}
		
	}

}
