package com.axeiya.stillcollab.wysiwyg.client.control.list;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.listinserter.UListInserter;

public class UList extends AbstractToggleControl {

  public UList() {
    super(new UListInserter());
    getUi().setText("UL");
  }
}
