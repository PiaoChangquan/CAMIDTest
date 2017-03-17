package org.CAMID.test.cep;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.CAMID.test.SensorAgent.SensorData;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Pubsub envelope publisher
 */

public class psenvpub {

	public static void main(String[] args) throws Exception {
		// Prepare our context and publisher
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		ObjectMapper mapper = new ObjectMapper();

		publisher.bind("tcp://*:5563");
		while (!Thread.currentThread().isInterrupted()) {

			// Write two messages, each with an envelope and content
			Random rand = new Random();
			Date day = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SensorData sensor = new SensorData("5042d537-c3b3-400e-8347-7d2eb3944a1b", time.format(day),
					String.valueOf(rand.nextDouble() * 100));
			
			String jsonData = "";
			jsonData = mapper.writeValueAsString(sensor);
			publisher.sendMore("SensorData");
			publisher.send(jsonData);
			// publisher.send ("27.4");
			// publisher.s
//			publisher.sendMore("B");
//			publisher.send("We would like to see this");
			Thread.sleep(1000);

		}
		publisher.close();
		context.term();
	}
}