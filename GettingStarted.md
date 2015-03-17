# Introduction #

This page demonstrate the simplest way to import and use Surface in your project


# Import the module #

The first step is to Import the Surface module to your project. To do that, add this line in your module descriptor (.gwt.xml) :

```
<inherits name='com.axeiya.stillcollab.wysiwyg.Wysiwyg'/>
```

# Create an editing surface #

The second step is to instantiate an editing surface called Surface. The default Surface implementation is the ContentEditableSurface, which uses a div with the contentEditable attribute.

```
Surface editor = new ContentEditableSurface();
```

# Create a toolbar #

With stillcollab-surface, the toolbar and the editing surface are splitted, because in a lot of cases we have to use a unique toolbar for many surfaces and reverse.
Every one can create its own toolbar, but in this example we will instantiate the DefaultToolBar :

```
DefaultToolBar toolBar = new DefaultToolBar();
```

# Register the surface in the toolbar #

To link the toolbar and the surface, we have to declare the surface to the toolbar. This is done with the addManagedSurface method :

```
toolBar.addManagedSurface(editor);
```

# Put the two component in the UI #

To finish, we just have to add them on the UI :

```
RootPanel.get().add(toolBar);
RootPanel.get().add(editor);
```

# Multiple surfaces #

If you want to manage more than one surface with the toolbar, create a new surface and add it to the toolbar :

```
Surface editor2 = new ContentEditableSurface();
toolBar.addManagedSurface(editor2);
```