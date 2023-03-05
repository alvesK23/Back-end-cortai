package sptech.school.backend.obj;

public class PilhaObj<T> {

    T[] pilha;

    private int topo;

    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {

        if (topo == -1) {

            return true;
        }

        return false;
    }

    public Boolean isFull() {

        if (pilha.length - 1 == topo) {

            return true;
        }

        return false;
    }

    public void push(T info) {
        if (isFull()) {

            throw new IllegalStateException();
        } else {

            pilha[++topo] = info;
        }
    }

    public T pop() {

        if (isEmpty()) {

            return null;
        }

        return pilha[topo--];
    }

    public T peek() {

        if (isEmpty()) {

            return null;
        }

        return pilha[topo];
    }

    public void exibe() {

        if (isEmpty()) {

            System.out.println("Pilha vazia!");
        } else {

            for (int i = topo; i >= 0; i--) {

                System.out.println(pilha[i]);
            }
        }
    }

    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}

