package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.StrikethroughtInserter;

public class Strikethrought extends AbstractToggleControl {

  public Strikethrought() {
    super(new StrikethroughtInserter());
    getUi().setText("S");
  }
}
