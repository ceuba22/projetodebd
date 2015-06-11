package br.com.activity.util;

abstract class Calcular {

	/*
	 * Método que retorna o percentual real de realização de uma atividade
	 */
	public double getPercentualReal(double tempoderealizacao,
			double tempodeexecucao) {
		double percentualReal = tempoderealizacao / tempodeexecucao * 100;
		return percentualReal;
	}

	/*
	 * Método que retorna o percentual teórico de realização de uma atividade
	 */
	public double getPercentualTeorico(double tempotranscorrido,
			double tempodeexecucao) {
		double percentualTeorico = tempotranscorrido / tempodeexecucao * 100;
		return percentualTeorico;
	}

	/*
	 * Método que retorna o nível de comprometimento de um empregado com 3
	 * argumentos de entrada, o tempo de realização, o tempo transcorrido e o
	 * tempo de execução
	 */
	public double getNivelDeComprometimento(double tempoderealizacao,
			double tempotranscorrido, double tempodeexecucao) {
		double pt = getPercentualTeorico(tempotranscorrido, tempodeexecucao);
		double pr = getPercentualReal(tempoderealizacao, tempodeexecucao);
		double nivelDeComprometimento = (1 - ((100 - pt) / (100 - pr))) * 100;
		return nivelDeComprometimento;
	}

	/*
	 * Método que retorna o nível de comprometimento de um empregado com 2
	 * argumentos de entrada, o Percentual Real de Realização da Atividade e o
	 * Percentual Teórico de Realização da Atividade
	 */
	public double getNivelDeComprometimento(double PT, double PR) {
		double ndc = (1 - ((100 - PT) / (100 - PR))) * 100;
		return ndc;
	}

	/*
	 * Método que retornar o desempenho individual do funcionário com 2
	 * argumentos de entrada, uma lista com o percentual de realização das
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
	 * Método que retornar o desempenho geral do funcionário com 2 argumentos de
	 * entrada, uma lista com o percentual de realização de todas as atividades
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
