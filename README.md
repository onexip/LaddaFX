# LaddaFX
JavaFX port of Ladda for HTML/CSS (http://lab.hakim.se/ladda/)

[![IMAGE ALT TEXT](http://img.youtube.com/vi/JAhmbOIq0AY/0.jpg)](http://www.youtube.com/watch?v=JAhmbOIq0AY "LaddaFX Demo")


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
    <version>0.1.0</version>
</dependency>
```

# Copyright
Free to use private and commercially. Please include a note to our company "UltraMixer Digital Audio Solutions / www.ultramixer.com".
