# LaddaFX
JavaFX port of Ladda for HTML/CSS (http://lab.hakim.se/ladda/)

![My image](tobium.github.com/LaddaFX/LaddaFX-Demo-screenshot.png)

https://www.youtube.com/watch?v=JAhmbOIq0AY

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
