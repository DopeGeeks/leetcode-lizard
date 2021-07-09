package daohuei.leetcodelizard;

import java.util.Iterator;
import java.util.List;

public class Flatten2DVector {

    public class Vector2D {
        Iterator<List<Integer>> itrs;
        Iterator<Integer> row;

        // initialization function
        public Vector2D(List<List<Integer>> vec2d) {
            if (vec2d == null || vec2d.size() == 0)
                return;
            // init the iterator for vec2d
            itrs = vec2d.iterator();
            // get the first row as a iterator
            row = itrs.next().iterator();
            // try get the next row (if empty, it will get the the next valid non-empty)
            getNextRow();
        }

        private void getNextRow() {
            // if current row have reach the end
            // and iterators has next
            while (!row.hasNext() && itrs.hasNext())
                // get the next iter
                row = itrs.next().iterator();
        }

        public int next() {
            // get the next row
            int next = row.next();
            // update row
            getNextRow();
            return next;
        }

        public boolean hasNext() {
            // if row has next iterator
            return row != null && row.hasNext();
        }
    }
}
