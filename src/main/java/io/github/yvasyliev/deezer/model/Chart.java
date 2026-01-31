package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A chart object.
 *
 * @param tracks    list of {@link Track}
 * @param albums    list of {@link Album}
 * @param artists   list of {@link Artist}
 * @param playlists list of {@link Playlist}
 * @param podcasts  list of {@link Podcast}
 */
public record Chart(
        @JsonProperty("tracks") Page<Track> tracks,
        @JsonProperty("albums") Page<Album> albums,
        @JsonProperty("artists") Page<Artist> artists,
        @JsonProperty("playlists") Page<Playlist> playlists,
        @JsonProperty("podcasts") Page<Podcast> podcasts
) {}
