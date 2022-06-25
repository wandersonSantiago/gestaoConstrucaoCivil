package br.com.app.financeiro.domain.enuns;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;


public enum DataBaseEnum {


	_1(1,"Um") ,
	_2(2, "Dois"),
	_3(3, "Tres"),
	_4(4, "Quatro"),
	_5 (5, "Cinco"),
	_6(6, "Seis"),
	_7(7, "Sete"),
	_8(8, "Oito"),
	_9 (9,"Nove") ,
	_10(10, "Dez"),
	_11(11, "Onze"),
	_12(12, "Doze"),
	_13 (13, "Treze"),
	_14(14, "Quatorze"),
	_15(15, "Quinze"),
	_16(16, "Dezesseis"),
	_17(17, "Dezessete"),
	_18(18, "Dezoito"),
	_19(19,"Dezenove") ,
	_20(20, "Vinte"),
	_21(21, "Vinte e Um"),
	_22(22, "Vinte e Dois"),
	_23 (23, "Vinte e Tres"),
	_24(24, "Vinte e Quatro"),
	_25(25, "Vinte e Cinco"),
	_26(26, "Vinte e Seis"),
	_27(27, "Vinte e Sete"),
	_28(28, "Vinte e Oito");
	
	private Integer id;
	private String nome;

	DataBaseEnum(Integer id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	//@JsonValue
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
 
	 @JsonCreator
	 public static Set<DataBaseEnum> getEnuns(List<Integer> dias){
		 Set<DataBaseEnum> enuns = new HashSet<>();
		 for (DataBaseEnum my : DataBaseEnum.values()) {
				if (dias.contains(my.id)) {
					enuns.add(my);
				}
			}
		 return enuns;
	 }
	 
	 public static DataBaseEnum getEnum(Integer id){
		 for (DataBaseEnum my : DataBaseEnum.values()) {
				if (my.id.equals(id)) {
					return my;
				}
			}
		 throw new IllegalArgumentException("Data Base não encontrada!");
	 }
 
}
