package com.elberjsn.biblioteca.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elberjsn.biblioteca.model.Emprestimo;
import com.elberjsn.biblioteca.service.EmprestimoService;
import com.elberjsn.biblioteca.service.DAO.EmprestimoDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/emp")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @GetMapping("/")
    public ResponseEntity<String> indexEmprestimo() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/buscar/{metodo}")
    public ResponseEntity<List<EmprestimoDAO>> buscarEmprestio(@RequestParam String opc, @PathVariable String metodo) {

        EmprestimoDAO dao = new EmprestimoDAO();
        switch (metodo) {
            case "userNome":
                List<EmprestimoDAO> daoNome = ((EmprestimoDAO) dao)
                        .emprestimoDAOs(emprestimoService.buscarPorUsuarioNome(opc));
                return ResponseEntity.ok(daoNome);
            case "userId":
                List<EmprestimoDAO> daoid = ((EmprestimoDAO) dao)
                        .emprestimoDAOs(emprestimoService.buscarPorUsuarioId(Long.parseLong(opc)));
                return ResponseEntity.ok(daoid);
            case "livroTitulo":
                List<EmprestimoDAO> daoNomeLivro = ((EmprestimoDAO) dao)
                        .emprestimoDAOs(emprestimoService.buscarPorLivro(opc));
                return ResponseEntity.ok(daoNomeLivro);
            case "livroId":
                List<EmprestimoDAO> daoIdLivro = ((EmprestimoDAO) dao)
                        .emprestimoDAOs(emprestimoService.buscarPorLivroId(Long.parseLong(opc)));
                return ResponseEntity.ok(daoIdLivro);
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    @PostMapping("/novo")
    public ResponseEntity<Emprestimo> novoEmprestimo(@RequestBody String entity) {
        Emprestimo em = retornaEmprestimo(entity);
        em = emprestimoService.novoEmprestimo(em);
        if (em.getId() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(em);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(em);
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Emprestimo> editarEmprestimo(@PathVariable String id, @RequestBody String entity) {

        Emprestimo em = retornaEmprestimo(entity);
        em = emprestimoService.editarEmprestimo(em);
        em.setId(Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(emprestimoService.editarEmprestimo(em));

    }

    public Emprestimo retornaEmprestimo(String json) {
        try {
            Gson jGson = new Gson();
            Emprestimo user = jGson.fromJson(json, Emprestimo.class);
            return user;
        } catch (Exception e) {
            try {
                ObjectMapper obm = new ObjectMapper();
                Emprestimo user = obm.readValue(json, Emprestimo.class);
                return user;
            } catch (Exception ex) {
                return new Emprestimo();
            }
        }

    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<String> deletaEmprestimo(@PathVariable String id){
        if (emprestimoService.deletarEmprestimo(Long.parseLong(id))) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Não Foi Deletar");

        }
    }
}
