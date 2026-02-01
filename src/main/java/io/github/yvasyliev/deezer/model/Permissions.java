package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("permissions") // TODO: check for DeserializationFeature.UNWRAP_ROOT_VALUE feature enabled
public record Permissions(
        @JsonProperty("basic_access") Boolean basicAccess,
        @JsonProperty("email") Boolean email,
        @JsonProperty("offline_access") Boolean offlineAccess,
        @JsonProperty("manage_library") Boolean manageLibrary,
        @JsonProperty("manage_community") Boolean manageCommunity,
        @JsonProperty("delete_library") Boolean deleteLibrary,
        @JsonProperty("listening_history") Boolean listeningHistory
) {}
