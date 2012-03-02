package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.LinkInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.LinkInserter.LinkConfig;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class InsertLink extends AbstractControl implements ClickHandler, IsWidget {

  private Button ui;
  private LinkInserter inserter;
  private boolean imgSelected = false;

  public InsertLink() {
    ui = new Button("Link...");
    inserter = new LinkInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    if (!imgSelected) {
      final String url = Window.prompt("URL", "http://axeiya.com");
      execute(new Command() {
        @Override
        public void execute() {
          inserter.insert(currentSurface.getSelection(), new LinkConfig(url));
        }
      });

    } else {
      LinkConfig config = inserter.getCurrentConfig(currentSurface.getSelection());
      if (config != null) {
        final String url = Window.prompt("URL", config.getUrl());
        execute(new Command() {
          @Override
          public void execute() {
            inserter.updateConfig(currentSurface.getSelection(), url);
          }
        });
      } else {
        final String url = Window.prompt("URL", "http://axeiya.com");
        execute(new Command() {
          @Override
          public void execute() {
            inserter.insert(currentSurface.getSelection(), new LinkConfig(url));
          }
        });
      }
    }
  }

  public Button getUi() {
    return ui;
  }

  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    imgSelected = inserter.isSelectionAssignee(event.getSelection());
  }

  @Override
  public Widget asWidget() {
    return ui;
  }
}
