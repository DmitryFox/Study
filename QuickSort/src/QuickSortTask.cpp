#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <sstream>
#include <stdlib.h>

#include "Arrays.h"
#include "student.h"

std::vector<std::string> split(const std::string& s, char delimiter);

int compare(const void *o1, const void *o2)
{
	return (*(student*)o2).mark - (*(student*) o1).mark;
}

int main() {
	setlocale(LC_ALL, "Russian");

	std::cout << "Load students from CSV file...\r\n";

	std::ifstream ifs("students.csv", std::ifstream::in);
	if (!ifs.is_open()) {
		std::cerr << "Input file not open!\r\n";
		return 0;
	}

	std::vector<student> students;

	std::string line;
	while (std::getline(ifs, line)) {
		std::vector<std::string> parts = split(line, ';');

		student student;
		student.name = parts[0].c_str();
		student.surname = parts[1].c_str();
		student.mark = atoi(parts[2].c_str());

		students.push_back(student);
	}
	ifs.close();

	std::cout << "Quick sort students by mark...\r\n";
	Arrays::quickSort(students.data(), sizeof(student), 0, students.size() - 1, compare);

	std::cout << "Save sort students to file...\r\n";

	std::ofstream ofs("sorted_students.csv", std::ifstream::out | std::ifstream::binary);
	if (!ofs.is_open()) {
		std::cerr << "Could not open output file!\r\n";
		return 0;
	}

	unsigned int vector_size = students.size();
	for (unsigned int i = 0; i < vector_size; i++) {
		ofs << students[i].name << ";" << students[i].surname << ";" << students[i].mark << "\r\n";
	}
	ofs.close();

	std::cout << "Press enter to exit...\r\n";
	std::cin.get();
	return 0;
}

std::vector<std::string> split(const std::string& s, char delimiter) {
	std::vector<std::string> tokens;
	std::string token;
	std::istringstream tokenStream(s);
	while (std::getline(tokenStream, token, delimiter)) {
		tokens.push_back(token);
	}

	return tokens;
}
