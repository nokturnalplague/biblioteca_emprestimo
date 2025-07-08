package br.com.emprestimobiblioteca.controllers;

import br.com.emprestimobiblioteca.entities.Vinculo;
import br.com.emprestimobiblioteca.services.VinculoService;
import java.sql.SQLException;
import java.util.List;


public class VinculoController {
    private final VinculoService service = new VinculoService();

    public void adicionarVinculo(Vinculo vinculo) throws SQLException {
        service.cadastrarVinculo(vinculo);
    }

    public List<Vinculo> listarVinculos() throws SQLException {
        return service.listarVinculos();
    }

    public Vinculo buscarPorId(long id) throws SQLException {
        return service.buscarPorId(id);
    }

    public void atualizarVinculo(Vinculo vinculo) throws SQLException {
        service.atualizar(vinculo);
    }

    public void deletarVinculo(long id) throws SQLException {
        service.deletar(id);
    }
}
