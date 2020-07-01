# ili2c-api

In `libs/` there is a "native image" ready (fat) `ili2c.jar`. It's the result of the ili2c graal tests, which can be found [here](https://github.com/edigonzales/ili2c/tree/graal-native): No gui stuff and the jar includes `native-image.properties` and `reflection-config.properties`.

I cannot use the original `ili2c-core` and `ili2c-tool` since `ili2c-tool` contains the gui classes, which break the native-image build. If this will change in the near future I still need to figure out if I can use the same `native-image.properties` and `reflection-config.properties` files and use the same approach when building the shared library.

Build fat jar (	out of sheer laziness) and build the native shared library:
```
./gradlew clean build shadowJar
native-image --no-server -cp build/libs/ili2c-utils-all.jar --shared -H:Name=libili2c
```

Compile "dummy" C program:
```
cc -Wall -I. -L. -lili2c getlastmodelname.c -o getlastmodelname
```

Run program:
```
./getlastmodelname ./SO_AGI_AV_GB_Administrative_Einteilungen_Publikation_20180822.ili
./getlastmodelname /Users/stefan/Downloads/Nutzungsplanung_V1_1.ili
```