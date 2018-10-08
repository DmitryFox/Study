#include "SpiralMatrix.h"

std::vector<std::vector<int> > SpiralMatrix::fillSpiralMatric(int rows, int columns) {
	int totalCells = rows * columns;
	std::vector<std::vector<int> > matrix(rows, std::vector<int>(columns, 0));

	int cellNumber = 1;
	int cellCounter = 0;
	for (int k = 0; cellCounter < totalCells; ++k) {
		/* Top horizontal line */
		for (int i = k; i < columns - k && cellCounter < totalCells; ++i) {
			matrix[k][i] = cellNumber++;
			++cellCounter;
		}
		/* Right vertical column */
		for (int i = 1 + k; i < rows - k && cellCounter < totalCells; ++i) {
			matrix[i][columns - 1 - k] = cellNumber++;
			++cellCounter;
		}
		/* Bottom horizontal line */
		for (int i = k; i < columns - 1 - k && cellCounter < totalCells; ++i) {
			matrix[rows - 1 - k][columns - i - 2] = cellNumber++;
			++cellCounter;
		}
		/* Left vertical column */
		for (int i = k; i < rows - 2 - k && cellCounter < totalCells; ++i) {
			matrix[rows - i - 2][k] = cellNumber++;
			++cellCounter;
		}
	}

	return matrix;
}

std::vector<std::vector<int> > SpiralMatrix::fillSpiralMatric2(int rows, int columns) {
	std::vector<std::vector<int> > matrix(rows, std::vector<int>(columns, 0));

	int totalCells = rows * columns;
	int cellNumber = 0;

	int n = 0;
	int x = 0, y = 0;
	int direction = 0;
	for (int k = 0; k < totalCells; ++k) {
		matrix[y][x] = ++cellNumber;

		switch (direction) {
		case 0:
			if (++x >= columns - 1)
				direction = 1;
			break;
		case 1:
			if (++y >= rows - 1)
				direction = 2;
			break;
		case 2:
			if (--x <= n)
				direction = 3;
			break;
		case 3:
			if (--y == n + 1) {
				columns--;
				rows--;
				++n;
				direction = 0;
			}
			break;
		}
	}

	return matrix;
}
