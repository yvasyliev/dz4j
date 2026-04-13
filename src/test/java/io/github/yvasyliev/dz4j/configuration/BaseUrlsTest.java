package io.github.yvasyliev.dz4j.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseUrlsTest {
    @Test
    void shouldInitializeDefaultBaseUrlsByBuilder() {
        var baseUrls = BaseUrls.builder().build();

        assertBaseUrls(baseUrls, "https://api.deezer.com", "https://connect.deezer.com", "https://upload.deezer.com");
    }

    @Test
    void shouldInitializeCustomBaseUrlsByConstructor() {
        var api = "https://api.example.com";
        var oauth = "https://connect.example.com";
        var upload = "https://upload.example.com";
        var baseUrls = new BaseUrls(api, oauth, upload);

        assertBaseUrls(baseUrls, api, oauth, upload);
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

        assertBaseUrls(baseUrls, api, oauth, upload);
    }

    private void assertBaseUrls(BaseUrls baseUrls, String api, String oauth, String upload) {
        assertNotNull(baseUrls);
        assertEquals(api, baseUrls.api());
        assertEquals(oauth, baseUrls.oauth());
        assertEquals(upload, baseUrls.upload());
    }
}
