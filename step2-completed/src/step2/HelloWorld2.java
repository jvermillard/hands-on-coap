package step2;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;

public class HelloWorld2 {

    public static void main(String[] args) {

        // binds on UDP port 5683
        CoapServer server = new CoapServer();

        // "hello"
        server.add(new HelloResource());

        // "subpath/Another"
        CoapResource path = new CoapResource("subpath");
        path.add(new AnotherResource());
        server.add(path);

        // "removeme!, "time", "writeme!"
        server.add(new RemovableResource(), new TimeResource(), new WritableResource());

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

        @Override
        public void handleDELETE(CoapExchange exchange) {
            delete();
            exchange.respond(DELETED);
        }
    }

    public static class TimeResource extends CoapResource {

        public TimeResource() {
            super("time");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(String.valueOf(System.currentTimeMillis()));
        }
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

        @Override
        public void handlePUT(CoapExchange exchange) {
            byte[] payload = exchange.getRequestPayload();

            try {
                value = new String(payload, "UTF-8");
                exchange.respond(CHANGED, value);
            } catch (Exception e) {
                e.printStackTrace();
                exchange.respond(BAD_REQUEST, "Invalid String");
            }
        }
    }

}