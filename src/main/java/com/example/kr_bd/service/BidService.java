package com.example.kr_bd.service;

import lombok.RequiredArgsConstructor;
import com.example.kr_bd.model.Bid;
import com.example.kr_bd.model.BidEntry;
import com.example.kr_bd.respository.BidCarRepository;
import com.example.kr_bd.respository.BidRespository;
import com.example.kr_bd.respository.CarRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRespository bidRespository;
    private final CarRepository carRepository;
    private final BidCarRepository bidCarRepository;

    public List<BidEntry> getBidList(Long authorId){
        List<BidEntry> bidEntryList = bidRespository.getBidListByAuthorId(authorId);

        bidEntryList.stream().forEach(bidEntry ->
                        bidEntry.setRegistrationNumberList(carRepository.getRegistrationNumber(bidEntry.getId(), authorId)));

        return bidEntryList;
    }

    public List<BidEntry> getBidList(){
        List<BidEntry> bidListByEmployeeId = bidRespository.getBidListByEmployeeId();

        bidListByEmployeeId.stream().forEach(bidEntry ->
                bidEntry.setRegistrationNumberList(carRepository.getRegistrationNumberByBidId(bidEntry.getId())));

        return bidListByEmployeeId;
    }

    public void create(Bid bid) {
        bid.setStatus("Новая заявка");
        Long carId = carRepository.getIdByRegNumber(bid.getRegistrationNumber());
        Long bidAmount = carId * (long) Math.random()*50;
        bid.setBidAmount(bidAmount);
        Long bidId = bidRespository.insert(bid);

        bidCarRepository.insert(carId, bidId);
    }

    public void deleteById(Long bidId){
        bidCarRepository.deleteByBidId(bidId);
        bidRespository.deleteById(bidId);
    }

    public void updateBidStatus(Long bidId, String status){ bidRespository.updateBidStatusById(bidId, status);}

    public void updateBidEmployee(Long bidId, Long employeeId){
        bidRespository.updateBidEmployee(bidId, employeeId);
        bidRespository.updateBidStatusById(bidId, "Принято в работу");
    }

    public void boostBid(Bid bid){
        bidRespository.updateBidAmount(bid.getId());
        Long carId = carRepository.getIdByRegNumber(bid.getRegistrationNumber());
        bidCarRepository.insert(carId, bid.getId());
    }
}
