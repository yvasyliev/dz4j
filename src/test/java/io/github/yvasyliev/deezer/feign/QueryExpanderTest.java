package io.github.yvasyliev.deezer.feign;

import feign.Param;
import io.github.yvasyliev.deezer.model.AdvancedQuery;
import io.github.yvasyliev.deezer.model.SimpleQuery;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class QueryExpanderTest {
    private static final Param.Expander EXPANDER = new QueryExpander(new JsonMapper());

    @SuppressWarnings({"checkstyle:ConstantName", "unused", "UnnecessaryLambda"})
    private static final Supplier<Stream<Arguments>> testExpand = () -> Stream.of(
            arguments(new SimpleQuery("test query"), "test query"),
            arguments(
                    new AdvancedQuery(
                            "test artist",
                            "test album",
                            "test track",
                            "test label",
                            Duration.ofMinutes(2),
                            Duration.ofMinutes(3),
                            85,
                            145
                    ),
                    """
                    artist:"test artist" album:"test album" track:"test track" label:"test label" dur_min:120"""
                            + " dur_max:180 bpm_min:85 bpm_max:145"
            ),
            arguments(new Object(), null)
    );

    @ParameterizedTest
    @FieldSource
    void testExpand(Object value, String expected) {
        var actual = EXPANDER.expand(value);

        assertEquals(expected, actual);
    }
}
