package com.axeiya.stillcollab.wysiwyg.client;

import com.axeiya.stillcollab.wysiwyg.client.control.block.InsertImage;
import com.axeiya.stillcollab.wysiwyg.client.control.block.InsertLink;
import com.axeiya.stillcollab.wysiwyg.client.control.block.Quote;
import com.axeiya.stillcollab.wysiwyg.client.control.block.RemoveLink;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Bold;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Italic;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Strikethrought;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Subscript;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Superscript;
import com.axeiya.stillcollab.wysiwyg.client.control.character.Underline;
import com.axeiya.stillcollab.wysiwyg.client.control.headless.HeadlessParagraph;
import com.axeiya.stillcollab.wysiwyg.client.control.indent.Indent;
import com.axeiya.stillcollab.wysiwyg.client.control.indent.Outdent;
import com.axeiya.stillcollab.wysiwyg.client.control.list.OList;
import com.axeiya.stillcollab.wysiwyg.client.control.list.UList;
import com.axeiya.stillcollab.wysiwyg.client.control.paragraph.ParagraphControl;
import com.axeiya.stillcollab.wysiwyg.client.control.table.AddColumn;
import com.axeiya.stillcollab.wysiwyg.client.control.table.AddLine;
import com.axeiya.stillcollab.wysiwyg.client.control.table.DropColumn;
import com.axeiya.stillcollab.wysiwyg.client.control.table.DropLine;
import com.axeiya.stillcollab.wysiwyg.client.control.table.DropTable;
import com.axeiya.stillcollab.wysiwyg.client.control.table.InsertTable;
import com.axeiya.stillcollab.wysiwyg.client.control.table.MainTableButton;
import com.axeiya.stillcollab.wysiwyg.client.inserter.paragraphinserter.StyledParagraphInserter;

public class DefaultToolBar extends ToolBar {

  public DefaultToolBar() {
    super();
    setStyleName("sc-Wysiwyg-Toolbar");

    ParagraphControl pControl = new ParagraphControl();
    pControl.addParagraphStyle("Code", new StyledParagraphInserter("code"));
    add(pControl);
    add(new UList());
    add(new OList());
    add(new HeadlessParagraph());
    // add(new Paragraph());
    // add(new Heading1());
    // add(new Heading2());
    add(new Bold());
    add(new Italic());
    add(new Underline());
    add(new Subscript());
    add(new Superscript());
    add(new Strikethrought());
    // add(new Pre());
    add(new Quote());
    add(new InsertLink());
    add(new RemoveLink());
    add(new InsertImage());
    add(new Indent());
    add(new Outdent());
    // add(new FFArial());
    // add(new FFHelvetica());
    // add(new FFCourrier());
    ToolGroup tableGroup = new ToolGroup(new MainTableButton());
    tableGroup.addSubComponent(new InsertTable());
    tableGroup.addSubComponent(new DropTable());
    tableGroup.addSubComponent(new AddLine());
    tableGroup.addSubComponent(new DropLine());
    tableGroup.addSubComponent(new AddColumn());
    tableGroup.addSubComponent(new DropColumn());
    add(tableGroup);

  }
}
