class No {
    Integer conteudo;
    No esquerda;
    No direita;

    public No(Integer conteudo) {
        this.conteudo = conteudo;
        this.esquerda = null;
        this.direita = null;
    }

    public Integer getConteudo() {
        return conteudo;
    }

    public void setConteudo(Integer conteudo) {
        this.conteudo = conteudo;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}

class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        // Inicializa a árvore vazia com raiz nula.
        this.raiz = null;
        System.out.println("Árvore criada com sucesso");
    }


    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);
        if(estaVazia()) {
            this.raiz = novoNo;
            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    public void remover(Integer conteudo) {
        // Remoção do nó raiz também é tratada aqui, pois o nó retornado atualiza a raiz.
        if (estaVazia()) {
            System.out.println("A árvore está vazia. Não há nós para remover.");
            return;
        }

        this.raiz = removerRecursivo(this.raiz, conteudo);
    }

    private No removerRecursivo(No atual, Integer conteudo) {
        if (atual == null) {
            return null;
        }

        if (conteudo < atual.getConteudo()) {
            atual.setEsquerda(removerRecursivo(atual.getEsquerda(), conteudo));
            return atual;
        } else if (conteudo > atual.getConteudo()) {
            atual.setDireita(removerRecursivo(atual.getDireita(), conteudo));
            return atual;
        }

        // Encontrou o nó a ser removido.
        // Remoção de nó folha.
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            System.out.println("Removendo nó folha: " + atual.getConteudo());
            return null;
        }

        // Remoção de nó com apenas um filho.
        if (atual.getEsquerda() == null) {
            System.out.println("Removendo nó com um filho (direita): " + atual.getConteudo());
            return atual.getDireita();
        }

        if (atual.getDireita() == null) {
            System.out.println("Removendo nó com um filho (esquerda): " + atual.getConteudo());
            return atual.getEsquerda();
        }

        // Remoção de nó com dois filhos.
        System.out.println("Removendo nó com dois filhos: " + atual.getConteudo());
        No menorDireita = encontrarMenor(atual.getDireita());
        atual.setConteudo(menorDireita.getConteudo());
        atual.setDireita(removerRecursivo(atual.getDireita(), menorDireita.getConteudo()));
        return atual;
    }

    private No encontrarMenor(No no) {
        while (no.getEsquerda() != null) {
            no = no.getEsquerda();
        }
        return no;
    }

    public void inserirRecursivo(No novoNo, No aux) {
        if(aux.getConteudo() > novoNo.getConteudo()) {
            if(aux.getEsquerda() == null) {
                aux.setEsquerda(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getEsquerda());
            }
        } else if (aux.getConteudo() < novoNo.getConteudo()) {
            if(aux.getDireita() == null) {
                aux.setDireita(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getDireita());
            }
        } else {
            System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
            return;
        }
    }

    private boolean estaVazia () {
        return this.raiz == null;
    }

    public void percurso(String percurso) {
        if(estaVazia()) {
            System.out.println("A árvore não existe.");
            return;
        }

        switch (percurso) {
            case("Pre"):
                System.out.println("Executando a árvore em pré ordem.");
                this.preOrdem(this.raiz);
                break;
            case("Em"):
                System.out.println("Executando a árvore em ordem.");
                this.emOrdem(this.raiz);
                break;
            case("Pos"):
                System.out.println("Executando a árvore em pós ordem.");
                this.posOrdem(this.raiz);
                break;
            default:
                System.out.println("Percurso inexistente!");
                break;

        }
    }

    private void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getConteudo());
    }

    private void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireita());
    }
}

class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();

        System.out.println("\n=== Inserindo os nós originais na árvore ===");
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(1);
        arvoreBinaria.inserir(7);
        arvoreBinaria.inserir(5); // duplicado, não será inserido

        System.out.println("\n--- Percurso em ordem após inserções ---");
        arvoreBinaria.percurso("Em");

        System.out.println("\n--- Removendo nó com dois filhos (5) ---");
        arvoreBinaria.remover(5);
        arvoreBinaria.percurso("Em");

        System.out.println("\n--- Removendo nó folha (1) ---");
        arvoreBinaria.remover(1);
        arvoreBinaria.percurso("Em");

        System.out.println("\n--- Removendo nó raiz (10) ---");
        arvoreBinaria.remover(10);
        arvoreBinaria.percurso("Em");
    }
}