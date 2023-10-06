import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class NoDaArvore {
    int data;
    NoDaArvore esquerda;
    NoDaArvore direita;
    NoDaArvore pai;

    public NoDaArvore(int data) {
        this.data = data;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }
}

class ArvoreBinaria {
    private NoDaArvore raiz;
    private List<Integer> elementosInseridos = new ArrayList<>();

    public ArvoreBinaria() {
        raiz = null;
    }

    // Método para inserir um elemento na árvore
    public void inserir(int data) {
        raiz = inserirRec(raiz, data);
        elementosInseridos.add(data); // Adiciona o elemento à lista
    }

    private NoDaArvore inserirRec(NoDaArvore raiz, int data) {
        if (raiz == null) {
            raiz = new NoDaArvore(data);
            return raiz;
        }

        if (data < raiz.data) {
            raiz.esquerda = inserirRec(raiz.esquerda, data);
            raiz.esquerda.pai = raiz;
        } else if (data > raiz.data) {
            raiz.direita = inserirRec(raiz.direita, data);
            raiz.direita.pai = raiz;
        }

        return raiz;
    }

    // Método para pesquisar um elemento na árvore
    public NoDaArvore buscar(int data) {
        return buscarRec(raiz, data);
    }

    private NoDaArvore buscarRec(NoDaArvore raiz, int data) {
        if (raiz == null || raiz.data == data) {
            return raiz;
        }

        if (data < raiz.data) {
            return buscarRec(raiz.esquerda, data);
        } else {
            return buscarRec(raiz.direita, data);
        }
    }

    // Método para obter a altura de um nó na árvore
    public int getAltura(NoDaArvore no) {
        if (no == null) {
            return -1;
        }
        int alturaEsquerda = getAltura(no.esquerda);
        int alturaDireita = getAltura(no.direita);
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    // Método para obter a profundidade de um nó na árvore
    public int getProfundidade(NoDaArvore no) {
        int profundidade = 0;
        NoDaArvore atual = no;
        while (atual != null) {
            profundidade++;
            atual = atual.pai;
        }
        return profundidade - 1; // Subtrai 1 para excluir a raiz
    }

    // Método para verificar se um nó é filho esquerdo
    public boolean ehFilhoEsquerdo(NoDaArvore no) {
        if (no == null || no.pai == null) {
            return false;
        }
        return no.pai.esquerda == no;
    }

    // Método para verificar se um nó é filho direito
    public boolean ehFilhoDireito(NoDaArvore no) {
        if (no == null || no.pai == null) {
            return false;
        }
        return no.pai.direita == no;
    }

    // Método para obter os elementos inseridos
    public List<Integer> getElementosInseridos() {
        return elementosInseridos;
    }

    // Método para imprimir a árvore em pré-ordem
    public void imprimirPreOrdem() {
        imprimirPreOrdem(raiz);
        System.out.println();
    }

    private void imprimirPreOrdem(NoDaArvore no) {
        if (no == null) {
            return;
        }
        System.out.print(no.data + " ");
        imprimirPreOrdem(no.esquerda);
        imprimirPreOrdem(no.direita);
    }

    // Método para imprimir a árvore em pós-ordem
    public void imprimirPosOrdem() {
        imprimirPosOrdem(raiz);
        System.out.println();
    }

    private void imprimirPosOrdem(NoDaArvore no) {
        if (no == null) {
            return;
        }
        imprimirPosOrdem(no.esquerda);
        imprimirPosOrdem(no.direita);
        System.out.print(no.data + " ");
    }

    // Método para imprimir a árvore em ordem (in-order traversal)
    public void imprimirEmOrdem() {
        imprimirEmOrdem(raiz);
        System.out.println();
    }

    private void imprimirEmOrdem(NoDaArvore no) {
        if (no == null) {
            return;
        }
        imprimirEmOrdem(no.esquerda);
        System.out.print(no.data + " ");
        imprimirEmOrdem(no.direita);
    }

    // Método para imprimir a árvore com indentação
    public void imprimirComIndentacao() {
        imprimirComIndentacao(raiz, 0, "Raiz: ");
    }

    private void imprimirComIndentacao(NoDaArvore no, int indentacao, String prefixo) {
        if (no == null) {
            return;
        }

        // Imprime o nó atual com indentação
        System.out.println(prefixo + no.data);

        // Chama recursivamente para os filhos
        if (no.esquerda != null || no.direita != null) {
            String prefixoFilho = "  " + prefixo; // Adiciona espaço para os filhos
            if (no.esquerda != null && no.direita != null) {
                imprimirComIndentacao(no.esquerda, indentacao + 4, prefixoFilho + "├── Esquerda: ");
                imprimirComIndentacao(no.direita, indentacao + 4, prefixoFilho + "└── Direita: ");
            } else if (no.esquerda != null) {
                imprimirComIndentacao(no.esquerda, indentacao + 4, prefixoFilho + "└── ");
            } else if (no.direita != null) {
                imprimirComIndentacao(no.direita, indentacao + 4, prefixoFilho + "└── ");
            }
        }
    }

    // Função recursiva para contar o número de nós de uma árvore binária
    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(NoDaArvore no) {
        if (no == null) {
            return 0;
        }
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    // Função para encontrar o menor elemento da árvore
    public int encontrarMinimo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        NoDaArvore atual = raiz;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual.data;
    }

    // Função para encontrar o maior elemento da árvore
    public int encontrarMaximo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }

