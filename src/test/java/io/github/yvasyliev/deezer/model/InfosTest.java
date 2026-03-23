package io.github.yvasyliev.deezer.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InfosTest {
    @Test
    void shouldReturnTrueWhenUploadTokenExpiresAtIsNull() {
        var infos = Infos.builder().build();

        assertTrue(infos.isUploadTokenExpired());
    }

    @Test
    void shouldReturnTrueWhenUploadTokenExpiresAtIsBeforeNow() {
        var infos = Infos.builder()
                .uploadTokenExpiresAt(Instant.now().minusSeconds(3600))
                .build();

        assertTrue(infos.isUploadTokenExpired());
    }

    @Test
    void shouldReturnFalseWhenUploadTokenExpiresAtIsAfterNow() {
        var infos = Infos.builder()
                .uploadTokenExpiresAt(Instant.now().plusSeconds(3600))
                .build();

        assertFalse(infos.isUploadTokenExpired());
    }
}
