package com.axeiya.stillcollab.wysiwyg.client.control.paragraph;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.PInserter;
import com.google.gwt.user.client.ui.Image;

public class Paragraph extends AbstractToggleControl {

  public Paragraph() {
    super(new PInserter(), new Image(ControlResources.Util.getInstance().unorderedList()),
        ControlResources.Util.getInstance());
    getUi().setText("P");
  }
}
