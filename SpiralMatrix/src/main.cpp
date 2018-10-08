#include <iostream>
#include <fstream>
#include <string.h>

#include "SpiralMatrix.h"

const char *HELP_MESSAGE = "Available commands:\r\n\
-help (Show help message)\r\n\
-make [N][M] (Generate matrix N x M size.)\r\n\
-make_to_file [N][M] (Generate matrix N x M size in file.)\r\n\
-exit (Exit.)";

int main() {
	std::cout << HELP_MESSAGE << "\r\n";

	char command[80];
	while (true) {
		std::cout << "\r\n" << ">";
		std::cin >> command;

		if (strcmp(command, "-help") == 0) {
			std::cout << HELP_MESSAGE << "\r\n";
		} else if (strcmp(command, "-make") == 0) {
			int rows, colums;
			std::cin >> rows;
			std::cin >> colums;
			std::cout << "\r\n";

			std::vector<std::vector<int> > matrix = SpiralMatrix::fillSpiralMatric(rows, colums);

			std::cout << "Matrix [ " << rows << " x " << colums << " ]: " << "\r\n\r\n";
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < colums; ++j) {
					std::cout << matrix[i][j] << " ";
				}

				std::cout << "\r\n";
			}
		} else if (strcmp(command, "-make_to_file") == 0) {
			int rows, colums;
			std::cin >> rows;
			std::cin >> colums;
			std::cout << "\r\n";

			std::vector<std::vector<int> > matrix = SpiralMatrix::fillSpiralMatric2(rows, colums);

			std::cout << "Save matrix to file...\r\n";

			std::ofstream ofs("generated_matrix.txt", std::ifstream::out | std::ifstream::binary);
			if (!ofs.is_open()) {
				std::cerr << "Could not open output file!\r\n";
				return 0;
			}

			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < colums; ++j) {
					ofs << matrix[i][j] << " ";
				}

				ofs << "\r\n";
			}

			ofs.close();

			std::cout << "Done.";
		} else if (strcmp(command, "-exit") == 0) {
			break;
		}
	}

	return 0;
}
