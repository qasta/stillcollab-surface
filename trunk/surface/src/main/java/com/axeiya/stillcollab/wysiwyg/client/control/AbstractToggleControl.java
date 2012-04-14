package com.axeiya.stillcollab.wysiwyg.client.control;

import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedToggleButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

public class AbstractToggleControl extends AbstractControl implements ClickHandler, IsWidget {

  private DecoratedToggleButton ui;
  private Inserter inserter;

  public AbstractToggleControl(Inserter inserter, Image icon, ControlResources resources) {
    ui = new DecoratedToggleButton(icon);
    ui.setStyleName(resources.button().surfacePushButton());
    this.inserter = inserter;
    ui.addClickHandler(this);
  }

  public AbstractToggleControl(Inserter inserter, String text) {
    ui = new DecoratedToggleButton(text);
    this.inserter = inserter;
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    if (ui.isDown()) {
      execute(new Command() {
        @Override
        public void execute() {
          inserter.insert(currentSurface.getSelection());
        }
      });
    } else {
      execute(new Command() {
        @Override
        public void execute() {
          inserter.remove(currentSurface.getSelection());
        }
      });
    }
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    ui.setDown(inserter.isSelectionAssignee(event.getSelection()));
  }

  public ToggleButton getUi() {
    return ui;
  }

  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public Widget asWidget() {
    return ui;
  }

}
