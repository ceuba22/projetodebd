package br.com.activity.util;

abstract class Calcular {

	/*
	 * M�todo que retorna o percentual real de realiza��o de uma atividade
	 */
	public double getPercentualReal(double tempoderealizacao,
			double tempodeexecucao) {
		double percentualReal = tempoderealizacao / tempodeexecucao * 100;
		return percentualReal;
	}

	/*
	 * M�todo que retorna o percentual te�rico de realiza��o de uma atividade
	 */
	public double getPercentualTeorico(double tempotranscorrido,
			double tempodeexecucao) {
		double percentualTeorico = tempotranscorrido / tempodeexecucao * 100;
		return percentualTeorico;
	}

	/*
	 * M�todo que retorna o n�vel de comprometimento de um empregado com 3
	 * argumentos de entrada, o tempo de realiza��o, o tempo transcorrido e o
	 * tempo de execu��o
	 */
	public double getNivelDeComprometimento(double tempoderealizacao,
			double tempotranscorrido, double tempodeexecucao) {
		double pt = getPercentualTeorico(tempotranscorrido, tempodeexecucao);
		double pr = getPercentualReal(tempoderealizacao, tempodeexecucao);
		double nivelDeComprometimento = (1 - ((100 - pt) / (100 - pr))) * 100;
		return nivelDeComprometimento;
	}

	/*
	 * M�todo que retorna o n�vel de comprometimento de um empregado com 2
	 * argumentos de entrada, o Percentual Real de Realiza��o da Atividade e o
	 * Percentual Te�rico de Realiza��o da Atividade
	 */
	public double getNivelDeComprometimento(double PT, double PR) {
		double ndc = (1 - ((100 - PT) / (100 - PR))) * 100;
		return ndc;
	}

	/*
	 * M�todo que retornar o desempenho individual do funcion�rio com 2
	 * argumentos de entrada, uma lista com o percentual de realiza��o das
	 * atividades realizadas de um projeto e a lista com os seus pesos
	 * correnpondentes
	 */
	public double desempenhoIndividual(double[] percentualDeRealizacao,
			double[] pesosDasAtividades) {
		int range = percentualDeRealizacao.length - 1;
		double dpi = 0;
		for (int i = 0; i >= range; i++) {
			dpi = pesosDasAtividades[i] * percentualDeRealizacao[i] / 100;
		}
		return dpi;
	}

	/*
	 * M�todo que retornar o desempenho geral do funcion�rio com 2 argumentos de
	 * entrada, uma lista com o percentual de realiza��o de todas as atividades
	 * realizadas e a lista com os seus pesos correnpondentes
	 */
	public double desempenhoGeral(double[] percentualDeRealizacao,
			double[] pesosDasAtividades) {
		int range = percentualDeRealizacao.length - 1;
		double dpg = 0;
		for (int i = 0; i >= range; i++) {
			dpg = pesosDasAtividades[i] * percentualDeRealizacao[i] / 100;
		}
		return dpg;
	}
}
