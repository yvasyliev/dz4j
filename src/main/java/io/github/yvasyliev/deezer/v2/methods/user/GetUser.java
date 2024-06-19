package io.github.yvasyliev.deezer.v2.methods.user;

import io.github.yvasyliev.deezer.objects.User;
import io.github.yvasyliev.deezer.service.UserService;
import io.github.yvasyliev.deezer.v2.methods.AbstractDzIdMethod;

import java.util.concurrent.CompletableFuture;

public class GetUser extends AbstractDzIdMethod<User, UserService> {
    public GetUser(UserService userService, long userId) {
        super(userService, userId);
    }

    @Override
    public CompletableFuture<User> executeAsync() {
        return deezerService.getUserAsync(objectId);
    }

    @Override
    public String toString() {
        return "/user/" + objectId;
    }
}
