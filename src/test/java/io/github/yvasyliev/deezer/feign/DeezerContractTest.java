package io.github.yvasyliev.deezer.feign;

import feign.Contract;
import feign.Param;
import feign.RequestLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;
import java.util.function.Consumer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeezerContractTest {
    @Mock private TestExpander expander;
    private Contract contract;

    @BeforeEach
    void setUp() {
        contract = new DeezerContract(Map.of(TestExpander.class, expander));
    }

    @Test
    void testParseAndValidateMetadata() {
        var expected = new Contract.Default().parseAndValidateMetadata(TestClient.class);

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

    private interface TestClient {
        @RequestLine("GET /test?arg={arg}")
        void testMethod(@Param(value = "arg", expander = TestExpander.class) String arg);
    }

    private static class TestExpander implements Param.Expander {
        @Override
        public String expand(Object value) {
            return "expanded:" + value;
        }
    }
}
