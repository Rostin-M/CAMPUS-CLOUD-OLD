package CampusCloud.CampusCloud.controller;

import CampusCloud.CampusCloud.model.Usuario;
import CampusCloud.CampusCloud.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashBoardController {

    private final UsuarioRepository usuarioRepository;

    public DashBoardController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Ruta genérica
    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        Usuario u = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("nombreUsuario", u != null ? u.getNombre() : "Usuario");
        return "dashboard";
    }

    @GetMapping("/dashboard/estudiante")
    public String dashboardEstudiante(Model model, Principal principal) {
        Usuario u = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("nombreUsuario", u != null ? u.getNombre() : "Estudiante");
        return "dashboard_estudiante";
    }

    @GetMapping("/dashboard/docente")
    public String dashboardDocente(Model model, Principal principal) {
        Usuario u = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("nombreUsuario", u != null ? u.getNombre() : "Docente");
        return "dashboard_profesor";
    }
}
