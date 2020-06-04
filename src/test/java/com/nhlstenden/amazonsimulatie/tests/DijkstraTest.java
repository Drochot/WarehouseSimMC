package com.nhlstenden.amazonsimulatie.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import com.nhlstenden.amazonsimulatie.models.GraphWeighted;
import com.nhlstenden.amazonsimulatie.models.IntPair;
import com.nhlstenden.amazonsimulatie.models.NodeWeighted;

import org.junit.Test;

// Deze class bevat 3 tests voor de kortste paden mbv dijkstra korste pad algo
// de tests verschillen in het berekende kortste pad 
// grafen zijn identiek
public class DijkstraTest {

    @Test
    public void RunDijkstraTest() {
        // given (parameters voor class)

        // definieer een gewogen graaf
        GraphWeighted graphWeighted = new GraphWeighted();
        NodeWeighted a = new NodeWeighted(0, "A", -8.5, -16.5);
        NodeWeighted b = new NodeWeighted(1, "B", -6.5, -16.5);
        NodeWeighted c = new NodeWeighted(2, "C", -3.5, -16.5);
        NodeWeighted d = new NodeWeighted(3, "D", -0.5, -16.5);
        NodeWeighted e = new NodeWeighted(4, "E", 2.5, -16.5);
        NodeWeighted f = new NodeWeighted(5, "F", 5.5, -16.5);
        NodeWeighted g = new NodeWeighted(6, "G", 8, -16.);

        NodeWeighted h = new NodeWeighted(7, "H", -8.5, -13);
        NodeWeighted i = new NodeWeighted(8, "I", -6.5, -13);
        NodeWeighted j = new NodeWeighted(9, "J", -3.5, -13);
        NodeWeighted k = new NodeWeighted(10, "K", -0.5, -13);
        NodeWeighted l = new NodeWeighted(11, "L", 2.5, -13);
        NodeWeighted m = new NodeWeighted(12, "M", 5.5, -13);
        NodeWeighted n = new NodeWeighted(13, "N", 8, -13);

        NodeWeighted o = new NodeWeighted(14, "O", -8.5, -9);
        NodeWeighted p = new NodeWeighted(15, "P", -6.5, -9);
        NodeWeighted q = new NodeWeighted(16, "Q", -3.5, -9);
        NodeWeighted r = new NodeWeighted(17, "R", -0.5, -9);
        NodeWeighted s = new NodeWeighted(18, "S", 2.5, -9);
        NodeWeighted t = new NodeWeighted(19, "T", 5.5, -9);
        NodeWeighted u = new NodeWeighted(20, "U", 8, -9);

        NodeWeighted v = new NodeWeighted(21, "V", -8.5, -4.5);
        NodeWeighted w = new NodeWeighted(22, "W", -3.5, -4.5);
        NodeWeighted x = new NodeWeighted(23, "X", 3.5, -4.5);
        NodeWeighted y = new NodeWeighted(23, "Y", 8, -4.5);

        // definieer de edges/verbindingen tussen punten

        graphWeighted.addEdge(a, b, 3);
        graphWeighted.addEdge(b, c, 3);
        graphWeighted.addEdge(c, d, 3);
        graphWeighted.addEdge(d, e, 3);
        graphWeighted.addEdge(e, f, 3);
        graphWeighted.addEdge(f, g, 2.5);

        graphWeighted.addEdge(h, i, 3);
        graphWeighted.addEdge(i, j, 3);
        graphWeighted.addEdge(j, k, 3);
        graphWeighted.addEdge(k, l, 3);
        graphWeighted.addEdge(l, m, 3);
        graphWeighted.addEdge(m, n, 2.5);

        graphWeighted.addEdge(o, p, 3);
        graphWeighted.addEdge(p, q, 3);
        graphWeighted.addEdge(q, r, 3);
        graphWeighted.addEdge(r, s, 3);
        graphWeighted.addEdge(s, t, 3);
        graphWeighted.addEdge(t, u, 2.5);

        graphWeighted.addEdge(v, w, 6.5);
        graphWeighted.addEdge(w, x, 6);
        graphWeighted.addEdge(x, y, 5.5);

        graphWeighted.addEdge(a, h, 3.5);
        graphWeighted.addEdge(h, o, 4);
        graphWeighted.addEdge(o, v, 4.5);

        graphWeighted.addEdge(g, n, 3.5);
        graphWeighted.addEdge(n, u, 4);
        graphWeighted.addEdge(n, y, 4.5);

        // zoek het korste pad a-i en stop die in een lijst
        ArrayList<IntPair> test = graphWeighted.DijkstraShortestPath(a, i);

        // Verwachte uitkomst
        ArrayList<IntPair> uitkomst = new ArrayList<IntPair>();

        uitkomst.add(new IntPair(-8.5, -16.5));
        uitkomst.add(new IntPair(-8.5, -13));
        uitkomst.add(new IntPair(-6.5, -13));

        ArrayList<IntPair> uitkomst2 = new ArrayList<IntPair>();
        uitkomst2.add(new IntPair(-8.5, -16.5));
        uitkomst2.add(new IntPair(-6.5, -16.5));
        uitkomst2.add(new IntPair(-3.5, -16.5));
        uitkomst2.add(new IntPair(-0.5, -16.5));

        // vergelijk verwachte uitkomst met daadwerkelijke uitkomst

        assertThat(test, is(uitkomst));

    }

