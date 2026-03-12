package io.github.yvasyliev.deezer;

import io.github.yvasyliev.deezer.model.AccessToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DeezerClientTest {
    private static final Supplier<Stream<Arguments>> shouldCreateDeezerClient = () -> {
        var client1 = new DeezerClient();
        client1.authorization("accessToken");

        var client2 = new DeezerClient();
        client2.authorization(new AccessToken("accessToken", Instant.MAX));

        var client3 = new DeezerClient();
        client3.authorization(123, "secret", "code");

        return Stream.of(
                arguments(new DeezerClient()),
                arguments(new DeezerClient("accessToken")),
                arguments(new DeezerClient(new AccessToken("accessToken", Instant.MAX))),
                arguments(new DeezerClient(123, "secret", "code")),
                arguments(client1),
                arguments(client2),
                arguments(client3),
                arguments(DeezerClient.builder().build()),
                arguments(DeezerClient.builder().authorization("accessToken").build()),
                arguments(DeezerClient.builder().authorization(new AccessToken("accessToken", Instant.MAX)).build()),
                arguments(DeezerClient.builder().authorization(123, "secret", "code").build())
        );
    };

    @ParameterizedTest
    @FieldSource
    void shouldCreateDeezerClient(DeezerClient client) {
        assertNotNull(client.album());
        assertNotNull(client.artist());
        assertNotNull(client.chart());
        assertNotNull(client.editorial());
        assertNotNull(client.episode());
        assertNotNull(client.genre());
        assertNotNull(client.infos());
        assertNotNull(client.oauth());
        assertNotNull(client.oEmbed());
        assertNotNull(client.options());
        assertNotNull(client.playlist());
        assertNotNull(client.podcast());
        assertNotNull(client.radio());
        assertNotNull(client.search());
        assertNotNull(client.track());
        assertNotNull(client.upload());
        assertNotNull(client.user());
    }
}
