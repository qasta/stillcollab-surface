package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.H2Inserter;
import com.google.gwt.user.client.ui.Image;

public class Heading2 extends AbstractToggleControl {

  public Heading2() {
    super(new H2Inserter(), new Image(ControlResources.Util.getInstance().unorderedList()),
        ControlResources.Util.getInstance());
    getUi().setText("H2");
  }
}
