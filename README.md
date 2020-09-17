# Chaos Simulator

The project implements a GUI for interacting with a chaotic dynamical system. The system modeled  involves an arm with a magnet swinging over a metal sheet on which other magnets can be placed. The arm will then swing around as its magnetic end is attracted and repelled by the magnetics on the sheet.

## Using the Program

Using the program is easy. Simply compile the program and run the main class. Then click “Simulation” on the main menu. Right click to place attracting magnets, left click to place repelling magnets, hit space to pause, and r to reset the simulation. 

## Extensions

I worked on a few other projects based off of this one. Short descriptions of them are below. 

### Chaos Simulator Plotter — https://github.com/mtp61/chaosSimulatorPlotter

This program runs simulations with the swinging arm with a set configuration of base magnets. The outputs are a file that can be processed into a cool image. See https://drive.google.com/file/d/1fyNijcBfbEP-rtuWcHBcEi1hubKCcwl0/view?usp=sharing for a sample image. ‘

### Python Chaos Render — https://github.com/mtp61/pythonChaosRenderer

This simple python program turns a list of starting points and ending points outputted by the chaos simulator plotter into an image. 

### Magnet Server and Magnet Client — https://github.com/mtp61/magnetServer, https://github.com/mtp61/magnetClient

These programs implement distributed computing for the chaos simulator plotter. 
