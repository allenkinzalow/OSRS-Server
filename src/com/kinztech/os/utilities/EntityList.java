package com.kinztech.os.utilities;

import com.kinztech.os.game.node.entity.Entity;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A {@link EntityList} is a repository of {@link Entity}s that are currently active in the game world.
 *
 * @author Graham - The Apollo Project
 * @author Allen
 * @param <T> The type of mob.
 */
public final class EntityList<T extends Entity> implements Iterable<T> {

    /**
     * The {@link Iterator} implementation for the {@link EntityList} class.
     *
     * @author Graham
     */
    private final class EntityListIterator implements Iterator<T> {

        /**
         * The current index of this iterator.
         */
        private int index = 0;

        /**
         * The previous index of this iterator.
         */
        private int previousIndex = -1;

        @Override
        public boolean hasNext() {
            for (int i = index; i < entities.length; i++) {
                if (entities[i] != null) {
                    index = i;
                    return true;
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            T mob = null;
            for (int i = index; i < entities.length; i++) {
                if (entities[i] != null) {
                    mob = (T) entities[i];
                    index = i;
                    break;
                }
            }

            if (mob == null) {
                throw new NoSuchElementException("Mob does not exist.");
            }

            previousIndex = index;
            index++;
            return mob;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void remove() {
            if(previousIndex <= -1)
                return;
            EntityList.this.remove((T) entities[previousIndex]);
            previousIndex = -1;
        }

    }

    /**
     * The array of entities in this repository.
     */
    private final Entity[] entities;

    /**
     * The position of the next free index.
     */
    private int pointer = 0;

    /**
     * The current size of this repository.
     */
    private int size = 0;

    /**
     * Creates a new mob repository with the specified capacity.
     *
     * @param capacity The maximum number of entities that can be present in the repository.
     */
    public EntityList(int capacity) {
        this.entities = new Entity[capacity];
    }

    /**
     * Adds a mob to the repository.
     *
     * @param entity The mob to add.
     * @return {@code true} if the mob was added, {@code false} if the size has reached the capacity of this repository.
     */
    public boolean add(T entity) {
        if (size == entities.length) {
            return false;
        }

        int index = -1;
        for (int i = pointer; i < entities.length; i++) {
            if (entities[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            for (int i = 0; i < pointer; i++) {
                if (entities[i] == null) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1) {
            return false; // shouldn't happen, but just in case
        }

        entities[index] = entity;
        entity.setIndex(index + 1);

        if (index == entities.length - 1) {
            pointer = 0;
        } else {
            pointer = index;
        }
        size++;
        return true;
    }

    /**
     * Gets the capacity of this repository.
     *
     * @return The maximum size of this repository.
     */
    public int capacity() {
        return entities.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new EntityListIterator();
    }

    /**
     * Removes a mob from the repository.
     *
     * @param entity The mob to remove.
     * @return {@code true} if the mob was removed, {@code false} if it was not (e.g. if it was never added or has been
     *         removed already).
     */
    public boolean remove(T entity) {
        int index = entity.getIndex() - 1;
        if (index < 0 || index >= entities.length) {
            return false;
        }

        if (entities[index] == entity) {
            entities[index] = null;
            entity.setIndex(-1);
            size--;
            return true;
        }
        return false;
    }

    /**
     * Gets the mob at the given index
     *
     * @param index The index of the mob
     * @return The mob instance
     * @throws IndexOutOfBoundsException If the specified index is less than 0, or greater than or equal to the capacity
     *             of this repository.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if(index > entities.length)
            return null;
        return (T) entities[index];
    }

    /**
     * Gets the size of this repository.
     *
     * @return The number of entities in this repository.
     */
    public int size() {
        return size;
    }

}
