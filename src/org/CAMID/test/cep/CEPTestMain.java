package org.CAMID.test.cep;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.CAMID.test.SensorAgent.SensorData;
import org.CAMID.test.cep.inputadapter.SubThread;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CEPTestMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		psenvsub sub = new psenvsub();

//		SensorData sensor = sub.sub("tcp://localhost:5555", "SensorData");
//		sub.sub("tcp://localhost:5555", "SensorData");
//		System.out.println(sensor.getId());
//		System.out.println(sensor.getTimestamp());
//		System.out.println(sensor.getValue());
//		System.out.println("Just test for function");
		// List<SensorData> list = paringSensorData.paringSensorData(dataList);
		// System.out.println(list.get(0).getId());
		// System.out.println(list.get(0).getTimestamp());
		// System.out.println(list.get(0).getValue());
		// System.out.println(dataList);
		String Sensor = SensorData.class.getName();
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
		EPAdministrator admin = epService.getEPAdministrator();
		EPRuntime epRuntime = epService.getEPRuntime();
		Map<String, Object> sensor = new HashMap<String, Object>();
		sensor.put("id", String.class);
		sensor.put("timestamp", String.class);
		sensor.put("value", double.class);

		admin.getConfiguration().addEventType("sensor", sensor);

		String expression = "select avg(value) from sensor.win:length_batch(5)";
		EPStatement stateinsertEPL = admin.createEPL(expression);
		stateinsertEPL.addListener(new AggergationListener());
		
		
		String expression1 = "select * from sensor(value>30)";
		EPStatement stateinsertEPL1 = admin.createEPL(expression1);
		stateinsertEPL1.addListener(new ExceptionListener());
		
		
		
		SubThread st = new SubThread("tcp://localhost:5563", "SensorData",epRuntime);
		new Thread(st).start();
		
	}
}
