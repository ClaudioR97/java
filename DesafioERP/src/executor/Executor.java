package executor;

import java.util.ArrayList;
import java.util.Scanner;

import lerArquivo.Arquivo;

public class Executor {
	public static void main(String[] args) {
		Arquivo arq = new Arquivo();
		arq.carregaDadosArquivo();		
		Scanner ler = new Scanner(System.in);		
		String acao = "";
		
		while (!"0".equals(acao)) {
			System.out.println("0. Encerrar aplicação;");
			System.out.println("1. Retornar somente as cidades que são capitais ordenadas por nome;");
			System.out.println("2. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;");
			System.out.println("3. Retornar a quantidade de cidades por estado;");
			System.out.println("4. Obter os dados da cidade informando o id do IBGE;");
			System.out.println("5. Retornar o nome das cidades baseado em um estado selecionado;");
			System.out.println("6. Permitir adicionar uma nova Cidade;");
			System.out.println("7. Permitir deletar uma cidade;");
			System.out.println("8. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;");
			System.out.println("9. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;");
			System.out.println("10. Retornar a quantidade de registros total; ");
			acao = ler.next();
			int indexColuna = 1;
			
			switch (acao) {
			case "0":
				break;
			case "1":
				arq.getCapitais();
				break;
			case "2":
				arq.obterQtCidadesPorEstado(acao);
				break;
			case "3":
				arq.obterQtCidadesPorEstado(acao);
				break;
			case "4":
				System.out.print("Informe o ID do IBGE: ");
				arq.obterCidadePorID(ler.next());
				break;
			case "5":
				System.out.print("Informe o UF do estado: ");
				arq.obterCidadesPorUF(ler.next());
				break;
			case "6":
				StringBuilder registro = new StringBuilder();
				System.out.print("Insira o ID IBGE: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira o UF do estado: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira o nome da cidade: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira \"TRUE\" caso seja uma capital: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira a longitude: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira o nome da cidade sem acentos: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira um nome alternativo para a cidade: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira a microrregião: ");
				registro.append(ler.next().concat(","));
				System.out.print("Insira a mesorregião: ");
				registro.append(ler.next());
				arq.gravaArquivo(registro.toString());
				break;
			case "7":
				System.out.print("Insira o ID IBGE: ");
				arq.remove(ler.next());
				break;
			case "8":
				System.out.println("Selecione uma coluna:");
				System.out.println("1. ibge_id;");
				System.out.println("2. uf;");
				System.out.println("3. name;");
				System.out.println("4. capital;");
				System.out.println("5. lon;");
				System.out.println("6. lat;");
				System.out.println("7. no_accents;");
				System.out.println("8. alternative_names;");
				System.out.println("9. microregion;");
				System.out.println("10. mesoregion.");
				
				indexColuna = ler.nextInt() - 1;
				
				System.out.print("Insira texto para filtro: ");
				ArrayList<String> dadosColuna = arq.getDadosColunaValor(indexColuna, ler.next());
				
				for (String dadoColuna : dadosColuna) {
					System.out.println(dadoColuna);
				}
				break;
			case "9":
				System.out.println("Selecione uma coluna:");
				System.out.println("1. ibge_id;");
				System.out.println("2. uf;");
				System.out.println("3. name;");
				System.out.println("4. capital;");
				System.out.println("5. lon;");
				System.out.println("6. lat;");
				System.out.println("7. no_accents;");
				System.out.println("8. alternative_names;");
				System.out.println("9. microregion;");
				System.out.println("10. mesoregion.");
				
				indexColuna = ler.nextInt() - 1;
				arq.getQtRegistroNaoRepetidos(indexColuna);
				break;
			case "10":
				arq.getQtDadosArquivo();
				break;
			default:
				break;
			}
		}
		
		System.out.println("Conexao fechada");
		ler.close();

	}
}
