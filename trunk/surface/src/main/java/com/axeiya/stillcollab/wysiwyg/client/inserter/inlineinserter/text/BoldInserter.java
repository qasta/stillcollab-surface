package com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text;

import java.util.Arrays;
import java.util.List;

import com.axeiya.stillcollab.wysiwyg.client.inserter.action.InsertAction;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.InlineInserter;
import com.axeiya.stillcollab.wysiwyg.client.ranges.SurfaceSelection;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.FontWeight;

public class BoldInserter extends InlineInserter<Element> {

  protected static final BoldInsertAction insertAction = new BoldInsertAction();
  protected static final BoldInvertAction invertAction = new BoldInvertAction();

  public BoldInserter() {
    this(insertAction, invertAction);
  }

  protected BoldInserter(InsertAction<Element> action, InsertAction<Element> invertAction) {
    super(action, invertAction);
  }

  protected static class BoldInsertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, SurfaceSelection selection) {
      element.getStyle().setFontWeight(FontWeight.BOLD);
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }

  protected static class BoldInvertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, SurfaceSelection selection) {
      element.getStyle().setFontWeight(FontWeight.NORMAL);
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
    return Arrays.asList("p", "span");
  }

  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, SurfaceSelection selection) {
    String fontWeight = "";
    while ((fontWeight == null || fontWeight.isEmpty()) && matchingAncestor != null) {
      fontWeight = matchingAncestor.getStyle().getFontWeight();
      if (FontWeight.BOLD.toString().toLowerCase().equals(fontWeight)) {
        return true;
      }
      matchingAncestor = matchingAncestor.getParentElement();
    }
    return false;
  }
}
