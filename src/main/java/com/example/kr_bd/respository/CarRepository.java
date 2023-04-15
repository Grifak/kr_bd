package com.example.kr_bd.respository;

import com.example.kr_bd.model.Car;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import static org.jooq.generated.tables.Car.CAR;
import static org.jooq.generated.tables.CarModification.CAR_MODIFICATION;
import static org.jooq.generated.tables.Modification.MODIFICATION;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarRepository {
    private final DSLContext dsl;

    public void createCar(Car car, Long userId){
        dsl.insertInto(CAR)
                .set(dsl.newRecord(CAR, car))
                .set(CAR.USER_ID, userId)
                .execute();
    }

    public Long getIdByRegNumber(String regNumber){
        return dsl.select(CAR.ID)
                .from(CAR)
                .where(CAR.REGISTRATION_NUMBER.eq(regNumber))
                .fetchOne()
                .into(Long.class);
    }

    public List<Car> getAllCar(Long userId, Boolean asc){
        OrderField<String> condition = Boolean.TRUE.equals(asc) ? CAR.BRAND.asc() : CAR.BRAND.desc();

        return dsl.select(
                CAR.ID,
                CAR.VIN,
                CAR.REGISTRATION_NUMBER,
                CAR.MODEL,
                CAR.BRAND,
                CAR.HORSE_POWER,
                        DSL.sum(MODIFICATION.MODIF_POWER).as("modifPower")
                )
                .from(CAR)
                .leftJoin(CAR_MODIFICATION).on(CAR.ID.eq(CAR_MODIFICATION.CAR_ID))
                .leftJoin(MODIFICATION).on(CAR_MODIFICATION.MODIF_ID.eq(MODIFICATION.ID))
                .where(CAR.USER_ID.eq(userId))
                .groupBy(CAR.VIN, CAR.REGISTRATION_NUMBER, CAR.MODEL, CAR.BRAND, CAR.HORSE_POWER, CAR.ID)
                .orderBy(condition)
                .fetchInto(Car.class);
    }
}
