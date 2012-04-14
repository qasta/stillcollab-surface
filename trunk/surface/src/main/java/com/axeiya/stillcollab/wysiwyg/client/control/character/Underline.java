package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.UnderlineInserter;
import com.google.gwt.user.client.ui.Image;

public class Underline extends AbstractToggleControl {

  public Underline() {
    this(ControlResources.Util.getInstance());
  }

  public Underline(ControlResources resources) {
    super(new UnderlineInserter(), new Image(resources.underline()), resources);
  }
}
