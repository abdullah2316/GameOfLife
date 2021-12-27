jhon Conway's Game of Life
the code of the game is divided into 3 modules UI module(GUI(javafx),CLI) , Bussiness Logic module and DB module(ms sql sevrer,filing)
The modules have minimum dependecies and are designed keeping in view The SOLID principles.
Modules have Service Level Independence Implementation as no java objects leave or enter a module, the communication between modules is only done through json files.
