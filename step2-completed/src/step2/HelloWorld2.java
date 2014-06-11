package step2;

import ch.ethz.inf.vs.californium.coap.CoAP.ResponseCode;
import ch.ethz.inf.vs.californium.server.Server;
import ch.ethz.inf.vs.californium.server.resources.CoapExchange;
import ch.ethz.inf.vs.californium.server.resources.ResourceBase;

public class HelloWorld2 {

    public static void main(String[] args) {

        // binds on UDP port 5683
        Server server = new Server();

        // "hello"
        server.add(new HelloResource());

        // "subpath/Another"
        ResourceBase path = new ResourceBase("subpath");
        path.add(new AnotherResource());
        server.add(path);

        // "removeme!, "time", "writeme!"
        server.add(new RemovableResource(), new TimeResource(), new WritableResource());

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

        @Override
        public void handleDELETE(CoapExchange exchange) {
            delete();
            exchange.respond(ResponseCode.DELETED);
        }
    }

    public static class TimeResource extends ResourceBase {

        public TimeResource() {
            super("time");
        }

        @Override
        public void handleGET(CoapExchange exchange) {
            exchange.respond(String.valueOf(System.currentTimeMillis()));
        }
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

        @Override
        public void handlePUT(CoapExchange exchange) {
            byte[] payload = exchange.getRequestPayload();

            try {
                value = new String(payload, "UTF-8");
                exchange.respond(ResponseCode.CHANGED, value);
            } catch (Exception e) {
                e.printStackTrace();
                exchange.respond(ResponseCode.BAD_REQUEST, "Invalid String");
            }
        }
    }

}