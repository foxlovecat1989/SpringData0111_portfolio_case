package com.spring.mvc.portfolio.controller;


import com.spring.mvc.portfolio.entities.Investor;
import com.spring.mvc.portfolio.service.PortfolioService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/portfolio")
public class LoginController {
    @Autowired
    PortfolioService portfolioService;
    @RequestMapping(value = "/login")
    public String login(
            @RequestParam Optional<String> username,
            @RequestParam Optional<String> password,
            HttpSession httpSession){
        Investor investor = portfolioService.getInvestorRepository().getInvestor(username.get());
        if(investor != null && investor.getCode().equals(investor.getPassword())){
            httpSession.setAttribute("investor", investor);
            httpSession.setAttribute("watch_id", investor.getWatchs().iterator().next().getId());
        }
        return "redirect:/portfolio/index.jsp";
    }
}
