package com.axeiya.stillcollab.wysiwyg.client.control.indent;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.indentinserter.OutdentInserter;

public class Outdent extends AbstractButtonControl {

  public Outdent() {
    super(new OutdentInserter());
    getUi().setText("Outdent");
  }
}
