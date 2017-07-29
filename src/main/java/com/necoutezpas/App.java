package com.necoutezpas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.necoutezpas.data.Field;
import com.necoutezpas.data.IntField;
import com.necoutezpas.data.Request;
import com.necoutezpas.data.StringField;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final ObjectMapper om = new ObjectMapper()
            .registerModule(new GuavaModule());
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        Request r = Request.builder().id("hello").build();
        String jsonR = om.writeValueAsString(r);

        Request r2 = r.toBuilder().id(null).count(3)
                .value("test").build();
        String jsonR2 = om.writeValueAsString(r2);

        LOGGER.info("{} -> {}", r, r2);
        LOGGER.info("{} -> {}", jsonR, jsonR2);
        LOGGER.info("{} -> {}", om.readValue(jsonR, Request.class), om.readValue(jsonR2, Request.class));

        IntField intField = IntField.builder().data(54).build();
        StringField stringField = StringField.builder().data("Hello world").build();
        LOGGER.info("{}, {}", intField, stringField);

        String intFieldJson = om.writeValueAsString(intField.toBuilder().data(42).build());
        String stringFieldJson = om.writeValueAsString(stringField);

        LOGGER.info("{}, {}", intFieldJson, stringFieldJson);

        LOGGER.info("{}, {}", om.readValue(intFieldJson, Field.class), om.readValue(stringFieldJson, Field.class));

        String customJson = "{\"data\":\"Hello world\",\"messages\":[],\"type\":\"string\"}";
        LOGGER.info("{}", om.readValue(customJson, Field.class));



        Server server = new Server(8080);

        final HandlerList handlers = new HandlerList();

        // Handler for Swagger UI, static handler.
        handlers.addHandler(buildSwaggerUI());

        ResourceConfig config = new ResourceConfig().register(JacksonFeature.class);
        config.packages("com.necoutezpas.resource");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));
        ServletContextHandler context = new ServletContextHandler(server, "/test");
        context.addServlet(servlet, "/*");

        // Handler for Test API
        handlers.addHandler(context);

        server.setHandler(handlers);

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    private static ContextHandler buildSwaggerUI() throws Exception
    {
        final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
        swaggerUIResourceHandler.setResourceBase(App.class.getClassLoader().getResource( "swaggerui" ).toURI().toString() );
        final ContextHandler swaggerUIContext = new ContextHandler();
        swaggerUIContext.setContextPath("/docs");
        swaggerUIContext.setHandler(swaggerUIResourceHandler);

        return swaggerUIContext;
    }
}
