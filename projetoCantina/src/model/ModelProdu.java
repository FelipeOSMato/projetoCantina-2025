package model;

public class ModelProdu {
	public int getIdProdu() {
		return idProdu;
	}
	public void setIdProdu(int idProdu) {
		this.idProdu = idProdu;
	}
	public String getTituloTipoProdu() {
		return tituloTipoProdu;
	}
	public void setTituloTipoProdu(String tituloTipoProdu) {
		this.tituloTipoProdu = tituloTipoProdu;
	}
	public String getNomeProdu() {
		return nomeProdu;
	}
	public void setNomeProdu(String nomeProdu) {
		this.nomeProdu = nomeProdu;
	}
	public String getDescProdu() {
		return descProdu;
	}
	public void setDescProdu(String descProdu) {
		this.descProdu = descProdu;
	}
	public double getValorProdu() {
		return valorProdu;
	}
	public void setValorProdu(double valorProdu) {
		this.valorProdu = valorProdu;
	}

	public int getQuantiaEstoque() {
		return quantiaEstoque;
	}
	public void setQuantiaEstoque(int quantiaEstoque) {
		this.quantiaEstoque = quantiaEstoque;
	}
	private int idProdu, quantiaEstoque;
	private String nomeProdu, descProdu, tituloTipoProdu;
	private double valorProdu;
	
    public String toString() {
        return nomeProdu + " | " + descProdu + " | R$ " + valorProdu + " | " + quantiaEstoque + " | ";
    }
}
