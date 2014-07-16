package se.jmkit.rest.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import se.jmkit.rest.client.http.RestURIBuilder;

public abstract class AbstractControllerClient {

    final RestTemplate restTemplate = new RestTemplate();
    protected RestURIBuilder restURIBuilder;
    protected String entityURI;

    // Create a list for the message converters
    private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

    public AbstractControllerClient() {
        super();
        // Add the Jackson Message converter
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        // Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);
    }

    public AbstractControllerClient(RestURIBuilder restURIBuilder) {
        this();
        this.restURIBuilder = restURIBuilder;
    }

    /**
     * Get URI as string from RestURIBuilder
     * 
     * @return the URL from the RestURIBuilder
     */
    protected String getURL() {
        String url = "";
        if (restURIBuilder != null) {
            url = restURIBuilder.getUriAsString() + "/" + entityURI;
        }
        return url;
    }

}
