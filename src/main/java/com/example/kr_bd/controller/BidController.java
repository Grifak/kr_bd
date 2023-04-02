package com.example.kr_bd.controller;

import com.example.kr_bd.model.Bid;
import com.example.kr_bd.model.BidEntry;
import com.example.kr_bd.service.BidService;
import lombok.RequiredArgsConstructor;
import com.example.kr_bd.service.CarService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {
    private final BidService bidService;
    private final CarService carService;

    @GetMapping("/list")
    public String bidList(Model model){
        log.info("Path: /bid/list");
        model.addAttribute("bidList", bidService.getBidList(1L));

        return "bid-list";
    }

    @GetMapping("/list-employee")
    public String bidListEmployee(Model model){
        log.info("Path: /bid/list");
        model.addAttribute("bidList", bidService.getBidList());

        return "bid-list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        log.info("Path: /bid/create");
        Bid bid = new Bid();
        model.addAttribute("bid", bid);
        model.addAttribute("carList", carService.getAllCar(1L));

        return "create_bid";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute Bid bid){
        log.info("Path: /bid/create, Mode: {}", bid);
        bid.setAuthorId(1L);
        bidService.create(bid);

        return "redirect:/user/menu";
    }

    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("bid", new BidEntry());

        return "delete-bid";
    }

    @PostMapping("delete")
    public String delete(Model model, @ModelAttribute BidEntry bid){
        log.info("BidId = {}", bid.getId());
        bidService.deleteById(bid.getId());

        return "redirect:/user/menu";
    }

    @GetMapping("/update")
    public String bidStatusUpdateForm(Model model){
        model.addAttribute("bid", new BidEntry());

        return "bid-update";
    }

    @PostMapping("/update")
    public String bidStatusUpdate(Model model, @ModelAttribute BidEntry bid){
        bidService.updateBidStatus(bid.getId(), bid.getStatus());

        return "redirect:/employee/menu_employee";
    }

    @GetMapping("/update-employee")
    public String bidEmployeeUpdateForm(Model mpdel){
        mpdel.addAttribute("bid", new BidEntry());

        return "bid_update_employee";
    }

    @PostMapping("/update-employee")
    public String bidEmployeeUpdate(Model model, @ModelAttribute BidEntry bid){
        bidService.updateBidEmployee(bid.getId(), 1L);

        return "redirect:/employee/menu_employee";
    }

    @GetMapping("/boost")
    public String bidBoostForm(Model model){
        model.addAttribute("bid", new Bid());
        model.addAttribute("carList", carService.getAllCar(1L));

        return "bid-boost";
    }

    @PostMapping("/boost")
    public String bidBoost(Model model, @ModelAttribute Bid bid){
        bidService.boostBid(bid);

        return "redirect:/user/menu";
    }

    @GetMapping("/end")
    public String bidEndForm(Model model){
        model.addAttribute("bid", new Bid());

        return "bid-end";
    }

    @PostMapping("/end")
    public String bidEnd(Model model, @ModelAttribute Bid bid){
        bidService.updateBidStatus(bid.getId(), "Завершена");

        return "redirect:/employee/menu_employee";
    }

}
