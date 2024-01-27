public class Telefone {
    private Long idTelefone;
    private String ddd;
    private Long numero;

    public Telefone(Long idTelefone, String ddd, long numero) {
        this.idTelefone = idTelefone;
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public Long getId() {
        return idTelefone;
    }

    @Override
    public String toString() {
        return idTelefone + " " + ddd + " " + numero;
    }
}
