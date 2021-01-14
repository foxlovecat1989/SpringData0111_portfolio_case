package com.spring.mvc.portfolio.controller;

import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.service.PortfolioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/portfolio/investor")
public class InvestorController {
    @Autowired
    private  PortfolioService portfolioService;
    
    @GetMapping(value = {"/", "query"})
    public List<Investor> query(){
        return portfolioService.getInvestorRepository().findAll();
    }
}
