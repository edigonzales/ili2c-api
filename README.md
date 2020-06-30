# ili2c-utils

```
./gradlew clean build shadowJar
native-image --no-server -cp build/libs/ili2c-utils-all.jar --shared -H:Name=libfoobar
```

```
cc -Wall -I. -L. -lfoobar getmodelname.c
```
