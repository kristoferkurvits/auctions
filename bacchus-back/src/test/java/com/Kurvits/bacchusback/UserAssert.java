package com.Kurvits.bacchusback;

import com.Kurvits.bacchusback.User.User;
import org.assertj.core.api.AbstractAssert;

class UserAssert extends AbstractAssert<UserAssert, User> {

    UserAssert(User user) {
        super(user, UserAssert.class);
    }

    static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    UserAssert hasBidDate() {
        isNotNull();
        if (actual.getDateTime() == null) {
            failWithMessage(
                    "Expected user to have a registration date, but it was null"
            );
        }
        return this;
    }
}
