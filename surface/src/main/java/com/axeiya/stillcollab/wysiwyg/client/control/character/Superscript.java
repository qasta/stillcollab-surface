package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.SuperscriptInserter;

public class Superscript extends AbstractButtonControl {

  public Superscript() {
    super(new SuperscriptInserter());
    getUi().setText("Sup");
  }
}
