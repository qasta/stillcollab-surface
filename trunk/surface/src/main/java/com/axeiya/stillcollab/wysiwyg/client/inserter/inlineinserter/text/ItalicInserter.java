package com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text;

import java.util.Arrays;
import java.util.List;

import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.InlineInserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.FontStyle;

public class ItalicInserter extends InlineInserter<Element> {

  protected static final ItalicInsertAction insertAction = new ItalicInsertAction();
  protected static final ItalicInvertAction invertAction = new ItalicInvertAction();

  public ItalicInserter() {
    this(insertAction, invertAction);
  }

  protected ItalicInserter(InsertAction<Element> action, InsertAction<Element> invertAction) {
    super(action, invertAction);
  }

  protected static class ItalicInsertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
      element.getStyle().setFontStyle(FontStyle.ITALIC);
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }

  protected static class ItalicInvertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
      element.getStyle().setFontStyle(FontStyle.NORMAL);
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }

  @Override
  protected Element as(Element element) {
    return element;
  }

  @Override
  protected List<String> getApplicableTags() {
    return Arrays.asList("p", "span", "div", "blockquote");
  }

  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, Selection selection) {
    String fontStyle = "";
    while ((fontStyle == null || fontStyle.isEmpty()) && matchingAncestor != null) {
      fontStyle = matchingAncestor.getStyle().getFontStyle();
      if (FontStyle.ITALIC.toString().toLowerCase().equals(fontStyle)) {
        return true;
      }
      matchingAncestor = matchingAncestor.getParentElement();
    }
    return false;
  }

}
