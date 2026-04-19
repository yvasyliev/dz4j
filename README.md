[![Maven Central Version](https://img.shields.io/maven-central/v/io.github.yvasyliev/dz4j)](https://central.sonatype.com/artifact/io.github.yvasyliev/dz4j)
[![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fio%2Fgithub%2Fyvasyliev%2Fdz4j%2Fmaven-metadata.xml&label=maven-central-snapshots)](https://central.sonatype.com/repository/maven-snapshots/io/github/yvasyliev/dz4j/maven-metadata.xml)
[![javadoc](https://javadoc.io/badge2/io.github.yvasyliev/dz4j/javadoc.svg)](https://javadoc.io/doc/io.github.yvasyliev/dz4j)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Build](https://github.com/yvasyliev/dz4j/actions/workflows/build.yml/badge.svg)](https://github.com/yvasyliev/dz4j/actions/workflows/build.yml)
[![CodeQL](https://github.com/yvasyliev/dz4j/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/yvasyliev/dz4j/actions/workflows/github-code-scanning/codeql)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=bugs)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=coverage)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=io.github.yvasyliev%3Adz4j&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=io.github.yvasyliev%3Adz4j)

# Dz4j

An unofficial Java wrapper for the [Deezer API](https://developers.deezer.com/api).

> [!IMPORTANT]
> Dz4j is an independent open source project and is not affiliated with, endorsed by, sponsored by, or otherwise
> officially connected to Deezer.

## Features

- Java-first client for Deezer REST API endpoints
- Sync (`execute()`) and async (`executeAsync()`) request execution
- OAuth helpers for login URL generation and access token exchange
- Typed models and request factories for major Deezer domains

## Requirements

- Minimum Java runtime for using the library: **Java 17**
- JDK required to build this project from source: **JDK 21**

## Installation

### Gradle

Release:

[//]: # (x-release-please-released-start-version)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.yvasyliev:dz4j:3.0.0'
}
```

[//]: # (x-release-please-released-end)

Snapshot:

[//]: # (x-release-please-start-version)

```groovy
repositories {
    mavenCentral()
    maven {
        url = uri('https://central.sonatype.com/repository/maven-snapshots/')
    }
}

dependencies {
    implementation 'io.github.yvasyliev:dz4j:3.0.1-SNAPSHOT'
}
```

[//]: # (x-release-please-end)

### Maven

Release:

[//]: # (x-release-please-released-start-version)

```xml
<dependencies>
    <dependency>
        <groupId>io.github.yvasyliev</groupId>
        <artifactId>dz4j</artifactId>
        <version>3.0.0</version>
    </dependency>
</dependencies>
```

[//]: # (x-release-please-released-end)

Snapshot:

[//]: # (x-release-please-start-version)

```xml
<repositories>
    <repository>
        <id>ossrh-snapshots</id>
        <url>https://central.sonatype.com/repository/maven-snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.yvasyliev</groupId>
        <artifactId>dz4j</artifactId>
        <version>3.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

[//]: # (x-release-please-end)

## Build From Source

JDK 21 is required for local builds.

Linux/MacOS:

```bash
./gradlew build
```

Windows:

```powershell
.\gradlew.bat build
```

> [!TIP]
> Running the commands above will compile the code, run the tests, and create both the regular JAR and a fat (shadow)
> JAR in the `build/libs` directory (for example, `dz4j-<version>.jar` and `dz4j-<version>-all.jar`).

## Quick Start

```java
package com.example;

import io.github.yvasyliev.dz4j.DeezerClient;

public class QuickStart {
    public static void main(String[] args) {
        var deezerClient = new DeezerClient();

        // Get album by ID (e.g., Discovery by Daft Punk)
        var album = deezerClient.album().getAlbum(302127).execute();
        System.out.println(album.title());
    }
}
```

## Customization

`DeezerClient` is built on top of the [Feign Client](https://github.com/OpenFeign/feign) and by default uses
`java.net.HttpURLConnection` for HTTP operations.

### Switching the HTTP client

If you want to use a different HTTP client, for example `OkHttp`, add the `io.github.openfeign:feign-okhttp`
dependency and configure the client like this:

```java
var deezerClient = DeezerClient.builder()
        .config(config -> config.feign(feign -> feign.client(new OkHttpClient())))
        .build();
```

### Enabling request/response logging

If you want to enable request/response logging, for example with `SLF4J`, add the `io.github.openfeign:feign-slf4j`
dependency and configure the client like this:

```java
var deezerClient = DeezerClient.builder()
        .config(config -> config.feign(feign -> feign.logger(new Slf4jLogger())))
        .build();
```

Follow the [Feign Client](https://github.com/OpenFeign/feign) documentation to discover more customization options.

## Authorization (OAuth)

Some Deezer endpoints are public, while others require user authorization.
For protected endpoints, provide a valid `access_token` obtained through Deezer OAuth 2.0 Authorization Code Flow.

1. Create an app in the [Deezer developer console](https://developers.deezer.com/myapps).
2. Generate login URL with `deezerClient.oauth().getLoginUrl(...)`.
3. Open it in a browser and complete login/consent.
4. Copy `code` value from the browser URL bar.
5. Exchange it with `deezerClient.oauth().getAccessToken(...)`.
6. Set authorization via `deezerClient.authorization(...)`.

Example:

```java
package com.example;

import io.github.yvasyliev.dz4j.DeezerClient;
import io.github.yvasyliev.dz4j.authorization.AuthorizationCodeFlow;
import io.github.yvasyliev.dz4j.model.Permission;

import java.net.URI;
import java.util.Scanner;

public class OAuthExample {
    public static void main(String[] args) {
        var deezerClient = new DeezerClient();

        // values from https://developers.deezer.com/myapps
        var appId = 123456;
        var secret = "your-app-secret";
        var redirectUri = URI.create("http://localhost:8080/callback");

        // https://connect.deezer.com/oauth/auth.php?app_id=123456&
        // redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcallback&perms=basic_access
        var loginUrl = deezerClient.oauth().getLoginUrl(
                appId,
                redirectUri,
                Permission.BASIC_ACCESS
        );
        System.out.println(
                "Open the following link in your browser and log in to Deezer: " + loginUrl
        );

        // http://localhost:8080/callback?code=A_CODE_GENERATED_BY_DEEZER
        System.out.println(
                "Enter a `code` from your browser URL bar when the login is completed: "
        );

        try (var scanner = new Scanner(System.in)) {
            var code = scanner.next();

            deezerClient.authorization(new AuthorizationCodeFlow(appId, secret, code));
        }

        // Now deezerClient is authorized and you can make authorized requests
        var me = deezerClient.user().getUser().execute();
        System.out.println(me.firstname());
    }
}
```

> [!WARNING]
> Deezer API does not provide automatic token refresh in this flow. Repeat the authorization process (steps 2-6) when
> the token expires.

> [!TIP]
> It's recommended not to hardcode any secrets in the source code. Use environment variables instead.

> [!TIP]
> By creating a loginUrl with the `Permission.OFFLINE_ACCESS` scope, you can obtain a token that does not expire.
> This way you can store a token in environment variables and reuse it across application restarts:
>
> ```java
> deezerClient.authorization(Authorization.of(new AccessToken("token")));
> ```
>
> It's worth noting that `Permission.OFFLINE_ACCESS` provides full access to the user's profile.

> [!TIP]
> If you need to remove authorization from the client, you can call `deezerClient.removeAuthorization()`.

Find more details about authorization at [Deezer for developers](https://developers.deezer.com/api/oauth).

## Error Handling

Every exception thrown by this library is wrapped into a `DeezerException` (extends `RuntimeException`) or one of its
subclasses:

```java
package com.example;

import io.github.yvasyliev.dz4j.DeezerClient;
import io.github.yvasyliev.dz4j.exception.DataException;
import io.github.yvasyliev.dz4j.exception.DeezerException;
import io.github.yvasyliev.dz4j.exception.OAuthException;

public class ExceptionHandling {
    public static void main(String[] args) {
        var deezerClient = new DeezerClient();

        try {
            var me = deezerClient.user().getUser(123).execute();
            System.out.println(me.firstname());
        } catch (OAuthException e) {
            // invalid token
        } catch (DataException e) {
            // user not found
        } catch (DeezerException e) {
            // other errors
        }
    }
}
```

Check the
[io.github.yvasyliev.dz4j.exception](https://javadoc.io/doc/io.github.yvasyliev/dz4j/io/github/yvasyliev/dz4j/exception/package-summary.html)
package for all available exceptions.

## Available API Methods

### Album

#### Get album by ID

```java
var album = deezerClient.album().getAlbum(302127).execute();
var albumFuture = deezerClient.album().getAlbum(302127).executeAsync();

System.out.println("Album: " + album);
System.out.println("Album (async): " + albumFuture.join());
```

#### Get album fans

```java
var fans = deezerClient.album().getFans(302127).execute();
var fansFuture = deezerClient.album().getFans(302127).executeAsync();

System.out.println("Album fans: " + fans);
System.out.println("Album fans (async): " + fansFuture.join());
```

> [!NOTE]
> It was noticed that this endpoint always returns an empty list.

#### Get album tracks

```java
var tracks = deezerClient.album().getTracks(302127).execute();
var tracksFuture = deezerClient.album().getTracks(302127).executeAsync();

System.out.println("Album tracks: " + tracks);
System.out.println("Album tracks (async): " + tracksFuture.join());
```

### Artist

#### Get artist by ID

```java
var artist = deezerClient.artist().getArtist(27).execute();
var artistFuture = deezerClient.artist().getArtist(27).executeAsync();

System.out.println("Artist: " + artist);
System.out.println("Artist (async): " + artistFuture.join());
```

#### Get artist albums

```java
var albums = deezerClient.artist().getAlbums(27).execute();
var albumsFuture = deezerClient.artist().getAlbums(27).executeAsync();

System.out.println("Artist albums: " + albums);
System.out.println("Artist albums (async): " + albumsFuture.join());
```

#### Get artist fans

```java
var fans = deezerClient.artist().getFans(27).execute();
var fansFuture = deezerClient.artist().getFans(27).executeAsync();

System.out.println("Artist fans: " + fans);
System.out.println("Artist fans (async): " + fansFuture.join());
```

> [!NOTE]
> It was noticed that this endpoint always returns an empty list.

#### Get artist playlists

```java
var playlists = deezerClient.artist().getPlaylists(27).execute();
var playlistsFuture = deezerClient.artist().getPlaylists(27).executeAsync();

System.out.println("Artist playlists: " + playlists);
System.out.println("Artist playlists (async): " + playlistsFuture.join());
```

#### Get artist radio

```java
var radioTracks = deezerClient.artist().getRadio(27).execute();
var radioTracksFuture = deezerClient.artist().getRadio(27).executeAsync();

System.out.println("Artist radio: " + radioTracks);
System.out.println("Artist radio (async): " + radioTracksFuture.join());
```

#### Get related artists

```java
var relatedArtists = deezerClient.artist().getRelated(27).execute();
var relatedArtistsFuture = deezerClient.artist().getRelated(27).executeAsync();

System.out.println("Related artists: " + relatedArtists);
System.out.println("Related artists (async): " + relatedArtistsFuture.join());
```

#### Get artist top tracks

```java
var topTracks = deezerClient.artist().getTop(27).execute();
var topTracksFuture = deezerClient.artist().getTop(27).executeAsync();

System.out.println("Artist top tracks: " + topTracks);
System.out.println("Artist top tracks (async): " + topTracksFuture.join());
```

### Chart

#### Get chart by genre ID

```java
var chart = deezerClient.chart().getChart(152).execute();
var chartFuture = deezerClient.chart().getChart(152).executeAsync();

System.out.println("Chart: " + chart);
System.out.println("Chart (async): " + chartFuture.join());
```

#### Get albums chart by genre ID

```java
var albumsChart = deezerClient.chart().getAlbumsChart(152).execute();
var albumsChartFuture = deezerClient.chart().getAlbumsChart(152).executeAsync();

System.out.println("Albums chart: " + albumsChart);
System.out.println("Albums chart (async): " + albumsChartFuture.join());
```

#### Get artists chart by genre ID

```java
var artistsChart = deezerClient.chart().getArtistsChart(152).execute();
var artistsChartFuture = deezerClient.chart().getArtistsChart(152).executeAsync();

System.out.println("Artists chart: " + artistsChart);
System.out.println("Artists chart (async): " + artistsChartFuture.join());
```

#### Get playlists chart by genre ID

```java
var playlistsChart = deezerClient.chart().getPlaylistsChart(152).execute();
var playlistsChartFuture = deezerClient.chart().getPlaylistsChart(152).executeAsync();

System.out.println("Playlists chart: " + playlistsChart);
System.out.println("Playlists chart (async): " + playlistsChartFuture.join());
```

#### Get podcasts chart by genre ID

```java
var podcastsChart = deezerClient.chart().getPodcastsChart(152).execute();
var podcastsChartFuture = deezerClient.chart().getPodcastsChart(152).executeAsync();

System.out.println("Podcasts chart: " + podcastsChart);
System.out.println("Podcasts chart (async): " + podcastsChartFuture.join());
```

#### Get tracks chart by genre ID

```java
var tracksChart = deezerClient.chart().getTracksChart(152).execute();
var tracksChartFuture = deezerClient.chart().getTracksChart(152).executeAsync();

System.out.println("Tracks chart: " + tracksChart);
System.out.println("Tracks chart (async): " + tracksChartFuture.join());
```

### Editorial

#### Get editorials

```java
var editorials = deezerClient.editorial().getEditorials().execute();
var editorialsFuture = deezerClient.editorial().getEditorials().executeAsync();

System.out.println("Editorials: " + editorials);
System.out.println("Editorials (async): " + editorialsFuture.join());
```

#### Get editorial by genre ID

```java
var editorial = deezerClient.editorial().getEditorial(152).execute();
var editorialFuture = deezerClient.editorial().getEditorial(152).executeAsync();

System.out.println("Editorial: " + editorial);
System.out.println("Editorial (async): " + editorialFuture.join());
```

#### Get editorial selection by genre ID

```java
var selection = deezerClient.editorial().getEditorialSelection(152).execute();
var selectionFuture = deezerClient.editorial().getEditorialSelection(152).executeAsync();

System.out.println("Editorial selection: " + selection);
System.out.println("Editorial selection (async): " + selectionFuture.join());
```

#### Get editorial releases by genre ID

```java
var releases = deezerClient.editorial().getEditorialReleases(152).execute();
var releasesFuture = deezerClient.editorial().getEditorialReleases(152).executeAsync();

System.out.println("Editorial releases: " + releases);
System.out.println("Editorial releases (async): " + releasesFuture.join());
```

#### Get editorial charts by genre ID

```java
var charts = deezerClient.editorial().getEditorialCharts(152).execute();
var chartsFuture = deezerClient.editorial().getEditorialCharts(152).executeAsync();

System.out.println("Editorial charts: " + charts);
System.out.println("Editorial charts (async): " + chartsFuture.join());
```

### Episode

#### Get episode by ID

```java
var episode = deezerClient.episode().getEpisode(605632582).execute();
var episodeFuture = deezerClient.episode().getEpisode(605632582).executeAsync();

System.out.println("Episode: " + episode);
System.out.println("Episode (async): " + episodeFuture.join());
```

> [!NOTE]
> It was noticed that this endpoint randomly returns `InvalidQueryException`.

#### Bookmark episode

```java
var bookmarkResponse = deezerClient.episode().bookmarkEpisode(0, 30).execute();
var bookmarkResponseFuture = deezerClient.episode().bookmarkEpisode(0, 30).executeAsync();

System.out.println("Bookmark response: " + bookmarkResponse);
System.out.println("Bookmark response (async): " + bookmarkResponseFuture.join());
```

#### Unbookmark episode

```java
var unbookmarkResponse = deezerClient.episode()
        .unbookmarkEpisode(605632582)
        .execute();
var unbookmarkResponseFuture = deezerClient.episode()
        .unbookmarkEpisode(605632582)
        .executeAsync();

System.out.println("Unbookmark response: " + unbookmarkResponse);
System.out.println("Unbookmark response (async): " + unbookmarkResponseFuture.join());
```

### Genre

#### Get genres

```java
var genres = deezerClient.genre().getGenres().execute();
var genresFuture = deezerClient.genre().getGenres().executeAsync();

System.out.println("Genres: " + genres);
System.out.println("Genres (async): " + genresFuture.join());
```

#### Get genre by ID

```java
var genre = deezerClient.genre().getGenre(152).execute();
var genreFuture = deezerClient.genre().getGenre(152).executeAsync();

System.out.println("Genre: " + genre);
System.out.println("Genre (async): " + genreFuture.join());
```

#### Get genre artists

```java
var artists = deezerClient.genre().getArtists(152).execute();
var artistsFuture = deezerClient.genre().getArtists(152).executeAsync();

System.out.println("Genre artists: " + artists);
System.out.println("Genre artists (async): " + artistsFuture.join());
```

#### Get genre radios

```java
var radios = deezerClient.genre().getRadios(152).execute();
var radiosFuture = deezerClient.genre().getRadios(152).executeAsync();

System.out.println("Genre radios: " + radios);
System.out.println("Genre radios (async): " + radiosFuture.join());
```

### Infos

#### Get infos

```java
var infos = deezerClient.infos().getInfos().execute();
var infosFuture = deezerClient.infos().getInfos().executeAsync();

System.out.println("Infos: " + infos);
System.out.println("Infos (async): " + infosFuture.join());
```

### OAuth

#### Get access token

```java
var accessToken = deezerClient.oauth()
        .getAccessToken(123456, "your-app-secret", "your-code")
        .execute();
var accessTokenFuture = deezerClient.oauth()
        .getAccessToken(123456, "your-app-secret", "your-code")
        .executeAsync();

System.out.println("Access token: " + accessToken);
System.out.println("Access token (async): " + accessTokenFuture.join());
```

#### Get login URL

```java
var loginUrl = deezerClient.oauth().getLoginUrl(
        123456,
        URI.create("http://localhost:8080/callback"), 
        Permission.BASIC_ACCESS
);

System.out.println("Login URL: " + loginUrl);
```

### OEmbed

#### Get album oEmbed

```java
var albumOEmbed = deezerClient.oEmbed().getAlbumOEmbed(500138701).execute();
var albumOEmbedFuture = deezerClient.oEmbed().getAlbumOEmbed(500138701).executeAsync();

System.out.println("Album oEmbed: " + albumOEmbed);
System.out.println("Album oEmbed (async): " + albumOEmbedFuture.join());
```

#### Get episode oEmbed

```java
var episodeOEmbed = deezerClient.oEmbed().getEpisodeOEmbed(605632582).execute();
var episodeOEmbedFuture = deezerClient.oEmbed().getEpisodeOEmbed(605632582).executeAsync();

System.out.println("Episode oEmbed: " + episodeOEmbed);
System.out.println("Episode oEmbed (async): " + episodeOEmbedFuture.join());
```

#### Get playlist oEmbed

```java
var playlistOEmbed = deezerClient.oEmbed()
        .getPlaylistOEmbed(11278651204L)
        .execute();
var playlistOEmbedFuture = deezerClient.oEmbed()
        .getPlaylistOEmbed(11278651204L)
        .executeAsync();

System.out.println("Playlist oEmbed: " + playlistOEmbed);
System.out.println("Playlist oEmbed (async): " + playlistOEmbedFuture.join());
```

#### Get show oEmbed

```java
var showOEmbed = deezerClient.oEmbed().getShowOEmbed(28142).execute();
var showOEmbedFuture = deezerClient.oEmbed().getShowOEmbed(28142).executeAsync();

System.out.println("Show oEmbed: " + showOEmbed);
System.out.println("Show oEmbed (async): " + showOEmbedFuture.join());
```

#### Get track oEmbed

```java
var trackOEmbed = deezerClient.oEmbed().getTrackOEmbed(2646150812L).execute();
var trackOEmbedFuture = deezerClient.oEmbed().getTrackOEmbed(2646150812L).executeAsync();

System.out.println("Track oEmbed: " + trackOEmbed);
System.out.println("Track oEmbed (async): " + trackOEmbedFuture.join());
```

#### Get page oEmbed

```java
var pageOEmbed = deezerClient.oEmbed().getPageOEmbed("2gk4fY").execute();
var pageOEmbedFuture = deezerClient.oEmbed().getPageOEmbed("2gk4fY").executeAsync();

System.out.println("Page oEmbed: " + pageOEmbed);
System.out.println("Page oEmbed (async): " + pageOEmbedFuture.join());
```

### Options

#### Get options

```java
var options = deezerClient.options().getOptions().execute();
var optionsFuture = deezerClient.options().getOptions().executeAsync();

System.out.println("Options: " + options);
System.out.println("Options (async): " + optionsFuture.join());
```

### Playlist

#### Get playlist by ID

```java
var playlist = deezerClient.playlist().getPlaylist(10517702202L).execute();
var playlistFuture = deezerClient.playlist().getPlaylist(10517702202L).executeAsync();

System.out.println("Playlist: " + playlist);
System.out.println("Playlist (async): " + playlistFuture.join());
```

#### Get playlist fans

```java
var fans = deezerClient.playlist().getFans(10517702202L).execute();
var fansFuture = deezerClient.playlist().getFans(10517702202L).executeAsync();

System.out.println("Playlist fans: " + fans);
System.out.println("Playlist fans (async): " + fansFuture.join());
```

#### Get playlist tracks

```java
var tracks = deezerClient.playlist().getTracks(10517702202L).execute();
var tracksFuture = deezerClient.playlist().getTracks(10517702202L).executeAsync();

System.out.println("Playlist tracks: " + tracks);
System.out.println("Playlist tracks (async): " + tracksFuture.join());
```

#### Get playlist radio

```java
var radio = deezerClient.playlist().getRadio(10517702202L).execute();
var radioFuture = deezerClient.playlist().getRadio(10517702202L).executeAsync();

System.out.println("Playlist radio: " + radio);
System.out.println("Playlist radio (async): " + radioFuture.join());
```

#### Add tracks to playlist

```java
var addTracksResult = deezerClient.playlist()
        .addTracks(10517702202L, 1807705517L, 1808237467L)
        .execute();
var addTracksResultFuture = deezerClient.playlist()
        .addTracks(10517702202L, 1807705517L, 1808237467L)
        .executeAsync();

System.out.println("Tracks added: " + addTracksResult);
System.out.println("Tracks added (async): " + addTracksResultFuture.join());
```

#### Delete tracks from playlist

```java
var deleteTracksResult = deezerClient.playlist()
        .deleteTracks(10517702202L, 1807705517L, 1808237467L)
        .execute();
var deleteTracksResultFuture = deezerClient.playlist()
        .deleteTracks(10517702202L, 1807705517L, 1808237467L)
        .executeAsync();

System.out.println("Tracks deleted: " + deleteTracksResult);
System.out.println("Tracks deleted (async): " + deleteTracksResultFuture.join());
```

#### Order playlist tracks

```java
var orderTracksResult = deezerClient.playlist()
        .orderTracks(10517702202L, 1808237467L, 1807705517L)
        .execute();
var orderTracksResultFuture = deezerClient.playlist()
        .orderTracks(10517702202L, 1808237467L, 1807705517L)
        .executeAsync();

System.out.println("Tracks ordered: " + orderTracksResult);
System.out.println("Tracks ordered (async): " + orderTracksResultFuture.join());
```

#### Mark playlist as seen

```java
var markAsSeenResult = deezerClient.playlist().markAsSeen(10517702202L).execute();
var markAsSeenResultFuture = deezerClient.playlist().markAsSeen(10517702202L).executeAsync();

System.out.println("Playlist marked as seen: " + markAsSeenResult);
System.out.println("Playlist marked as seen (async): " + markAsSeenResultFuture.join());
```

#### Update playlist

```java
var updateResult = deezerClient.playlist()
        .updatePlaylist(10517702202L)
        .title("New Title")
        .execute();
var updateResultFuture = deezerClient.playlist()
        .updatePlaylist(10517702202L)
        .title("New Title")
        .executeAsync();

System.out.println("Playlist updated: " + updateResult);
System.out.println("Playlist updated (async): " + updateResultFuture.join());
```

#### Delete playlist

```java
var deleteResult = deezerClient.playlist().deletePlaylist(10517702202L).execute();
var deleteResultFuture = deezerClient.playlist().deletePlaylist(10517702202L).executeAsync();

System.out.println("Playlist deleted: " + deleteResult);
System.out.println("Playlist deleted (async): " + deleteResultFuture.join());
```

### Podcast

#### Get podcast by ID

```java
var podcast = deezerClient.podcast().getPodcast(1447162).execute();
var podcastFuture = deezerClient.podcast().getPodcast(1447162).executeAsync();

System.out.println("Podcast: " + podcast);
System.out.println("Podcast (async): " + podcastFuture.join());
```

> [!NOTE]
> It was noticed that this endpoint randomly returns `InvalidQueryException`.

#### Get podcast episodes

```java
var episodes = deezerClient.podcast().getEpisodes(1447162).execute();
var episodesFuture = deezerClient.podcast().getEpisodes(1447162).executeAsync();

System.out.println("Podcast episodes: " + episodes);
System.out.println("Podcast episodes (async): " + episodesFuture.join());
```

> [!NOTE]
> It was noticed that this endpoint randomly returns `InvalidQueryException`.

### Radio

#### Get radio genres

```java
var genres = deezerClient.radio().getGenres().execute();
var genresFuture = deezerClient.radio().getGenres().executeAsync();

System.out.println("Radio genres: " + genres);
System.out.println("Radio genres (async): " + genresFuture.join());
```

#### Get radio lists

```java
var lists = deezerClient.radio().getLists().execute();
var listsFuture = deezerClient.radio().getLists().executeAsync();

System.out.println("Radio lists: " + lists);
System.out.println("Radio lists (async): " + listsFuture.join());
```

#### Get radio by ID

```java
var radio = deezerClient.radio().getRadio(38305).execute();
var radioFuture = deezerClient.radio().getRadio(38305).executeAsync();

System.out.println("Radio: " + radio);
System.out.println("Radio (async): " + radioFuture.join());
```

#### Get radios

```java
var radios = deezerClient.radio().getRadios().execute();
var radiosFuture = deezerClient.radio().getRadios().executeAsync();

System.out.println("Radios: " + radios);
System.out.println("Radios (async): " + radiosFuture.join());
```

#### Get radio tracks

```java
var tracks = deezerClient.radio().getTracks(38305).execute();
var tracksFuture = deezerClient.radio().getTracks(38305).executeAsync();

System.out.println("Radio tracks: " + tracks);
System.out.println("Radio tracks (async): " + tracksFuture.join());
```

### Search

#### Search

```java
var tracks = deezerClient.search().search("eminem").execute();
var tracksFuture = deezerClient.search().search("eminem").executeAsync();

System.out.println("Search results: " + tracks);
System.out.println("Search results (async): " + tracksFuture.join());
```

#### Advanced search

```java
var query = AdvancedQuery.builder().track("Lose Yourself").build();
var tracks = deezerClient.search()
        .search(query)
        .strict(true)
        .order(Order.DURATION_DESC)
        .execute();
var tracksFuture = deezerClient.search()
        .search(query)
        .strict(true)
        .order(Order.DURATION_DESC)
        .executeAsync();

System.out.println("Advanced search results: " + tracks);
System.out.println("Advanced search results (async): " + tracksFuture.join());
```

#### Search albums

```java
var albums = deezerClient.search().searchAlbum("eminem").execute();
var albumsFuture = deezerClient.search().searchAlbum("eminem").executeAsync();

System.out.println("Album search results: " + albums);
System.out.println("Album search results (async): " + albumsFuture.join());
```

#### Search artists

```java
var artists = deezerClient.search().searchArtist("eminem").execute();
var artistsFuture = deezerClient.search().searchArtist("eminem").executeAsync();

System.out.println("Artist search results: " + artists);
System.out.println("Artist search results (async): " + artistsFuture.join());
```

#### Search playlists

```java
var playlists = deezerClient.search().searchPlaylist("eminem").execute();
var playlistsFuture = deezerClient.search().searchPlaylist("eminem").executeAsync();

System.out.println("Playlist search results: " + playlists);
System.out.println("Playlist search results (async): " + playlistsFuture.join());
```

#### Search tracks

```java
var tracks = deezerClient.search().searchTrack("eminem").execute();
var tracksFuture = deezerClient.search().searchTrack("eminem").executeAsync();

System.out.println("Track search results: " + tracks);
System.out.println("Track search results (async): " + tracksFuture.join());
```

#### Search radios

```java
var radios = deezerClient.search().searchRadio("80s").execute();
var radiosFuture = deezerClient.search().searchRadio("80s").executeAsync();

System.out.println("Radio search results: " + radios);
System.out.println("Radio search results (async): " + radiosFuture.join());
```

#### Search users

```java
var users = deezerClient.search().searchUser("eminem").execute();
var usersFuture = deezerClient.search().searchUser("eminem").executeAsync();

System.out.println("User search results: " + users);
System.out.println("User search results (async): " + usersFuture.join());
```

### Track

#### Get track by ID

```java
var track = deezerClient.track().getTrack(541999).execute();
var trackFuture = deezerClient.track().getTrack(541999).executeAsync();

System.out.println("Track: " + track);
System.out.println("Track (async): " + trackFuture.join());
```

#### Update track

```java
var updateResult = deezerClient.track()
        .updateTrack(541999)
        .title("My Track")
        .artist("My Artist")
        .album("My Album")
        .execute();
var updateResultFuture = deezerClient.track()
        .updateTrack(541999)
        .title("My Track")
        .artist("My Artist")
        .album("My Album")
        .executeAsync();

System.out.println("Track updated: " + updateResult);
System.out.println("Track updated (async): " + updateResultFuture.join());
```

### Upload

#### Upload playlist cover

```java
var uploadResult = deezerClient.upload()
        .uploadPlaylistCover(10517702202L, new File("cover.jpg"))
        .execute();
var uploadResultFuture = deezerClient.upload()
        .uploadPlaylistCover(10517702202L, new File("cover.jpg"))
        .executeAsync();

System.out.println("Playlist cover uploaded: " + uploadResult);
System.out.println("Playlist cover uploaded (async): " + uploadResultFuture.join());
```

### User

#### Get current user

```java
var user = deezerClient.user().getUser().execute();
var userFuture = deezerClient.user().getUser().executeAsync();

System.out.println("User: " + user);
System.out.println("User (async): " + userFuture.join());
```

#### Get user by ID

```java
var user = deezerClient.user().getUser(2208).execute();
var userFuture = deezerClient.user().getUser(2208).executeAsync();

System.out.println("User: " + user);
System.out.println("User (async): " + userFuture.join());
```

#### Get user favorite albums

```java
var albums = deezerClient.user().getAlbums().execute();
var albumsFuture = deezerClient.user().getAlbums().executeAsync();

System.out.println("User albums: " + albums);
System.out.println("User albums (async): " + albumsFuture.join());
```

#### Get user favorite artists

```java
var artists = deezerClient.user().getArtists().execute();
var artistsFuture = deezerClient.user().getArtists().executeAsync();

System.out.println("User artists: " + artists);
System.out.println("User artists (async): " + artistsFuture.join());
```

#### Get user favorite tracks

```java
var tracks = deezerClient.user().getTracks().execute();
var tracksFuture = deezerClient.user().getTracks().executeAsync();

System.out.println("User tracks: " + tracks);
System.out.println("User tracks (async): " + tracksFuture.join());
```

#### Get user playlists

```java
var playlists = deezerClient.user().getPlaylists().execute();
var playlistsFuture = deezerClient.user().getPlaylists().executeAsync();

System.out.println("User playlists: " + playlists);
System.out.println("User playlists (async): " + playlistsFuture.join());
```

#### Get user favorite radios

```java
var radios = deezerClient.user().getRadios().execute();
var radiosFuture = deezerClient.user().getRadios().executeAsync();

System.out.println("User radios: " + radios);
System.out.println("User radios (async): " + radiosFuture.join());
```

#### Get user flow

```java
var flow = deezerClient.user().getFlow().execute();
var flowFuture = deezerClient.user().getFlow().executeAsync();

System.out.println("User flow: " + flow);
System.out.println("User flow (async): " + flowFuture.join());
```

#### Get user listening history

```java
var history = deezerClient.user().getHistory().execute();
var historyFuture = deezerClient.user().getHistory().executeAsync();

System.out.println("User history: " + history);
System.out.println("User history (async): " + historyFuture.join());
```

#### Get user personal songs

```java
var personalSongs = deezerClient.user().getPersonalSongs().execute();
var personalSongsFuture = deezerClient.user().getPersonalSongs().executeAsync();

System.out.println("User personal songs: " + personalSongs);
System.out.println("User personal songs (async): " + personalSongsFuture.join());
```

#### Get user followers

```java
var followers = deezerClient.user().getFollowers().execute();
var followersFuture = deezerClient.user().getFollowers().executeAsync();

System.out.println("User followers: " + followers);
System.out.println("User followers (async): " + followersFuture.join());
```

#### Get user followings

```java
var followings = deezerClient.user().getFollowings().execute();
var followingsFuture = deezerClient.user().getFollowings().executeAsync();

System.out.println("User followings: " + followings);
System.out.println("User followings (async): " + followingsFuture.join());
```

#### Get user options

```java
var options = deezerClient.user().getOptions().execute();
var optionsFuture = deezerClient.user().getOptions().executeAsync();

System.out.println("User options: " + options);
System.out.println("User options (async): " + optionsFuture.join());
```

#### Get user permissions

```java
var permissions = deezerClient.user().getPermissions().execute();
var permissionsFuture = deezerClient.user().getPermissions().executeAsync();

System.out.println("User permissions: " + permissions);
System.out.println("User permissions (async): " + permissionsFuture.join());
```

#### Get user chart

```java
var chart = deezerClient.user().getChart().execute();
var chartFuture = deezerClient.user().getChart().executeAsync();

System.out.println("User chart: " + chart);
System.out.println("User chart (async): " + chartFuture.join());
```

#### Get user album chart

```java
var albumChart = deezerClient.user().getAlbumChart().execute();
var albumChartFuture = deezerClient.user().getAlbumChart().executeAsync();

System.out.println("User album chart: " + albumChart);
System.out.println("User album chart (async): " + albumChartFuture.join());
```

#### Get user artist chart

```java
var artistChart = deezerClient.user().getArtistChart().execute();
var artistChartFuture = deezerClient.user().getArtistChart().executeAsync();

System.out.println("User artist chart: " + artistChart);
System.out.println("User artist chart (async): " + artistChartFuture.join());
```

#### Get user playlist chart

```java
var playlistChart = deezerClient.user().getPlaylistChart().execute();
var playlistChartFuture = deezerClient.user().getPlaylistChart().executeAsync();

System.out.println("User playlist chart: " + playlistChart);
System.out.println("User playlist chart (async): " + playlistChartFuture.join());
```

#### Get user track chart

```java
var trackChart = deezerClient.user().getTrackChart().execute();
var trackChartFuture = deezerClient.user().getTrackChart().executeAsync();

System.out.println("User track chart: " + trackChart);
System.out.println("User track chart (async): " + trackChartFuture.join());
```

#### Get album recommendations

```java
var albumRecommendations = deezerClient.user()
        .getAlbumRecommendations()
        .execute();
var albumRecommendationsFuture = deezerClient.user()
        .getAlbumRecommendations()
        .executeAsync();

System.out.println("Album recommendations: " + albumRecommendations);
System.out.println("Album recommendations (async): " + albumRecommendationsFuture.join());
```

#### Get artist recommendations

```java
var artistRecommendations = deezerClient.user()
        .getArtistRecommendations()
        .execute();
var artistRecommendationsFuture = deezerClient.user()
        .getArtistRecommendations()
        .executeAsync();

System.out.println("Artist recommendations: " + artistRecommendations);
System.out.println("Artist recommendations (async): " + artistRecommendationsFuture.join());
```

#### Get playlist recommendations

```java
var playlistRecommendations = deezerClient.user()
        .getPlaylistRecommendations()
        .execute();
var playlistRecommendationsFuture = deezerClient.user()
        .getPlaylistRecommendations()
        .executeAsync();

System.out.println("Playlist recommendations: " + playlistRecommendations);
System.out.println("Playlist recommendations (async): " + playlistRecommendationsFuture.join());
```

#### Get radio recommendations

```java
var radioRecommendations = deezerClient.user()
        .getRadioRecommendations()
        .execute();
var radioRecommendationsFuture = deezerClient.user()
        .getRadioRecommendations()
        .executeAsync();

System.out.println("Radio recommendations: " + radioRecommendations);
System.out.println("Radio recommendations (async): " + radioRecommendationsFuture.join());
```

#### Get release recommendations

```java
var releaseRecommendations = deezerClient.user()
        .getReleaseRecommendations()
        .execute();
var releaseRecommendationsFuture = deezerClient.user()
        .getReleaseRecommendations()
        .executeAsync();

System.out.println("Release recommendations: " + releaseRecommendations);
System.out.println("Release recommendations (async): " + releaseRecommendationsFuture.join());
```

#### Get track recommendations

```java
var trackRecommendations = deezerClient.user()
        .getTrackRecommendations()
        .execute();
var trackRecommendationsFuture = deezerClient.user()
        .getTrackRecommendations()
        .executeAsync();

System.out.println("Track recommendations: " + trackRecommendations);
System.out.println("Track recommendations (async): " + trackRecommendationsFuture.join());
```

#### Add albums to library

```java
var addAlbumsResult = deezerClient.user().addAlbums(302127L).execute();
var addAlbumsResultFuture = deezerClient.user().addAlbums(302127L).executeAsync();

System.out.println("Albums added: " + addAlbumsResult);
System.out.println("Albums added (async): " + addAlbumsResultFuture.join());
```

#### Add artists to favorites

```java
var addArtistsResult = deezerClient.user().addArtists(27L).execute();
var addArtistsResultFuture = deezerClient.user().addArtists(27L).executeAsync();

System.out.println("Artists added: " + addArtistsResult);
System.out.println("Artists added (async): " + addArtistsResultFuture.join());
```

#### Add tracks to favorites

```java
var addTracksResult = deezerClient.user().addTracks(541999L).execute();
var addTracksResultFuture = deezerClient.user().addTracks(541999L).executeAsync();

System.out.println("Tracks added: " + addTracksResult);
System.out.println("Tracks added (async): " + addTracksResultFuture.join());
```

#### Add playlists to favorites

```java
var addPlaylistsResult = deezerClient.user().addPlaylists(10517702202L).execute();
var addPlaylistsResultFuture = deezerClient.user().addPlaylists(10517702202L).executeAsync();

System.out.println("Playlists added: " + addPlaylistsResult);
System.out.println("Playlists added (async): " + addPlaylistsResultFuture.join());
```

#### Add podcast to favorites

```java
var addPodcastResult = deezerClient.user().addPodcast(1447162).execute();
var addPodcastResultFuture = deezerClient.user().addPodcast(1447162).executeAsync();

System.out.println("Podcast added: " + addPodcastResult);
System.out.println("Podcast added (async): " + addPodcastResultFuture.join());
```

#### Add radio to favorites

```java
var addRadioResult = deezerClient.user().addRadio(38305).execute();
var addRadioResultFuture = deezerClient.user().addRadio(38305).executeAsync();

System.out.println("Radio added: " + addRadioResult);
System.out.println("Radio added (async): " + addRadioResultFuture.join());
```

#### Add notification

```java
var notification = deezerClient.user().addNotification("Hello Deezer").execute();
var notificationFuture = deezerClient.user().addNotification("Hello Deezer").executeAsync();

System.out.println("Notification: " + notification);
System.out.println("Notification (async): " + notificationFuture.join());
```

#### Remove album from library

```java
var removeAlbumResult = deezerClient.user().removeAlbum(302127).execute();
var removeAlbumResultFuture = deezerClient.user().removeAlbum(302127).executeAsync();

System.out.println("Album removed: " + removeAlbumResult);
System.out.println("Album removed (async): " + removeAlbumResultFuture.join());
```

#### Remove artist from favorites

```java
var removeArtistResult = deezerClient.user().removeArtist(27).execute();
var removeArtistResultFuture = deezerClient.user().removeArtist(27).executeAsync();

System.out.println("Artist removed: " + removeArtistResult);
System.out.println("Artist removed (async): " + removeArtistResultFuture.join());
```

#### Remove track from favorites

```java
var removeTrackResult = deezerClient.user().removeTrack(541999).execute();
var removeTrackResultFuture = deezerClient.user().removeTrack(541999).executeAsync();

System.out.println("Track removed: " + removeTrackResult);
System.out.println("Track removed (async): " + removeTrackResultFuture.join());
```

#### Remove playlist from favorites

```java
var removePlaylistResult = deezerClient.user()
        .removePlaylist(10517702202L)
        .execute();
var removePlaylistResultFuture = deezerClient.user()
        .removePlaylist(10517702202L)
        .executeAsync();

System.out.println("Playlist removed: " + removePlaylistResult);
System.out.println("Playlist removed (async): " + removePlaylistResultFuture.join());
```

#### Remove podcast from favorites

```java
var removePodcastResult = deezerClient.user().removePodcast(1447162).execute();
var removePodcastResultFuture = deezerClient.user().removePodcast(1447162).executeAsync();

System.out.println("Podcast removed: " + removePodcastResult);
System.out.println("Podcast removed (async): " + removePodcastResultFuture.join());
```

#### Remove radio from favorites

```java
var removeRadioResult = deezerClient.user().removeRadio(38305).execute();
var removeRadioResultFuture = deezerClient.user().removeRadio(38305).executeAsync();

System.out.println("Radio removed: " + removeRadioResult);
System.out.println("Radio removed (async): " + removeRadioResultFuture.join());
```

#### Create playlist

```java
var playlist = deezerClient.user().createPlaylist("My playlist").execute();
var playlistFuture = deezerClient.user().createPlaylist("My playlist").executeAsync();

System.out.println("Playlist created: " + playlist);
System.out.println("Playlist created (async): " + playlistFuture.join());
```

#### Follow user

```java
var followResult = deezerClient.user().followUser(15).execute();
var followResultFuture = deezerClient.user().followUser(15).executeAsync();

System.out.println("User followed: " + followResult);
System.out.println("User followed (async): " + followResultFuture.join());
```

#### Unfollow user

```java
var unfollowResult = deezerClient.user().unfollowUser(15).execute();
var unfollowResultFuture = deezerClient.user().unfollowUser(15).executeAsync();

System.out.println("User unfollowed: " + unfollowResult);
System.out.println("User unfollowed (async): " + unfollowResultFuture.join());
```

## Documentation

- [Deezer API](https://developers.deezer.com/api)
- [Javadoc Reference](https://javadoc.io/doc/io.github.yvasyliev/dz4j)

## Contributing

Issues and pull requests are welcome. Please read our [contribution guidelines](.github/CONTRIBUTING.md) for more
details.

## Security

Please follow the reporting guidance in [SECURITY.md](SECURITY.md).

## License

This project is licensed under the [MIT License](LICENSE).
