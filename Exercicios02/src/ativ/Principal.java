package ativ;

import java.util.Scanner;

public class Pricipal {

    public static void main(String[] args) {
        DAO dao = new DAO();

        // Conectar ao banco de dados
        if (dao.conectar()) {
            System.out.println("Conexão bem-sucedida!");

            // Menu com opções
            Scanner scanner = new Scanner(System.in);
            int opcao;
            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1) Listar Biscoitos");
                System.out.println("2) Inserir Biscoito");
                System.out.println("3) Excluir Biscoito");
                System.out.println("4) Atualizar Biscoito");
                System.out.println("5) Sair");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        // Listar biscoitos
                        Biscoito[] biscoitos = dao.getBiscoitos();
                        if (biscoitos.length > 0) {
                            for (Biscoito biscoito : biscoitos) {
                                System.out.println(biscoito);
                            }
                        } else {
                            System.out.println("Nenhum biscoito encontrado.");
                        }
                        break;
                    case 2:
                        // Inserir biscoito
                        System.out.println("Inserir novo biscoito:");
                        System.out.print("Código: ");
                        int codigoInserir = scanner.nextInt();
                        System.out.print("Marca: ");
                        String marca = scanner.next();
                        System.out.print("Recheio: ");
                        String recheio = scanner.next();
                        Biscoito novoBiscoito = new Biscoito(codigoInserir, marca, recheio);
                        dao.inserirBiscoito(novoBiscoito);
                        System.out.println("Biscoito inserido com sucesso!");
                        break;
                    case 3:
                        // Excluir biscoito
                        System.out.println("Digite o código do biscoito a ser excluído:");
                        int codigoExcluir = scanner.nextInt();
                        dao.excluirBiscoito(codigoExcluir);
                        System.out.println("Biscoito excluído com sucesso!");
                        break;
                    case 4:
                        // Atualizar biscoito
                        System.out.println("Digite o código do biscoito a ser atualizado:");
                        int codigoAtualizar = scanner.nextInt();
                        System.out.println("Digite a nova marca:");
                        String novaMarca = scanner.next();
                        System.out.println("Digite o novo recheio:");
                        String novoRecheio = scanner.next();
                        Biscoito biscoitoAtualizado = new Biscoito(codigoAtualizar, novaMarca, novoRecheio);
                        dao.atualizarBiscoito(biscoitoAtualizado);
                        System.out.println("Biscoito atualizado com sucesso!");
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (opcao != 5);

            scanner.close();
            dao.close();
        } else {
            System.out.println("Falha na conexão com o banco de dados.");
        }
    }
}
