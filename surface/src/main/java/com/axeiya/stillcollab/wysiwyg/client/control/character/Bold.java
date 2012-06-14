package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.BoldInserter;
import com.google.gwt.user.client.ui.Image;

public class Bold extends AbstractToggleControl {

  public Bold() {
    this(ControlResources.Util.getInstance());
  }

  public Bold(ControlResources resources) {
    super(new BoldInserter(), new Image(resources.bold()), resources, CONSTANTS.bold());
  }
}
