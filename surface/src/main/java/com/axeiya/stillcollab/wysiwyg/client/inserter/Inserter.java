package com.axeiya.stillcollab.wysiwyg.client.inserter;

import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.google.gwt.dom.client.Element;

abstract public class Inserter {

  abstract public void insert(Selection selection);

  abstract public void remove(Selection selection);

  abstract public boolean isSelectionAssignee(Selection selection);

  abstract protected boolean adjustSelectionAssignee(Element matchingAncestor, Selection selection);
}
