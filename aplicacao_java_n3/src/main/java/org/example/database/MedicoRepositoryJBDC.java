package org.example.database;

import org.example.entities.Medico;
import org.example.repositories.MedicoRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicoRepositoryJBDC implements MedicoRepository {
    private final Conexao conexao;
    public MedicoRepositoryJBDC(Conexao conexao){
        this.conexao = Conexao.getInstancia();
    }

    @Override
    public void salvar(Medico medico) {
        PreparedStatement statement = conexao.createPreparedStatement("INSERT INTO medicos (id, nome, especialidade, cpf) VALUES (?, ?, ?, ?)");
        try{
            statement.setString(1, String.valueOf(medico.getId()));
            statement.setString(2, medico.getNome());
            statement.setString(3, medico.getEspecialidade());
            statement.setString(4, medico.getCpf());
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar medico");
        }
    }

    @Override
    public Medico atualizar(Medico medico) {
        PreparedStatement statement =
                conexao.createPreparedStatement(
                        "UPDATE medicos SET nome = ?, especialidade = ?, cpf = ? WHERE id = ?"
                );

        try {

            statement.setString(1, medico.getNome());
            statement.setString(2, medico.getEspecialidade());
            statement.setString(3, medico.getCpf());
            statement.setString(4, String.valueOf(medico.getId()));

            statement.execute();

            return medico;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar médico");
        }
    }

    @Override
    public Medico buscarPorCpf(String cpf) {
        PreparedStatement statement =
                conexao.createPreparedStatement(
                        "SELECT * FROM medicos WHERE cpf = ?"
                );

        try {

            statement.setString(1, cpf);

            ResultSet resultSet =
                    conexao.execute(statement);

            if (!resultSet.next()) {
                return null;
            }

            Medico medico = new Medico();

            medico.setId(UUID.fromString(resultSet.getString("id")));
            medico.setNome(resultSet.getString("nome"));
            medico.setEspecialidade(resultSet.getString("especialidade"));
            medico.setCpf(resultSet.getString("cpf"));

            return medico;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar medico");
        }
    }

    @Override
    public List<Medico> buscarTodos() {
        ResultSet resultSet =
                conexao.execute("SELECT * FROM medicos");

        List<Medico> medicos = new ArrayList<>();

        try {

            while (resultSet.next()) {

                Medico medico = new Medico();

                medico.setId(UUID.fromString(resultSet.getString("id")));
                medico.setNome(resultSet.getString("nome"));
                medico.setEspecialidade(resultSet.getString("especialidade"));
                medico.setCpf(resultSet.getString("cpf"));

                medicos.add(medico);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medicos;
    }

    @Override
    public boolean excluir(String id) {
        try {

            conexao.createStatement()
                    .execute("DELETE FROM medicos WHERE id = '" + id + "'");

            return true;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir medico");
        }
    }
}
