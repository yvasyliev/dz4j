package io.github.yvasyliev.deezer.v2.methods.radio;

import io.github.yvasyliev.deezer.objects.Radio;
import io.github.yvasyliev.deezer.service.RadioService;
import io.github.yvasyliev.deezer.v2.methods.AbstractObjectServiceMethod;

import java.util.concurrent.CompletableFuture;

public class GetRadio extends AbstractObjectServiceMethod<Radio, RadioService> {
    public GetRadio(RadioService radioService, long radioId) {
        super(radioService, radioId);
    }

    @Override
    public Radio execute() {
        return deezerService.getRadio(objectId);
    }

    @Override
    public CompletableFuture<Radio> executeAsync() {
        return deezerService.getRadioAsync(objectId);
    }

    @Override
    public String toString() {
        return "/radio/" + objectId;
    }
}
