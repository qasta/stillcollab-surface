package com.axeiya.stillcollab.wysiwyg.client.control;

import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class AbstractButtonControl extends AbstractControl implements ClickHandler, IsWidget {

  private Button ui;
  private Inserter inserter;

  public AbstractButtonControl(Inserter inserter) {
    ui = new Button("C");
    this.inserter = inserter;
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    execute(new Command() {
      @Override
      public void execute() {
        inserter.insert(currentSurface.getSelection());
      }
    });
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
  }

  public Button getUi() {
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
