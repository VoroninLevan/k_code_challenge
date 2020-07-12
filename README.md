# k_code_challenge
Assessment


## Filter test program
Contains two test methods in order to test 'Star' and 'Sauna' filters functionality.
Contains framework to interract with web elements. Separate page object for each page for maintainability of code. 
Tests runnable from `suite.xml`
### Tests:
1. testSaunaFilterOptionFunctionality()
#### 'Sauna' filter option test
Tests 'Sauna' filter option functionality.
The following cases considered:
* Filter switched on -> only properties with 'Sauna' should appear in the search result
* Filter checkbox checked when filter switched on
* Filter switched off -> properties without 'Sauna' should appear in the search result

2. testStarFilterOptionFunctionality()
#### 'Star' filter option test
Tests 'Star' filter option functionality. Stars might vary 1-5. The following cases considered:
* Filter switched on -> only properties with 'N Star' should appear in the search result
* Filter switched off -> all properties should appear in the search result

### Parameters:
It is possible to set desired browser, window hight and width from `parameters.xml` at the moment.
* `<profile>` - currently supports two browsers: 
  1. Chrome - 'chrome',
  2. Firefox - 'firefox'
* `<window_width>`, `<window_height>` - specify browser window width an height here
* `<fullscreen>` - might have one out of two values:
  1. `true` - sets window to fullscreen mode, values from `<window_width>`, `<window_height>` ignored,
  2. `false` - will get values from `<window_width>`, `<window_height>`
  
### Framework:
**Test** from **page** separation - contains `BasePage` and `BaseTest` classes

#### BasePage:
Contains navigation methods for interacting with web elements. Parent class for other classes which represent pages.

#### BaseTest:
Parent class for other test classes. WebDriver initialization starts here for each test method. `ParameterReader` and `Driver` classes initializes here.

#### ParameterReader:
Parses `parameter.xml` file for setting up `Driver` object

#### Driver:
Magic happens here. Sets up and starts browser. Currently supports 2 profiles: Chrome and Firefox.
