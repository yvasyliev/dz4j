package io.github.yvasyliev.dz4j.databind.json;

import io.github.yvasyliev.dz4j.databind.cfg.DeezerHandlerInstantiator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;

class DeezerJsonMapperBuilderTest {
    @Test
    void shouldBuildJsonMapper() {
        var module = Mockito.<Consumer<SimpleModule>>mock();
        var mapper = Mockito.<Consumer<JsonMapper.Builder>>mock();
        var handlerInstantiator = Mockito.<Consumer<DeezerHandlerInstantiator.DeezerHandlerInstantiatorBuilder>>mock();
        var jsonMapper = new DeezerJsonMapperBuilder()
                .module(module)
                .mapper(mapper)
                .handlerInstantiator(handlerInstantiator)
                .build();

        assertNotNull(jsonMapper);
        assertTrue(jsonMapper.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT));
        assertThat(jsonMapper.registeredModules())
                .anySatisfy(m -> assertThat(m.getModuleName()).isEqualTo("deezer-api"));
        verify(module).accept(notNull());
        verify(mapper).accept(notNull());
        verify(handlerInstantiator).accept(notNull());
    }
}
