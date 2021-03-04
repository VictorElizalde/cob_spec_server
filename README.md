# Running the environment

To run the tests and build the .jar:
```
$ gradle build
```
To run server:
```
$ java -jar app/build/libs/app.jar
```

The server supports custom ports and directories that can be passed as flags

```
$ java -jar app/build/libs/app.jar -p 2000 -d /custom/directory  
```
