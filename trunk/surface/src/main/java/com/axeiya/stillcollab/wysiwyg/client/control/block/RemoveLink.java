package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.LinkInserter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class RemoveLink extends AbstractControl implements ClickHandler, IsWidget {

  private Button ui;
  private Inserter inserter;

  public RemoveLink() {
    super();
    ui = new Button("Drop link");
    inserter = new LinkInserter();
    ui.addClickHandler(this);
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
  public void onSelectionChange(SelectionChangeEvent event) {
    ui.setEnabled(inserter.isSelectionAssignee(event.getSelection()));
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
