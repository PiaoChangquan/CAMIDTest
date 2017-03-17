package org.CAMID.test.cep;
import java.io.IOException;
import java.util.List;

import org.CAMID.test.SensorAgent.SensorData;
import org.CAMID.test.cep.paringSensorData;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* Pubsub envelope subscriber
*/

public class psenvsub {

    public static  void sub(String URL,String tital) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        // Prepare our context and subscriber
    	
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect(URL);
        subscriber.subscribe(tital.getBytes());
//        subscriber.connect("tcp://localhost:5555");
//        subscriber.subscribe("SensorData".getBytes());
        
        while (!Thread.currentThread ().isInterrupted ()) {
            String address = subscriber.recvStr ();
            String contents = subscriber.recvStr ();
//            return contents;
            System.out.println(contents);
            SensorData sensor = mapper.readValue(contents, SensorData.class);
//            
//            System.out.println(sensor.getId());
//            System.out.println(sensor.getTimestamp());
//            System.out.println(sensor.getValue());
           
//            List<SensorData> list = paringSensorData.paringSensorData(contents);
//            System.out.println(list.get(0).toString());
//            list.get(0).getId();
        }
        subscriber.close ();
        context.term ();
//		return "0";
    }

}
	