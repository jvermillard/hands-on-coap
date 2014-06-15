package step2;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class HelloWorld2 {

    public static void main(String[] args) {

        // binds on UDP port 5683
        CoapServer server = new CoapServer();

        // "hello"
        server.add(new HelloResource());

        // TODO "subpath/Another"

        // TODO "removeme!, "time", "writeme!"

        server.start();
    }

    public static class HelloResource extends CoapResource {
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

    public static class AnotherResource extends CoapResource {
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

    public static class RemovableResource extends CoapResource {
        public RemovableResource() {
            super("removeme!");
        }
        // TODO
    }

    public static class TimeResource extends CoapResource {

        public TimeResource() {
            super("time");
        }
        // TODO
    }

    public static class WritableResource extends CoapResource {

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