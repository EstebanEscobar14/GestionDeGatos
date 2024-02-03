package raven.utils;

import java.util.Iterator;
import java.util.Stack;

/**
 * Clase que implementa un mecanismo simple de deshacer y rehacer (undo/redo) mediante el uso de pilas.
 *
 * @param <E> Tipo de elemento que se va a deshacer o rehacer.
 * @author Esteban
 */
public class UndoRedo<E> implements Iterable<E> {

    private final Stack<E> stack1;
    private final Stack<E> stack2;

    /**
     * Constructor que inicializa la clase UndoRedo con dos pilas vacías.
     */
    public UndoRedo() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Agrega un elemento a la pila de deshacer y borra la pila de rehacer.
     *
     * @param item El elemento a agregar.
     */
    public void add(E item) {
        stack1.push(item);
        stack2.clear();
    }

    /**
     * Deshace la última acción y devuelve el elemento resultante.
     *
     * @return El elemento deshecho o nulo si no hay más acciones para deshacer.
     */
    public E undo() {
        if (stack1.size() > 1) {
            stack2.push(stack1.pop());
            return stack1.get(stack1.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Rehace la última acción deshecha y devuelve el elemento resultante.
     *
     * @return El elemento rehecho o nulo si no hay más acciones para rehacer.
     */
    public E redo() {
        if (!stack2.isEmpty()) {
            E item = stack2.pop();
            stack1.push(item);
            return item;
        } else {
            return null;
        }
    }

    /**
     * Obtiene el elemento actual (sin deshacer ni rehacer).
     *
     * @return El elemento actual o nulo si no hay elementos.
     */
    public E getCurrent() {
        if (stack1.isEmpty()) {
            return null;
        } else {
            return stack1.get(stack1.size() - 1);
        }
    }

    /**
     * Verifica si es posible deshacer una acción.
     *
     * @return `true` si es posible deshacer, `false` en caso contrario.
     */
    public boolean isUndoAble() {
        return stack1.size() > 1;
    }

    /**
     * Verifica si es posible rehacer una acción.
     *
     * @return `true` si es posible rehacer, `false` en caso contrario.
     */
    public boolean isRedoAble() {
        return !stack2.empty();
    }

    /**
     * Borra todas las acciones de deshacer y rehacer.
     */
    public void clear() {
        stack1.clear();
        stack2.clear();
    }

    /**
     * Borra todas las acciones de rehacer.
     */
    public void clearRedo() {
        stack2.clear();
    }

    /**
     * Obtiene un iterador que itera sobre todos los elementos (deshacer y rehacer) en el orden en que se realizaron.
     *
     * @return Un iterador sobre los elementos.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Clase interna que implementa un iterador personalizado para la clase UndoRedo.
     */
    private class MyIterator implements Iterator<E> {

        private int index = 0;

        /**
         * Verifica si hay más elementos por iterar.
         *
         * @return `true` si hay más elementos, `false` en caso contrario.
         */
        @Override
        public boolean hasNext() {
            return index < stack1.size() || index < stack1.size() + stack2.size();
        }

        /**
         * Obtiene el siguiente elemento en la secuencia de deshacer y rehacer.
         *
         * @return El siguiente elemento.
         */
        @Override
        public E next() {
            if (index < stack1.size()) {
                return stack1.elementAt(index++);
            } else {
                return stack2.elementAt((index++) - stack1.size());
            }
        }
    }
}
