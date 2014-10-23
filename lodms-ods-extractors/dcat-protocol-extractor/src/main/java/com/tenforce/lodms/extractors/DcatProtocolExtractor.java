package com.tenforce.lodms.extractors;

import at.punkt.lodms.integration.*;
import at.punkt.lodms.spi.extract.ExtractContext;
import at.punkt.lodms.spi.extract.ExtractException;
import at.punkt.lodms.spi.extract.Extractor;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.sesame.SesameTripleCallback;
import com.vaadin.Application;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.Resource;
import org.openrdf.model.impl.ValueFactoryImpl;
import org.openrdf.rio.ParserConfig;
import org.openrdf.rio.RDFHandler;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

public class DcatProtocolExtractor extends ConfigurableBase<DcatProtocolExtractorConfig> implements Extractor, UIComponent, ConfigBeanProvider<DcatProtocolExtractorConfig> {
    private List<String> warnings = new ArrayList<String>();

    @Override
    public DcatProtocolExtractorConfig newDefaultConfig() {
        return new DcatProtocolExtractorConfig();
    }

    @Override
    protected void configureInternal(DcatProtocolExtractorConfig config) throws ConfigurationException {

    }

    @Override
    public void extract(RDFHandler handler, ExtractContext context) throws ExtractException {
        try {
            Object response = new Object();
            int page = 1;
            while (response != null) {
                response = getJson(page++);
                parseResponse(handler, response);
            }
        } catch (Exception e) {
            throw new ExtractException(e.getMessage(), e.getCause());
        }
        finally {
            context.getWarnings().addAll(warnings);
        }
    }

    /**
     * Retrieve a page of datasets from the dcat endpoint
     * @param page
     * @return the json wrapped in an object, null if the page could not be retrieved
     */
    private Object getJson(int page) {
        try {
            RestTemplate rest = getRestTemplate();
            HttpEntity<?> httpEntity = new HttpEntity<Object>(getHttpHeaders());
            ResponseEntity<Object> dataSetResponseEntity = rest.exchange(pageUrl(), HttpMethod.GET, httpEntity, Object.class, page);
            return dataSetResponseEntity.getBody();
        }
        catch (RestClientException e) {
            return null;
        }

    }

    private String pageUrl() {
        return config.getDcatProtocolEndpoint() + "?page={page}";
    }

    /**
     *
     * @return a rest template with a json message converter
     */
    private RestTemplate getRestTemplate() {
        RestTemplate rest = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJacksonHttpMessageConverter());
        rest.setMessageConverters(converters);
        return rest;
    }

    /**
     * builds HttpHeaders for the resttemplate client.
     * This sets the correct user agent, content-type etc
     * @return
     */
    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.<MediaType>asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "LODMS DCAT spec harvesting plugin");
        headers.setAcceptCharset(Arrays.<Charset>asList(Charset.forName("UTF-8")));
        return headers;
    }

    /**
     * parse a json body using jsonld and pass the resulting rdf to the rdf handler
     * @param handler RDFHandler
     * @param json object representation of the json message
     * @throws JsonLdError
     */
    private void parseResponse(RDFHandler handler, Object json) throws JsonLdError {
        SesameTripleCallback callback = new SesameTripleCallback(handler, ValueFactoryImpl.getInstance(), new ParserConfig(), null);
        JsonLdOptions options = new JsonLdOptions("http://data.opendatasupport.eu/raw/");
        JsonLdProcessor.toRDF(json, callback, options);
    }


    @Override
    public String getName() {
        return "Dcat Protocol Exctractor";
    }

    @Override
    public String getDescription() {
        return "Harvest catalog metadata from an endpoint using the datacalalog specification (http://spec.datacatalogs.org/).";
    }

    @Override
    public Resource getIcon(Application application) {
        return new ClassResource("/ods.png", application);
    }

    @Override
    public String asString() {
        return String.format("%s for %s", this.getName(), this.getConfig().getDcatProtocolEndpoint());
    }
}
