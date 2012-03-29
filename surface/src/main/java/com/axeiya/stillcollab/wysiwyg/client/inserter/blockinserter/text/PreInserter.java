package com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text;

import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.BlockInserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.SurfaceSelection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.PreElement;

public class PreInserter extends BlockInserter<PreElement> {

  protected static final PreInsertAction insertAction = new PreInsertAction();

  public PreInserter() {
    this(insertAction);
  }

  protected PreInserter(InsertAction<PreElement> action) {
    super(action);
  }

  protected static class PreInsertAction extends InsertAction<PreElement> {
    private static final PreElement emptyElement = Document.get().createPreElement();

    @Override
    public void onAction(PreElement element, SurfaceSelection selection) {
      if (!DOMUtil.hasStrongNode(element)) {
        element.appendChild(DOMUtil.createFocusBr());
      }
    }

    @Override
    public PreElement getEmptyElement() {
      return emptyElement;
    }
  }

  @Override
  protected PreElement as(Element element) {
    return PreElement.as(element);
  }
}