package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.LinkInserter;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedPushButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class RemoveLink extends AbstractControl implements ClickHandler, IsWidget {

  private DecoratedPushButton ui;
  private Inserter inserter;

  public RemoveLink() {
    this(ControlResources.Util.getInstance());
  }

  public RemoveLink(ControlResources resources) {
    super();
    ui = new DecoratedPushButton(new Image(resources.dropLink()));
    ui.setTitle(CONSTANTS.dropLink());
    ui.setStyleName(resources.button().surfacePushButton());
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

  public DecoratedPushButton getUi() {
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
