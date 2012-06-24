package com.axeiya.stillcollab.wysiwyg.client.control.paragraph.alignment;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.alignment.LeftAlignInserter;
import com.google.gwt.user.client.ui.Image;

public class LeftAlign extends AbstractToggleControl {

  public LeftAlign() {
    this(ControlResources.Util.getInstance());
  }

  public LeftAlign(ControlResources resources) {
    super(new LeftAlignInserter(), new Image(resources.alignLeft()), resources, CONSTANTS
        .leftAlign());
  }
}
