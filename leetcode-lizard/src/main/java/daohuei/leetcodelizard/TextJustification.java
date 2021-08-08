package daohuei.leetcodelizard;

import java.util.ArrayList;
import java.util.List;

/*
 * 68. Text Justification
 * Link: https://leetcode.com/problems/text-justification/description/
 */
public class TextJustification {
    /**
     * @author: daohuei
     * @description: loop with edge case solving
     * @time: O(n): for the length of the words
     * @space: O(k): for max width
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        List<String> rst = new ArrayList<>();
        int width = 0;

        // 1. Split & group
        for (String s : words) {
            // check if current the length in temp list plus next word will exceed max
            if (getRowLength(list, width) + s.length() + 1 > maxWidth) {
                // 2. Juststify a row of words and add to the result
                rst.add(justify(list, width, maxWidth));
                // reset the temp list and width
                list = new ArrayList<>();
                width = 0;
            }

            // add word to the temp list
            list.add(s);
            // count the total word length as current width
            width += s.length();
        }

        // if nothing left in temp list, answer found
        if (list.size() == 0)
            return rst;

        // 3. but if there is, clean up last row
        StringBuffer sb = new StringBuffer();

        // concat all words with a space
        for (int i = 0; i < list.size() - 1; i++)
            sb.append(list.get(i) + " ");
        sb.append(list.get(list.size() - 1));

        // append the rest with space
        sb.append(generateSpace(maxWidth - sb.length()));
        // add to the result and return it
        rst.add(sb.toString());
        return rst;
    }

    // get current temp list justification length
    private int getRowLength(List<String> list, int width) {
        // list -> contains all candidate words for this row
        // width -> the total width of all words
        // at least 1 space between each word -> overall width + (n - 1) spaces
        return width + (list.size() - 1);
    }

    // Juststify a row of words
    private String justify(List<String> list, int width, int max) {
        // # of slots in-between words
        int slot = list.size() - 1;
        // additional space to consume
        int extraSpace = max - width;

        // single word, just return
        if (slot == 0)
            return list.get(0) + generateSpace(extraSpace);

        // max avg length to insert in each slot
        int length = extraSpace / slot;
        // remaining extra spaces
        int remain = extraSpace % slot;

        // generate spaces string
        String space = generateSpace(length);
        StringBuffer sb = new StringBuffer();

        // concat words with space
        for (int i = 0; i < slot; i++) {
            sb.append(list.get(i)).append(space);
            // if still in remain, add one more additional space
            if (i < remain)
                sb.append(" ");
        }

        // append last word
        sb.append(list.get(slot));
        sb.append(generateSpace(max - sb.length()));
        return sb.toString();
    }

    // generate n spaces string
    private String generateSpace(int n) {
        if (n <= 0)
            return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++)
            sb.append(" ");
        return sb.toString();
    }
}