        NoDaArvore atual = raiz;
        while (atual.direita != null) {
            atual = atual.direita;
        }
        return atual.data;
    }

    // Função para verificar se a árvore é uma árvore binária de busca
    public boolean ehArvoreBinariaDeBusca() {
        return ehArvoreBinariaDeBusca(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean ehArvoreBinariaDeBusca(NoDaArvore no, int valorMinimo, int valorMaximo) {
        if (no == null) {
            return true;
        }

        if (no.data < valorMinimo || no.data > valorMaximo) {
            return false;
        }

        return ehArvoreBinariaDeBusca(no.esquerda, valorMinimo, no.data - 1) &&
                ehArvoreBinariaDeBusca(no.direita, no.data + 1, valorMaximo);
    }

    // Função para contar quantos nós de uma árvore binária têm um único filho
    public int contarNosComApenasUmFilho() {
        return contarNosComApenasUmFilho(raiz);
    }

    private int contarNosComApenasUmFilho(NoDaArvore no) {
        if (no == null) {
            return 0;
        }

        if ((no.esquerda != null && no.direita == null) || (no.esquerda == null && no.direita != null)) {
            return 1 + contarNosComApenasUmFilho(no.esquerda) + contarNosComApenasUmFilho(no.direita);
        } else {
            return contarNosComApenasUmFilho(no.esquerda) + contarNosComApenasUmFilho(no.direita);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Escolha uma opção:");
            System.out.println();
            System.out.println("1. Inserir elementos manualmente");
            System.out.println("2. Inserir elementos aleatoriamente");

            int escolha = scanner.nextInt();
            System.out.println();

            if (escolha == 1) {
                System.out.println("Digite os elementos da árvore (-999 para encerrar):");
                int elemento;
                while ((elemento = scanner.nextInt()) != -999) {
                    arvore.inserir(elemento);
                }

            } else if (escolha == 2) {
                System.out.println("Digite o número de nós para gerar aleatoriamente:");
                int numNos = scanner.nextInt();
                Random rand = new Random();
                for (int i = 0; i < numNos; i++) {
                    int valorAleatorio = rand.nextInt(100); // Gere valores aleatórios entre 0 e 99
                    arvore.inserir(valorAleatorio);
                }
            } else {
                System.out.println("Opção inválida.");
                return;
            }

            // Imprimir os elementos inseridos
            List<Integer> elementosInseridos = arvore.getElementosInseridos();
            System.out.println();
            System.out.println("Elementos inseridos na árvore:");
            for (int elemento : elementosInseridos) {
                System.out.print(elemento + " ");
            }
            System.out.println();
            System.out.println();
            System.out.println("Árvore em pré-ordem:");
            arvore.imprimirPreOrdem();
            System.out.println("Árvore em ordem:");
            arvore.imprimirEmOrdem();
            System.out.println("Árvore em pós-ordem:");
            arvore.imprimirPosOrdem();

            System.out.println();
            System.out.println("Árvore com indentação:");
            arvore.imprimirComIndentacao();
            System.out.println();
            System.out.println("Número de nós na árvore: " + arvore.contarNos());
            System.out.println("Menor elemento na árvore: " + arvore.encontrarMinimo());
            System.out.println("Maior elemento na árvore: " + arvore.encontrarMaximo());
            System.out.println();
            if (arvore.ehArvoreBinariaDeBusca()) {
                System.out.println("A árvore é uma árvore binária de busca.");
            } else {
                System.out.println("A árvore não é uma árvore binária de busca.");
            }
            System.out.println();
            System.out.println("Número de nós com um único filho: " + arvore.contarNosComApenasUmFilho());
            System.out.println();

            int elementoParaBuscar;

            while (true) {
                System.out.println("Digite o elemento que deseja pesquisar (-999 para encerrar):");
                elementoParaBuscar = scanner.nextInt();

                if (elementoParaBuscar == -999) {
                    System.out.println("Encerrando o programa.");
                    break; // Sai do loop
                }

                NoDaArvore noResultado = arvore.buscar(elementoParaBuscar);
                System.out.println();
                if (noResultado != null) {
                    System.out.println("Informações sobre o elemento " + elementoParaBuscar + ":");
                    System.out.println("Altura: " + arvore.getAltura(noResultado));
                    System.out.println("Nível: " + arvore.getProfundidade(noResultado));
                    System.out.println("Profundidade: " + arvore.getProfundidade(noResultado));
                    if (arvore.ehFilhoEsquerdo(noResultado)) {
                        System.out.println("É filho esquerdo.");
                    } else if (arvore.ehFilhoDireito(noResultado)) {
                        System.out.println("É filho direito.");
                    } else {
                        System.out.println("Não é filho de nenhum nó.");
                    }
                    if (noResultado.pai != null) {
                        System.out.println("Seu nó pai: " + noResultado.pai.data);
                    } else {
                        System.out.println("Não tem pai, é a raiz.");
                    }
                } else {
                    System.out.println("Elemento não encontrado na árvore.");
                }
                System.out.println();
            }
        }
    }
}
