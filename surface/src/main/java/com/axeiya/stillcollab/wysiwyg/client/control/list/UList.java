package com.axeiya.stillcollab.wysiwyg.client.control.list;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.listinserter.UListInserter;
import com.google.gwt.user.client.ui.Image;

public class UList extends AbstractToggleControl {

  public UList() {
    this(ControlResources.Util.getInstance());
  }

  public UList(ControlResources resources) {
    super(new UListInserter(), new Image(resources.unorderedList()), resources, CONSTANTS
        .unorderedlist());
  }
}
