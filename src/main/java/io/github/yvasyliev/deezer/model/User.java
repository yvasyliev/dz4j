package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.LocalDate;
import java.util.EnumSet;

/**
 * A user object.
 *
 * @param id                             the user's Deezer ID
 * @param name                           the user's Deezer nickname
 * @param lastname                       the user's last name
 * @param firstname                      the user's first name
 * @param email                          the user's email
 * @param status                         the user's status
 * @param birthday                       the user's birthday
 * @param inscriptionDate                the user's inscription date
 * @param gender                         the user's gender: {@code F} or {@code M}
 * @param link                           the url of the profil for the user on Deezer
 * @param picture                        the url of the user's profil picture. Add {@code size} parameter to the url to
 *                                       change size. Can be {@code small}, {@code medium}, {@code big}, {@code xl}
 * @param pictureSmall                   the url of the user's profil picture in size small.
 * @param pictureMedium                  the url of the user's profil picture in size medium.
 * @param pictureBig                     the url of the user's profil picture in size big.
 * @param pictureXl                      the url of the user's profil picture in size xl.
 * @param country                        the user's country
 * @param lang                           the user's language
 * @param isKid                          if the user is a kid or not
 * @param explicitContentLevel           the user's explicit content level according to his country
 * @param explicitContentLevelsAvailable the user's available explicit content levels according to his country. Possible
 *                                       values are: {@code explicit_display}, {@code explicit_no_recommendation} and
 *                                       {@code explicit_hide}
 * @param tracklist                      API Link to the flow of this user
 */
public record User(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("lastname") String lastname,
        @JsonProperty("firstname") String firstname,
        @JsonProperty("email") String email,
        @JsonProperty("status") Integer status,
        @JsonProperty("birthday") LocalDate birthday,
        @JsonProperty("inscription_date") LocalDate inscriptionDate,
        @JsonProperty("gender") Gender gender,
        @JsonProperty("link") URL link,
        @JsonProperty("picture") URL picture,
        @JsonProperty("picture_small") URL pictureSmall,
        @JsonProperty("picture_medium") URL pictureMedium,
        @JsonProperty("picture_big") URL pictureBig,
        @JsonProperty("picture_xl") URL pictureXl,
        @JsonProperty("country") Country country,
        @JsonProperty("lang") String lang,
        @JsonProperty("is_kid") Boolean isKid,
        @JsonProperty("explicit_content_level") ExplicitContentLevel explicitContentLevel,
        @JsonProperty("explicit_content_levels_available") EnumSet<ExplicitContentLevel> explicitContentLevelsAvailable,
        @JsonProperty("tracklist") URL tracklist
) {
    /**
     * The user's explicit content level.
     */
    public enum ExplicitContentLevel {
        /**
         * Explicit content is displayed.
         */
        @JsonProperty("explicit_display")
        EXPLICIT_DISPLAY,

        /**
         * No explicit content recommendation.
         */
        @JsonProperty("explicit_no_recommendation")
        EXPLICIT_NO_RECOMMENDATION,

        /**
         * Explicit content is hidden.
         */
        @JsonProperty("explicit_hide")
        EXPLICIT_HIDE
    }
}
