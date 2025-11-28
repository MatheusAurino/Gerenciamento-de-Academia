package br.edu.ifpr.pgua.eic.tads.utils.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.utils.model.Plano;

public class PlanoDAO {
    private final Connection con;

    public PlanoDAO(Connection con) {
        this.con = con;
    }

    // LISTAR todos os planos
    public List<Plano> findAll() {
        String sql = "SELECT * FROM bd_plano ORDER BY valor";
        List<Plano> lista = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Plano plano = new Plano(
                        rs.getInt("id_plano"),
                        rs.getString("nome"),
                        rs.getDouble("valor"),
                        rs.getString("beneficios"));
                lista.add(plano);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // BUSCAR por ID
    public Plano findById(int id) {
        String sql = "SELECT * FROM bd_plano WHERE id_plano = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Plano(
                            rs.getInt("id_plano"),
                            rs.getString("nome"),
                            rs.getDouble("valor"),
                            rs.getString("beneficios"));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar plano por ID: " + e.getMessage());
        }

        return null;
    }

    // Criar novo plano
    public boolean create(Plano plano) {
        String sql = "INSERT INTO bd_plano (nome, valor, beneficios) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setString(3, plano.getBeneficios());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao criar plano: " + e.getMessage());
            return false;
        }
    }

    // Atualizar plano
    public boolean update(Plano plano) {
        String sql = "UPDATE bd_plano SET nome=?, valor=?, beneficios=? WHERE id_plano=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setString(3, plano.getBeneficios());
            stmt.setInt(4, plano.getId_plano());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao atualizar plano: " + e.getMessage());
            return false;
        }
    }

    // Deletar plano
    public boolean delete(int id) {
        String sql = "DELETE FROM bd_plano WHERE id_plano=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.err.println("Erro ao deletar plano: " + e.getMessage());
            return false;
        }
    }
}
