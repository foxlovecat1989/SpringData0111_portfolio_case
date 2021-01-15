package com.spring.mvc.portfolio.controller;

import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.repository.InvestorRepository;
import com.spring.mvc.portfolio.service.PortfolioService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/portfolio/verify")
public class VerifyController {
    @Autowired
    PortfolioService portfolioService;
    @Transactional
    @RequestMapping(value = "/{id}/{code}")
    public String verify(
            @PathVariable("id") Optional<Integer> id,
            @PathVariable("code") Optional<String> code,
            HttpSession httpSession){
        String message = "FAIL";
        Investor investor = portfolioService.getInvestorRepository().findOne(id.get());
        if(investor != null && investor.getCode().equals(code.get())){
            portfolioService.getInvestorRepository().updatePass(id.get(), Boolean.TRUE);
            message = "SUCCESS";
        }
        httpSession.setAttribute("message", message);

        return "redirect:/portfolio/verify.jsp";
    }
}