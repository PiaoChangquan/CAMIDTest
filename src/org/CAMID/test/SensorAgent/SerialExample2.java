package org.CAMID.test.SensorAgent;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;

public class SerialExample2 {
	public static void main(String args[]) throws InterruptedException {
		System.out.println("<--Pi4J--> Serial Communication Example ...started.");
		System.out.println(" ... connect using settings: device: /dev/ttyACM0/ baudRate: 9600");
		System.out.println(" ... data received on serial port should bedisplayed below.");
		String device="/dev/ttyACM0";
		int baudRate = 9600;
		String ID = "0001";
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder sb = new StringBuilder();
		
		Context context = ZMQ.context(1);
		Socket publisher = context.socket(ZMQ.PUB);
		publisher.bind("tcp://*:5563");
		
		final Serial serial = SerialFactory.createInstance();
		try {
			serial.open(device, baudRate);
			while(true){
				try {	
					Date day=new Date();
					SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
					char temp = serial.read();
					
					if(temp!='\n'){
						sb.append(temp);
					}else{
						
						String vTemp = sb.toString();
						String[] value = vTemp.split("\r");
//						System.out.println("Id:"+ID + ",timestamp:" +time.format(day)+", value:"+ value[0]);
						SensorData sensordata = new SensorData(ID,time.format(day),value[0]);
//						System.out.println(sensordata.toString());
						
						
						
						String jsonData = "";
						jsonData = mapper.writeValueAsString(sensordata);
						publisher.sendMore ("SensorData");
						publisher.send (jsonData);
						sb = new StringBuilder();
						System.err.println(jsonData);
					}
				}
				catch(IllegalStateException ex){
					ex.printStackTrace();
					publisher.sendMore ("Exception");
					publisher.send ("Id:"+ID+" "+ ex.toString());
				} catch (Exception e) {
            		e.printStackTrace();

					publisher.sendMore ("Exception");
					publisher.send ("Id:"+ID+" "+ e.toString());
        		}
				//Thread.sleep(100);	
			}	
			
		}
		catch(SerialPortException ex) {
			System.out.println(" ==>> SERIAL SETUP FAILED : " +	ex.getMessage()); 

			publisher.sendMore ("Exception");
			publisher.send ("Id:"+ID+" "+ ex.getMessage());
	        publisher.close();
	        context.term();
			return;
		}
	}
}

