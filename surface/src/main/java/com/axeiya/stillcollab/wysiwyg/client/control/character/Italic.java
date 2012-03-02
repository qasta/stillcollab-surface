package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.ItalicInserter;

public class Italic extends AbstractToggleControl {

  public Italic() {
    super(new ItalicInserter());
    getUi().setText("I");
  }
}
