# movies-metadata

Proposition of wrapping of the MediaInfo DLL to fetch
metadata of movie files.

Just run `mvn:test` on project moviereader to see samples on MKV, MP4, OGG, FLV, WEBM, AVI.

The project contains a DLL to be install in your repository first with following command line :
```
mvn install:install-file -Dfile=./main/java/mediaInfo-1.0.dll -DgroupId=fr.clunven.movie -DartifactId=mediaInfo -Dversion=1.0 -Dpackaging=dll
```

@clunven
