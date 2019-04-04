/**
 * 
 */
package com.utils;

import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sun.glass.ui.CommonDialogs.Type;

/**
 * @author drana
 *
 */
public class DotNetDateDeserializer implements JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonElement json, java.lang.reflect.Type typfOfT, JsonDeserializationContext context)
			throws JsonParseException {
		// TODO Auto-generated method stub
		try {
			String dateStr = json.getAsString();
			if (dateStr != null)
				dateStr = dateStr.replace("/Date(", "");
			if (dateStr != null)
				dateStr = dateStr.replace("-0500)/", "");
			if (dateStr != null)
				dateStr = dateStr.replace(")/", "");
			long time = Long.parseLong(dateStr);
			
			return new Date(time);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

}
