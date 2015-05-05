package com.jason.ddoMsg.util;

public class DmMobileArrays {

	public final static void sort(DmMobile[] mobiles) {
		sort1(mobiles, 0, mobiles.length);
	}

	/*
	 * The code for each of the seven primitive types is largely identical.
	 * C'est la vie.
	 */

	/**
	 * Sorts the specified sub-array of longs into ascending order.
	 */
	private static void sort1(DmMobile x[], int off, int len) {
		// Insertion sort on smallest arrays
		if (len < 7) {
			for (int i = off; i < len + off; i++)
				for (int j = i; j > off
						&& x[j - 1].msisdn > x[j].msisdn; j--)
					swap(x, j, j - 1);
			return;
		}

		// Choose a partition element, v
		int m = off + (len >> 1); // Small arrays, middle element
		if (len > 7) {
			int l = off;
			int n = off + len - 1;
			if (len > 40) { // Big arrays, pseudomedian of 9
				int s = len / 8;
				l = med3(x, l, l + s, l + 2 * s);
				m = med3(x, m - s, m, m + s);
				n = med3(x, n - 2 * s, n - s, n);
			}
			m = med3(x, l, m, n); // Mid-size, med of 3
		}
		long v = x[m].msisdn;

		// Establish Invariant: v* (<v)* (>v)* v*
		int a = off, b = a, c = off + len - 1, d = c;
		while (true) {
			while (b <= c && x[b].msisdn <= v) {
				if (x[b].msisdn == v)
					swap(x, a++, b);
				b++;
			}
			while (c >= b && x[c].msisdn >= v) {
				if (x[c].msisdn == v)
					swap(x, c, d--);
				c--;
			}
			if (b > c)
				break;
			swap(x, b++, c--);
		}

		// Swap partition elements back to middle
		int s, n = off + len;
		s = Math.min(a - off, b - a);
		vecswap(x, off, b - s, s);
		s = Math.min(d - c, n - d - 1);
		vecswap(x, b, n - s, s);

		// Recursively sort non-partition-elements
		if ((s = b - a) > 1)
			sort1(x, off, s);
		if ((s = d - c) > 1)
			sort1(x, n - s, s);
	}

	/**
	 * Swaps x[a] with x[b].
	 */
	private static void swap(DmMobile x[], int a, int b) {
		DmMobile t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

	/**
	 * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
	 */
	private static void vecswap(DmMobile x[], int a, int b, int n) {
		for (int i = 0; i < n; i++, a++, b++)
			swap(x, a, b);
	}

	/**
	 * Returns the index of the median of the three indexed longs.
	 */
	private static int med3(DmMobile x[], int a, int b, int c) {
		return (x[a].msisdn < x[b].msisdn ? (x[b].msisdn < x[c].msisdn ? b
				: x[a].msisdn < x[c].msisdn ? c : a)
				: (x[b].msisdn > x[c].msisdn ? b
						: x[a].msisdn > x[c].msisdn ? c : a));
	}

	public static int binarySearch(DmMobile[] a, long mobile) {
		return binarySearch0(a, 0, a.length, mobile);
	}

	private static int binarySearch0(DmMobile[] a, int fromIndex, int toIndex,
			long key) {
		int low = fromIndex;
		int high = toIndex - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			long midVal = a[mid].msisdn;

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}
}
