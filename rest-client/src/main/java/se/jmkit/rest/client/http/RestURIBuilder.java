package se.jmkit.rest.client.http;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestURIBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestURIBuilder.class);

    private String schema;
    private String host;
    private String path;

    public RestURIBuilder() {
        super();
    }

    public RestURIBuilder(String schema, String host, String path) {
        this();
        this.schema = schema;
        this.host = host;
        this.path = path;
    }

    public String getUriAsString() {
        return this.getUri().toString();
    }

    public URI getUri() {
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme(schema).setHost(host).setPath(path).build();
        } catch (URISyntaxException e) {
            LOGGER.debug(e.getMessage());
        }
        return uri;
    }

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema the schema to set
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

}
