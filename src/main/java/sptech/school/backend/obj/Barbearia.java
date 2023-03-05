package sptech.school.backend.obj;

public class Barbearia {

    private Long id;
    private String nomeBarbearia;
    private String nomeBarbeiro;
    private String cnpjBarbearia;
    private String emailBarbearia;

    public Barbearia(Long id, String nomeBarbearia, String nomeBarbeiro, String cnpjBarbearia, String emailBarbearia) {
        this.id = id;
        this.nomeBarbearia = nomeBarbearia;
        this.nomeBarbeiro = nomeBarbeiro;
        this.cnpjBarbearia = cnpjBarbearia;
        this.emailBarbearia = emailBarbearia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBarbeiro() {
        return nomeBarbeiro;
    }

    public void setNomeBarbeiro(String nomeBarbeiro) {
        this.nomeBarbeiro = nomeBarbeiro;
    }

    public String getNomeBarbearia() {
        return nomeBarbearia;
    }

    public void setNomeBarbearia(String nomeBarbearia) {
        this.nomeBarbearia = nomeBarbearia;
    }

    public String getCnpjBarbearia() {
        return cnpjBarbearia;
    }

    public void setCnpjBarbearia(String cnpjBarbearia) {
        this.cnpjBarbearia = cnpjBarbearia;
    }

    public String getEmailBarbearia() {
        return emailBarbearia;
    }

    public void setEmailBarbearia(String emailBarbearia) {
        this.emailBarbearia = emailBarbearia;
    }
}
