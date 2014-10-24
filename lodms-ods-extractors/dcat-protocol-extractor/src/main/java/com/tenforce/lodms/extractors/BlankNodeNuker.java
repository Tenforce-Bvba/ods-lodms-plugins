package com.tenforce.lodms.extractors;


import org.openrdf.model.*;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.impl.StatementImpl;
import org.openrdf.model.impl.URIImpl;

import java.net.URL;
import java.util.UUID;

/**
 * This class helps in converting blank nodes in a Model to URIs.
 * It was largely inspired by the BlankNodeNuker in Edcat https://github.com/tenforce/edcat-base/blob/master/edcat-plugin-base/src/main/java/eu/lod2/edcat/utils/BlankNodeNuker.java
 */
public class BlankNodeNuker {
    /**
     * JsonLdContext for translating predicates
     */
    private JsonLdContext ldContext;

    /**
     * Constructs a new BlankNodeNuker
     *
     * @param context json context based on which the nodes will be nuked.  This calculates the
     *              paths.
     */
    public BlankNodeNuker(URL context) {
        this.ldContext = new JsonLdContext(context);
    }

    /**
     * Nukes the understood blank nodes in model and translates them to URIs.
     * @param model sets of statements to parse
     */
    public void nukeBlankNodes(Model model) {
        // until we have changes
        int changes;
        do {
            changes = 0;
            // walk over each triple
            Model walkingModel = new LinkedHashModel(model);
            for (Statement s : walkingModel) {
                walkingModel = new LinkedHashModel(model); // reinitialize model, some triples may have changed during this step
                Resource target = s.getSubject();
                // if the triple is a blank node
                if (target instanceof BNode
                        && model.contains(s.getSubject(), s.getPredicate(), s.getObject())) {
                    // find a connection which refers to our blank node through a predicate known by the jsonLdContext
                    for (Statement nameConnection : walkingModel.filter(null, null, s.getSubject())) {
                        Resource connectingSubject = nameConnection.getSubject();
                        URI connectingPredicate = nameConnection.getPredicate();
                        if (ldContext.getReverseKeywordMap().containsKey(connectingPredicate.stringValue())
                                && connectingSubject instanceof URI) {
                            // build the new URI
                            URI newTarget = new URIImpl(""
                                    + connectingSubject.stringValue() + "/"
                                    + ldContext.getReverseKeywordMap().get(connectingPredicate.stringValue()) + "/"
                                    + UUID.randomUUID());
                            // replace the triples which have the blank node as subject or as object.
                            for (Statement changeMySubject : walkingModel.filter(target, null, null)) {
                                model.remove(changeMySubject);
                                model.add(new StatementImpl(newTarget, changeMySubject.getPredicate(), changeMySubject.getObject()));
                            }
                            for (Statement changeMyObject : walkingModel.filter(null, null, target)) {
                                model.remove(changeMyObject);
                                model.add(new StatementImpl(changeMyObject.getSubject(), changeMyObject.getPredicate(), newTarget));
                            }
                            // indicate something changed
                            changes++;
                        }
                    }
                }
            }
        } while (changes > 0);
    }
}