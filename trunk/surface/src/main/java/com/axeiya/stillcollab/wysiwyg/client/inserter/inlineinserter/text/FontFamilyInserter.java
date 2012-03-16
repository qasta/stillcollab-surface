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

public class FontFamilyInserter extends InlineInserter<Element> {

  private FontFamilyConfig currentConfig;

  public FontFamilyInserter() {
    action = new FontFamilyInsertAction();
    invertAction = new FontFamilyInvertAction();
  }

  protected FontFamilyInserter(InsertAction<Element> action, InsertAction<Element> invertAction) {
    super(action, invertAction);
  }

  protected class FontFamilyInsertAction extends InsertAction<Element> {
    private final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
      if (FontFamilyInserter.this.currentConfig.getFontName().equals(
          FontFamilyConfig.DEFAULT_FONTNAME)) {
        element.getStyle().clearProperty("fontFamily");
      } else {
        element.getStyle().setProperty("fontFamily",
            FontFamilyInserter.this.currentConfig.getFontName());
      }
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }

  protected static class FontFamilyInvertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }

  public static class FontFamilyConfig {
    public static final String DEFAULT_FONTNAME = "default";
    private String fontName;

    public FontFamilyConfig(String fontName) {
      super();
      this.fontName = fontName;
    }

    public String getFontName() {
      return fontName;
    }

    public void setFontName(String fontName) {
      this.fontName = fontName;
    }
  }

  public void insert(Selection selection, FontFamilyConfig config) {
    currentConfig = config;
    super.insert(selection);
  }

  @Deprecated
  @Override
  public void insert(Selection selection) {
    throw new IllegalArgumentException("Use insert(Selection,FontFamilyConfig) instead");
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
