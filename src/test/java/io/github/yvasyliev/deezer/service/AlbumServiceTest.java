package io.github.yvasyliev.deezer.service;

import io.github.yvasyliev.deezer.DeezerClient;
import io.github.yvasyliev.deezer.objects.Album;
import org.junit.jupiter.api.Test;

class AlbumServiceTest {
    DeezerClient deezerClient = DeezerClient.create();

    @Test
    void getAlbum() {
        Album album = deezerClient.getAlbum(302127).execute();
        System.out.println(album);
//        Page<Track> trackPage = album.getTrackList().limit(1).execute();
//        System.out.println(trackPage);
    }

    @Test
    void getAlbumFans() {
//        Page<User> fans = deezerClient.getAlbumFans(302127).execute();
//        System.out.println(fans);
    }

    @Test
    void getAlbumTracks() {
//        Page<Track> trackPage = deezerClient.getAlbumTracks(302127).limit(1).execute();
//        System.out.println(trackPage);
    }
}