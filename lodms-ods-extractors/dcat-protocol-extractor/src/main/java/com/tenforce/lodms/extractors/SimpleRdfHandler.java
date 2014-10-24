package com.tenforce.lodms.extractors;

import org.openrdf.model.Statement;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.helpers.RDFHandlerBase;

public class SimpleRdfHandler extends RDFHandlerBase {
    private LinkedHashModel statements = new LinkedHashModel(100);

    @Override
    public void handleStatement(Statement st) throws RDFHandlerException {
        statements.add(st);
    }

    public LinkedHashModel getStatements() {
        return statements;
    }

}
