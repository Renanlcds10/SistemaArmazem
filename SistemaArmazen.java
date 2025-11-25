import java.util.*;

class Produto {
    String codigo, nome, categoria;
    int quantidade;
    double preco;

    Produto(String codigo, String nome, String categoria, int qtd, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = qtd;
        this.preco = preco;
    }

    public String toString() {
        return codigo + " | " + nome + " | " + categoria + " | Qtd: " + quantidade + " | R$" + preco;
    }
}

public class SistemaArmazem {
    static ArrayList<Produto> produtos = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Dados de exemplo
        produtos.add(new Produto("P001", "Notebook", "Eletrônicos", 15, 3500.00));
        produtos.add(new Produto("P002", "Mouse", "Eletrônicos", 50, 89.90));
        produtos.add(new Produto("P003", "Cadeira", "Móveis", 8, 1200.00));

        while(true) {
            System.out.println("\n=== SISTEMA DE ARMAZÉM ===");
            System.out.println("1-Cadastrar 2-Buscar 3-Atualizar 4-Ordenar 5-Listar 0-Sair");
            int op = sc.nextInt();
            sc.nextLine();

            if(op == 0) break;
            else if(op == 1) cadastrar();
            else if(op == 2) buscar();
            else if(op == 3) atualizar();
            else if(op == 4) ordenar();
            else if(op == 5) listar();
        }
    }

    static void cadastrar() {
        System.out.print("Código: ");
        String cod = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Categoria: ");
        String cat = sc.nextLine();
        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        produtos.add(new Produto(cod, nome, cat, qtd, preco));
        System.out.println("Cadastrado!");
    }

    static void buscar() {
        System.out.print("Buscar por (1-Código 2-Nome 3-Categoria): ");
        int tipo = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite: ");
        String busca = sc.nextLine();

        for(Produto p : produtos) {
            if((tipo == 1 && p.codigo.equals(busca)) ||
                    (tipo == 2 && p.nome.toLowerCase().contains(busca.toLowerCase())) ||
                    (tipo == 3 && p.categoria.equalsIgnoreCase(busca))) {
                System.out.println(p);
            }
        }
    }

    static void atualizar() {
        System.out.print("Código do produto: ");
        String cod = sc.nextLine();
        for(Produto p : produtos) {
            if(p.codigo.equals(cod)) {
                System.out.print("Nova quantidade: ");
                p.quantidade = sc.nextInt();
                System.out.println("Atualizado!");
                return;
            }
        }
        System.out.println("Não encontrado!");
    }

    static void ordenar() {
        System.out.print("Ordenar por (1-Preço 2-Quantidade): ");
        int op = sc.nextInt();
        for(int i = 0; i < produtos.size()-1; i++) {
            for(int j = 0; j < produtos.size()-i-1; j++) {
                boolean trocar = (op == 1) ? produtos.get(j).preco > produtos.get(j+1).preco
                        : produtos.get(j).quantidade > produtos.get(j+1).quantidade;
                if(trocar) {
                    Produto temp = produtos.get(j);
                    produtos.set(j, produtos.get(j+1));
                    produtos.set(j+1, temp);
                }
            }
        }
        System.out.println("Ordenado!");
        listar();
    }

    static void listar() {
        System.out.println("\n=== PRODUTOS ===");
        for(Produto p : produtos) System.out.println(p);
    }
}
