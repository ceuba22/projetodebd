package br.com.activity.util;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum StatusTipo {
	
	@XmlEnumValue("ABERTO")
	ABERTO("ABERTO"),
	
	@XmlEnumValue("INATIVO")
	INATIVO("INATIVO"),
	
	@XmlEnumValue("ENCERRADO")
	ENCERRADO("ENCERRADO");

	private final String value;
	
	private StatusTipo(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static StatusTipo fromValue(String v) {
		for (StatusTipo st : StatusTipo.values()) {
			if (st.value.equals(v)) {
				return st;
			}
		}

		throw new IllegalArgumentException(v);
	}
}
