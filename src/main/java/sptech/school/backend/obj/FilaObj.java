package sptech.school.backend.obj;

import java.time.LocalDateTime;

public class FilaObj<T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        tamanho = 0;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {

        if (isFull()) {
            throw new IllegalStateException();

        }

        fila[tamanho++] = info;
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {

        LocalDateTime tamanhoUm = (LocalDateTime) fila[0];

        if (!isEmpty()) {

            for (int i = 0; i < tamanho - 1; i++) {

                fila[i] = fila[i + 1];
            }

            fila[--tamanho] = null;
        }

        return (T) tamanhoUm;
    }

    // Exibe os elementos da fila
    public void exibe() {

        if (isEmpty()) {

            System.out.println("Fila vazia");
        } else {
            for (int i = 0; i < tamanho; i++) {

                System.out.println(fila[i]);
            }
        }
    }

    public T[] getFila() {
        return fila;
    }

    public int getTamanho() {
        return tamanho;
    }
}