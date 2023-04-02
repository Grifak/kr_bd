package com.example.kr_bd.respository;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Car;
import java.util.List;
import org.jooq.DSLContext;
import static org.jooq.generated.tables.Bid.BID;
import static org.jooq.generated.tables.BidCarDoc.BID_CAR_DOC;
import static org.jooq.generated.tables.Car.CAR;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CarRepository {
    private final DSLContext dsl;

    public void createCar(Car car){
        dsl.insertInto(CAR)
                .set(dsl.newRecord(CAR, car))
                .set(CAR.USER_ID, 1L)
                .execute();

    }

    public Long getIdByRegNumber(String regNumber){
        return dsl.select(CAR.ID)
                .from(CAR)
                .where(CAR.REGISTRATION_NUMBER.eq(regNumber))
                .fetchOne()
                .into(Long.class);
    }

    public List<Car> getAllCar(Long userId){
        return dsl.selectFrom(CAR)
                .where(CAR.USER_ID.eq(userId))
                .fetchInto(Car.class);
    }

    public List<String> getRegistrationNumber(Long bidId, Long authorId){
        return dsl.select(CAR.REGISTRATION_NUMBER)
                .from(CAR)
                .innerJoin(BID_CAR_DOC).on(CAR.ID.eq(BID_CAR_DOC.CAR_ID))
                .innerJoin(BID).on(BID_CAR_DOC.BID_ID.eq(BID.ID))
                .where(BID.AUTHOR_ID.eq(authorId).and(BID.ID.eq(bidId)))
                .fetchInto(String.class);
    }

    public List<String> getRegistrationNumberByBidId(Long bidId){
        return dsl.select(CAR.REGISTRATION_NUMBER)
                .from(CAR)
                .innerJoin(BID_CAR_DOC).on(CAR.ID.eq(BID_CAR_DOC.CAR_ID))
                .innerJoin(BID).on(BID_CAR_DOC.BID_ID.eq(BID.ID))
                .where((BID.ID.eq(bidId)))
                .fetchInto(String.class);
    }

}
