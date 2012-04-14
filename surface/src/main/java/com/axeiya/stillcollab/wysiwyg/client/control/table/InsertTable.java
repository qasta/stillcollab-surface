package com.axeiya.stillcollab.wysiwyg.client.control.table;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.tableinserter.TableConfig;
import com.axeiya.stillcollab.wysiwyg.client.inserter.tableinserter.TableInserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedPushButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class InsertTable extends AbstractControl implements ClickHandler, IsWidget {

  private DecoratedPushButton ui;
  private TableInserter inserter;

  public InsertTable() {
    this(ControlResources.Util.getInstance());
  }

  public InsertTable(ControlResources resources) {
    ui = new DecoratedPushButton(new Image(resources.addTable()));
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
    final Integer line = Integer.parseInt(Window.prompt("Nombre de lignes", "4"));
    final Integer column = Integer.parseInt(Window.prompt("Nombre de colonnes", "4"));
    execute(new Command() {
      @Override
      public void execute() {
        inserter.insert(currentSurface.getSelection(), new TableConfig(line, column, true));
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
