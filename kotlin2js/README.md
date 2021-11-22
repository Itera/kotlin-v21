Kotlin2js
===

This folder contains a small example of a project written in Kotlin that compiles (transpiles) to JavaScript, and thus can be executed in a browser. 

## Getting started

For simplicity this project is intended to be opened and built in IntelliJ. Select `file -> open` and navigate to this folder (kotlin2js). Once opened, build the project with `ctrl+9` (or select `build -> build project` from the menu. The keyboard shortcut is different on Mac OS). Open the `index.html` file and by hovering the mouse in the upper right corner you should be offered to open a browser. This should execute the file in your preferred browser. 

## Code explanation

There are four simple files in the project. 

### index.html

A very minimal HTML-file that links to two JavaScript files: The official kotlin2js-library from JetBrains, and the compiled code from the project. In addition to this and a `<head>`-tag there is a single `div` with `id="content"`. This is the div where our application will render content. 

### style.css

Adds a border elements with the `comment` class. This is to easily see each comment as separate objects. As you might have spotted by now, this is not a UX/UI-showcase. 

### Comment.kt

Has a `Comment` data class with properties mirroring our data source (yes, that is an entire class in a single line!). Also has a function that takes a Comment instance, and generates some structured HTML based on the data. 

### Main.kt

Contains a main function that selects our content div from the DOM, fetches JSON-data from an API-endpoint using the getAsync-function and appends generated comment-HTML to the content-div. 

## Notes

This example is really bare bones, but shows how Kotlin can be used to interact with DOM in a JavaScript-environment. One can imagine how this could mature into allowing for full-stack Kotlin projects with partially shared code base between the front and back end.

## Troubleshooting

- If the file fails to load comments you might want to right click the `src` folder in IntelliJ and select `Mark Directory As -> Sources Root`