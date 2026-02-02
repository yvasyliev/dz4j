package io.github.yvasyliev.deezer.feign;

import feign.Contract;
import feign.MethodMetadata;
import feign.Param;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class DeezerContract extends Contract.Default {
    private final Map<Class<? extends Param.Expander>, Param.Expander> expanders;



    @Override
    protected MethodMetadata parseAndValidateMetadata(Class<?> targetType, Method method) {
        var methodMetadata = super.parseAndValidateMetadata(targetType, method);

        setExpanders(methodMetadata);

        return methodMetadata;
    }

    private void setExpanders(MethodMetadata methodMetadata) {
        var indexToExpander = methodMetadata.indexToExpander();

        methodMetadata.indexToExpanderClass().forEach(
                (index, expanderClass) -> Optional.ofNullable(expanders.get(expanderClass))
                        .ifPresent(expander -> indexToExpander.put(index, expander))
        );
    }
}
