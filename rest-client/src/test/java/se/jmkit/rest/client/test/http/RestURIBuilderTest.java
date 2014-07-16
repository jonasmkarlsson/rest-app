package se.jmkit.rest.client.test.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;

import se.jmkit.rest.client.http.RestURIBuilder;

public class RestURIBuilderTest {

    private String schema;
    private String host;
    private String path;

    RestURIBuilder restURIBuilder;

    @Before
    public void before() {
        this.schema = "http";
        this.host = "localhost:8080";
        this.path = "/rest/person";
        restURIBuilder = new RestURIBuilder();
    }

    @Test
    public void testRestURIBuilder() {
        assertNull(restURIBuilder.getSchema());
        assertNull(restURIBuilder.getHost());
        assertNull(restURIBuilder.getPath());
    }

    @Test
    public void testGetUri() throws URISyntaxException {
        restURIBuilder = new RestURIBuilder(schema, host, path);
        assertEquals(this.schema + "://" + this.host + this.path, restURIBuilder.getUriAsString());

        URI uri = new URIBuilder().setScheme(schema).setHost(host).setPath(path).build();
        assertEquals(uri, restURIBuilder.getUri());
    }

    @Test
    public void testSetAndGet() {
        restURIBuilder.setSchema(schema);
        restURIBuilder.setHost(host);
        restURIBuilder.setPath(path);
        assertEquals(this.schema, restURIBuilder.getSchema());
        assertEquals(this.host, restURIBuilder.getHost());
        assertEquals(this.path, restURIBuilder.getPath());
    }

}
