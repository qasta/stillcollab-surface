package com.axeiya.stillcollab.wysiwyg.client.control.paragraph.alignment;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.alignment.RightAlignInserter;
import com.google.gwt.user.client.ui.Image;

public class RightAlign extends AbstractToggleControl {

  public RightAlign() {
    this(ControlResources.Util.getInstance());
  }

  public RightAlign(ControlResources resources) {
    super(new RightAlignInserter(), new Image(resources.alignRight()), resources, CONSTANTS
        .rightAlign());
  }
}
