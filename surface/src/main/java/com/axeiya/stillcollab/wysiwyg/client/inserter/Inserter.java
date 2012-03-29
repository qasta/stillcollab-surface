package com.axeiya.stillcollab.wysiwyg.client.inserter;

import com.axeiya.stillcollab.wysiwyg.client.ranges.SurfaceSelection;
import com.google.gwt.dom.client.Element;

abstract public class Inserter {

  abstract public void insert(SurfaceSelection selection);

  abstract public void remove(SurfaceSelection selection);

  abstract public boolean isSelectionAssignee(SurfaceSelection selection);

  abstract protected boolean adjustSelectionAssignee(Element matchingAncestor, SurfaceSelection selection);
}
