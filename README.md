# LaddaFX
JavaFX port of Ladda for HTML/CSS (http://lab.hakim.se/ladda/)

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
