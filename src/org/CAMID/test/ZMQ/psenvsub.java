package org.CAMID.test.ZMQ;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
* Pubsub envelope subscriber
*/

public class psenvsub {

    public static void main (String[] args) {

        // Prepare our context and subscriber
        Context context = ZMQ.context(1);
        Socket subscriber = context.socket(ZMQ.SUB);

        subscriber.connect("tcp://localhost:5563");
        subscriber.subscribe("SensorData".getBytes());
        while (!Thread.currentThread ().isInterrupted ()) {
            // Read envelope with address
            String address = subscriber.recvStr ();
//            System.out.println(address+"  address");
            // Read message contents
            String contents = subscriber.recvStr ();
//            String value = subscriber.recvStr ();
            System.out.println(address + " : " + contents);
        }
        subscriber.close ();
        context.term ();
    }
}