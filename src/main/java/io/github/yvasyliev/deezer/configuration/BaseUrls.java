package io.github.yvasyliev.deezer.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
@Builder
public class BaseUrls {
    @Builder.Default private final String api = "https://api.deezer.com";
    @Builder.Default private final String oauth = "https://connect.deezer.com";
    @Builder.Default private final String upload = "https://upload.deezer.com";
}
