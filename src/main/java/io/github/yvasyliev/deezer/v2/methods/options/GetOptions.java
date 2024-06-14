package io.github.yvasyliev.deezer.v2.methods.options;

import io.github.yvasyliev.deezer.objects.Options;
import io.github.yvasyliev.deezer.service.OptionsService;
import io.github.yvasyliev.deezer.v2.methods.AbstractServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetOptions extends AbstractServiceMethod<Options, OptionsService> {
    public GetOptions(OptionsService optionsService) {
        super(optionsService);
    }

    @Override
    public Options execute() {
        return deezerService.getOptions();
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
