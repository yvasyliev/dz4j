package io.github.yvasyliev.dz4j.feign;

import feign.DefaultContract;
import feign.Param;
import feign.RequestLine;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeezerContractTest {
    @Test
    void testParseAndValidateMetadata() {
        var expander = mock(TestExpander.class);
        var expected = new DefaultContract().parseAndValidateMetadata(TestClient.class);
        var contract = DeezerContract.builder()
                .expanders(expanders -> expanders.put(TestExpander.class, expander))
                .build();

        expected.get(0).indexToExpander(Map.of(0, expander));

        var actual = contract.parseAndValidateMetadata(TestClient.class);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldBuildDeezerContract() {
        var jsonMapper = mock(JsonMapper.class);
        var expanders = Mockito.<Consumer<Map<Class<? extends Param.Expander>, Param.Expander>>>mock();
        var deezerContract = DeezerContract.builder()
                .jsonMapper(jsonMapper)
                .expanders(expanders)
                .build();

        assertNotNull(deezerContract);
        verify(expanders).accept(anyMap());
    }

    @Test
    void shouldUseDefaultExpander() {
        var expected = List.of("Hello", "World");
        var expander = DeezerContract.builder()
                .build()
                .parseAndValidateMetadata(TestClient.class)
                .get(0)
                .indexToExpander()
                .get(0);

        assertNotNull(expander);

        var actual = expander.expand(expected);

        assertEquals(expected.toString(), actual);
    }

    private interface TestClient {
        @SuppressWarnings("unused")
        @RequestLine("GET /test?arg={arg}")
        void testMethod(@Param(value = "arg", expander = TestExpander.class) String arg);
    }

    private static final class TestExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return "expanded:" + value;
        }
    }
}
