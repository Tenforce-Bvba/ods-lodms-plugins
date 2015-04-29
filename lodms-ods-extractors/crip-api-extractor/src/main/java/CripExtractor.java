import at.punkt.lodms.integration.ConfigBeanProvider;
import at.punkt.lodms.integration.ConfigurableBase;
import at.punkt.lodms.integration.ConfigurationException;
import at.punkt.lodms.integration.UIComponent;
import at.punkt.lodms.spi.extract.ExtractContext;
import at.punkt.lodms.spi.extract.ExtractException;
import at.punkt.lodms.spi.extract.Extractor;
import com.tenforce.lodms.extractors.CripExtractorConfig;
import com.vaadin.Application;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.Resource;
import org.openrdf.rio.RDFHandler;

public class CripExtractor extends ConfigurableBase<CripExtractorConfig> implements Extractor, UIComponent, ConfigBeanProvider<CripExtractorConfig> {

  /**
   * Returns a new (blank) JavaBean instance with its default values set.
   */
  public CripExtractorConfig newDefaultConfig() {
    return new CripExtractorConfig();
  }

  /**
   * Called after this.config is set, internal handling of config can be done here
   * @param config
   * @throws ConfigurationException
   */
  @Override
  protected void configureInternal(CripExtractorConfig config) throws ConfigurationException {

  }

  /**
   * Extracts data from a data source and converts it to RDF.<br/>
   *
   * @param handler This handler has to be used to store the produced RDF statements.<br/>
   * @param context Context for one extraction cycle containing meta information about the extraction.
   * @throws ExtractException If any error occurs troughout the extraction cycle.
   */
  public void extract(RDFHandler handler, ExtractContext context) throws ExtractException {
    try {

    }
    catch (Exception e) {
      throw new ExtractException(e.getMessage(),e.getCause());
    }
  }

  /**
   * Returns a short, self-descriptive name of the component.
   */
  public String getName() {
    return "CRIP API Extractor";
  }

  /**
   * Returns a description of what functionality this component provides.
   */
  public String getDescription() {
      return "Extracts metadata from the CRIP api.";
  }

  /**
   * Returns an icon as vaadin {@link Resource}, {@code null} if no icon is available.
   */
  public Resource getIcon(Application application) {
      return new ClassResource("/ods.png", application);
  }

  /**
   * Returns a string representing the configured internal state of this component.<br/>
   * This will be used to display this component after having been configured.
   */
  public String asString() {
    return getName();
  }
}
