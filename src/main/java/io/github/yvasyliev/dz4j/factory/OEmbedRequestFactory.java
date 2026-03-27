package io.github.yvasyliev.dz4j.factory;

import io.github.yvasyliev.dz4j.request.OEmbedDeezerRequest;
import io.github.yvasyliev.dz4j.service.OEmbedService;
import lombok.RequiredArgsConstructor;

/**
 * Factory for creating requests related to oEmbed information.
 */
@RequiredArgsConstructor
public class OEmbedRequestFactory {
    private final OEmbedService oEmbedService;

    /**
     * Creates a request to retrieve oEmbed information for an album.
     *
     * @param albumId album ID
     * @return a request to retrieve oEmbed information for the specified album
     */
    public OEmbedDeezerRequest getAlbumOEmbed(long albumId) {
        return createDeezerRequest("https://www.deezer.com/album/", albumId);
    }

    /**
     * Creates a request to retrieve oEmbed information for an episode.
     *
     * @param episodeId episode ID
     * @return a request to retrieve oEmbed information for the specified episode
     */
    public OEmbedDeezerRequest getEpisodeOEmbed(long episodeId) {
        return createDeezerRequest("https://www.deezer.com/episode/", episodeId);
    }

    /**
     * Creates a request to retrieve oEmbed information for a playlist.
     *
     * @param playlistId playlist ID
     * @return a request to retrieve oEmbed information for the specified playlist
     */
    public OEmbedDeezerRequest getPlaylistOEmbed(long playlistId) {
        return createDeezerRequest("https://www.deezer.com/playlist/", playlistId);
    }

    /**
     * Creates a request to retrieve oEmbed information for a show.
     *
     * @param showId show ID
     * @return a request to retrieve oEmbed information for the specified show
     */
    public OEmbedDeezerRequest getShowOEmbed(long showId) {
        return createDeezerRequest("https://www.deezer.com/show/", showId);
    }

    /**
     * Creates a request to retrieve oEmbed information for a track.
     *
     * @param trackId track ID
     * @return a request to retrieve oEmbed information for the specified track
     */
    public OEmbedDeezerRequest getTrackOEmbed(long trackId) {
        return createDeezerRequest("https://www.deezer.com/track/", trackId);
    }

    /**
     * Creates a request to retrieve oEmbed information for a page.
     *
     * @param pageId page ID
     * @return a request to retrieve oEmbed information for the specified page
     */
    public OEmbedDeezerRequest getPageOEmbed(String pageId) {
        return createDeezerRequest("https://deezer.page.link/", pageId);
    }

    private <T> OEmbedDeezerRequest createDeezerRequest(String baseUrl, T id) {
        return new OEmbedDeezerRequest(baseUrl + id, oEmbedService);
    }
}
