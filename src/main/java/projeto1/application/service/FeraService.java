package projeto1.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto1.application.constants.MensagensDeErro;
import projeto1.application.database.FeraDatabase;
import projeto1.application.exception.Api1CursoException;
import projeto1.application.model.Fera;
import projeto1.application.repository.FeraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeraService {

    @Autowired
    private FeraRepository _feraRepository;
    @Autowired
    private MensagensDeErro mensagens;

    public String cadastrarFera(Fera fera) {

        try {
            
            FeraDatabase feraDatabase = mapearFeraParaDatadase(fera);
            FeraDatabase respostaDaDatabase = _feraRepository.save(feraDatabase);
            String feraFromDatabase = mapearDatabaseParaFera(respostaDaDatabase).toString();
            String usuario = String.format(mensagens.NOVO_USUARIO + ": %s%s", feraDatabase.getNomeCompleto().toUpperCase(), feraDatabase.getId());

            return usuario;
        } catch (RuntimeException e) {
            throw new Api1CursoException(mensagens.ERRO_NO_CADASTRO);
        }
    }

    public FeraDatabase listarPeloUsuario(String usuario) {

        try {
            Optional<FeraDatabase> resultado = _feraRepository.findByUsuario(usuario);
            return resultado.get();
        } catch (RuntimeException e) {
            throw new Api1CursoException(mensagens.USUARIO_NAO_ENCONTRADO);
        }
    }

    public String listarPelaIdENome(Long id, String nomeCompleto) {

        Optional<FeraDatabase> resultadoID = _feraRepository.findById(id);
        Optional<FeraDatabase> resultadoNome = _feraRepository.findByNomeCompleto(nomeCompleto);
        if (resultadoID.isPresent() && resultadoNome.isPresent()) {
            String usuario = String.format(mensagens.USUARIO_ENCONTRADO + ": %s%s", resultadoNome, resultadoID).toString();
            return usuario;
        }
        return mensagens.USUARIO_NAO_ENCONTRADO;
    }

    public List<FeraDatabase> listarTodosFeras() {
        List<FeraDatabase> lista = _feraRepository.findAll();
        return lista;
    }

    public FeraDatabase feraPorId(Long id) {

        try {
            Optional<FeraDatabase> resultado = _feraRepository.findById(id);
            return resultado.get();
        } catch (RuntimeException e) {
            throw new Api1CursoException(mensagens.ID_NAO_ENCONTRADA);
        }
    }

    public void excluirFera(Long id) {

        Optional<FeraDatabase> resultadoID = _feraRepository.findById(id);
        if (!resultadoID.isPresent()) {
            throw new Api1CursoException(mensagens.ID_NAO_ENCONTRADA);
        }
        try {
            _feraRepository.deleteById(id);
        } catch (Exception e) {
            throw new Api1CursoException(mensagens.ERRO_AO_EXCLUIR);
        }
    }

    public Fera atualizarInfoFera(Long id, Fera fera) {

        try {
            FeraDatabase feraDatabase = _feraRepository.findById(id).get();
            atualizarFeraParaFeraDatabase(fera, feraDatabase);
            _feraRepository.save(feraDatabase);
            return fera;

        } catch (RuntimeException e) {
            throw new Api1CursoException(mensagens.ERRO_AO_ATUALIZAR);
        }
    }


    private FeraDatabase mapearFeraParaDatadase(Fera fera) {
        FeraDatabase feraDatabase = new FeraDatabase();

        feraDatabase.setId(fera.getId());
        feraDatabase.setNomeCompleto(fera.getNomeCompleto());
        feraDatabase.setUsuario(fera.getNomeCompleto().toUpperCase() + fera.getId());

        return feraDatabase;
    }

    private Fera mapearDatabaseParaFera(FeraDatabase feraDatabase) {
        Fera fera = new Fera();

        fera.setId(feraDatabase.getId());
        fera.setNomeCompleto(feraDatabase.getNomeCompleto());
        fera.setUsuario(feraDatabase.getNomeCompleto() + feraDatabase.getId());

        return fera;
    }

    private void atualizarFeraParaFeraDatabase(Fera fera, FeraDatabase feraDatabase) {

        feraDatabase.setId(fera.getId());
        feraDatabase.setNomeCompleto(fera.getNomeCompleto());
        feraDatabase.setUsuario(fera.getUsuario());
    }


}
