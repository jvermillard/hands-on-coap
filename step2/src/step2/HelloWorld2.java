package step2;

import ch.ethz.inf.vs.californium.server.Server;
import ch.ethz.inf.vs.californium.server.resources.CoapExchange;
import ch.ethz.inf.vs.californium.server.resources.ResourceBase;

public class HelloWorld2 {

    public static void main(String[] args) {

        // binds on UDP port 5683
        Server server = new Server();

        // "hello"
        server.add(new HelloResource());

        // TODO "subpath/Another"

        // TODO "removeme!, "time", "writeme!"

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

    public static class AnotherResource extends ResourceBase {
        public AnotherResource() {

            // resource identifier
            super("Another");

            // set display name
            getAttributes().setTitle("Another Hello-World Resource");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond("Fun with CoAP!");
        }
    }

    public static class RemovableResource extends ResourceBase {
        public RemovableResource() {
            super("removeme!");
        }
        // TODO
    }

    public static class TimeResource extends ResourceBase {

        public TimeResource() {
            super("time");
        }
        // TODO
    }

    public static class WritableResource extends ResourceBase {

        public String value = "to be replaced";

        public WritableResource() {
            super("writeme!");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(value);
        }

        // TODO

    }

}