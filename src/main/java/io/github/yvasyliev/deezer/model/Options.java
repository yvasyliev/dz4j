package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

/**
 * Get the user's options.
 *
 * @param streaming         if the user can stream on the platform
 * @param streamingDuration the streaming duration of the user
 * @param offline           the user can listen to the music in offline mode
 * @param hq                the HQ can be activated
 * @param adsDisplay        displays ads
 * @param adsAudio          activates audio ads
 * @param tooManyDevices    if the user reached the limit of linked devices
 * @param canSubscribe      if the user can subscribe to the service
 * @param radioSkips        the limit of radio skips. {@code 0} = no limit
 * @param lossless          lossless is available
 * @param preview           allows to display the preview of the tracks
 * @param radio             allows to stream the radio
 */
public record Options(
        @JsonProperty("streaming") Boolean streaming,
        @JsonProperty("streaming_duration") Duration streamingDuration, //TODO: deserialize
        @JsonProperty("offline") Boolean offline,
        @JsonProperty("hq") Boolean hq,
        @JsonProperty("ads_display") Boolean adsDisplay,
        @JsonProperty("ads_audio") Boolean adsAudio,
        @JsonProperty("too_many_devices") Boolean tooManyDevices,
        @JsonProperty("can_subscribe") Boolean canSubscribe,
        @JsonProperty("radio_skips") Integer radioSkips,
        @JsonProperty("lossless") Boolean lossless,
        @JsonProperty("preview") Boolean preview,
        @JsonProperty("radio") Boolean radio
) {}

