package com.axeiya.stillcollab.wysiwyg.client.control.image;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.event.selectionchange.SelectionChangeEvent;
import com.axeiya.stillcollab.wysiwyg.client.inserter.Inserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.media.ImageInserter.ImageConfig;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImageSize extends AbstractControl implements ValueChangeHandler<Integer>, IsWidget {

  private ControlResources resources;

  private FlowPanel ui;
  private ImageInserter inserter;
  private boolean imgSelected = false;

  private IntegerBox sizeBox;

  public ImageSize() {
    this(ControlResources.Util.getInstance());
  }

  public ImageSize(ControlResources resources) {
    this.resources = resources;

    ui = new FlowPanel();
    ui.setStyleName(resources.button().surfaceDiv());
    Label ratio = new Label(CONSTANTS.imageSize());
    ui.add(ratio);
    sizeBox = new IntegerBox();
    sizeBox.setWidth("50px");
    ui.add(sizeBox);
    Label percent = new Label("px");
    ui.add(percent);

    inserter = new ImageInserter();
    sizeBox.addValueChangeHandler(this);
  }

  @Override
  public void onSelectionChange(SelectionChangeEvent event) {
    imgSelected = inserter.isSelectionAssignee(event.getSelection());
    sizeBox.setEnabled(imgSelected);
    if (imgSelected) {
      ImageConfig config = inserter.getCurrentConfig(event.getSelection());
      sizeBox.setValue(config.getWidth());
    }
  }

  @Override
  public Inserter getInserter() {
    return inserter;
  }

  @Override
  public Widget asWidget() {
    return ui;
  }

  @Override
  public void onValueChange(ValueChangeEvent<Integer> event) {
    if (imgSelected) {
      ImageConfig config = inserter.getCurrentConfig(currentSurface.getSelection());
      config.setWidth(event.getValue());
      inserter.updateConfig(currentSurface.getSelection(), config);
    }
  }

}
