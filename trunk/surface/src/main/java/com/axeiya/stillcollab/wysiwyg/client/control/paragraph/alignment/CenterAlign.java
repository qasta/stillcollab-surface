package com.axeiya.stillcollab.wysiwyg.client.control.paragraph.alignment;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.alignment.CenterAlignInserter;
import com.google.gwt.user.client.ui.Image;

public class CenterAlign extends AbstractToggleControl {

  public CenterAlign() {
    this(ControlResources.Util.getInstance());
  }

  public CenterAlign(ControlResources resources) {
    super(new CenterAlignInserter(), new Image(resources.alignCenter()), resources, CONSTANTS
        .centerAlign());
  }
}
