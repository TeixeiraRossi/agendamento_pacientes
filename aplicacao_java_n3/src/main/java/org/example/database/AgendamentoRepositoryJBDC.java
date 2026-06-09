package org.example.database;

import org.example.entities.Agendamento;
import org.example.repositories.AgendamentoRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgendamentoRepositoryJBDC implements AgendamentoRepository {

    private final Conexao conexao;

    public AgendamentoRepositoryJBDC(Conexao conexao) {
        this.conexao = Conexao.getInstancia();
    }

    @Override
    public void salvar(Agendamento agendamento) {

        PreparedStatement statement =
                conexao.createPreparedStatement(
                        "INSERT INTO agendamentos (id, paciente_id, medico_id, data, hora) VALUES (?, ?, ?, ?, ?)"
                );

        try {

            statement.setString(1, String.valueOf(agendamento.getId()));
            statement.setString(2, String.valueOf(agendamento.getPacienteId()));
            statement.setString(3, String.valueOf(agendamento.getMedicoId()));
            statement.setString(4, agendamento.getData());
            statement.setString(5, agendamento.getHora());

            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar agendamento");
        }
    }

    @Override
    public Agendamento atualizar(Agendamento agendamento) {

        PreparedStatement statement =
                conexao.createPreparedStatement(
                        "UPDATE agendamentos SET paciente_id = ?, medico_id = ?, data = ?, hora = ? WHERE id = ?"
                );

        try {

            statement.setString(1, String.valueOf(agendamento.getPacienteId()));
            statement.setString(2, String.valueOf(agendamento.getMedicoId()));
            statement.setString(3, agendamento.getData());
            statement.setString(4, agendamento.getHora());
            statement.setString(5, String.valueOf(agendamento.getId()));

            statement.execute();

            return agendamento;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar agendamento");
        }
    }

    @Override
    public Agendamento buscarPorId(String id) {

        PreparedStatement statement =
                conexao.createPreparedStatement(
                        "SELECT * FROM agendamentos WHERE id = ?"
                );

        try {

            statement.setString(1, id);

            ResultSet resultSet =
                    conexao.execute(statement);

            if (!resultSet.next()) {
                return null;
            }

            Agendamento agendamento = new Agendamento();

            agendamento.setId(UUID.fromString(resultSet.getString("id")));
            agendamento.setPacienteId(UUID.fromString(resultSet.getString("paciente_id")));
            agendamento.setMedicoId(UUID.fromString(resultSet.getString("medico_id")));
            agendamento.setData(resultSet.getString("data"));
            agendamento.setHora(resultSet.getString("hora"));

            return agendamento;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar agendamento");
        }
    }

    @Override
    public List<Agendamento> buscarTodos() {

        ResultSet resultSet =
                conexao.execute("SELECT * FROM agendamentos");

        List<Agendamento> agendamentos = new ArrayList<>();

        try {

            while (resultSet.next()) {

                Agendamento agendamento = new Agendamento();

                agendamento.setId(UUID.fromString(resultSet.getString("id")));
                agendamento.setPacienteId(UUID.fromString(resultSet.getString("paciente_id")));
                agendamento.setMedicoId(UUID.fromString(resultSet.getString("medico_id")));
                agendamento.setData(resultSet.getString("data"));
                agendamento.setHora(resultSet.getString("hora"));

                agendamentos.add(agendamento);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return agendamentos;
    }

    @Override
    public boolean excluir(String id) {

        try {

            conexao.createStatement()
                    .execute("DELETE FROM agendamentos WHERE id = '" + id + "'");

            return true;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir agendamento");
        }
    }
}