package com.axeiya.stillcollab.wysiwyg.client.control.list;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.control.resource.ControlResources;
import com.axeiya.stillcollab.wysiwyg.client.inserter.listinserter.OListInserter;
import com.google.gwt.user.client.ui.Image;

public class OList extends AbstractToggleControl {

  public OList() {
    this(ControlResources.Util.getInstance());
  }

  public OList(ControlResources resources) {
    super(new OListInserter(), new Image(resources.orderedList()), resources);
  }
}
