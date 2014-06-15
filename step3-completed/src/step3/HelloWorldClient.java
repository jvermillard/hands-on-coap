package step3;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

public class HelloWorldClient {

    public static void main(String[] args) {

        CoapClient client = new CoapClient("coap://localhost/Hello");
        
        CoapResponse response = client.get();
        
        if (response!=null) {
        
        	System.out.println( response.getCode() );
        	System.out.println( response.getOptions() );
        	System.out.println( response.getResponseText() );
        	
        } else {
        	
        	System.out.println("Request failed");
        	
        }
    }

}
