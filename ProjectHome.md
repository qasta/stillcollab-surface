Surface is a wysiwyg editing surface that doesn't rely on browser's execCommand and queryCommandState commands.

Because of using these commands leads to output differences, this is not a good behavior for products that need to compute produced HTML elsewhere than in a browser.

Furthermore, table, list, paragraph, etc. manipulation can differ from a browser to another one, so this wysiwyg try to handle these differences to provide the same look and feel for every browser.

To finish, this editor is, as possible as it could be, developed in Java/GWT, so it can be easily embedded in every GWT application, debugged and improved