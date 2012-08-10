package com.axeiya.stillcollab.wysiwyg.client.widget;

import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.google.gwt.user.client.ui.Image;

public class Separator extends Image {

  public Separator() {
    this(ControlResources.Util.getInstance());
  }

  public Separator(ControlResources resources) {
    super(resources.separator());
    setStyleName(resources.button().separator());
  }
}
