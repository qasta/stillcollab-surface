package com.axeiya.stillcollab.wysiwyg.client.control.paragraph.alignment;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractClickableControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.alignment.LeftAlignInserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedToggleButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MainAlignButton extends AbstractClickableControl implements ClickHandler, IsWidget {

  private DecoratedToggleButton ui;
  private LeftAlignInserter inserter;

  public MainAlignButton() {
    this(ControlResources.Util.getInstance());
  }

  public MainAlignButton(ControlResources resources) {
    ui = new DecoratedToggleButton(new Image(resources.alignMenu()));
    ui.setTitle(CONSTANTS.alignment());
    ui.setStyleName(resources.button().surfacePushButton());
    inserter = new LeftAlignInserter();
    ui.addClickHandler(this);
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler handler) {
    return ui.addClickHandler(handler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    ui.fireEvent(event);
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
  }

  @Override
  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public void onClick(ClickEvent event) {

  }

  @Override
  public Widget asWidget() {
    return ui;
  }

  @Override
  public void setDown(boolean b) {
    ui.setDown(b);
  }

}
