/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Carros;

/**
 *
 * @author Senai
 */
public class CarrosDAO {

    public List<Carros> leitura() {
        List<Carros> carros = new ArrayList<>();
        try {
            Connection conexao = Conexao.connect();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conexao.prepareStatement("SELECT * FROM carros");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Carros carro = new Carros();
                carro.setIdCarro(rs.getInt("idCarro"));
                carro.setMarca(rs.getString("marca"));
                carro.setModelo(rs.getString("modelo"));
                carro.setAno(rs.getInt("ano"));
                carro.setCor(rs.getString("cor"));
                carro.setPlaca(rs.getString("placa"));
                carro.setStatus(rs.getString("status"));
                carros.add(carro);
            }
            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carros;
    }

    public void cadastrarCarro(Carros objCarros) {  
        try{
            Connection conexao = Conexao.connect();
            PreparedStatement stmt = null;
            stmt = conexao.prepareStatement("insert into carros(marca,modelo,ano,cor,placa)values(?,?,?,?,?)");
            stmt.setString(1, objCarros.getMarca());
            stmt.setString(2, objCarros.getModelo());
            stmt.setInt(3, objCarros.getAno());
            stmt.setString(4, objCarros.getCor());
            stmt.setString(5, objCarros.getPlaca());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Carro inserido com sucesso!");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletarCampo(Carros objCarros) {
        try{
            Connection conexao = Conexao.connect();
            PreparedStatement stmt = null;
            stmt = conexao.prepareStatement("DELETE FROM carros WHERE idCarro = ?");
            stmt.setInt(1, objCarros.getIdCarro());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Campo deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void alterarCompos(Carros objCarros) {
        try {
            Connection conexao = Conexao.connect();
            PreparedStatement stmt = null;
            stmt = conexao.prepareStatement("update carros  set status = ? where idCarro = ?");
            stmt.setString(1, objCarros.getStatus());
            stmt.setInt(2, objCarros.getIdCarro());
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Status atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
