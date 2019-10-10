# Code guidelines, formatting and naming conventions
The following conventions should be considered when contributing to the plugin. It greatly helps to read through the code as well as reduce merge conflicts due to different style guides.

## Formatting conventions
### Indentation
Please use spaces as tab policy with an indentation size of 4.

### Imports
Import statements should be sorted alphabetical to prevent double imports.

### Brackets
Opening brackets should be placed in the same line as the class/method implementation. See example
```Java
class TestClass{
    private testMethod{
        
    }
}
```

### Comments
Comments are mandatory ;)
#### Single-Line-comments
```Java
//This is a Single-line comment for the following line
String test="";
String test2=""; //This is a Single-line comment for the statement in the same line (aka Trailing comment)
```
Single-line comments are used to describe certain line of codes. They can be placed right before the line or in the same line after the statement.
#### Block-comments
```Java
/*This is a block comment
with multiple lines*/
```
Block comments are used to provide descriptive information of files and methods. They may be used at the beginning of each file and before each method, but can also be used in other places, such as inside methods.
Block comments inside a function or method should be indented to the same level as the code they describe.

## Naming conventions

## Constants
Use upper case with underscore spacing where necessary.
```Java
final String THIS_IS_A_CONSTANT="TestValue";
```
## Classes
Class names should be nouns in PascalCase (UpperCamelCase), with the first letter of every word capitalised. Use whole words - avoid acronyms and abbreviations (unless the abbreviation is much more widely used than the long form) Filename must match the class name.
```Java
class StringUtil...
class FileUtil...
```

## Methods
Methods should be verbs in lowerCamelCase or a multi-word name that begins with a verb in lowercase.
```Java
static String appendQuote(...)...
static String splitPath(...)...
```

## Variables
