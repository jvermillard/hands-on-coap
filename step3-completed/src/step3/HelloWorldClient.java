package step3;

import ch.ethz.inf.vs.californium.coap.Request;
import ch.ethz.inf.vs.californium.coap.Response;

public class HelloWorldClient {

    public static void main(String[] args) {
        Request request = Request.newGet();

        request.setURI("coap://localhost/Hello");

        request.send();

        // receive response
        try {
            Response response = request.waitForResponse(1000);

            if (response != null) {
                // response received, output a pretty-print
                System.out.println("RESPONSE RECEIVED!");
                System.out.println(response);
            } else {
                System.out.println("No response received.");
            }

        } catch (InterruptedException e) {
            System.err.println("Receiving of response interrupted: " + e.getMessage());
            System.exit(-1);
        }
    }

}
