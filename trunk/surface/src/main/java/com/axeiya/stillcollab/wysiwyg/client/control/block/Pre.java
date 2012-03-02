package com.axeiya.stillcollab.wysiwyg.client.control.block;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.blockinserter.text.PreInserter;

public class Pre extends AbstractToggleControl {

  public Pre() {
    super(new PreInserter());
    getUi().setText("Pre");
  }
}
