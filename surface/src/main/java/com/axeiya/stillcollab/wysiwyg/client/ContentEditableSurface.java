package com.axeiya.stillcollab.wysiwyg.client;

import java.util.ArrayList;
import java.util.List;

import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HotKeyPressedEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HotKeyPressedHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.SelectedSurfaceChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.SelectedSurfaceChangeHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeHandler;
import com.axeiya.stillcollab.wysiwyg.client.processor.CleanProcessor;
import com.axeiya.stillcollab.wysiwyg.client.processor.InPlaceProcessor;
import com.axeiya.stillcollab.wysiwyg.client.processor.ParagraphProcessor;
import com.axeiya.stillcollab.wysiwyg.client.processor.Processor;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Range;
import com.axeiya.stillcollab.wysiwyg.client.ranges.Selection;
import com.axeiya.stillcollab.wysiwyg.client.util.DOMUtil;
import com.axeiya.stillcollab.wysiwyg.client.util.DelayedScheduler;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public class ContentEditableSurface extends Widget implements Surface {

  private Selection currentSelection;
  private Range currentRange;
  private boolean hasFocus = false;
  private List<Processor> preProcessors = new ArrayList<Processor>();
  private List<Processor> postProcessors = new ArrayList<Processor>();

  Timer selectionChangeTimer = new Timer() {
    @Override
    public void run() {
      if (currentRange == null || !Selection.getSelection().getRange().equivalent(currentRange)) {
        SelectionChangeEvent.fire(ContentEditableSurface.this, Selection.getSelection());
      }
      currentSelection = Selection.getSelection();
      currentRange = currentSelection.getRange();
    }
  };

  Timer valueChangeTimer = new Timer() {
    private String previousValue = "";

    @Override
    public void run() {
      String currentValue = getValue();
      if (!previousValue.equals(currentValue)) {
        previousValue = currentValue;
        ValueChangeEvent.fire(ContentEditableSurface.this, currentValue);
      }
    }
  };

  public ContentEditableSurface() {
    this(Document.get().createDivElement());
  }

  public ContentEditableSurface(Element mainPanel) {
    setElement(mainPanel);
    mainPanel.setAttribute("contentEditable", "true");
    mainPanel.setAttribute(DOMUtil.ROOT_ATTRIBUTE, "true");
    mainPanel.getStyle().setBorderWidth(1, Unit.PX);
    mainPanel.getStyle().setBorderColor("black");
    mainPanel.getStyle().setBorderStyle(BorderStyle.DASHED);
    sinkEvents(Event.ONMOUSEUP | Event.ONKEYDOWN | Event.ONFOCUS | Event.ONBLUR | Event.ONPASTE);
    addPreProcessor(new ParagraphProcessor());
    addPostProcessor(new CleanProcessor());
  }

  @Override
  public void setEditable(boolean editable) {
    getElement().setAttribute("contentEditable", editable + "");
    if (editable) {
      getElement().getStyle().setBorderStyle(BorderStyle.DASHED);
    } else {
      getElement().getStyle().setBorderStyle(BorderStyle.NONE);
    }
  }

  public void onBrowserEvent(com.google.gwt.user.client.Event event) {
    switch (event.getTypeInt()) {
      case Event.ONMOUSEUP:
        selectionChangeTimer.cancel();
        selectionChangeTimer.schedule(200);
        break;
      case Event.ONKEYDOWN:
        selectionChangeTimer.cancel();
        selectionChangeTimer.schedule(200);
        valueChangeTimer.cancel();
        valueChangeTimer.schedule(1000);
        if (event.getKeyCode() == KeyCodes.KEY_ENTER) {
          EnterKeyPressedEvent enter =
              EnterKeyPressedEvent.fire(this, Selection.getSelection(), event.getAltKey(), event
                  .getCtrlKey(), event.getMetaKey());
          if (enter.isPreventDefault()) {
            event.preventDefault();
          }
        } else if (event.getCtrlKey()) {
          // GWT.log("keycode : " + event.getKeyCode());
          HotKeyPressedEvent hotKey =
              HotKeyPressedEvent.fire(this, Selection.getSelection(), event.getAltKey(), event
                  .getCtrlKey(), event.getMetaKey(), event.getKeyCode());
          if (hotKey.isPreventDefault()) {
            event.preventDefault();
          }
        }
        break;
      case Event.ONFOCUS:
        hasFocus = true;
        FocusEvent.fireNativeEvent(event, this);
        // if (getSelection().getRange().getStartContainer().equals(getElement())) {
        // // si la sélection courante est à l'origine, dans le container parent, on la pousse au
        // // premier
        // // enfant, pour être sur d'être dans un paragraphe
        // Range range = Range.createRange();
        // range.setStart(getElement().getFirstChild(), 0);
        // range.setEnd(getElement().getFirstChild(), 0);
        // Selection.getSelection().setSingleRange(range);
        // }
        SelectedSurfaceChangeEvent.fire(ContentEditableSurface.this, ContentEditableSurface.this);
        break;
      case Event.ONBLUR:
        hasFocus = false;
        selectionChangeTimer.cancel();
        ValueChangeEvent.fire(this, getValue());
        event.preventDefault();
        break;
      case Event.ONPASTE:
        // On conserve la sélection courante
        getSelection().getRange().deleteContents();
        getSelection().getRange().collapse(true);
        final Node startNode = getSelection().getRange().getStartContainer();
        final int offset = getSelection().getRange().getStartOffset();

        final DivElement fragment = Document.get().createDivElement();
        fragment.getStyle().setDisplay(Display.NONE);
        Node nextSibling,
        child = getElement().getFirstChild();
        while (child != null) {
          nextSibling = child.getNextSibling();
          fragment.appendChild(child);
          child = nextSibling;
        }
        getElement().getParentElement().insertAfter(fragment, getElement());

        DelayedScheduler.scheduleDelayed(new Command() {
          @Override
          public void execute() {
            String clipboardData =
                getElement().getInnerHTML().replaceAll("<[^<>]+>", "").replaceAll("</[^<>]+>", "")
                    .trim().replaceAll("\r\n", "<br />").replaceAll("\n", "<br />");
            getElement().setInnerHTML("");
            ParagraphElement par = Document.get().createPElement();
            par.setInnerHTML(clipboardData);

            Node nextSibling, child = fragment.getFirstChild();
            while (child != null) {
              nextSibling = child.getNextSibling();
              getElement().appendChild(child);
              child = nextSibling;
            }
            if (startNode.getNodeType() == Node.TEXT_NODE) {
              String before = startNode.getNodeValue().substring(0, offset);
              String after = startNode.getNodeValue().substring(offset);
              startNode.setNodeValue(before);
              Node nextNode = startNode.cloneNode(false);
              nextNode.setNodeValue(after);
              startNode.getParentElement().insertAfter(par, startNode);
              startNode.getParentElement().insertAfter(nextNode, par);
              getSelection().getRange().setStart(nextNode, 0);
            } else {
              startNode.insertBefore(par, startNode.getChild(offset));
              getSelection().getRange().setStart(startNode, offset + 1);
            }
          }
        });
        break;
    }
    super.onBrowserEvent(event);
  };

  @Override
  protected void onLoad() {
    super.onLoad();

    DelayedScheduler.scheduleDelayed(new Command() {
      @Override
      public void execute() {
        setValue("");
        focus();

      }
    });
  }

  private Document getDocument() {
    return getElement().getOwnerDocument();
  }

  @Override
  public HandlerRegistration addSelectionChangeHandler(SelectionChangeHandler handler) {
    return this.addHandler(handler, SelectionChangeEvent.getType());
  }

  @Override
  public HandlerRegistration addEnterKeyPressedHandler(EnterKeyPressedHandler handler) {
    return this.addHandler(handler, EnterKeyPressedEvent.getType());
  }

  @Override
  public HandlerRegistration addHotKeyPressedHandler(HotKeyPressedHandler handler) {
    return this.addHandler(handler, HotKeyPressedEvent.getType());
  }

  @Override
  public HandlerRegistration addSelectedSurfaceChangeHandler(SelectedSurfaceChangeHandler handler) {
    return this.addHandler(handler, SelectedSurfaceChangeEvent.getType());
  }

  @Override
  public Selection getSelection() {
    if (!hasFocus) {
      Selection.getSelection().setSingleRange(currentRange);
    }
    return Selection.getSelection();
  }

  @Override
  public void notifyUpdate() {
    SelectionChangeEvent.fire(this, getSelection());
  }

  @Override
  public void focus() {
    focus(getElement());
  }

  private static native void focus(Element element) /*-{
		element.focus();
  }-*/;

  @Override
  public void addPostProcessor(Processor postProcessor) {
    postProcessors.add(postProcessor);
  }

  @Override
  public void addPreProcessor(Processor preProcessor) {
    preProcessors.add(preProcessor);
  }

  @Override
  public String getValue() {
    BodyElement element = (BodyElement) Document.get().createElement("body");
    Node child = getElement().getFirstChild();
    while (child != null) {
      element.appendChild(child.cloneNode(true));
      child = child.getNextSibling();
    }
    for (Processor processor : postProcessors) {
      element = processor.process(element);
    }
    StringBuilder sb = new StringBuilder();
    child = element.getFirstChild();
    while (child != null) {
      DOMUtil.getXhtml(child, sb);
      child = child.getNextSibling();
    }
    return sb.toString();
  }

  @Override
  public void setValue(String value) {
    setValue(value, false);
  }

  @Override
  public void setValue(String value, boolean fireEvents) {
    BodyElement body = (BodyElement) Document.get().createElement("body");
    body.setInnerHTML(value);
    for (Processor processor : preProcessors) {
      body = processor.process(body);
    }
    DOMUtil.clear(getElement());
    Node nextSibling, child = body.getFirstChild();
    while (child != null) {
      nextSibling = child.getNextSibling();
      getElement().appendChild(child);
      child = nextSibling;
    }
    if (fireEvents) {
      ValueChangeEvent.fire(this, getValue());
    }
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
    return this.addHandler(handler, ValueChangeEvent.getType());
  }

  @Override
  public HandlerRegistration addFocusHandler(FocusHandler handler) {
    return addHandler(handler, FocusEvent.getType());
  }

  @Override
  public void processOnce(InPlaceProcessor processor) {
    Node child = getElement().getFirstChild();
    while (child != null) {
      processor.process(child);
      child = child.getNextSibling();
    }
  }

  @Override
  public boolean isEditable() {
    return getElement().getAttribute("contentEditable") != null
        && getElement().getAttribute("contentEditable").equals("true");
  }

}
