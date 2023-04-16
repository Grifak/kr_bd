package com.example.kr_bd.respository;

import com.example.kr_bd.model.Modification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.OrderField;
import static org.jooq.generated.tables.CarModification.CAR_MODIFICATION;
import static org.jooq.generated.tables.Modification.MODIFICATION;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ModifRepository {
    private final DSLContext dsl;

    public List<Modification> getAllModif(Boolean asc){
        OrderField condition = asc ? MODIFICATION.MODIF_POWER.asc() : MODIFICATION.MODIF_POWER.desc();

        return dsl.selectFrom(MODIFICATION)
                .orderBy(condition)
                .fetchInto(Modification.class);
    }

    public List<Modification> getAllModifByCarId(Long carId){
        return dsl.select(
                MODIFICATION.ID,
                MODIFICATION.NAME,
                MODIFICATION.MODIF_POWER
        ).from(MODIFICATION)
                .innerJoin(CAR_MODIFICATION).on(MODIFICATION.ID.eq(CAR_MODIFICATION.MODIF_ID))
                .where(CAR_MODIFICATION.CAR_ID.eq(carId))
                .fetchInto(Modification.class);

    }
}
