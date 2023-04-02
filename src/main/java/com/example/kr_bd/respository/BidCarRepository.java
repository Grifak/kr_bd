package com.example.kr_bd.respository;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.tables.BidCarDoc.BID_CAR_DOC;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BidCarRepository {
    private final DSLContext dsl;

    public void insert(Long carId, Long bidId){
        dsl.insertInto(BID_CAR_DOC)
                .set(BID_CAR_DOC.CAR_ID, carId)
                .set(BID_CAR_DOC.BID_ID, bidId)
                .execute();
    }

    public void deleteByBidId(Long bidId){
        dsl.deleteFrom(BID_CAR_DOC)
                .where(BID_CAR_DOC.BID_ID.eq(bidId))
                .execute();
    }
}
