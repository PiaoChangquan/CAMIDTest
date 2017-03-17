package org.CAMID.test.cep;

import java.io.IOException;
import java.util.List;

import org.CAMID.test.SensorAgent.SensorData;

import com.fasterxml.jackson.databind.ObjectMapper;

public class paringSensorData {
	public static List<SensorData> paringSensorData(String jason){
		ObjectMapper mapper = new ObjectMapper();

		try {
			List<SensorData> dataList = mapper.readValue(jason,
					mapper.getTypeFactory().constructCollectionType(List.class, SensorData.class));
			return dataList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
}
