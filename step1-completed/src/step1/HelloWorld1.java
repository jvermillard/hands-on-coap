package step1;

import ch.ethz.inf.vs.californium.server.Server;
import ch.ethz.inf.vs.californium.server.resources.CoapExchange;
import ch.ethz.inf.vs.californium.server.resources.ResourceBase;

public class HelloWorld1 {

    public static void main(String[] args) {

        // binds on UDP port 5683
        Server server = new Server();

        server.add(new HelloResource());

        server.start();
    }

    public static class HelloResource extends ResourceBase {
        public HelloResource() {

            // resource identifier
            super("Hello");

            // set display name
            getAttributes().setTitle("Hello-World Resource");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond("Hello world!");
        }
    }

}
