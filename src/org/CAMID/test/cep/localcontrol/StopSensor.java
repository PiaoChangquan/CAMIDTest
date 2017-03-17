package org.CAMID.test.cep.localcontrol;

import java.io.IOException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class StopSensor {
	
//	public static void main(String[] arge) throws IOException{
	public static String StopSensor() throws ClientProtocolException, IOException{
		
//		URL url = new URL(surl);
//		url.openConnection();
//		URIBuilder builder = new URIBuilder(Url+StreamID);
		// Call RESTful API for forecast
		
		
		String surl = "http://192.168.1.112:8002/killthisSensor";
		HttpClient client = HttpClients.createDefault();
		HttpGet getStubMethod = new HttpGet(surl);
		HttpResponse getStubResponse = client.execute(getStubMethod);
		int getStubStatusCode = getStubResponse.getStatusLine().getStatusCode();
		if (getStubStatusCode < 200 || getStubStatusCode >= 300) {
			return null;
		}
		System.out.println("in the stop sensor funcation");
		return "Stop Sensor";
	}	
}
