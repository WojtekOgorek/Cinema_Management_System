package ogorek.wojciech.domain.model.user;

import java.util.function.Function;

public interface UserFunctors {
    Function<User, Long> toId = user -> user.id;
    Function<User, String> toPassword = user -> user.password;

}
