package br.com.fiap.compraonline.teste;

import br.com.fiap.compraonline.entities.*;
import br.com.fiap.compraonline.exceptions.PagamentoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) throws SQLException, PagamentoException {

        Scanner scanner = new Scanner(System.in);

        // CADASTRO DO CLIENTE
        System.out.println("🔹 Bem-vindo ao sistema de pagamentos!");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Digite seu telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite seu CPF: ");
        String cpf = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, email, telefone);


        boolean continuar = true;

        // ESCOLHA MÉTODO PAGAMENTO
        while (continuar) {

            System.out.println("\n💳 Escolha o método de pagamento:");
            System.out.println("1 - Cartão de Crédito");
            System.out.println("2 - Pix");
            System.out.println("3 - Boleto");
            System.out.println("4 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            Pagamento pagamento = null;
            double valor = 0;

            // SAIR
            if (opcao == 4) {
                System.out.println("✅ Saindo do sistema. Obrigado!");
                break;
            }

            System.out.print("Digite o valor do pagamento: R$ ");
            valor = scanner.nextDouble();
            scanner.nextLine();


            // CARTÃO DE CRÉDITO
            if (opcao == 1) {

                List<CartaoCredito> cartoesDoCliente = CartaoCredito.listarCartoesDoCliente(cliente.getIdCliente());

                CartaoCredito cartaoSelecionado = null;

                if (!cartoesDoCliente.isEmpty()) {
                    System.out.println("\n💳 Cartões cadastrados:");
                    for (int i = 0; i < cartoesDoCliente.size(); i++) {
                        System.out.println((i + 1) + " - " + cartoesDoCliente.get(i).getNumeroMascarado());
                    }
                    System.out.println((cartoesDoCliente.size() + 1) + " - Cadastrar novo cartão");
                    System.out.print("Escolha: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha > 0 && escolha <= cartoesDoCliente.size()) {
                        cartaoSelecionado = cartoesDoCliente.get(escolha - 1);
                    }
                }

                if (cartaoSelecionado == null) {
                    System.out.print("Digite o número do cartão (16 dígitos): ");
                    String numeroCartao = scanner.nextLine();
                    System.out.print("Digite o nome empresso: ");
                    String nomeImpresso = scanner.nextLine();
                    System.out.print("Digite a validade (MM/AA): ");
                    String validade = scanner.nextLine();
                    System.out.print("Digite o CVV (3 dígitos): ");
                    int cvv = scanner.nextInt();
                    scanner.nextLine();

                    cartaoSelecionado = new CartaoCredito(0, nomeImpresso, numeroCartao, validade, cvv, cliente.getIdCliente(), opcao);
                    CartaoCredito.adicionarCartao(cartaoSelecionado);
                }

                pagamento = new CartaoCredito(valor, cartaoSelecionado.getNomeImpresso(), cartaoSelecionado.getNumero(), cartaoSelecionado.getValidade(), cartaoSelecionado.getCvv(), cliente.getIdCliente(), opcao);

                // PIX
            } else if (opcao == 2) {

                pagamento = new Pix(valor, cliente.getIdCliente(), opcao);

                // BOLETO
            }else if (opcao == 3) {

                pagamento = new Boleto(valor, cliente.getIdCliente(), opcao);

                // INVÁLIDO
            } else {
                System.out.println("❌ Opção inválida!");
            }

            // PROCESSAMENTO PAGAMENTO
            if (pagamento != null) {
                cliente.realizarPagamento(pagamento);
            }
        }

        // EXIBIR PAGAMENTOS
        cliente.consultarPagamentos(cliente.getIdCliente());


        scanner.close();
    }
}


