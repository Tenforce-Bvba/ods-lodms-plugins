package com.tenforce.lodms;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public interface ODSVoc {
  ValueFactory valueFactory = ValueFactoryImpl.getInstance();
  URI RDFTYPE = valueFactory.createURI("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
  String DCAT = "http://www.w3.org/ns/dcat";
  URI DCAT_CATALOG = valueFactory.createURI(DCAT + "#Catalog");
  URI DCAT_CATALOGRECORD = valueFactory.createURI(DCAT + "#CatalogRecord");
  URI DCAT_DATASET = valueFactory.createURI(DCAT + "#Dataset");
  URI DCAT_DISTRIBUTION = valueFactory.createURI(DCAT + "#Distribution");
  URI DCAT_CAT_PROP_DATASET = valueFactory.createURI(DCAT + "#dataset");
  URI DCAT_CAT_PROP_RECORD = valueFactory.createURI(DCAT + "#record");
  URI DCAT_DATASET_DISTRIBUTION = valueFactory.createURI(DCAT + "#distribution");
  URI DCAT_KEYWORD = valueFactory.createURI(DCAT + "#keyword");
  URI DCAT_MEDIA_TYPE = valueFactory.createURI(DCAT + "#mediaType");
  URI DCAT_THEME = valueFactory.createURI(DCAT + "#theme");
  URI DCAT_ACCESS_URL = valueFactory.createURI(DCAT + "#accessURL");
  URI DCAT_THEME_TAXONOMY = valueFactory.createURI(DCAT + "#themeTaxonomy");
  URI DCAT_LANDING_PAGE = valueFactory.createURI(DCAT + "#landingPage");
  URI DCAT_BYTE_SIZE = valueFactory.createURI(DCAT + "#byteSize");
  URI DCAT_DOWNLOAD_URL = valueFactory.createURI(DCAT + "#downloadURL");
  String FOAF = "http://xmlns.com/foaf/0.1/";
  URI FOAF_PRIMARYTOPIC = valueFactory.createURI(FOAF + "primaryTopic");
  URI FOAF_HOMEPAGE = valueFactory.createURI(FOAF + "homepage");
  String DC_TERMS = "http://purl.org/dc/terms/";
  URI DCT_PUBLISHER = valueFactory.createURI(DC_TERMS + "publisher");
  URI DCT_MODIFIED = valueFactory.createURI(DC_TERMS + "modified");
  URI DCT_DESCRIPTION = valueFactory.createURI(DC_TERMS + "description");
  URI DCT_TITLE = valueFactory.createURI(DC_TERMS + "title");
  URI DCT_LICENSE = valueFactory.createURI(DC_TERMS + "license");
  URI DCT_FORMAT = valueFactory.createURI(DC_TERMS + "format");
  URI DCT_SPATIAL = valueFactory.createURI(DC_TERMS + "spatial");
  URI DCT_LANGUAGE = valueFactory.createURI(DC_TERMS + "language");
  URI DCT_ACCRUAL_PERIODICTY = valueFactory.createURI(DC_TERMS + "accrualPeriodicity");
  URI DCT_TYPE = valueFactory.createURI(DC_TERMS + "type");
  URI DCT_ISSUED = valueFactory.createURI(DC_TERMS + "issued");
  URI DCT_RIGHTS = valueFactory.createURI(DC_TERMS + "rights");
  URI DCT_CONFORMS_TO = valueFactory.createURI(DC_TERMS + "conformsTo");
  URI DCT_IDENTIFIER = valueFactory.createURI(DC_TERMS + "identifier");
  URI DCT_TEMPORAL = valueFactory.createURI(DC_TERMS + "temporal");
  String ADMS_NS = "http://www.w3.org/ns/adms#";
  URI ADMS_STATUS = valueFactory.createURI(ADMS_NS + "status");
  URI ADMS_CONTACT_POINT = valueFactory.createURI(ADMS_NS + "contactPoint");
  URI ADMS_IDENTIFIER = valueFactory.createURI(ADMS_NS + "identifier");
  URI ODS_NS = valueFactory.createURI("http://data.opendatasupport.eu/ontology/harmonisation.owl#");
  URI ODS_RAW_CATALOG = valueFactory.createURI(ODS_NS + "raw_catalog");
  URI ODS_RAW_DATASET = valueFactory.createURI(ODS_NS + "raw_dataset");
  URI ODS_CONTENT_HASH = valueFactory.createURI(ODS_NS + "content_hash");
  URI ODS_HARVEST_DATE = valueFactory.createURI(ODS_NS + "harvest_date");
  URI ADMS_VERSION = valueFactory.createURI(ADMS_NS + "version");
  URI ADMS_VERSION_NOTES = valueFactory.createURI(ADMS_NS + "versionNotes");
}
