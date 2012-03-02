package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.BoldInserter;

public class Bold extends AbstractToggleControl {

  public Bold() {
    super(new BoldInserter());
    getUi().setText("B");
  }
}
