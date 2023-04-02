package com.example.kr_bd.respository;

import com.example.kr_bd.exception.KrBdException;
import com.example.kr_bd.model.Employee;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.Tables.EMPLOYEE;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
    private final DSLContext dsl;

    public Employee getByEmail(String email){
        return dsl.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.EMAIL.eq(email))
                .fetchOptional()
                .orElseThrow(
                        ()-> new KrBdException("Employee not found")
                )
                .into(Employee.class);
    }
}
