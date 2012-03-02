package com.axeiya.stillcollab.wysiwyg.client.control.list;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.listinserter.OListInserter;

public class OList extends AbstractToggleControl {

  public OList() {
    super(new OListInserter());
    getUi().setText("OL");
  }
}
