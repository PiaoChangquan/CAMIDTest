package org.CAMID.test.cep;

import java.io.IOException;

import org.CAMID.test.cep.localcontrol.StopSensor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class ExceptionListener implements UpdateListener{

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		// TODO Auto-generated method stub
		if (newEvents != null) {
		
			
			for (int i = 0; i < newEvents.length; i++) {
				EventBean event = newEvents[i];
				System.out.println("");
				System.out.println("Received NewEvents: " + event.getUnderlying());
				try {
					String ss=StopSensor.StopSensor();
					System.out.println(ss);
									
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				}
		} else if (oldEvents != null) {
			for (int i = 0; i < oldEvents.length; i++) {
				EventBean event = oldEvents[i];
				System.out.println("Received OldEvents: " + event.getUnderlying());
			}
		}
	}
}
