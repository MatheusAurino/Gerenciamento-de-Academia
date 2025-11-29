package br.edu.ifpr.pgua.eic.tads.utils.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.utils.model.Exercicio;

public class ExercicioDAO {
    private final Connection con;

    public ExercicioDAO(Connection con) {
        this.con = con;
    }

    // LISTAR todos os exercicios
    public List<Exercicio> findAll() {
        String sql = "SELECT * FROM bd_exercicio ORDER BY nome";
        List<Exercicio> lista = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Exercicio exercicio = new Exercicio(
                        rs.getInt("id_exercicio"),
                        rs.getString("nome"),
                        rs.getString("grupo_muscular"));
                lista.add(exercicio);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar exercícios: " + e.getMessage());
        }
        return lista;
    }

    // Buscar por ID
    public Exercicio findById(int id) {
        String sql = "SELECT * FROM bd_exercicio WHERE id_exercicio = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Exercicio(
                            rs.getInt("id_exercicio"),
                            rs.getString("nome"),
                            rs.getString("grupo_muscular"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar exercício por ID: " + e.getMessage());
        }

        return null;
    }

    // Criar novo exercicio
    public boolean create(Exercicio exercicio) {
        String sql = "INSERT INTO bd_exercicio (nome, grupo_muscular) VALUES (?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getGrupoMuscular());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao criar exercício: " + e.getMessage());
            return false;
        }
    }

    // Atualizar exercicio
    public boolean update(Exercicio exercicio) {
        String sql = "UPDATE bd_exercicio SET nome=?, grupo_muscular=? WHERE id_exercicio=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getGrupoMuscular());
            stmt.setInt(3, exercicio.getId_Exercicio());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar exercício: " + e.getMessage());
            return false;
        }
    }

    // Deletar exercicio
    public boolean delete(int id) {
        String sql = "DELETE FROM bd_exercicio WHERE id_exercicio=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao deletar exercício: " + e.getMessage());
            return false;
        }
    }

}