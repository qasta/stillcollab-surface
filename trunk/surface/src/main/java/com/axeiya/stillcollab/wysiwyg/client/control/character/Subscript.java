package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.SubscriptInserter;
import com.google.gwt.user.client.ui.Image;

public class Subscript extends AbstractButtonControl {

  public Subscript() {
    this(ControlResources.Util.getInstance());
  }

  public Subscript(ControlResources resources) {
    super(new SubscriptInserter(), new Image(resources.subscript()), resources);
  }
}
