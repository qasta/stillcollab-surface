package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.StrikethroughtInserter;
import com.google.gwt.user.client.ui.Image;

public class Strikethrought extends AbstractToggleControl {

  public Strikethrought() {
    this(ControlResources.Util.getInstance());
  }

  public Strikethrought(ControlResources resources) {
    super(new StrikethroughtInserter(), new Image(resources.strikethrought()), resources, CONSTANTS
        .strikethrought());
  }
}
