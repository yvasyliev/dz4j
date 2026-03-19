package io.github.yvasyliev.deezer.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class BaseUrlsTest {
    @Test
    void shouldInitializeDefaultBaseUrls() {
        var baseUrls = BaseUrls.builder().build();

        assertNotNull(baseUrls);
        assertEquals("https://api.deezer.com", baseUrls.api());
        assertEquals("https://connect.deezer.com", baseUrls.oauth());
        assertEquals("https://upload.deezer.com", baseUrls.upload());
    }

    @Test
    void shouldInitializeCustomBaseUrls() {
        var api = "https://api.example.com";
        var oauth = "https://connect.example.com";
        var upload = "https://upload.example.com";
        var baseUrls = BaseUrls.builder()
                .api(api)
                .oauth(oauth)
                .upload(upload)
                .build();

        assertNotNull(baseUrls);
        assertEquals(api, baseUrls.api());
        assertEquals(oauth, baseUrls.oauth());
        assertEquals(upload, baseUrls.upload());
    }
}
