package org.example.database;

import org.example.entities.Paciente;
import org.example.repositories.PacienteRepository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacienteRepositoryJBDC implements PacienteRepository {

    private final Conexao conexao;
    public PacienteRepositoryJBDC(Conexao conexao) {
        this.conexao = Conexao.getInstancia();
    }

    @Override
    public void salvar(Paciente paciente) {
        PreparedStatement statement = conexao.createPreparedStatement("INSERT INTO pacientes (id, nome, sexo, data_nascimento, cpf, telefone) VALUES (?, ?, ?, ?, ?, ?)");
        try{
            statement.setString(1, String.valueOf(paciente.getId()));
            statement.setString(2, paciente.getNome());
            statement.setInt(3, paciente.getSexo());
            statement.setString(4, paciente.getDataNascimento());
            statement.setString(5, paciente.getCpf());
            statement.setString(6, paciente.getTelefone());
            statement.execute();
        }catch(Exception ex){
            throw new RuntimeException("Erro ao salvar Paciente");
        }
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        PreparedStatement statement = conexao.createPreparedStatement("UPDATE pacientes SET nome = ?, cpf = ?, telefone = ? WHERE id = ?");
        try {
            statement.setString(1, paciente.getNome());
            statement.setString(2, paciente.getCpf());
            statement.setString(3, paciente.getTelefone());
            statement.setString(4, String.valueOf(paciente.getId()));
            statement.execute();
            return paciente;
        }catch (Exception ex){
            throw new RuntimeException("Erro ao atualizar Paciente");
        }
    }

    @Override
    public Paciente buscarPorCpf(String cpf) {
        PreparedStatement statement = conexao.createPreparedStatement("SELECT * FROM pacientes WHERE cpf = ?");
        try {
            statement.setString(1, cpf);
            ResultSet resultSet = conexao.execute(statement);
            if (!resultSet.next()) {
                return null;
            }
            Paciente paciente = new Paciente();
            paciente.setId(UUID.fromString(resultSet.getString("id")));
            paciente.setNome(resultSet.getString("nome"));
            paciente.setCpf(resultSet.getString("cpf"));
            paciente.setTelefone(resultSet.getString("telefone"));
            return paciente;
        }catch(Exception ex){
            throw new RuntimeException("Erro ao buscar Paciente ou Paciente não encontrado");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        ResultSet resultSet = conexao.execute("SELECT * FROM pacientes");
        List<Paciente> pacientes = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(UUID.fromString(resultSet.getString("id")));
                paciente.setNome(resultSet.getString("nome"));
                paciente.setCpf(resultSet.getString("cpf"));
                paciente.setTelefone(resultSet.getString("telefone"));
                pacientes.add(paciente);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }

    @Override
    public boolean excluir(String id) {
        try {
            conexao.createStatement().execute("DELETE FROM pacientes WHERE id = '" + id +"'");
            return true;
        }catch (Exception ex){
            throw new RuntimeException("Erro ao excluir Paciente");
        }
    }
}
