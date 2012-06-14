package com.axeiya.stillcollab.wysiwyg.client.control.indent;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.indentinserter.IndentInserter;
import com.google.gwt.user.client.ui.Image;

public class Indent extends AbstractButtonControl {

  public Indent() {
    this(ControlResources.Util.getInstance());
  }

  public Indent(ControlResources resources) {
    super(new IndentInserter(), new Image(resources.indent()), resources, CONSTANTS.indent());
  }

}
