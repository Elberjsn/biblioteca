package com.elberjsn.biblioteca.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elberjsn.biblioteca.model.Administrador;
import com.elberjsn.biblioteca.service.AdministradorService;
import com.elberjsn.biblioteca.service.util.JwtUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;


@RestController
@RequestMapping("/adm")
public class AdministradorController {

    @Autowired
    AdministradorService admService;

    @GetMapping("/{id}")
    public ResponseEntity<String> indexAdm(@PathVariable String id) {
        if (id != null) {
            return ResponseEntity.ok().body(admService.buscarPorId(Long.parseLong(id)).toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Adm não encontrado");
        }
    }

    @PostMapping("/novoAdm")
    public ResponseEntity<Administrador> novoAdm(@RequestBody String entity) {

        Administrador adm = admService.salvar(retornaAdm(entity));
        if (adm.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(adm);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdm(@RequestBody String entity) {
        Administrador adm = retornaAdm(entity);
        try {
            Administrador admLogin =admService.logarAdministrador(adm);
            if ( admLogin.getId() != null) {
                String token = JwtUtil.geradorToken(adm.getEmail());
                Gson g = new Gson();
                JsonElement json = g.toJsonTree(admLogin);
                json.getAsJsonObject().addProperty("token", token);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(json.toString());
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario e/ ou senha Invalidos!!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
    }

    public Administrador retornaAdm(String json) {
        Gson jGson = new Gson();
        return jGson.fromJson(json, Administrador.class);
    }

}
