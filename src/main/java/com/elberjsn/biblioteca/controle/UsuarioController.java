package com.elberjsn.biblioteca.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elberjsn.biblioteca.model.Usuario;
import com.elberjsn.biblioteca.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    Gson gson = new Gson();

    @GetMapping("/")
    public ResponseEntity<String> indexUser() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/novo")
    public ResponseEntity<Usuario> novoUsuario(@RequestBody String entity) {

        Usuario newUser = service.salvar(retornaUser(entity));

        if (newUser.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/buscar/{metodo}")
    public ResponseEntity<List<Usuario>> buscarPorMetodos(@RequestParam String opc, @PathVariable String metodo) {
        switch (metodo) {
            case "cpf":
                return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorCPF(opc));

            case "email":
                return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorEmail(opc));

            default:
                return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNome(opc));

        }
    }
    @PutMapping("editar/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable String id, @RequestBody String entity) {
        Usuario usuario = retornaUser(entity);
        usuario.setId(Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.editarUsuario(usuario));

        
    }

    public Usuario retornaUser(String json) {
        try {
            Gson jGson = new Gson();
            Usuario user = jGson.fromJson(json, Usuario.class);
            return user;
        } catch (Exception e) {
            try {
                ObjectMapper obm = new ObjectMapper();
                Usuario user = obm.readValue(json, Usuario.class);
                return user;
            } catch (Exception ex) {
                return new Usuario();
            }
        }

    }

}
