# LaddaFX
JavaFX port of Ladda for HTML/CSS (http://lab.hakim.se/ladda/)

2

A UI concept which merges loading indicators into the action that invoked them. Primarily intended for use with forms where it gives users immediate feedback upon submit rather than leaving them wondering while the browser does its thing.

[![IMAGE ALT TEXT](http://img.youtube.com/vi/iZOVJi-5ZVg/0.jpg)](http://www.youtube.com/watch?v=iZOVJi-5ZVg "LaddaFX 0.2.0 Demo")


# Usage via Code
```java
LaddaButton button = new LaddaButton("Submit");
button.setLaddaButtonStyle(LaddaButtonStyle.EXPAND_LEFT);

button.setInProgress(true);
...
button.setInProgress(false);
```

# Usage via FXML
```xml
 <LaddaButton text="Submit" laddaButtonStyle="EXPAND_LEFT"></LaddaButton>
 ```
# Maven
```xml
<dependency>
    <groupId>com.ultramixer</groupId>
    <artifactId>laddafx</artifactId>
    <version>0.2.0</version>
</dependency>
```

# Copyright
Free to use private and commercially. Please include a note to our company "UltraMixer Digital Audio Solutions / www.ultramixer.com".
