# Introduction #

One of the most important functionality of Surface is the ability to define and add customs inserters.
As it is mentioned in the project summary, Surface does not rely on execCommand, but on custom hand-written DOM inserters. At root, Surface brings two inserters, BlockInserter and InlineInserter (both inheriting from Inserter).

## BlockInserter ##

The BlockInserter allows to create a surrounding Element around the selection. Example :

```
 <p>My |sample <span>text</span></p>
 <p>My second| paragraph</p>
```
_The | represents the start and end point of the selection_

If I _BlockInsert_ this selection with a PRE element, Surface will produce :
```
 <p>My </p>
 <pre>
  <p>sample <span>text</span></p>
  <p>My second</p>
 </pre>
 <p> paragraph</p>
```

This inserter is currently used for a lot of inserters, like Pre, Quote, List, Paragraph etc.

## InlineInserter ##
The InlineInserter allows to create Elements surrounding TEXT\_NODES around the selection.

Example :
```
 <p>My |sample <span>text</span></p>
 <p>My second| paragraph</p>
```
_The | represents the start and end point of the selection_

If I _InlineInsert_ this selection with a EM element, Surface will produce :
```
 <p>My <em>sample </em><span><em>text</em></span></p>
 <p><em>My second</em> paragraph</p>
```

This inserter is currently used for text inserters, like Italic, Bold etc.

# Create an Inserter #

To create an inserter, we have to choose which insertion behavior we want to use (Inline or Block), and inherit it. In the example below, we will see how to create a StrikethroughtInserter, which will InlineInsert a Span Element, and define a style attribute to "text-decoration: line-throught".

First of all, we create the inserter class, inheriting from InlineInserter
```
public class StrikethroughtInserter extends InlineInserter<Element> {

  @Override
  protected SpanElement as(Element element) {
    return SpanElement.as(element);
  }

  @Override
  protected List<String> getApplicableTags() {
    return Arrays.asList("p", "span");
  }

  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, Selection selection) {
    return false;
  }

}
```

The InlineInserter is generic to the Element type, here SpanElement ; so we have to provide a cast helper (as) and the applicable tags. The adjustSelectionAsignee method will be seen later.

## Applicable tags ##
Insomuch as some inserter can be applied to a collection of elements instead of an only element, InlineInserter allows to define which elements can be used to apply an InsertAction (we will see InsertActions bellow).
If a surrounding matching element is available to apply the action, so it will be used by the inserter ; else, the inserter will create a new Span element.

So in our example, the applicable tags are P and SPAN.

## Insert Action ##

By default, the Inserter will just insert a SPAN element. Here, the element is not sufficient ; we have to add an inline style to strikethrought the text.
To do that, we have to define an InsertAction that will produce the inline style :

```
  protected static class StrikethroughtInsertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
      element.getStyle().setTextDecoration(TextDecoration.LINE_THROUGH);
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }
```

The onAction method allows to manipulate the inserted Element and provide the current selection, if needed.

The getEmptyElement is used for comparison purpose.

In the same way, we have to define an inversion action, that remove the strikethrought :

```
  protected static class StrikethroughtInvertAction extends InsertAction<Element> {
    private static final SpanElement emptyElement = Document.get().createSpanElement();

    @Override
    public void onAction(Element element, Selection selection) {
      element.getStyle().setTextDecoration(TextDecoration.NONE);
    }

    @Override
    public SpanElement getEmptyElement() {
      return emptyElement;
    }
  }
```

## Constructors ##

Now, we have to create constructors using these InsertActions. We create static instances for the actions, and use it as default in the constructor :

```
  protected static final StrikethroughtInsertAction insertAction = new StrikethroughtInsertAction();
  protected static final StrikethroughtInvertAction invertAction = new StrikethroughtInvertAction();

  public StrikethroughtInserter() {
    this(insertAction, invertAction);
  }

  protected StrikethroughtInserter(InsertAction<Element> action, InsertAction<Element> invertAction) {
    super(action, invertAction);
  }
```
The protected constructor is used to keep the possibility of inheriting this inserter using customs actions.

## Adjust the selection assignation ##
The method adjustSelectionAssignee is used by the inserter to adjust the selection assignation predicate. By default an inline inserter just check if the element tag is compliant with the given one. But in some case, this is not sufficient ; here, we have to check if the TextDecoration is LINE\_THROUGHT :

```
  @Override
  protected boolean adjustSelectionAssignee(Element matchingAncestor, Selection selection) {
    String textDecoration = "";
    while ((textDecoration == null || textDecoration.isEmpty()) && matchingAncestor != null) {
      textDecoration = matchingAncestor.getStyle().getTextDecoration();
      if (TextDecoration.LINE_THROUGH.getCssName().toLowerCase().equals(textDecoration)) {
        return true;
      }
      matchingAncestor = matchingAncestor.getParentElement();
    }
    return false;
  }
```

Now our inserter is fully functionnal. The next step is to create a default UI control for this inserter. See the CreateUiControl.