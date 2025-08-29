/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/* @author André Olímpio */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao_BD {
    // Importação das classes necessárias para a aplicação
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bd_crud_java";
    String usuario = "root";
    String senha = "";

    private static Connection conexao;
    
    // Método para passar os parâmetros de conexão com o banco de dados
    public Conexao_BD() throws Exception {
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão OK");
        }
        catch(ClassNotFoundException Driver) {
            throw new Exception("Driver não encontrado!!!");
        }
        catch(SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }
    
    // Método que efetivamente faz a conexão com o banco
    public Connection getConexao() {
        return conexao;
    }

    //Método que para encerrar conexão com o banco de dados
    public void Fechar_Conexao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed())
                return;
            conexao.close();
        }
        catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }

    // Método para efetuar a confirmação de uma transação realizada no banco de dados
    public void Confirmar_Transacao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed())
                return;
            conexao.commit();
        }
        catch (SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }

    // Método para efetuar o cancelamento de uma transação
    public void Cancelar_Transacao() throws Exception {
        try {
            if (conexao == null || conexao.isClosed())
                return;
            conexao.rollback();
        }
        catch(SQLException sql) {
            throw new Exception("Falha ocorrida: " + sql.getMessage());
        }
    }
}