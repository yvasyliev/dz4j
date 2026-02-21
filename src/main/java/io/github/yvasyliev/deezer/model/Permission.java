package io.github.yvasyliev.deezer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Permissions that can be requested by the application.
 */
@RequiredArgsConstructor
@Getter
public enum Permission {
    /**
     * Access users basic information.
     */
    BASIC_ACCESS("basic_access"),

    /**
     * Get the user's email.
     */
    EMAIL("email"),

    /**
     * Access user data any time.
     */
    OFFLINE_ACCESS("offline_access"),

    /**
     * Manage users' library.
     */
    MANAGE_LIBRARY("manage_library"),

    /**
     * Manage users' friends.
     */
    MANAGE_COMMUNITY("manage_community"),

    /**
     * Delete library items.
     */
    DELETE_LIBRARY("delete_library"),

    /**
     * Allow the application to access the user's listening history.
     */
    LISTENING_HISTORY("listening_history");

    private final String value;
}
