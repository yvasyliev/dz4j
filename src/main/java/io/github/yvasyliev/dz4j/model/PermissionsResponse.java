package io.github.yvasyliev.dz4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * Response for permissions endpoint.
 *
 * @param permissions the permissions granted by the user
 */
public record PermissionsResponse(@JsonProperty("permissions") Permissions permissions) {
    /**
     * A {@code Permissions} record with the specified permission values.
     *
     * @param basicAccess      whether the user has granted basic access permission
     * @param email            whether the user has granted email permission
     * @param offlineAccess    whether the user has granted offline access permission
     * @param manageLibrary    whether the user has granted manage library permission
     * @param manageCommunity  whether the user has granted manage community permission
     * @param deleteLibrary    whether the user has granted delete library permission
     * @param listeningHistory whether the user has granted listening history permission
     */
    @Builder
    public record Permissions(
            @JsonProperty("basic_access") Boolean basicAccess,
            @JsonProperty("email") Boolean email,
            @JsonProperty("offline_access") Boolean offlineAccess,
            @JsonProperty("manage_library") Boolean manageLibrary,
            @JsonProperty("manage_community") Boolean manageCommunity,
            @JsonProperty("delete_library") Boolean deleteLibrary,
            @JsonProperty("listening_history") Boolean listeningHistory
    ) {}
}
