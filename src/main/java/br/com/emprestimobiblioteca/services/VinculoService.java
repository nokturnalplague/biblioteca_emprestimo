package br.com.emprestimobiblioteca.services;

import br.com.emprestimobiblioteca.daos.VinculoDAO;
import br.com.emprestimobiblioteca.entities.Vinculo;

import java.sql.SQLException;
import java.util.List;

public class VinculoService {
    private final VinculoDAO dao = new VinculoDAO();

    public void cadastrarVinculo(Vinculo vinculo) throws SQLException {
        dao.inserir(vinculo);
    }

    public List<Vinculo> listarVinculos() throws SQLException {
        return dao.listarTodos();
    }

    public Vinculo buscarPorId(long id) throws SQLException {
        return dao.buscarPorId(id);
    }

    public void atualizar(Vinculo vinculo) throws SQLException {
        dao.atualizar(vinculo);
    }

    public void deletar(long id) throws SQLException {
        dao.deletar(id);
    }
}
