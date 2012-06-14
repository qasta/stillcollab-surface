package com.axeiya.stillcollab.wysiwyg.client.control.indent;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.indentinserter.OutdentInserter;
import com.google.gwt.user.client.ui.Image;

public class Outdent extends AbstractButtonControl {

  public Outdent() {
    this(ControlResources.Util.getInstance());
  }

  public Outdent(ControlResources resources) {
    super(new OutdentInserter(), new Image(resources.outdent()), resources, CONSTANTS.outdent());
  }
}
