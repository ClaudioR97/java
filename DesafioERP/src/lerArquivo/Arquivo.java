package lerArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Arquivo {
	private static String filePath = System.getProperty("user.dir").concat("\\dados\\lista_cidade.csv");
	private ArrayList<String> dadosArquivo = new ArrayList<>();

	public BufferedReader getFile() {
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(new File(filePath)));
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getDadosArquivo() {
		return dadosArquivo;
	}
	
	public void getQtDadosArquivo() {
		System.out.println(getDadosArquivo().size());
	}

	public void carregaDadosArquivo() {
		try {
			BufferedReader arquivo = getFile();

			while (arquivo.readLine() != null) {
				dadosArquivo.add(arquivo.readLine());
			}
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCapitais() {
		ArrayList<String> capitais = new ArrayList<>();

		try {
			for (String cidade : getDadosColunaValor(3, "true")) {
				capitais.add(cidade.split(",")[2]);
			}

			Collections.sort(capitais);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (capitais.size() > 0) {
			for (String capital : capitais) {
				System.out.println(capital);
			}
		}
	}

	public void obterQtCidadesPorEstado(String qtCidadesOuTudo) {
		ArrayList<String> cidades = new ArrayList<>();
		HashMap<String, Object> qtCidades = new HashMap<>();
		int last = 0;

		for (String cidade : getDadosArquivo()) {
			if (cidade != null) {
				String[] dadosCidade = cidade.split(",");

				cidades.add(dadosCidade[1]);
			}
		}

		for (int i = 0; i < cidades.size(); i++) {
			int count = 0;
			for (last = i; last < cidades.size(); last++) {
				if (cidades.get(i).trim().equalsIgnoreCase(cidades.get(last).trim())) {
					count++;
				} else {
					qtCidades.put(cidades.get(i), count);
					i = last - 1;
					break;
				}
			}
		}

		Object[] cidadesPorEst = qtCidades.values().toArray();

		Integer maiorValor = (Integer) cidadesPorEst[cidadesPorEst.length - 1];
		Integer menorValor = (Integer) cidadesPorEst[0];

		for (String uf : qtCidades.keySet()) {
			if (qtCidades.get(uf).equals(maiorValor) && "2".equals(qtCidadesOuTudo)) {
				System.out.println(uf.concat(" Tem ").concat(maiorValor.toString()).concat(" cidades"));
			}

			if (qtCidades.get(uf).equals(menorValor) && "2".equals(qtCidadesOuTudo)) {
				System.out.println(uf.concat(" Tem ").concat(menorValor.toString()).concat(" cidades"));
			}

			if ("3".equals(qtCidadesOuTudo)) {
				System.out.println(uf.concat(" ".concat(qtCidades.get(uf).toString()).concat(" cidades")));
			}

		}
	}

	public void obterCidadePorID(String id) {
		ArrayList<String> cidades = getDadosArquivo();
		String cidadeId = null;
		for (String cidade : cidades) {
			if (cidade != null) {
				String idCidade = cidade.split(",")[0];
				if (idCidade.equals(id)) {
					cidadeId = cidade;
				}
			}
		}
		
		if (cidadeId != null) {
			System.out.println(cidadeId);
		} else {
			System.out.println("Cidade não encontrada");
		}
	}

	public void obterCidadesPorUF(String uf) {
		for (String cidade : getDadosArquivo()) {
			if (cidade != null) {
				String[] dadosCidade = cidade.split(",");

				if (uf.equals(dadosCidade[1])) {
					System.out.println(dadosCidade[2]);
				}
			}
		}
	}

	public void gravaArquivo(String conteudo) {
		try {
			PrintWriter arquivo = new PrintWriter(new FileWriter(new File(filePath), true), true);
			arquivo.println(conteudo);
			arquivo.close();
			System.out.println("Registro gravado com sucesso!");
		} catch (Exception e) {
			System.out.println("Não foi possível gravar no arquivo..");
			System.out.println(e.getMessage());
		}
	}

	public static void gravaArquivo(ArrayList<String> conteudo) {
		try {
			PrintWriter arquivo = new PrintWriter(new FileWriter(new File(filePath), false), false);
			for (String linha : conteudo) {
				arquivo.println(linha);
			}
			arquivo.close();
		} catch (Exception e) {
			System.out.println("Não foi possível gravar no arquivo..");
			System.out.println(e.getMessage());
		}
	}

	public void remove(String idIBGE) {
		ArrayList<String> dadosArquivo = getDadosArquivo();
		ArrayList<String> dadosArquivoAux = new ArrayList<>();

		for (int i = 0; i < dadosArquivo.size(); i++) {
			if (!dadosArquivo.get(i).contains(idIBGE)) {
				dadosArquivoAux.add(dadosArquivo.get(i));
			}
		}
		dadosArquivo = dadosArquivoAux;
		gravaArquivo(dadosArquivo);
		System.out.println("Arquivo removido!");
	}

	public ArrayList<String> getDadosColunaValor(int indexColuna, String valor) {
		ArrayList<String> dadosColuna = new ArrayList<>();

		for (String cidade : getDadosArquivo()) {
			if (cidade != null) {
				String[] dadosCidade = cidade.split(",");

				if (dadosCidade[indexColuna].contains(valor)) {
					dadosColuna.add(cidade);
				}
			}
		}

		return dadosColuna;
	}
	
	public ArrayList<String> getDadosColuna(int indexColuna) {
		ArrayList<String> dadosColuna = new ArrayList<>();

		for (String cidade : getDadosArquivo()) {
			if (cidade != null) {
				String[] dadosCidade = cidade.split(",");

				if (dadosCidade[indexColuna] != null) {
					dadosColuna.add(dadosCidade[indexColuna]);
				}
			}
		}

		return dadosColuna;
	}
	
	public void getQtRegistroNaoRepetidos(int indeceColuna) {
		Set<String> set = new HashSet<>();
		for(String a: getDadosColuna(indeceColuna)) {
		    set.add(a);
		}
		System.out.println(set.size());
	}
}
