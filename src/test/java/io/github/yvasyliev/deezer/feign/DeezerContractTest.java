package io.github.yvasyliev.deezer.feign;

import feign.Contract;
import feign.Param;
import feign.RequestLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    void testBuilder() {
        var actual = DeezerContract.builder()
                .expanders(new HashMap<>())
                .expanders(expanders -> expanders.put(TestExpander.class, expander))
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(contract);
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
