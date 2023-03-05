package sptech.school.backend.obj;

public class ListaObj<T> {

    T[] vetor;

    int nroElem;

    public ListaObj(int tamanhoMax) {
        vetor = (T[]) new Object[tamanhoMax];
        nroElem = 0;
    }

    public void adiciona(T elemento) {

        if (nroElem >= vetor.length) {

            throw new IllegalStateException();
        } else {

            vetor[nroElem++] = elemento;
        }
    }

    public int busca(T elemento) {

        for (int i = 0; i < nroElem; i++) {

            if (vetor[i].equals(elemento)) {

                return i;
            }
        }

        return -1;
    }

    public boolean removePeloIndice(int indice) {

        if (indice < 0 || indice >= nroElem) {

            return false;
        }

        for (int i = indice; i < nroElem - 1; i++) {

            vetor[i] = vetor[i + 1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elemento) {

        return removePeloIndice(busca(elemento));
    }


    public void exibe() {

        if (nroElem == 0) {
            System.out.println("Lista vazia");
        } else {

            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }


    public T[] getVetor() {
        return vetor;
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }

        return vetor[indice];
    }

    public void limpa() {
        nroElem = 0;
    }
}






