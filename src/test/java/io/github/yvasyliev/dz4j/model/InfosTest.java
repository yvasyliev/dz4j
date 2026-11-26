package io.github.yvasyliev.dz4j.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

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
