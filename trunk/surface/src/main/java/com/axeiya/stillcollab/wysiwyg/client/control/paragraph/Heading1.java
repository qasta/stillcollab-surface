package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H1Inserter;
import com.google.gwt.user.client.ui.Image;

public class Heading1 extends AbstractToggleControl {

  public Heading1() {
    super(new H1Inserter(), new Image(ControlResources.Util.getInstance().unorderedList()),
        ControlResources.Util.getInstance());
    getUi().setText("H1");
  }
}
