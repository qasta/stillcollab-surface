package com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter;

import java.util.List;

import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadingElement;

abstract public class HInserter extends ParagraphInserter<HeadingElement> {

  protected static class HInsertAction extends InsertAction<HeadingElement> {
    private HeadingElement emptyElement;

    public HInsertAction(int headLevel) {
      emptyElement = Document.get().createHElement(headLevel);
    }

    @Override
    public void onAction(HeadingElement element, Selection selection) {
      if (!DOMUtil.hasStrongNode(element)) {
        element.appendChild(DOMUtil.createFocusBr());
      }
    }

    @Override
    public HeadingElement getEmptyElement() {
      return emptyElement;
    }
  }

  protected HInserter(int headLevel) {
    this(new HInsertAction(headLevel));
  }

  protected HInserter(InsertAction<HeadingElement> action) {
    super(action);
  }

  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, Selection selection) {
    return true;
  }

  @Override
  protected List<String> getTagCollection() {
    return PInserter.PARAGRAPH_TAGS;
  }

  @Override
  protected Element getDefaultElement() {
    return PInserter.DEFAULT_ELEMENT;
  }

  @Override
  protected HeadingElement as(Element element) {
    return HeadingElement.as(element);
  }
}
