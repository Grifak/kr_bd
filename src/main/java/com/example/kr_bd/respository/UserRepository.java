package com.example.kr_bd.respository;

import com.example.kr_bd.exception.KrBdException;
import com.example.kr_bd.model.User;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.Tables.USERS;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final DSLContext dsl;

    public User getByEmail(String email){
        return dsl.selectFrom(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetchOptional()
                .orElseThrow( () ->
                        new KrBdException("User not found")
                )
                .into(User.class);
    }
}
