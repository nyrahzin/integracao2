package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entity.Pessoa;


public class PessoaDAO {
    // CRUD
    // C - create | R - retrieve | U - update | D - delete
    public void inserir(Pessoa pessoa){
        ConectaBD con = new ConectaBD();
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?,?)";
        try{
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());
            pst.execute();
            System.out.println(pessoa.getNome() + " inserido");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Metodo que consulta registro do tipo pessoa por id
     * @param id - refere-se a chave primária do registro
     * @return um objeto do tipo Pessoa, caso não entcontre retorna nulo
     */

    public Pessoa consultar(int id){
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa p = null;
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                p = new Pessoa(nome, email);
                p.setId(rs.getInt("id"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;

    }


    public List<Pessoa> consultarTodos(){
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> lista = new LinkedList<Pessoa>();
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                Pessoa pessoa = new Pessoa(); 
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    public boolean excluir (int chave) {
        String sql = "DELETE FROM pessoa WHERE id = ?";
        try {
            ConectaBD conexao = new ConectaBD();
            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, chave);
            pst.execute();
            return pst.getUpdateCount()>0;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}