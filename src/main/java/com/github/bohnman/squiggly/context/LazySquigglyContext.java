package com.github.bohnman.squiggly.context;

import com.github.bohnman.squiggly.parser.SquigglyNode;
import com.github.bohnman.squiggly.parser.SquigglyParser;
import net.jcip.annotations.NotThreadSafe;

import java.util.List;

/**
 * Squiggly context that loads the parsed nodes on demand.
 */
@NotThreadSafe
public class LazySquigglyContext implements SquigglyContext {

    private final Object object;
    private final String filter;
    private List<SquigglyNode> nodes;
    private final SquigglyParser parser;

    public LazySquigglyContext(Object object, SquigglyParser parser, String filter) {
        this.object = object;
        this.parser = parser;
        this.filter = filter;
    }

    @Override
    public Object getObject() {
        return object;
    }

    @Override
    public List<SquigglyNode> getNodes() {
        if (nodes == null) {
            nodes = parser.parse(filter);
        }

        return nodes;
    }

    @Override
    public String getFilter() {
        return filter;
    }
}
