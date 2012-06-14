package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.SuperscriptInserter;
import com.google.gwt.user.client.ui.Image;

public class Superscript extends AbstractButtonControl {

  public Superscript() {
    this(ControlResources.Util.getInstance());
  }

  public Superscript(ControlResources resources) {
    super(new SuperscriptInserter(), new Image(resources.superscript()), resources, CONSTANTS
        .superscript());
  }
}
