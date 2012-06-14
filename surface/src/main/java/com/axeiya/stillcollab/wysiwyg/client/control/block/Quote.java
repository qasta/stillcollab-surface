package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.QuoteInserter;
import com.google.gwt.user.client.ui.Image;

public class Quote extends AbstractToggleControl {

  public Quote() {
    this(ControlResources.Util.getInstance());
  }

  public Quote(ControlResources resources) {
    super(new QuoteInserter(), new Image(resources.quote()), resources, CONSTANTS.quote());
  }
}
