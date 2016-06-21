# movies-metadata

Proposition of wrapping of the MediaInfo DLL to fetch
metadata of movie files.

Just run `mvn:test` on project movieader to see samples on MKV, MP4, OGG, FLV, WEBM, AVI.

## Prerequisites

In order for this tool to work you need to download and install a library named MediaInfo available at
[https://mediaarea.net/fr/MediaInfo/Download/Source](https://mediaarea.net/fr/MediaInfo/Download/Source)

At the moment the tool only works for Windows.


## Windows

The project contains a DLL to be installed in your local repository. You can do this after checkout with the the following command:
```
mvn install:install-file -Dfile=./movieader/src/main/java/mediaInfo-1.0.dll -DgroupId=fr.clunven.movie -DartifactId=mediaInfo -Dversion=1.0 -Dpackaging=dll
```

@clunven
