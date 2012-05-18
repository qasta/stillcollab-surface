package com.axeiya.stillcollab.wysiwyg.client;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.EnterKeyPressedHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.enterkeypressed.HasEnterKeyPressedHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HasHotKeyPressedHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HotKeyPressedEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.hotkeypressed.HotKeyPressedHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.HasSelectedSurfaceChangeHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.SelectedSurfaceChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.selectedsurfacechange.SelectedSurfaceChangeHandler;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.HasSelectionChangeHandlers;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends Composite implements HasHotKeyPressedHandlers, HotKeyPressedHandler,
    HasSelectedSurfaceChangeHandlers, SelectedSurfaceChangeHandler, HasSelectionChangeHandlers,
    SelectionChangeHandler, HasEnterKeyPressedHandlers, EnterKeyPressedHandler {

  protected FlowPanel mainPanel;
  protected FlowPanel firstLine;
  protected FlowPanel secondLine;
  protected Surface currentSurface;

  public ToolBar() {
    this(ControlResources.Util.getInstance());
  }

  public ToolBar(ControlResources resources) {
    super();
    mainPanel = new FlowPanel();
    firstLine = new FlowPanel();
    firstLine.setStyleName(resources.toolbar().firstLine());
    mainPanel.add(firstLine);
    secondLine = new FlowPanel();
    secondLine.setStyleName(resources.toolbar().secondLine());
    mainPanel.add(secondLine);
    initWidget(mainPanel);
  }

  public void add(AbstractControl w) {
    addSelectionChangeHandler(w);
    addSelectedSurfaceChangeHandler(w);
    if (w.getInserter() instanceof HotKeyPressedHandler) {
      addHotKeyPressedHandler((HotKeyPressedHandler) w.getInserter());
    }
    if (w.getInserter() instanceof EnterKeyPressedHandler) {
      addEnterKeyPressedHandler((EnterKeyPressedHandler) w.getInserter());
    }
    if (w instanceof IsWidget) {
      firstLine.add((IsWidget) w);
    }
  }

  public void add(final ToolGroup group) {
    add(group.getMainComponent());

    for (AbstractControl control : group.getSubComponents()) {
      addSelectionChangeHandler(control);
      addSelectedSurfaceChangeHandler(control);
    }

    group.getMainComponent().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        secondLine.clear();
        for (AbstractControl control : group.getSubComponents()) {
          if (control instanceof IsWidget) {
            secondLine.add((IsWidget) control);
          }
        }
      }
    });
  }

  public void clear() {
    firstLine.clear();
    secondLine.clear();
  }

  public boolean remove(Widget w) {
    return firstLine.remove(w);
  }

  public void addManagedSurface(Surface surface) {
    surface.addSelectedSurfaceChangeHandler(this);
    surface.addSelectionChangeHandler(this);
    surface.addHotKeyPressedHandler(this);
    surface.addEnterKeyPressedHandler(this);
  }

  @Override
  public HandlerRegistration addHotKeyPressedHandler(HotKeyPressedHandler handler) {
    return this.addHandler(handler, HotKeyPressedEvent.getType());
  }

  @Override
  public void onHotKeyPressed(HotKeyPressedEvent event) {
    HotKeyPressedEvent.fire(this, event);
  }

  @Override
  public void onSelectedSurfaceChange(SelectedSurfaceChangeEvent event) {
    currentSurface = event.getSurface();
    SelectedSurfaceChangeEvent.fire(this, event);
  }

  @Override
  public HandlerRegistration addSelectedSurfaceChangeHandler(SelectedSurfaceChangeHandler handler) {
    return this.addHandler(handler, SelectedSurfaceChangeEvent.getType());
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    SelectionChangeEvent.fire(this, event);
  }

  @Override
  public HandlerRegistration addSelectionChangeHandler(SelectionChangeHandler handler) {
    return this.addHandler(handler, SelectionChangeEvent.getType());
  }

  @Override
  public void onEnterKeyPressed(EnterKeyPressedEvent event) {
    EnterKeyPressedEvent.fire(this, event);
  }

  @Override
  public HandlerRegistration addEnterKeyPressedHandler(EnterKeyPressedHandler handler) {
    return this.addHandler(handler, EnterKeyPressedEvent.getType());
  }

}