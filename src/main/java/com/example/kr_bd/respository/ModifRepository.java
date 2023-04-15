package com.example.kr_bd.respository;

import com.example.kr_bd.model.Modification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.OrderField;
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
}
