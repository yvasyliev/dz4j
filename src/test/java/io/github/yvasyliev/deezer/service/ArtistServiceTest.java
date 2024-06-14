package io.github.yvasyliev.deezer.service;

import io.github.yvasyliev.deezer.DeezerClient;
import io.github.yvasyliev.deezer.objects.Artist;
import org.junit.jupiter.api.Test;

class ArtistServiceTest {
    DeezerClient deezerClient = DeezerClient.create();

    @Test
    void getArtist() {
        Artist artist = deezerClient.getArtist(27).execute();
        System.out.println(artist);
    }

    @Test
    void getArtistAlbums() {
//        Page<Album> albumPage = deezerClient.getArtistAlbums(27).limit(5).execute();
//        System.out.println(albumPage);
//        albumPage = albumPage.getNext().execute();
//        System.out.println(albumPage);
    }

    @Test
    void getArtistFans() {

    }

    @Test
    void getArtistPlaylists() {

    }

    @Test
    void getArtistRadio() {

    }

    @Test
    void getArtistRelated() {

    }

    @Test
    void getArtistTop() {
//        Page<Track> trackPage = deezerClient.getArtistTop(27).limit(1).execute();
//        System.out.println(trackPage);
    }
}