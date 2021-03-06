package com.tenforce.lodms.transformers;

import at.punkt.lodms.integration.ConfigDialog;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.openrdf.model.URI;
import org.openrdf.model.impl.URIImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TranslatorDialog extends VerticalLayout implements ConfigDialog {
  private final Form form = new Form();
  private TranslatorConfig config;
  private final BeanItemContainer urlList = new BeanItemContainer(URI.class);

  public TranslatorDialog(TranslatorConfig tConfig) {
    config = tConfig;
    urlList.addAll(config.DEFAULT_PREDICATES);

    form.setFormFieldFactory(new DefaultFieldFactory() {
      @Override
      public Field createField(Item item, Object propertyId, Component uiContext) {
        if ("predicates".equals(propertyId)) {
          ListSelect box = new ListSelect("predicates to translate");
          box.setMultiSelect(true);
          box.setContainerDataSource(urlList);
          box.setValue(config.getPredicates());
          box.setRows(3);
          box.setNewItemsAllowed(true);
          for (URI predicate : config.getPredicates())
            box.addItem(predicate.stringValue());
          return box;
        } else if ("translationCache".equals("propertyId")) {
          TextField uriField = new TextField("Translation Cache Graph");
          uriField.setWidth(350, VerticalLayout.UNITS_PIXELS);
          uriField.setDescription("Graph URI were translations are stored");
          uriField.setImmediate(true);
          uriField.addValidator(new AbstractStringValidator(null) {
            @Override
            protected boolean isValidString(String value) {
              try {
                new URIImpl(value);
                return true;
              } catch (Exception ex) {
                setErrorMessage("Invalid graph URI: " + ex.getMessage());
                return false;
              }
            }
          });
          return uriField;
        } else
          return super.createField(item, propertyId, uiContext);
      }
    });
    form.setItemDataSource(new BeanItem<TranslatorConfig>(this.config));
    form.setVisibleItemProperties(new String[]{"providerClientID", "providerClientSecret", "predicates", "translationCache"});
    addComponent(form);
  }

  @Override
  public Object getConfig() {
    List<URI> selectedPred = new ArrayList<URI>();
    selectedPred.addAll((Collection<URI>) form.getField("predicates").getValue());
    config.setPredicates(selectedPred);
    config.setProviderClientID(form.getField("providerClientID").getValue().toString());
    config.setProviderClientSecret(form.getField("providerClientSecret").getValue().toString());
    config.setTranslationCache(form.getField("translationCache").getValue().toString());
    return config;
  }
}
