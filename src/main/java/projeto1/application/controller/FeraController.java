package projeto1.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto1.application.database.FeraDatabase;
import projeto1.application.exception.Api1CursoException;
import projeto1.application.model.Fera;
import projeto1.application.service.FeraService;

import java.util.List;


@RestController
@RequestMapping("api/curso/projeto1")
public class FeraController {


    @Autowired
    private FeraService _feraService;

    @PostMapping("/cadastranovofera")
    public ResponseEntity<String> cadastrarFera(@RequestBody Fera fera){

        String feraCadastrado = _feraService.cadastrarFera(fera);
        return ResponseEntity.ok().body(feraCadastrado);
    }


    @GetMapping("/listaPeloUsuario/{usuario}")
    public ResponseEntity<FeraDatabase> listarPeloUsuario(@PathVariable String usuario){

        FeraDatabase resultado = _feraService.listarPeloUsuario(usuario);
         return ResponseEntity.ok().body(resultado);
    }

    @RequestMapping(value = "listaPelaIdENome/{id}/{nomeCompleto}", method=RequestMethod.GET)
    public String listarPelaIdENome(@PathVariable("id") Long id, @PathVariable("nomeCompleto") String nomeCompleto){

        String resultado = _feraService.listarPelaIdENome(id, nomeCompleto);
        return resultado;
    }

    @GetMapping("/listatodosferas")
    public ResponseEntity<List<FeraDatabase>> listarTodos(){
        List<FeraDatabase> lista = _feraService.listarTodosFeras();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/listaferapelaId/{id}")
    public ResponseEntity<FeraDatabase> listarPelaID(@PathVariable Long id) {

        FeraDatabase resultado = _feraService.feraPorId(id);
        return ResponseEntity.ok().body(resultado);
    }

    @DeleteMapping(value = "/excluifera/{id}")
    public ResponseEntity<Void> excluirFera(@PathVariable Long id){

        _feraService.excluirFera(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/atualizaInfoFera/{id}")
    public ResponseEntity<Fera> atualizaInfoFera(@PathVariable Long id, @RequestBody Fera fera){
        Fera feraAtualizado = _feraService.atualizarInfoFera(id, fera);
        return ResponseEntity.ok().body(feraAtualizado);
    }

}
