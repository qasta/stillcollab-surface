package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.ItalicInserter;
import com.google.gwt.user.client.ui.Image;

public class Italic extends AbstractToggleControl {

  public Italic() {
    this(ControlResources.Util.getInstance());
  }

  public Italic(ControlResources resources) {
    super(new ItalicInserter(), new Image(resources.italic()), resources);
  }
}
