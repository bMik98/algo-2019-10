package fib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibTest {

    @Test
    void recursion() {
        assertEquals(0, Fib.recursion(0));
        assertEquals(1, Fib.recursion(1));
        assertEquals(1, Fib.recursion(2));
        assertEquals(2, Fib.recursion(3));
        assertEquals(3, Fib.recursion(4));
        assertEquals(5, Fib.recursion(5));
        assertEquals(8, Fib.recursion(6));
        assertEquals(13, Fib.recursion(7));
        assertEquals(21, Fib.recursion(8));
        assertEquals(34, Fib.recursion(9));
        assertEquals(55, Fib.recursion(10));
        assertEquals(89, Fib.recursion(11));
        assertEquals(832_040, Fib.recursion(30));
        assertEquals(14_930_352, Fib.recursion(36));
    }

    @Test
    void iteration() {
        assertEquals(0, Fib.iteration(0));
        assertEquals(1, Fib.iteration(1));
        assertEquals(1, Fib.iteration(2));
        assertEquals(2, Fib.iteration(3));
        assertEquals(3, Fib.iteration(4));
        assertEquals(5, Fib.iteration(5));
        assertEquals(8, Fib.iteration(6));
        assertEquals(13, Fib.iteration(7));
        assertEquals(21, Fib.iteration(8));
        assertEquals(34, Fib.iteration(9));
        assertEquals(55, Fib.iteration(10));
        assertEquals(89, Fib.iteration(11));
        assertEquals(832_040, Fib.iteration(30));
        assertEquals(14_930_352, Fib.iteration(36));
    }

    @Test
    void unlimitedIteration() {
        assertEquals(0, Fib.unlimitedIteration(0).longValue());
        assertEquals(1, Fib.unlimitedIteration(1).longValue());
        assertEquals(1, Fib.unlimitedIteration(2).longValue());
        assertEquals(2, Fib.unlimitedIteration(3).longValue());
        assertEquals(3, Fib.unlimitedIteration(4).longValue());
        assertEquals(5, Fib.unlimitedIteration(5).longValue());
        assertEquals(8, Fib.unlimitedIteration(6).longValue());
        assertEquals(13, Fib.unlimitedIteration(7).longValue());
        assertEquals(21, Fib.unlimitedIteration(8).longValue());
        assertEquals(34, Fib.unlimitedIteration(9).longValue());
        assertEquals(55, Fib.unlimitedIteration(10).longValue());
        assertEquals(89, Fib.unlimitedIteration(11).longValue());
        assertEquals(832_040, Fib.unlimitedIteration(30).longValue());
        assertEquals(14_930_352, Fib.unlimitedIteration(36).longValue());
    }

    @Test
    void binet() {
        assertEquals(0, Fib.binet(0));
        assertEquals(1, Fib.binet(1));
        assertEquals(1, Fib.binet(2));
        assertEquals(2, Fib.binet(3));
        assertEquals(3, Fib.binet(4));
        assertEquals(5, Fib.binet(5));
        assertEquals(8, Fib.binet(6));
        assertEquals(13, Fib.binet(7));
        assertEquals(21, Fib.binet(8));
        assertEquals(34, Fib.binet(9));
        assertEquals(55, Fib.binet(10));
        assertEquals(89, Fib.binet(11));
        assertEquals(832_040, Fib.binet(30));
        assertEquals(14_930_352, Fib.binet(36));
    }

    @Test
    void matrix() {
        assertEquals(0, Fib.matrix(0));
        assertEquals(1, Fib.matrix(1));
        assertEquals(1, Fib.matrix(2));
        assertEquals(2, Fib.matrix(3));
        assertEquals(3, Fib.matrix(4));
        assertEquals(5, Fib.matrix(5));
        assertEquals(8, Fib.matrix(6));
        assertEquals(13, Fib.matrix(7));
        assertEquals(21, Fib.matrix(8));
        assertEquals(34, Fib.matrix(9));
        assertEquals(55, Fib.matrix(10));
        assertEquals(89, Fib.matrix(11));
        assertEquals(832_040, Fib.matrix(30));
        assertEquals(14_930_352, Fib.matrix(36));
    }
}