    @Test
    public void RunDijkstraTest2() {
        // given (parameters voor class)
        // definieer een gewogen graaf
        GraphWeighted graphWeighted = new GraphWeighted();
        NodeWeighted a = new NodeWeighted(0, "A", -8.5, -16.5);
        NodeWeighted b = new NodeWeighted(1, "B", -6.5, -16.5);
        NodeWeighted c = new NodeWeighted(2, "C", -3.5, -16.5);
        NodeWeighted d = new NodeWeighted(3, "D", -0.5, -16.5);
        NodeWeighted e = new NodeWeighted(4, "E", 2.5, -16.5);
        NodeWeighted f = new NodeWeighted(5, "F", 5.5, -16.5);
        NodeWeighted g = new NodeWeighted(6, "G", 8, -16.);

        NodeWeighted h = new NodeWeighted(7, "H", -8.5, -13);
        NodeWeighted i = new NodeWeighted(8, "I", -6.5, -13);
        NodeWeighted j = new NodeWeighted(9, "J", -3.5, -13);
        NodeWeighted k = new NodeWeighted(10, "K", -0.5, -13);
        NodeWeighted l = new NodeWeighted(11, "L", 2.5, -13);
        NodeWeighted m = new NodeWeighted(12, "M", 5.5, -13);
        NodeWeighted n = new NodeWeighted(13, "N", 8, -13);

        NodeWeighted o = new NodeWeighted(14, "O", -8.5, -9);
        NodeWeighted p = new NodeWeighted(15, "P", -6.5, -9);
        NodeWeighted q = new NodeWeighted(16, "Q", -3.5, -9);
        NodeWeighted r = new NodeWeighted(17, "R", -0.5, -9);
        NodeWeighted s = new NodeWeighted(18, "S", 2.5, -9);
        NodeWeighted t = new NodeWeighted(19, "T", 5.5, -9);
        NodeWeighted u = new NodeWeighted(20, "U", 8, -9);

        NodeWeighted v = new NodeWeighted(21, "V", -8.5, -4.5);
        NodeWeighted w = new NodeWeighted(22, "W", -3.5, -4.5);
        NodeWeighted x = new NodeWeighted(23, "X", 3.5, -4.5);
        NodeWeighted y = new NodeWeighted(23, "Y", 8, -4.5);

        // definieer de edges/verbindingen tussen punten

        graphWeighted.addEdge(a, b, 3);
        graphWeighted.addEdge(b, c, 3);
        graphWeighted.addEdge(c, d, 3);
        graphWeighted.addEdge(d, e, 3);
        graphWeighted.addEdge(e, f, 3);
        graphWeighted.addEdge(f, g, 2.5);

        graphWeighted.addEdge(h, i, 3);
        graphWeighted.addEdge(i, j, 3);
        graphWeighted.addEdge(j, k, 3);
        graphWeighted.addEdge(k, l, 3);
        graphWeighted.addEdge(l, m, 3);
        graphWeighted.addEdge(m, n, 2.5);

        graphWeighted.addEdge(o, p, 3);
        graphWeighted.addEdge(p, q, 3);
        graphWeighted.addEdge(q, r, 3);
        graphWeighted.addEdge(r, s, 3);
        graphWeighted.addEdge(s, t, 3);
        graphWeighted.addEdge(t, u, 2.5);

        graphWeighted.addEdge(v, w, 6.5);
        graphWeighted.addEdge(w, x, 6);
        graphWeighted.addEdge(x, y, 5.5);

        graphWeighted.addEdge(a, h, 3.5);
        graphWeighted.addEdge(h, o, 4);
        graphWeighted.addEdge(o, v, 4.5);

        graphWeighted.addEdge(g, n, 3.5);
        graphWeighted.addEdge(n, u, 4);
        graphWeighted.addEdge(n, y, 4.5);

        // zoek het korste pad a-d en stop die in een lijst
        ArrayList<IntPair> test2 = graphWeighted.DijkstraShortestPath(a, d);

        // Verwachte uitkomst
        ArrayList<IntPair> uitkomst2 = new ArrayList<IntPair>();
        uitkomst2.add(new IntPair(-8.5, -16.5));
        uitkomst2.add(new IntPair(-6.5, -16.5));
        uitkomst2.add(new IntPair(-3.5, -16.5));
        uitkomst2.add(new IntPair(-0.5, -16.5));

        // vergelijk verwachte uitkomst met daadwerkelijke uitkomst
        assertThat(test2, is(uitkomst2));

    }

