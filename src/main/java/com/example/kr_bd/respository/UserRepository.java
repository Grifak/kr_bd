package com.example.kr_bd.respository;

import com.example.kr_bd.exception.KrBdException;
import com.example.kr_bd.model.RegistrationRequest;
import com.example.kr_bd.model.User;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.Tables.USERS;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final DSLContext dsl;

    public User getByLogin(String login){
        return dsl.selectFrom(USERS)
                .where(USERS.LOGIN.eq(login))
                .fetchOptional()
                .orElseThrow( () ->
                        new KrBdException("User not found")
                )
                .into(User.class);
    }

    public void insertUser(RegistrationRequest registrationRequest){
        dsl.insertInto(USERS)
                .set(USERS.LOGIN, registrationRequest.getLogin())
                .set(USERS.PASSWORD, registrationRequest.getPassword())
                .set(USERS.EMAIL, registrationRequest.getEmail())
                .execute();
    }
}
