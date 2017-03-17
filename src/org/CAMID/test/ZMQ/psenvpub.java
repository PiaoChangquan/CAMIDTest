package org.CAMID.test.ZMQ;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.CAMID.test.SensorAgent.SensorData;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
* Pubsub envelope publisher
*/

public class psenvpub {

    public static void main (String[] args) throws Exception {
        // Prepare our context and publisher
        Context context = ZMQ.context(1);
        Socket publisher = context.socket(ZMQ.PUB);

        publisher.bind("tcp://*:5555");
        while (!Thread.currentThread ().isInterrupted ()) {
        	 Random rand = new Random();
            // Write two messages, each with an envelope and content
        	 Date day=new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	SensorData sensor = new SensorData("5042d537-c3b3-400e-8347-7d2eb3944a1b", time.format(day), String.valueOf(rand.nextDouble()*100));
            publisher.sendMore ("SensorData");
            publisher.send (sensor.toString());
//            publisher.send ("27.4");
//            publisher.s
            publisher.sendMore ("B");
            publisher.send("We would like to see this");
            Thread.sleep(1000);
            
        }
        publisher.close();
        context.term();
    }
}