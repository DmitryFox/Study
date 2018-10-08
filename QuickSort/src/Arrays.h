#ifndef ARRAYS_H
#define ARRAYS_H

class Arrays {
public:
	static void quickSort(void *data, int size, int left, int right, int(*comparator) (const void *, const void *));
	static inline void swap(char *a, char *b, int size);
};

#endif
