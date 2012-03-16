package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractToggleControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.UnderlineInserter;

public class Underline extends AbstractToggleControl {

  public Underline() {
    super(new UnderlineInserter());
    getUi().setText("Underline");
  }
}
