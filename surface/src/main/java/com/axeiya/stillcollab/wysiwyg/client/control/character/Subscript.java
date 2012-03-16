package com.axeiya.stillcollab.wysiwyg.client.control.character;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.inlineinserter.text.SubscriptInserter;

public class Subscript extends AbstractButtonControl {

  public Subscript() {
    super(new SubscriptInserter());
    getUi().setText("Sub");
  }
}
