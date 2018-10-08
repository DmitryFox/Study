#ifndef SPIRAL_MATRIX_H
#define SPIRAL_MATRIX_H

#include <vector>

class SpiralMatrix
{
public:
	static std::vector<std::vector<int> > fillSpiralMatric(int rows, int colums);
	static std::vector<std::vector<int> > fillSpiralMatric2(int rows, int colums);
};

#endif
