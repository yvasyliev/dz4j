package io.github.yvasyliev.deezer.v2.methods.options;

import io.github.yvasyliev.deezer.objects.Options;
import io.github.yvasyliev.deezer.service.OptionsService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzMethod;

import java.util.concurrent.CompletableFuture;

public class GetOptions extends AbstractDzMethod<Options, OptionsService> {
    public GetOptions(OptionsService optionsService) {
        super(optionsService);
    }

    @Override
    public CompletableFuture<Options> executeAsync() {
        return deezerService.getOptionsAsync();
    }

    @Override
    public String toString() {
        return OptionsService.OPTIONS;
    }
}
