# Graph Parser and Editor

This project implements a graph algorithm in Java that parses and edits a .dot file representing a graph. It includes functionality for efficient searching for a specific node in the graph. The project uses a Maven workflow for building and managing dependencies. Project utilizes JUnit tests in order to ensure quality assurance. Uses graphviz library.

## Features

- **Parsing .dot files:** Parses .dot files to create a graph representation.
- **Editing graphs:** Allows editing of graphs by adding or removing nodes and edges.
- **Efficient node search:** Implements an efficient search algorithm for finding a specific node in the graph.

## Design Patterns Used

- **Template Design Pattern:** Used to define the basic structure of algorithms for parsing and editing graphs, allowing subclasses to provide specific implementations.
- **Strategy Design Pattern:** Used to encapsulate different search strategies, allowing the algorithm to be easily interchangeable.
