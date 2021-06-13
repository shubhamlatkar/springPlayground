# Reactive spring boot notification sample app

### JS Code to test the app

#### To post the data

```
var data = {name:"test",dept:"it"}

fetch('http://localhost:8080/', {
    method: 'POST',
            headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
})
    .then(response => response.text())
    .then(data => {
        console.log('Success:', data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

```

#### To test the event

```
function loadNotifications () {

    this.source = null;
    
    this.start = function () {
        this.source = new EventSource("http://localhost:8080/{dept_name}");
        this.source.addEventListener("notification", function (event) {
            var data = event.data;
            console.log("Event data : ", data);
            // process the data
        });
        this.source.onerror = function () {
            console.log("Error");
        };
    };
}

notifications = new loadNotifications();

notifications.start();
```
#### Ref
1. https://www.vinsguru.com/mongodb-tailable-cursor/
2. https://mkyong.com/spring-boot/spring-boot-webflux-thymeleaf-reactive-example/