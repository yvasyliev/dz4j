package io.github.yvasyliev.deezer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An error response returned by the Deezer API.
 *
 * @param error an error object
 */
public record ErrorResponse(@JsonProperty("error") Error error) {
    /**
     * Represents an error returned by the Deezer API.
     *
     * @param type    an error type
     * @param message an error message
     * @param code    an error code
     */
    public record Error(
            @JsonProperty("type") Type type,
            @JsonProperty("message") String message,
            @JsonProperty("code") Integer code
    ) {
        /**
         * Types of errors returned by the Deezer API.
         */
        public enum Type {
            /**
             * General exception.
             */
            @JsonProperty("Exception")
            EXCEPTION,

            /**
             * Authentication related exception.
             */
            @JsonProperty("OAuthException")
            OAUTH_EXCEPTION,

            /**
             * Parameter related exception.
             */
            @JsonProperty("ParameterException")
            PARAMETER_EXCEPTION,

            /**
             * Missing parameter exception.
             */
            @JsonProperty("MissingParameterException")
            MISSING_PARAMETER_EXCEPTION,

            /**
             * Invalid query exception.
             */
            @JsonProperty("InvalidQueryException")
            INVALID_QUERY_EXCEPTION,

            /**
             * Data related exception.
             */
            @JsonProperty("DataException")
            DATA_EXCEPTION,

            /**
             * Permission related exception.
             */
            @JsonProperty("IndividualAccountChangedNotAllowedException")
            INDIVIDUAL_ACCOUNT_CHANGED_NOT_ALLOWED_EXCEPTION,
        }
    }
}
