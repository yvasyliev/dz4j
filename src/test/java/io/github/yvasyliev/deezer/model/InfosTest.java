package io.github.yvasyliev.deezer.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class InfosTest {
    @Test
    void shouldReturnUploadTokenExpiredTrueWhenUploadTokenExpiresAtIsNull() {
        var infos = Infos.builder().build();

        assertTrue(infos.isUploadTokenExpired());
    }

    @Test
    void shouldReturnUploadTokenExpiredTrueWhenUploadTokenExpiresAtIsBeforeNow() {
        var infos = Infos.builder()
                .uploadTokenExpiresAt(Instant.now().minusSeconds(3600))
                .build();

        assertTrue(infos.isUploadTokenExpired());
    }

    @Test
    void shouldReturnUploadTokenExpiredFalseWhenUploadTokenExpiresAtIsAfterNow() {
        var infos = Infos.builder()
                .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                .build();

        assertFalse(infos.isUploadTokenExpired());
    }
}