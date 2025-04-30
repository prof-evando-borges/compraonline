package br.com.fiap.compraonline.entities;

public class Cliente {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String documento;

    public Cliente(String id, String nome, String email, String senha, String telefone, String documento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.documento = documento;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
}

