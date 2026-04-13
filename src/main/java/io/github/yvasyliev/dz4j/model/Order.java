package io.github.yvasyliev.dz4j.model;

/**
 * Enumeration of possible orderings for search results.
 */
public enum Order {
    /**
     * Order by ranking.
     */
    RANKING,

    /**
     * Order by track name ascending.
     */
    TRACK_ASC,

    /**
     * Order by track name descending.
     */
    TRACK_DESC,

    /**
     * Order by artist name ascending.
     */
    ARTIST_ASC,

    /**
     * Order by artist name descending.
     */
    ARTIST_DESC,

    /**
     * Order by album name ascending.
     */
    ALBUM_ASC,

    /**
     * Order by album name descending.
     */
    ALBUM_DESC,

    /**
     * Order by rating ascending.
     */
    RATING_ASC,

    /**
     * Order by rating descending.
     */
    RATING_DESC,

    /**
     * Order by duration ascending.
     */
    DURATION_ASC,

    /**
     * Order by duration descending.
     */
    DURATION_DESC
}
