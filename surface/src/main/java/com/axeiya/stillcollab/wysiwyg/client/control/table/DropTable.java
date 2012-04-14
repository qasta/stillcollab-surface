package com.axeiya.stillcollab.wysiwyg.client.control.table;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.tableinserter.TableInserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedPushButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class DropTable extends AbstractControl implements ClickHandler, IsWidget {

  private DecoratedPushButton ui;
  private TableInserter inserter;

  public DropTable() {
    this(ControlResources.Util.getInstance());
  }

  public DropTable(ControlResources resources) {
    ui = new DecoratedPushButton(new Image(resources.dropTable()));
    ui.setStyleName(resources.button().surfacePushButton());
    inserter = new TableInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onClick(ClickEvent event) {
    execute(new Command() {
      @Override
      public void execute() {
        inserter.remove(currentSurface.getSelection());
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
