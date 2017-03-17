package org.CAMID.test.cep;

import java.io.IOException;

import org.CAMID.test.cep.localcontrol.StopSensor;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AggergationListener implements UpdateListener{

	@Override
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		// TODO Auto-generated method stub
		if (newEvents != null) {
			for (int i = 0; i < newEvents.length; i++) {
				EventBean event = newEvents[i];
				System.out.println("");
				System.out.println("Received NewEvents: " + event.getUnderlying());
//				System.out.println("Received NewEventsTypes: " + event.getEventType());
			
			}
		} else if (oldEvents != null) {
			for (int i = 0; i < oldEvents.length; i++) {
				EventBean event = oldEvents[i];
				System.out.println("Received OldEvents: " + event.getUnderlying());
			}
		}
	}
}
