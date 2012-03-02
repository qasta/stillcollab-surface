package com.axeiya.stillcollab.wysiwyg.client.control.indent;

import com.axeiya.stillcollab.wysiwyg.client.control.AbstractButtonControl;
import com.axeiya.stillcollab.wysiwyg.client.inserter.indentinserter.IndentInserter;

public class Indent extends AbstractButtonControl {

  public Indent() {
    super(new IndentInserter());
    getUi().setText("Indent");
  }

}
