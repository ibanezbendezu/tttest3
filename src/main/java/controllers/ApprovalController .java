package cl.tingeso.mueblesstgo.controllers;

import cl.tingeso.mueblesstgo.entities.ApprovalEntity;
import cl.tingeso.mueblesstgo.services.ApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("approval")
public class ApprovalController {

    private final ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping
    public String approvalForm(Model model) {
        model.addAttribute("approval", new ApprovalEntity());
        return "pages/provide-approval";
    }

    @PostMapping
    public String approvalSubmit(@ModelAttribute ApprovalEntity approval, @RequestParam("rut") String rut, Model model) {
        try {
            model.addAttribute("approval", approvalService.saveApproval(approval, rut));
            return "pages/approval-result";
        } catch (Exception e) {
            model.addAttribute("approval", approval);
            model.addAttribute("error", "El rut ingresado no existe.");
            return "pages/provide-approval";
        }
    }
}