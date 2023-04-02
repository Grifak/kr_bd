package com.example.kr_bd.respository;

import com.example.kr_bd.model.Bid;
import com.example.kr_bd.model.BidEntry;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import static org.jooq.generated.Tables.BID;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BidRespository {
    private final DSLContext dsl;

    public List<BidEntry> getBidListByAuthorId(Long authorId){
        return dsl.select(
                BID.ID,
                BID.NAME,
                BID.STATUS,
                BID.BID_AMOUNT
                ).from(BID)
                .where(BID.AUTHOR_ID.eq(authorId))
                .fetchInto(BidEntry.class);
    }

    public List<BidEntry> getBidListByEmployeeId(){
        return dsl.selectFrom(BID)
                .fetchInto(BidEntry.class);
    }

    public Long insert(Bid bid){
        return (long) dsl.insertInto(BID)
                .set(dsl.newRecord(BID, bid))
                .returning()
                .fetchSingle().getValue(BID.ID);
    }

    public void deleteById(Long id){
        dsl.deleteFrom(BID)
                .where(BID.ID.eq(id))
                .execute();
    }

    public void updateBidStatusById(Long id, String status){
        dsl.update(BID)
                .set(BID.STATUS, status)
                .where(BID.ID.eq(id))
                .execute();
    }

    public void updateBidEmployee(Long id, Long employeeId){
        dsl.update(BID)
                .set(BID.EMPLOYEE_ID, employeeId)
                .where(BID.ID.eq(id))
                .execute();
    }

    public void updateBidStatus(Long id, String status){
        dsl.update(BID)
                .set(BID.STATUS, status)
                .where(BID.ID.eq(id))
                .execute();
    }

    public void updateBidAmount(Long bidId){
        dsl.update(BID)
                .set(BID.BID_AMOUNT,
                        dsl.select(BID.BID_AMOUNT)
                                .from(BID).where(BID.ID.eq(bidId)).fetchOne().into(Long.class) * 2)
                .where(BID.ID.eq(bidId))
                .execute();
    }
}
