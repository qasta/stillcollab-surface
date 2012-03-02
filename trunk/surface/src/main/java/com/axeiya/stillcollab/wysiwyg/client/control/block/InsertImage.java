package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter.ImageConfig;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class InsertImage extends AbstractControl implements ClickHandler, IsWidget {

  private Button ui;
  private ImageInserter inserter;
  private boolean imgSelected = false;

  public InsertImage() {
    ui = new Button("Img...");
    inserter = new ImageInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    if (!imgSelected) {
      final String url = Window.prompt("URL", "");
      execute(new Command() {
        @Override
        public void execute() {
          inserter.insert(currentSurface.getSelection(), new ImageConfig(url));
        }
      });

    } else {
      ImageConfig config = inserter.getCurrentConfig(currentSurface.getSelection());
      if (config != null) {
        final String url = Window.prompt("URL", config.getUrl());
        execute(new Command() {
          @Override
          public void execute() {
            inserter.updateConfig(currentSurface.getSelection(), new ImageConfig(url));
          }
        });
      } else {
        final String url = Window.prompt("URL", "");
        execute(new Command() {
          @Override
          public void execute() {
            inserter.insert(currentSurface.getSelection(), new ImageConfig(url));
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
