## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Perque s'atura al cap d'un temps?
Perque al final tots els fils es queden esperant a que algu cancel·li la seva reserva.
Com que reservar i cancel·lar es aleatori, en algun punt tots els fils quedaran penjats esperant a que acabin els uns als altres.

## Que passa si la probabilitat es del 70% de reserva i 30% de cancel·la'
El programa es queda bloquejat molt mes rapid, ja que sera mes facil de que tots els fils esperin a fer una reserva.

## Que passa si la probabilitat es del 30% de reserva i 70% de cancel·la'
El programa trigara mes en bloquejarse. Es mes dificil que tots els fils esperin a fer una reserva.

## Perque necessitem una llista?
Perque sino no savem quins assistents estan registrats i quins no. Llavors tothom podria apuntarse o cancel·lar sense cap control.
