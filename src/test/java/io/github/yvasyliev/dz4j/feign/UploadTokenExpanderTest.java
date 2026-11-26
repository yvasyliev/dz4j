package io.github.yvasyliev.dz4j.feign;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.yvasyliev.dz4j.model.Infos;
import org.junit.jupiter.api.Test;

class UploadTokenExpanderTest {
    @Test
    void testExpand() {
        var expected = "upload_token";
        var actual = new UploadTokenExpander()
                .expand(Infos.builder().uploadToken(expected).build());

        assertEquals(expected, actual);
    }
}
