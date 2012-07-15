package com.axeiya.stillcollab.wysiwyg.client.control.image;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter.ImageConfig;
import com.axeiya.stillcollab.wysiwyg.client.widget.DecoratedPushButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class InsertImage extends AbstractControl implements ClickHandler, IsWidget {

  private ControlResources resources;

  private DecoratedPushButton ui;
  private ImageInserter inserter;
  private boolean imgSelected = false;

  public InsertImage() {
    this(ControlResources.Util.getInstance());
  }

  public InsertImage(ControlResources resources) {
    this.resources = resources;

    ui = new DecoratedPushButton(new Image(resources.imageMenu()));
    ui.setTitle(CONSTANTS.insertImage());
    ui.setStyleName(resources.button().surfacePushButton());
    inserter = new ImageInserter();
    ui.addClickHandler(this);
  }

  @Override
  public void onClick(ClickEvent event) {
    if (!imgSelected) {
      final String url = Window.prompt("URL", "");
      if (url != null && !url.trim().isEmpty()) {
        execute(new Command() {
          @Override
          public void execute() {
            inserter.insert(currentSurface.getSelection(), new ImageConfig(url));
          }
        });
      }
    } else {
      ImageConfig config = inserter.getCurrentConfig(currentSurface.getSelection());
      if (config != null) {
        final String url = Window.prompt("URL", config.getUrl());
        if (url != null && !url.trim().isEmpty()) {
          execute(new Command() {
            @Override
            public void execute() {
              inserter.updateConfig(currentSurface.getSelection(), new ImageConfig(url));
            }
          });
        }
      } else {
        final String url = Window.prompt("URL", "");
        if (url != null && !url.trim().isEmpty()) {
          execute(new Command() {
            @Override
            public void execute() {
              inserter.insert(currentSurface.getSelection(), new ImageConfig(url));
            }
          });
        }
      }
    }
  }

  public PushButton getUi() {
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
