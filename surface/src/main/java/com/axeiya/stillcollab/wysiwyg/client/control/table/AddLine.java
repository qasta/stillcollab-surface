package com.axeiya.stillcollab.wysiwyg.client.control.table;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.tableinserter.TableLineInserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedPushButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class AddLine extends AbstractControl implements ClickHandler, IsWidget {

  private DecoratedPushButton ui;
  private TableLineInserter inserter;

  public AddLine() {
    this(ControlResources.Util.getInstance());
  }

  public AddLine(ControlResources resources) {
    ui = new DecoratedPushButton(new Image(resources.addRow()));
    ui.setTitle(CONSTANTS.insertLine());
    ui.setStyleName(resources.button().surfacePushButton());
    inserter = new TableLineInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    ui.setEnabled(inserter.isSelectionAssignee(event.getSelection()));
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
  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public Widget asWidget() {
    return ui;
  }

}
