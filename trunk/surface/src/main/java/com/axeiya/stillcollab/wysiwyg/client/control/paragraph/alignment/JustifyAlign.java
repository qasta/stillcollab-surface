package com.axeiya.stillcollab.wysiwyg.client.control.paragraph.alignment;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.alignment.JustifyAlignInserter;
import com.google.gwt.user.client.ui.Image;

public class JustifyAlign extends AbstractToggleControl {

  public JustifyAlign() {
    this(ControlResources.Util.getInstance());
  }

  public JustifyAlign(ControlResources resources) {
    super(new JustifyAlignInserter(), new Image(resources.alignJustify()), resources, CONSTANTS
        .justifyAlign());
  }
}
