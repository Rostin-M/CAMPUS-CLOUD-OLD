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

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String email = principal.getName();

        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario != null) {
            model.addAttribute("nombreUsuario", usuario.getNombre());
        } else {
            model.addAttribute("nombreUsuario", "Usuario");
        }

        return "dashboard";
    }
}
