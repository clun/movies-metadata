# Movies Metadata Parser

Proposition of wrapper for the MediaInfo DLL in order to parse and retrieve metadatas movies.

At the moment the tool only works for Windows (as base on a DLL)

## Prerequisites 

In order for this tool to work you need to download and install a library named MediaInfo available at
[https://mediaarea.net/fr/MediaInfo/Download/Source](https://mediaarea.net/fr/MediaInfo/Download/Source)

The library is also provided in this reposotiry in `src/main/resources`. 

To install it in your maven repository you can use the following command:
```
mvn install:install-file -Dfile=./movieader/src/main/resources/mediaInfo-1.0.dll -DgroupId=com.github.clun.movie -DartifactId=mediaInfo -Dversion=1.0 -Dpackaging=dll
```

## Usage

Samples videos have been put in folder `src/test/resources` to illustrate. As a consequence, just run `mvn:test` on project to see samples outputs on MKV, MP4, OGG, FLV, WEBM and 3GP files.


### Sample code
```java

// Parsing
MovieMetadata video = MovieMetadataParser.getInstance().parseFile("./src/test/resources/small.mp4");

// Access attributs
System.out.println("width=" + video.getVideoWidth().get());

// Access any metadata with get (return an optional)
// Enums are provided for this list of available keys (missing part of mediainfo by the way)
video.get(General.FORMAT);
video.get(Video.DURATION_STRING);
video.get(Video.WIDTH_STRING);
video.get(Video.HEIGHT_STRING);
video.get(Video.BITRATE_STRING);
video.get(Audio.COMPRESSION_RATIO);
```

### Sample Output for a MP4 file

```ini
====================small.mp4 ======================================
width=560
height=320

[KEYS GENERAL]
DURATION=5568
TAGGED_DATE=UTC 2010-03-20 21:29:12
ENCODED_DATE=UTC 2010-03-20 21:29:11
NAME=C:\clu\dev\workspace-documentaires\movies-metadata\movie-metadata-parser\.\src\test\resources\small.mp4
OVERALLBITRATE_STRING=551 Kbps
OVERALLBITRATE_MODE=VBR
FORMAT=MPEG-4
OVERALLBITRATE=551194
FILESIZE_ASSTRING=375 KiB
OVERALLBITRATE_MODE_STRING=Variable
DURATION_STRING=5s 568ms
ENCODED_APPLICATION_STRING=HandBrake 0.9.4 2009112300
FILESIZE=383631

[AUDIO]
BITRATE=83.1 Kbps
ID_STRING=2
SAMPLINGRATE_STRING=48.0 KHz
LANGUAGE_STRING=English
STREAMSIZE_STRING=56.4 KiB (15%)
CHANNELPOSITIONS=Front: C
TITLE=Stereo
DURATION_STRING=5s 568ms
FORMAT_INFO=Advanced Audio Codec
CHANNEL_STRING=1 channel
ENCODED_DATE=UTC 2010-03-20 21:29:11
FORMAT_PROFILE=LC
TAGGED_DATE=UTC 2010-03-20 21:29:12
BITRATE_MAXIMUM_STRING=91.6 Kbps
COMPRESSION_MODE_STRING=Lossy
CODECID=40
FORMAT=AAC
BITRATE_MODE_STRING=Variable

[VIDEO]
DURATION=5533
COLORSPACE=YUV
DURATION_STRING=5s 533ms
FRAMERATE_STRING=30.000 fps
FORMAT=AVC
FORMAT_SETTINGS_REFFRAMES_STRING=2 frames
FRAMERATE_MODE_STRING=Constant
BITDEPTH_STRING=8 bits
WIDTH_STRING=560 pixels
BITRATE=465642
ENCODED_LIBRARY_STRING=x264 core 79
FORMAT_SETTINGS_CABAC_STRING=No
MATRIX_COEFFICIENTS=BT.709
STREAMSIZE_STRING=315 KiB (84%)
DISPLAYASPECTRATIO_STRING=16:9
CODECID_INFO=Advanced Video Coding
ID=1
FORMAT_INFO=Advanced Video Codec
HEIGHT_STRING=320 pixels
CHROMASUBSAMPLING=4:2:0
SCANTYPE_STRING=Progressive
ENCODED_LIBRARY_SETTINGS=cabac=0 / ref=2 / deblock=1:0:0 / analyse=0x1:0x111 / me=umh / subme=6 / psy=1 / psy_rd=1.0:0.0 / mixed_ref=1 / me_range=16 / chroma_me=1 / trellis=0 / 8x8dct=0 / cqm=0 / deadzone=21,11 / chroma_qp_offset=-2 / threads=6 / nr=0 / decimate=1 / mbaff=0 / constrained_intra=0 / bframes=0 / wpredp=0 / keyint=300 / keyint_min=30 / scenecut=40 / rc_lookahead=40 / rc=crf / mbtree=1 / crf=20.0 / qcomp=0.60 / qpmin=10 / qpmax=51 / qpstep=4 / ip_ratio=1.40 / aq=1:1.00
TRANSFER_CHARACTERISTICS=BT.709
BITRATE_STRING=466 Kbps
ENCODED_DATE=UTC 2010-03-20 21:29:11
COLOUR_PRIMARIES=BT.709
HEIGHT=320
COLOUR_RANGE=Limited
WIDTH=560
TAGGED_DATE=UTC 2010-03-20 21:29:12
CODECID=avc1
FORMAT_PROFILE=Baseline@L3
```

## MIT License

This repository is provided with The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
