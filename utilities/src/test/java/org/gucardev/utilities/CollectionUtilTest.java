package org.gucardev.utilities;

import org.gucardev.utilities.utils.CollectionUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilTest {

    @Test
    void testIsNullOrEmpty() {
        assertTrue(CollectionUtil.isNullOrEmpty(null));
        assertTrue(CollectionUtil.isNullOrEmpty(Collections.emptyList()));
        assertFalse(CollectionUtil.isNullOrEmpty(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testFindMax() {
        List<Integer> list = Arrays.asList(1, 3, 2);
        assertEquals(3, CollectionUtil.findMax(list));
    }

    @Test
    void testFindMin() {
        List<Integer> list = Arrays.asList(1, 3, 2);
        assertEquals(1, CollectionUtil.findMin(list));
    }

    @Test
    void testReverse() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> reversed = CollectionUtil.reverse(list);
        assertEquals(Arrays.asList(3, 2, 1), reversed);
    }

//    @Test
//    void testShuffle() {
//        List<Integer> list = Arrays.asList(1, 2, 3);
//        List<Integer> shuffled = CollectionUtil.shuffle(list);
//        assertNotEquals(list, shuffled);
//    }

    @Test
    void testFindMaxWithComparator() {
        List<Address> addresses = Arrays.asList(
                new Address(new Address.Detail(1)),
                new Address(new Address.Detail(44)),
                new Address(new Address.Detail(90))
        );
        Address maxAddress = CollectionUtil.findMax(addresses, Comparator.comparing(a -> a.detail.countryCode));
        assertEquals(90, maxAddress.detail.countryCode);
    }

    static class Address {
        Detail detail;

        Address(Detail detail) {
            this.detail = detail;
        }

        static class Detail {
            Integer countryCode;

            Detail(Integer countryCode) {
                this.countryCode = countryCode;
            }
        }
    }
}
