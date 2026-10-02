package model;

import java.util.Date;

public class ModelFuncionario {
	private int idFuncionario;
	private  String nomeFuncionario, emailFuncionario, generoFuncionario, cpfFuncionario, foneFuncionario, cargoFuncionario, statusFuncionario;
	private Date dataNascFuncionario;
	private double salarioFunc;
	
	public double getSalarioFunc() {
		return salarioFunc;
	}
	public void setSalarioFunc(double salarioFunc) {
		this.salarioFunc = salarioFunc;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getEmailFuncionario() {
		return emailFuncionario;
	}
	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}
	public String getGeneroFuncionario() {
		return generoFuncionario;
	}
	public void setGeneroFuncionario(String generoFuncionario) {
		this.generoFuncionario = generoFuncionario;
	}
	public String getCpfFuncionario() {
		return cpfFuncionario;
	}
	public void setCpfFuncionario(String cpfFuncionario) {
		this.cpfFuncionario = cpfFuncionario;
	}
	public String getFoneFuncionario() {
		return foneFuncionario;
	}
	public void setFoneFuncionario(String foneFuncionario) {
		this.foneFuncionario = foneFuncionario;
	}
	public String getCargoFuncionario() {
		return cargoFuncionario;
	}
	public void setCargoFuncionario(String cargoFuncionario) {
		this.cargoFuncionario = cargoFuncionario;
	}
	public String getStatusFuncionario() {
		return statusFuncionario;
	}
	public void setStatusFuncionario(String statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	public Date getDataNascFuncionario() {
		return dataNascFuncionario;
	}
	public void setDataNascFuncionario(Date dataNascFuncionario) {
		this.dataNascFuncionario = dataNascFuncionario;
	}
	
}
