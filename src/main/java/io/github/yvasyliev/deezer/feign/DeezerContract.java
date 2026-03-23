package io.github.yvasyliev.deezer.feign;

import feign.Contract;
import feign.MethodMetadata;
import feign.Param;
import io.github.yvasyliev.deezer.util.Customizer;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Custom Feign contract for Deezer API, allowing for custom parameter expanders.
 */
@RequiredArgsConstructor
public class DeezerContract implements Contract {
    private static final Param.Expander DEFAULT_EXPANDER = new Param.ToStringExpander();
    private final Contract delegate;
    private final Map<Class<? extends Param.Expander>, Param.Expander> expanders;

    @Builder
    private DeezerContract(
            JsonMapper jsonMapper,
            Consumer<Map<Class<? extends Param.Expander>, Param.Expander>> expanders
    ) {
        this(new Default(), new HashMap<>());

        this.expanders.put(QueryExpander.class, new QueryExpander(jsonMapper));
        this.expanders.put(StrictExpander.class, new StrictExpander());

        Customizer.customize(this.expanders, expanders);
    }

    /**
     * Parses and validates the metadata of the Feign interface, replacing any parameter expanders with the custom ones
     * defined in this contract.
     *
     * @param targetType {@inheritDoc}
     * @return a list of {@link MethodMetadata} for the Feign interface, with custom parameter expanders applied.
     */
    @Override
    public List<MethodMetadata> parseAndValidateMetadata(Class<?> targetType) {
        var result = delegate.parseAndValidateMetadata(targetType);

        result.forEach(this::setExpanders);

        return result;
    }

    private void setExpanders(MethodMetadata md) {
        var indexToExpander = md.indexToExpanderClass()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> expanders.getOrDefault(entry.getValue(), DEFAULT_EXPANDER)
                ));

        md.indexToExpander(indexToExpander);
    }
}
