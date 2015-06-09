package br.com.activity.util;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum PrioridadeTipo {
	
	@XmlEnumValue("ALTA")
	ALTA("ALTA"),
	
	@XmlEnumValue("MEDIA")
	MEDIA("MEDIA"),
	
	@XmlEnumValue("BAIXA")
	BAIXA("BAIXA");

	private final String value;
	
	private PrioridadeTipo(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static PrioridadeTipo fromValue(String v) {
		for (PrioridadeTipo st : PrioridadeTipo.values()) {
			if (st.value.equals(v)) {
				return st;
			}
		}

		throw new IllegalArgumentException(v);
	}

}
