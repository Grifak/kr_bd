package com.example.kr_bd.respository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.tables.CarModification.CAR_MODIFICATION;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarModificationRepository {
    private final DSLContext dsl;

    public void addModif(Long carId, Long modifId){
        dsl.insertInto(CAR_MODIFICATION)
                .set(CAR_MODIFICATION.CAR_ID, carId)
                .set(CAR_MODIFICATION.MODIF_ID, modifId)
                .execute();
    }

    public void deleteModif(Long carId, Long modifId){
        dsl.deleteFrom(CAR_MODIFICATION)
                .where(CAR_MODIFICATION.CAR_ID.eq(carId).and(CAR_MODIFICATION.MODIF_ID.eq(modifId)))
                .execute();
    }
}
