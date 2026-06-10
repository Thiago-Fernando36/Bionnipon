package piClasses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Instanciando o Funcionário
        Funcionario func = new Funcionario(1, "Micael", "micael_login", "senha123", "Técnico");

        //Instanciando o Cliente
        Cliente cliente = new Cliente(10, "Condomínio Primavera", "primavera_cli", "1234", 
                                     "12.345.678/0001-90", "(11) 9999-8888", "Rua das Flores, 123");

        // Criando Estoque
        Produto veneno = new Produto(101, "Inseticida K-Othrine", 50); // 50 litros

        System.out.println("--- BIONNIPON AGENDA: STATUS INICIAL ---");
        System.out.println(func);
        System.out.println(cliente);
        System.out.println(veneno);

        System.out.println("\n--- REGISTRANDO SERVIÇO NA AGENDA ---");
        
        //Criando e processando o serviço
        Servico servico = new Servico("Dedetização de Baratas", cliente.getNome(), LocalDate.now(), 6);
        
        // Simulando o uso de 5 litros do estoque para este serviço
        veneno.darBaixa(5);

        //Exibindo Relatório de Garantia
        System.out.println("\n--- RELATÓRIO FINAL ---");
        servico.exibirRelatorio();
        System.out.println(veneno); // Mostra o estoque atualizado
        
        // Simulação de verificação de login do cliente
        if(cliente.autenticar("primavera_cli", "1234")) {
            System.out.println("\nPortal do Cliente: " + cliente.getNome() + " logado com sucesso.");
        }
    }
}