    @Test
    public void RunDijkstraTest3() {
        // given (parameters voor class)
        // definieer een gewogen graaf
        GraphWeighted graphWeighted = new GraphWeighted();
        NodeWeighted a = new NodeWeighted(0, "A", -8.5, -16.5);
        NodeWeighted b = new NodeWeighted(1, "B", -6.5, -16.5);
        NodeWeighted c = new NodeWeighted(2, "C", -3.5, -16.5);
        NodeWeighted d = new NodeWeighted(3, "D", -0.5, -16.5);
        NodeWeighted e = new NodeWeighted(4, "E", 2.5, -16.5);
        NodeWeighted f = new NodeWeighted(5, "F", 5.5, -16.5);
        NodeWeighted g = new NodeWeighted(6, "G", 8, -16.);

        NodeWeighted h = new NodeWeighted(7, "H", -8.5, -13);
        NodeWeighted i = new NodeWeighted(8, "I", -6.5, -13);
        NodeWeighted j = new NodeWeighted(9, "J", -3.5, -13);
        NodeWeighted k = new NodeWeighted(10, "K", -0.5, -13);
        NodeWeighted l = new NodeWeighted(11, "L", 2.5, -13);
        NodeWeighted m = new NodeWeighted(12, "M", 5.5, -13);
        NodeWeighted n = new NodeWeighted(13, "N", 8, -13);

        NodeWeighted o = new NodeWeighted(14, "O", -8.5, -9);
        NodeWeighted p = new NodeWeighted(15, "P", -6.5, -9);
        NodeWeighted q = new NodeWeighted(16, "Q", -3.5, -9);
        NodeWeighted r = new NodeWeighted(17, "R", -0.5, -9);
        NodeWeighted s = new NodeWeighted(18, "S", 2.5, -9);
        NodeWeighted t = new NodeWeighted(19, "T", 5.5, -9);
        NodeWeighted u = new NodeWeighted(20, "U", 8, -9);

        NodeWeighted v = new NodeWeighted(21, "V", -8.5, -4.5);
        NodeWeighted w = new NodeWeighted(22, "W", -3.5, -4.5);
        NodeWeighted x = new NodeWeighted(23, "X", 3.5, -4.5);
        NodeWeighted y = new NodeWeighted(23, "Y", 8, -4.5);

        // definieer de edges/verbindingen tussen punten

        graphWeighted.addEdge(a, b, 3);
        graphWeighted.addEdge(b, c, 3);
        graphWeighted.addEdge(c, d, 3);
        graphWeighted.addEdge(d, e, 3);
        graphWeighted.addEdge(e, f, 3);
        graphWeighted.addEdge(f, g, 2.5);

        graphWeighted.addEdge(h, i, 3);
        graphWeighted.addEdge(i, j, 3);
        graphWeighted.addEdge(j, k, 3);
        graphWeighted.addEdge(k, l, 3);
        graphWeighted.addEdge(l, m, 3);
        graphWeighted.addEdge(m, n, 2.5);

        graphWeighted.addEdge(o, p, 3);
        graphWeighted.addEdge(p, q, 3);
        graphWeighted.addEdge(q, r, 3);
        graphWeighted.addEdge(r, s, 3);
        graphWeighted.addEdge(s, t, 3);
        graphWeighted.addEdge(t, u, 2.5);

        graphWeighted.addEdge(v, w, 6.5);
        graphWeighted.addEdge(w, x, 6);
        graphWeighted.addEdge(x, y, 5.5);

        graphWeighted.addEdge(a, h, 3.5);
        graphWeighted.addEdge(h, o, 4);
        graphWeighted.addEdge(o, v, 4.5);

        graphWeighted.addEdge(g, n, 3.5);
        graphWeighted.addEdge(n, u, 4);
        graphWeighted.addEdge(n, y, 4.5);

        // zoek het korste pad a-o en stop die in een lijst
        ArrayList<IntPair> test3 = graphWeighted.DijkstraShortestPath(a, o);

        // Verwachte uitkomst
        ArrayList<IntPair> uitkomst3 = new ArrayList<IntPair>();
        uitkomst3.add(new IntPair(-8.5, -16.5));
        uitkomst3.add(new IntPair(-8.5, -13));
        uitkomst3.add(new IntPair(-8.5, -9));

        // vergelijk verwachte uitkomst met daadwerkelijke uitkomst
        assertThat(test3, is(uitkomst3));

    }
}