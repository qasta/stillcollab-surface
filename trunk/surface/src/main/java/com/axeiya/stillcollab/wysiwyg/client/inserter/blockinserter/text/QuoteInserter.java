package com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text;

import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.BlockInserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.QuoteElement;

public class QuoteInserter extends BlockInserter<QuoteElement> {

  protected static final QuoteInsertAction insertAction = new QuoteInsertAction();

  public QuoteInserter() {
    this(insertAction);
  }

  protected QuoteInserter(InsertAction<QuoteElement> action) {
    super(action);
  }

  protected static class QuoteInsertAction extends InsertAction<QuoteElement> {
    private static final QuoteElement emptyElement = Document.get().createBlockQuoteElement();

    @Override
    public void onAction(QuoteElement element, Selection selection) {
      if (!DOMUtil.hasStrongNode(element)) {
        element.appendChild(DOMUtil.createFocusBr());
      }
    }

    @Override
    public QuoteElement getEmptyElement() {
      return emptyElement;
    }
  }

  @Override
  protected QuoteElement as(Element element) {
    return QuoteElement.as(element);
  }

}
