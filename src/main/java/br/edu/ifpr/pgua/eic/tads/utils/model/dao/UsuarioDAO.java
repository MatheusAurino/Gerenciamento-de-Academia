package br.edu.ifpr.pgua.eic.tads.utils.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.utils.model.Usuario;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }


    // Criar usuario

    public boolean create(Usuario u) {
        String sql = "INSERT INTO bd_usuario (nome, cpf, senha, tipo) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTipo());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // LISTAR TUDO

    public List<Usuario> findAll() {
        String sql = "SELECT * FROM bd_usuario";
        List<Usuario> lista = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getString("id_usuario"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("senha"),
                    rs.getString("tipo")
                );

                lista.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar id do usuario

    public Usuario findById(int id) {
        String sql = "SELECT * FROM bd_usuario WHERE id_usuario = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Usuario u = new Usuario(
                        rs.getString("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getString("tipo")
                    );

                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Atualizar usuario

    public boolean update(Usuario u) {
        String sql = "UPDATE bd_usuario SET nome=?, cpf=?, senha=?, tipo=? WHERE id_usuario=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getCpf());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTipo());
            stmt.setInt(5, Integer.parseInt(u.getId_usuario()));

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Deletar usuario
    public boolean delete(int id) {
        String sql = "DELETE FROM bd_usuario WHERE id_usuario=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
