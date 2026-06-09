package org.example.database;


import org.example.entities.Usuario;
import org.example.repositories.UsuarioRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioRepositoryJBDC implements UsuarioRepository {
    private final Conexao conexao;

    public UsuarioRepositoryJBDC(Conexao conexao){
        this.conexao = Conexao.getInstancia();
    }

    @Override
    public void salvar(Usuario usuario){
        PreparedStatement statement = conexao.createPreparedStatement("INSERT INTO usuarios (id, nome, email, senha VALUES(?, ?, ?, ?)");
        try{
            statement.setString(1, String.valueOf(usuario.getId()));
            statement.setString(2, usuario.getNome());
            statement.setString(3, String.valueOf(usuario.getEmail()));
            statement.setString(4, String.valueOf(usuario.getSenha()));
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar usuario");
        }
    }

    @Override
    public Usuario atualizar(Usuario usuario) {
        PreparedStatement statement = conexao.createPreparedStatement("UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE id = ?");
        try{
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, String.valueOf(usuario.getId()));
            statement.execute();
            return usuario;
        }catch (Exception e){
            throw new RuntimeException("erro ao atualizar usuario");
        }
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        PreparedStatement statement = conexao.createPreparedStatement("SELECT * FROM usuarios WHERE email = ?");
        try{
            statement.setString(1, email);
            ResultSet resultSet = conexao.execute(statement);
            if(!resultSet.next()){
                return null;
            }

            Usuario usuario = new Usuario();

            usuario.setId(UUID.fromString(resultSet.getString("id")));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setSenha(resultSet.getString("senha"));

            return usuario;
        }catch (Exception e){
            throw new RuntimeException("Usuario nao encontrado");
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        ResultSet resultSet = conexao.execute("SELECT * from usuarios");

        List<Usuario> usuarios = new ArrayList<>();
        try {
            while(resultSet.next()){
                Usuario usuario = new Usuario();

                usuario.setId(UUID.fromString(resultSet.getString("id")));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));

                usuarios.add(usuario);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    @Override
    public boolean excluir(String id) {
        try{
            conexao.createStatement().execute("DELETE FROM usuarios WHERE id = '\" + id + \"'");
            return true;
        }catch (Exception e){
            throw new RuntimeException("Erro ao excluir usuario");
        }
    }
}
