package org.CAMID.test.cep.inputadapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.CAMID.test.SensorAgent.SensorData;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.espertech.esper.client.EPRuntime;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SubThread  implements Runnable{
	String URL;
	String DataType;
	int i =0;
	EPRuntime epRuntime;
	public SubThread(String URL,String DataType, EPRuntime epRuntime){
		this.URL=URL;
		this.DataType = DataType;
		this.epRuntime =epRuntime;
	}
	
	
	@Override
	public void run() {
		ObjectMapper mapper = new ObjectMapper();
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect(URL);
        subscriber.subscribe(DataType.getBytes());
        
        
        System.out.println(i++);
		// TODO Auto-generated method stub
		System.out.println("finish!");
		
        while (!Thread.currentThread ().isInterrupted ()) {
            String address = subscriber.recvStr ();
            String contents = subscriber.recvStr ();
            try {
            	System.out.println("get data Start");
            	System.out.println(contents);
				SensorData sensor = mapper.readValue(contents, SensorData.class);
				System.out.println(sensor.toString());
				Map<String, Object> sensorMap = new HashMap<String, Object>();
				sensorMap.put("id", sensor.getId());
				sensorMap.put("timestamp", sensor.getTimestamp());
				sensorMap.put("value", Double.parseDouble(sensor.getValue()));
				
				epRuntime.sendEvent(sensorMap, "sensor");
				
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            
//            System.out.println(sensor.getId());
//            System.out.println(sensor.getTimestamp());
//            System.out.println(sensor.getValue());
           

        }
        subscriber.close ();
        context.term ();
	}
	

}
