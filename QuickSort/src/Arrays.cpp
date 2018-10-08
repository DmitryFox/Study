#include "Arrays.h"

void Arrays::quickSort(void *data, int size, int left, int right, int(*comparator) (const void *, const void *)) {
	char *dataPtr = (char *) data;
	int meanElement = left + (right - left) / 2;
	char *pivot = &dataPtr[size * meanElement];

	int l = left;
	int r = right;
	do {
		while (comparator(&dataPtr[size * l], pivot) < 0)
			l++;

		while (comparator(pivot, &dataPtr[size * r]) < 0)
			r--;

		if (l <= r) {
			Arrays::swap(&dataPtr[size * l], &dataPtr[size * r], size);

			if (l == meanElement)
				pivot = &dataPtr[size * r];
			else if (r == meanElement)
				pivot = &dataPtr[size * l];

			l++;
			r--;
		}
	} while (l <= r);

	if (left < r)
		quickSort(data, size, left, r, comparator);
	if (l < right)
		quickSort(data, size, l, right, comparator);
}

inline void Arrays::swap(char *a, char *b, int size) {
	do {
		int tmpSize = size;
		char *tmpA = a, *tmpB = b;
		do {
			char tmp = *tmpA;
			*tmpA++ = *tmpB;
			*tmpB++ = tmp;
		} while (--tmpSize > 0);
	} while (0);
}
