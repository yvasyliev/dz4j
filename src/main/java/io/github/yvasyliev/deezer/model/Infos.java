package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.yvasyliev.deezer.annotation.Experimental;
import io.github.yvasyliev.deezer.databind.util.ExpiresConverter;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.net.URL;
import java.time.Instant;
import java.util.List;

/**
 * Get the information about the API in the current country
 *
 * @param countryIso           the current country ISO code
 * @param country              the current country name
 * @param open                 indicates if Deezer is open in the current country or not
 * @param offers               an array of available offers in the current country
 * @param pop                  the pop value returned by the API
 * @param uploadToken          the upload token
 * @param uploadTokenExpiresAt the upload token expiration time
 * @param userToken            the user token
 * @param hosts                hosts information
 * @param ads                  ads configuration for the current country
 * @param hasPodcasts          whether podcasts are available in the current country
 */
public record Infos(
        @JsonProperty("country_iso") String countryIso,
        @JsonProperty("country") String country,
        @JsonProperty("open") Boolean open,
        @JsonProperty("offers") List<Offer> offers,
        @JsonProperty("pop") @Experimental String pop,
        @JsonProperty("upload_token") @Experimental String uploadToken,

        @JsonProperty("upload_token_lifetime")
        @JsonDeserialize(converter = ExpiresConverter.class)
        @Experimental
        Instant uploadTokenExpiresAt,

        @JsonProperty("user_token") @Experimental String userToken,
        @JsonProperty("hosts") @Experimental Hosts hosts,
        @JsonProperty("ads") @Experimental Ads ads,
        @JsonProperty("has_podcasts") @Experimental Boolean hasPodcasts
) {
    /**
     * An offer object.
     *
     * @param id              offer ID
     * @param name            offer name
     * @param amount          offer amount
     * @param currency        offer currency
     * @param displayedAmount offer display amount
     * @param tc              PDF terms of use
     * @param tcHtml          HTML terms of use
     * @param tcTxt           text terms of use
     * @param tryAndBuy       trial period
     */
    @Experimental
    public record Offer(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("amount") String amount,
            @JsonProperty("currency") String currency,
            @JsonProperty("displayed_amount") String displayedAmount,
            @JsonProperty("tc") URL tc,
            @JsonProperty("tc_html") URL tcHtml,
            @JsonProperty("tc_txt") URL tcTxt,
            @JsonProperty("try_and_buy") Integer tryAndBuy
    ) {}

    /**
     * A hosts object.
     *
     * @param stream the stream host URL
     * @param images the images host URL
     */
    @Experimental
    public record Hosts(@JsonProperty("stream") URL stream, @JsonProperty("images") URL images) {}

    /**
     * An ads object.
     *
     * @param audio            audio ads configuration
     * @param display          display ads configuration
     * @param bigNativeAdsHome big native ads configuration for home
     */
    @Experimental
    public record Ads(
            @JsonProperty("audio") Audio audio,
            @JsonProperty("display") Display display,
            @JsonProperty("big_native_ads_home") BigNativeAdsHome bigNativeAdsHome
    ) {
        /**
         * An interval object.
         *
         * @param start    the start
         * @param interval the interval
         * @param unit     the unit of time
         */
        public record Interval(
                @JsonProperty("start") Integer start,
                @JsonProperty("interval") Integer interval,
                @JsonProperty("unit") String unit
        ) {}

        /**
         * An audio ads object.
         *
         * @param defaultInterval the default interval configuration
         */
        public record Audio(@JsonProperty("default") Interval defaultInterval) {}

        /**
         * A display ads object.
         *
         * @param interstitial the interstitial configuration
         */
        public record Display(@JsonProperty("interstitial") Interval interstitial) {}

        /**
         * A big native ads home object.
         *
         * @param iphone        iPhone platform configuration
         * @param ipad          iPad platform configuration
         * @param android       Android platform configuration
         * @param androidTablet Android tablet platform configuration
         */
        public record BigNativeAdsHome(
                @JsonProperty("iphone") Platform iphone,
                @JsonProperty("ipad") Platform ipad,
                @JsonProperty("android") Platform android,
                @JsonProperty("android_tablet") Platform androidTablet
        ) {
            /**
             * A platform object.
             *
             * @param enabled whether the big native ads are enabled on this platform
             */
            public record Platform(@JsonProperty("enabled") Boolean enabled) {}
        }
    }
}
