package io.github.yvasyliev.deezer.feign;

import feign.Contract;
import feign.MethodMetadata;
import feign.Param;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Tolerate;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Builder
@RequiredArgsConstructor
public class DeezerContract implements Contract {
    private static final Contract DELEGATE = new Default();
    private final Map<Class<? extends Param.Expander>, Param.Expander> expanders;

    @Override
    public List<MethodMetadata> parseAndValidateMetadata(Class<?> targetType) {
        var result = DELEGATE.parseAndValidateMetadata(targetType);

        result.forEach(this::setExpanders);

        return result;
    }

    private void setExpanders(MethodMetadata md) {
        var indexToExpander = md.indexToExpanderClass()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> expanders.get(entry.getValue())));

        md.indexToExpander(indexToExpander);
    }

    public static class DeezerContractBuilder {
        private Map<Class<? extends Param.Expander>, Param.Expander> expanders;

        @Tolerate
        public DeezerContractBuilder expanders(
                Consumer<Map<Class<? extends Param.Expander>, Param.Expander>> expandersCustomizer
        ) {
            expandersCustomizer.accept(expanders);

            return this;
        }
    }
}
